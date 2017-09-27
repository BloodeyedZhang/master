package game_server_parent.master.game.player;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.player.AttrChangeRecord;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.game.player.events.EventUpdatePlayer;
import game_server_parent.master.game.player.message.ResPlayerMessage;
import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionManager;

/**
 * <p>Filename:PlayerListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class PlayerListener {

    private Logger logger = LoggerFactory.getLogger(PlayerListener.class);
    
    @EventHandler(value= {EventType.PLAYER_UPDATE_MONEY})
    public void onPlayerUpdate(EventUpdatePlayer event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(player_id);
        ResPlayerMessage resPlayerMessage = new ResPlayerMessage();
        resPlayerMessage.setPlayer(player);
        IoSession session = SessionManager.INSTANCE.getSessionBy(player_id);
        MessagePusher.pushMessage(session, resPlayerMessage);
    }
    
    @EventHandler(value=EventType.PLAYER_CREATE)
    public void onPlayerNew(EventNewPlayer event) {
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().createNewPlayer(playerId, "test"+playerId, (byte)1);
        DbService.getInstance().add2Queue(player);
    }

}
