package game_server_parent.master.net;

import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:Message.java</p>
 * <p>Description: 通信消息体定义 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class Message {
    public short getModule() {
        MessageMeta annotation = getClass().getAnnotation(MessageMeta.class);
        if (annotation != null) {
            return annotation.module();
        }
        return 0;
    }
    
    public short getCmd() {
        MessageMeta annotation = getClass().getAnnotation(MessageMeta.class);
        if (annotation != null) {
            return annotation.cmd();
        }
        return 0;
    }
    
    public String key() {
        return this.getModule() + "_" + this.getCmd();
    }
}
