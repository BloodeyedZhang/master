package game_server_parent.master.game.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.crossrank.CrossRankService;
import game_server_parent.master.game.crossrank.events.EventBpUpdate;
import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.record.AttrChangeRecord;
import game_server_parent.master.game.kapai.events.EventKapaiUpdate;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.player.events.EventUpdatePlayer;
import game_server_parent.master.game.rank.BattleIdManager;
import game_server_parent.master.game.rank.RankDataPool;
import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.game.treasury.TreasuryDataPool;
import game_server_parent.master.game.treasury.events.EventTreasuryEnd;
import game_server_parent.master.game.treasury.events.EventTreasuryUpdate;
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
    
    @EventHandler(value= {EventType.MONEY2_ADD})
    public void onMoney2Add(EventAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(playerId);
        int money1 = player.getMoney2();
        player.setMoney2(money1+event.getMoney1_change());
        player.setFocsUpdate();
        DbService.getInstance().add2Queue(player);
        
        EventDispatcher.getInstance().fireEvent(new EventUpdatePlayer(EventType.PLAYER_UPDATE_MONEY, playerId));
    }
    
    @EventHandler(value= {EventType.MONEY2_DEDUCT})
    public void onMoney2Deduct(EventAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(playerId);
        int money1 = player.getMoney2();
        player.setMoney2(money1-event.getMoney1_change());
        player.setFocsUpdate();
        DbService.getInstance().add2Queue(player);
        
        EventDispatcher.getInstance().fireEvent(new EventUpdatePlayer(EventType.PLAYER_UPDATE_MONEY, playerId));
    }
    
    @EventHandler(value= {EventType.BONUS_POINT_ADD})
    public void onBonusPointAdd(EventAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(playerId);
        int money1 = player.getBonus_points();
        player.setBonus_points(money1+event.getMoney1_change());
        player.setFocsUpdate();
        DbService.getInstance().add2Queue(player);
        
        int bonusPints = event.getMoney1_change();
        
        EventDispatcher.getInstance().fireEvent(new EventUpdatePlayer(EventType.PLAYER_UPDATE_MONEY, playerId));
        EventDispatcher.getInstance().fireEvent(new EventBpUpdate(EventType.CROSSRANK_BP_UPDATE, playerId, bonusPints));
    }
    
    @EventHandler(value= {EventType.BONUS_POINTS_DEDUCT})
    public void onBonumsPointDeduct(EventAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(playerId);
        int money1 = player.getBonus_points();
        player.setBonus_points(money1-event.getMoney1_change());
        player.setFocsUpdate();
        DbService.getInstance().add2Queue(player);
        
        int bonusPints = event.getMoney1_change();
        
        EventDispatcher.getInstance().fireEvent(new EventUpdatePlayer(EventType.PLAYER_UPDATE_MONEY, playerId));
        EventDispatcher.getInstance().fireEvent(new EventBpUpdate(EventType.CROSSRANK_BP_UPDATE, playerId, 0-bonusPints));
    }
    
    @EventHandler(value=EventType.TREASURY_BATTLE_END)
    public void onTreasuryAttr(EventTreasuryEnd event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        int battle_result = event.getBattle_result();
        if(battle_result==PlayerDataPool.BATTLE_WIN) {
            long player_id = event.getPlayerId();
            Player player = PlayerManager.getInstance().get(player_id);
            int treasuryLevel = player.getTreasuryLevel();
            int maxTreasuryLevel = player.getMaxTreasuryLevel();
            int treasuryLevelProgress = player.getTreasuryLevelProgress();
            if(treasuryLevel< maxTreasuryLevel) {
                if(treasuryLevelProgress==0 || treasuryLevelProgress==1) {
                    treasuryLevelProgress = 2;
                } else if(treasuryLevelProgress==2) {
                    treasuryLevelProgress = 0;
                    treasuryLevel++;
                    // 发送宝库升级奖励事件
                    EventDispatcher.getInstance().fireEvent(new EventTreasuryUpdate(EventType.TREASURY_UPDATE, player_id, treasuryLevel));
                }
                
                player.setTreasuryLevel(treasuryLevel);
                player.setTreasuryLevelProgress(treasuryLevelProgress);
                player.setFocsUpdate();
                DbService.getInstance().add2Queue(player);
                
            } else {
                treasuryLevelProgress = 0;
            }

        }
    }

}
