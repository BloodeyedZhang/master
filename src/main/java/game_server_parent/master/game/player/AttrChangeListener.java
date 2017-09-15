package game_server_parent.master.game.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    @EventHandler(value= {EventType.KAPAI_NEW, EventType.KAPAI_UPDATE, EventType.KAPAI_SELL})
    public void onMoney1Change(PlayerEvent playerEvent) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+playerEvent);
        
    }
}