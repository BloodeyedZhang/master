package game_server_parent.master.net;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import game_server_parent.master.net.annotation.MessageMeta;
import game_server_parent.master.utils.ClassFilter;
import game_server_parent.master.utils.ClassScanner;

/**
 * <p>Filename:MessageFactory.java</p>
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
public enum MessageFactory {
    INSTANCE;
    
    private Map<String, Class<?>> messagePool = new HashMap<String, Class<?>>();
    
    private final String SCAN_PATH = "game_server_parent.master";
    
    /**
     * 初始化所有通信协议库
     */
    public void initMeesagePool() {
        Set<Class<?>> messages = ClassScanner.listAllSubclasses(SCAN_PATH, Message.class);
        
        for (Class<?> clazz: messages) {
            MessageMeta protocol = clazz.getAnnotation(MessageMeta.class);
            if (protocol == null) {
                throw new RuntimeException("messages["+clazz.getSimpleName()+"]缺少Protocol注解");
            }
            String key = protocol.module() + "_" + protocol.cmd();
            if (messagePool.containsKey("协议号 "+key)) {
                throw new RuntimeException("协议号["+key+"]重复，请检查！！");
            }
            messagePool.put(key,clazz);
//            System.out.println(key);
        }
    }

    
    public Class<?> getMessage(short module, short cmd) {
        return messagePool.get(buildKey(module, cmd));
    }
    
    private String buildKey(short module, short cmd) {
        return module + "_" + cmd;
    }
}
