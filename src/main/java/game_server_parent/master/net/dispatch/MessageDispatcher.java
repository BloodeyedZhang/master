package game_server_parent.master.net.dispatch;

import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.SessionManager;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.MessageMeta;
import game_server_parent.master.net.annotation.RequestMapping;
import game_server_parent.master.net.context.MessageTask;
import game_server_parent.master.net.context.TaskHandlerContext;
import game_server_parent.master.utils.ClassFilter;
import game_server_parent.master.utils.ClassScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.session.IoSession;


/**
 * <p>Filename:MessageDispatcher.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class MessageDispatcher {
    private volatile static MessageDispatcher instance;  
    
    /** [module_cmd, CmdExecutor] */  
    private static final Map<String, CmdExecutor> MODULE_CMD_HANDLERS = new HashMap<String, CmdExecutor>();  
  
    public static MessageDispatcher getInstance() {  
        //双重检查锁单例  
        if (instance == null) {  
            synchronized (MessageDispatcher.class) {  
                if (instance == null) {  
                    instance = new MessageDispatcher();  
                }  
            }  
        }  
        return instance;  
    } 
    
    private MessageDispatcher() {  
        initalize();  
    }  
  
    public void initalize() {  
        Set<Class<?>> controllers = ClassScanner.listClassesWithAnnotation("game_server_parent.master.game", Controller.class);
  
        for (Class<?> controller: controllers) {  
            try {  
                Object handler = controller.newInstance();  
                Method[] methods = controller.getDeclaredMethods();  
                for (Method method:methods) {  
                    RequestMapping mapperAnnotation = method.getAnnotation(RequestMapping.class);  
                    if (mapperAnnotation != null) {  
                        short[] meta = getMessageMeta(method);  
                        if (meta == null) {  
                            throw new RuntimeException(String.format("controller[%s]方法[%s]缺少RequestMapping注解",   
                                    controller.getName(), method.getName()));  
                        }  
                        short module = meta[0];  
                        short cmd    = meta[1];  
                        String key = buildKey(module, cmd);  
                        CmdExecutor cmdExecutor = MODULE_CMD_HANDLERS.get(key);  
                        if (cmdExecutor != null) {  
                            throw new RuntimeException(String.format("module[%d] cmd[%d]重复", module, cmd));  
                        }  
  
                        cmdExecutor = CmdExecutor.valueOf(method, method.getParameterTypes(), handler);  
                        MODULE_CMD_HANDLERS.put(key, cmdExecutor);  
//                        System.out.println("MODULE_CMD_HANDLERS: key="+key+";cmdExecutor="+cmdExecutor.toString());
                    }  
                }  
            }catch(Exception e) {  
                LoggerUtils.error("", e);
            }  
        }  
    }  
  
    private short[] getMessageMeta(Method method) {  
        for (Class<?> paramClazz: method.getParameterTypes()) {  
            if (Message.class.isAssignableFrom(paramClazz)) {  
                MessageMeta protocol = paramClazz.getAnnotation(MessageMeta.class);  
                if (protocol != null) {  
                    short[] meta= {protocol.module(), protocol.cmd()};
                    return meta;
                    //return MessageMeta.valueOf(protocol.module(), protocol.cmd());  
                }  
            }  
        }  
        return null;  
    } 
    
    /** 
     * 向线程池分发消息 
     * @param session 
     * @param message 
     */  
    public void dispatch(IoSession session, Message message) {  
        short module = message.getModule();  
        short cmd    = message.getCmd();  
  
        CmdExecutor cmdExecutor = MODULE_CMD_HANDLERS.get(buildKey(module, cmd));  
        if (cmdExecutor == null) {  
            LoggerUtils.error("请求协议不存在,module={},cmd={}", module, cmd);  
            return;  
        }  
  
        Object[] params = convertToMethodParams(session, cmdExecutor.getParams(), message);  
        Object controller = cmdExecutor.getHandler();  
        
        int distributeKey = (int) session.getAttribute(SessionProperties.DISTRIBUTE_KEY);
        TaskHandlerContext.INSTANCE.acceptTask(
                MessageTask.valueOf(distributeKey, controller, cmdExecutor.getMethod(), params));

//        不在io线程进行业务处理
//        try {  
//            //通过反射，  
//            cmdExecutor.getMethod().invoke(controller, params);  
//        }catch(Exception e) {  
//        }  
        
    }
    
    /** 
     * 将各种参数转为被RequestMapper注解的方法的实参 
     * @param session 
     * @param methodParams 
     * @param message 
     * @return 
     */  
    private Object[] convertToMethodParams(IoSession session, Class<?>[] methodParams, Message message) {  
        Object[] result = new Object[methodParams==null?0:methodParams.length];  
  
        for (int i=0;i<result.length;i++) {  
            Class<?> param = methodParams[i];  
            if (IoSession.class.isAssignableFrom(param)) {  
                result[i] = session;  
            }else if (Long.class.isAssignableFrom(param)) {  
                result[i] = SessionManager.INSTANCE.getPlayerIdBy(session);  
            }else if (long.class.isAssignableFrom(param)) {  
                result[i] = SessionManager.INSTANCE.getPlayerIdBy(session);  
            }else if (Message.class.isAssignableFrom(param)) {  
                result[i] = message;  
            }  
        }  
  
        return result;  
    }
    
    private String buildKey(short module, short cmd) {  
        return module + "_" + cmd;  
    }  
}
