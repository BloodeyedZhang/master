package game_server_parent.master.game.treasury;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigTreasuryReward;
import game_server_parent.master.game.database.user.storage.Treasury;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.game.treasury.events.EventTreasuryUpdate;
import game_server_parent.master.game.treasury.message.ResTreasuryUpdateMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;
import groovyjarjarantlr.debug.Event;

/**
 * <p>Filename:TreasuryListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class TreasuryListener {
    private Logger logger = LoggerFactory.getLogger(TreasuryListener.class);
    
    @EventHandler(value=EventType.PLAYER_CREATE)
    public void onPlayerNew(EventNewPlayer event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long playerId = event.getPlayerId();
        Treasury treasury = TreasuryManager.getInstance().create(playerId);
        
        DbService.getInstance().add2Queue(treasury);
    }
    
    @EventHandler(value=EventType.TREASURY_UPDATE)
    public void onTreasuryUpdate(EventTreasuryUpdate event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        int level = event.getTreasury_level();
        ConfigTreasuryReward ctr = ConfigDatasPool.getInstance().configTreasuryRewardContainer.getConfigBy(level);
        int levelupCoins = ctr.getLevelupCoins();
        int levelupDiamonds = ctr.getLevelupDiamonds();
        String gotArmy = ctr.getGotArmy();
        
        EventAttrChange eventAttrChange = new EventAttrChange(EventType.MONEY1_ADD, player_id);
        eventAttrChange.setMoney1_change(levelupCoins);
        eventAttrChange.setSource_evtType(EventType.TREASURY_UPDATE);
        EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送金币增加事件

        EventAttrChange eventAttrChange2 = new EventAttrChange(EventType.MONEY2_ADD, player_id);
        eventAttrChange2.setMoney1_change(levelupDiamonds);
        eventAttrChange2.setSource_evtType(EventType.TREASURY_UPDATE);
        EventDispatcher.getInstance().fireEvent(eventAttrChange2); // 发送宝石增加事件
        
        ResTreasuryUpdateMessage message = new ResTreasuryUpdateMessage();
        message.setArmy(gotArmy);
        message.setCoin(levelupCoins);
        message.setDiamond(levelupDiamonds);
        MessagePusher.pushMessage(player_id, message);
    }
    
    @EventHandler(value=EventType.TREASURY_UPDATE_SHOW)
    public void onTreasuryUpdateShow(EventTreasuryUpdate event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        int level = event.getTreasury_level();
        ConfigTreasuryReward ctr = ConfigDatasPool.getInstance().configTreasuryRewardContainer.getConfigBy(level);
        int levelupCoins = ctr.getLevelupCoins();
        int levelupDiamonds = ctr.getLevelupDiamonds();
        String gotArmy = ctr.getGotArmy();
        ResTreasuryUpdateMessage message = new ResTreasuryUpdateMessage();
        message.setArmy(gotArmy);
        message.setCoin(levelupCoins);
        message.setDiamond(levelupDiamonds);
        MessagePusher.pushMessage(player_id, message);
    } 
}
