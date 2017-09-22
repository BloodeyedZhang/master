package game_server_parent.master.game.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.player.AttrChangeRecord;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.kapai.events.EventKapaiUpdate;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.player.events.EventUpdatePlayer;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;

/**
 * <p>Filename:PlayerListener.java</p>
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
public class AttrChangeListener {

    private Logger logger = LoggerFactory.getLogger(AttrChangeListener.class);
    
    @EventHandler(value= {EventType.MONEY1_ADD})
    public void onMoney1Add(EventAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(playerId);
        int money1 = player.getMoney1();
        player.setMoney1(money1+event.getMoney1_change());
        player.setFocsUpdate();
        DbService.getInstance().add2Queue(player);
        
        EventDispatcher.getInstance().fireEvent(new EventUpdatePlayer(EventType.PLAYER_UPDATE_MONEY, playerId));
    }
    
    @EventHandler(value= {EventType.MONEY1_DEDUCT})
    public void onMoney1Deduct(EventAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(playerId);
        int money1 = player.getMoney1();
        player.setMoney1(money1-event.getMoney1_change());
        player.setFocsUpdate();
        DbService.getInstance().add2Queue(player);
        
        EventDispatcher.getInstance().fireEvent(new EventUpdatePlayer(EventType.PLAYER_UPDATE_MONEY, playerId));
    }
}
