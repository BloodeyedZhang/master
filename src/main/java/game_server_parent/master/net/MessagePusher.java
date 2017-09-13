package game_server_parent.master.net;

import org.apache.mina.core.session.IoSession;

/**
 * <p>Filename:MessagePusher.java</p>
 * <p>Description: 消息推送器 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class MessagePusher {
    public static void pushMessage(IoSession session, Message message) {
        session.write(message);
    }
    
    public static void pushMessage(long playerId, Message message) {
        IoSession session = SessionManager.INSTANCE.getSessionBy(playerId);
        pushMessage(session, message);
    }
}
