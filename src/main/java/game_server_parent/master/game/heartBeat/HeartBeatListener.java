package game_server_parent.master.game.heartBeat;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.heartBeat.events.EventHeartBeat;
import game_server_parent.master.game.heartBeat.message.ResClientHeartBeatMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionProperties;

/**
 * <p>Filename:HeartBeatListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月15日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class HeartBeatListener {
    private Logger logger = LoggerFactory.getLogger(HeartBeatListener.class);

    @EventHandler(value=EventType.HEARTBEAT)
    public void onHeartBeat(EventHeartBeat event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        IoSession session = event.getSession();
        HeartBeatManager.getInstance().removeUserOvertime(session);
    }
    
    @EventHandler(value=EventType.HEARTBEAT_TIMEOUT)
    public void onHeartBeatTimeOut(EventHeartBeat event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        IoSession session = event.getSession();
        if(HeartBeatManager.getInstance().isUserOvertime(session)) {
            Object attribute = session.getAttribute(SessionProperties.PLAYER_ID);
            if(attribute!=null) {
                long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
                Player player = PlayerManager.getInstance().get(player_id);
                PlayerManager.getInstance().removeFromOnline(player);
            }
            session.close(false);
        } else {
            HeartBeatManager.getInstance().addUserOvertime(session);
            MessagePusher.pushMessage(session, new ResClientHeartBeatMessage()); // 发送心跳包
        }
    }
}
