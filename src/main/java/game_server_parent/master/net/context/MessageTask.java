package game_server_parent.master.net.context;

import java.lang.reflect.Method;

import game_server_parent.master.net.Message;

/**
 * <p>Filename:MessageTask.java</p>
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
public class MessageTask extends AbstractDistributeTask {
    private long playerId;
    /** 消息实体 */
    private Message message;
    /** 消息处理器 */
    private Object handler;
    
    private Method method;
    /** 处理器方法的参数 */
    private Object[] params;
    
    public static MessageTask valueOf(int distributeKey, Object handler,
            Method method, Object[] params) {
        MessageTask msgTask = new MessageTask();
        msgTask.distributeKey = distributeKey;
        msgTask.handler = handler;
        msgTask.method  = method;
        msgTask.params  = params;
        
        return msgTask;
    }
    @Override
    public void action() {
        // TODO Auto-generated method stub
        try{
            method.invoke(handler, params);
        }catch(Exception e){
            
        }
    }
    public long getPlayerId() {
        return playerId;
    }

    public Message getMessage() {
        return message;
    }

    public Object getHandler() {
        return handler;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getParams() {
        return params;
    }
    
    @Override
    public String toString() {
        return this.getName() + "[" + handler.getClass().getName() + "@" + method.getName() + "]";
    }
}
