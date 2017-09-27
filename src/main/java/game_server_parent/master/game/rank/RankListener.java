package game_server_parent.master.game.rank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.rank.events.EventRankBattle;
import game_server_parent.master.game.rank.message.ResRankBattleEndMessage;
import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.game.team.TeamListener;
import game_server_parent.master.game.team.event.EventSoilderTeamUpdate;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import groovyjarjarantlr.debug.Event;

/**
 * <p>Filename:RamkListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月21日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class RankListener {
    private Logger logger = LoggerFactory.getLogger(RankListener.class);

    @EventHandler(value=EventType.SOILDER_TEAM_UPDATE)
    public void onTeamUpdate(EventSoilderTeamUpdate event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        SoilderTeam st = event.getSt();
        RankSoilderTeamManager.getInstance().updateSoilderTeam(st, player_id);
    }
    
    @EventHandler(value=EventType.BATTLE_START)
    public void onBattleStart(EventRankBattle event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        int nextId = BattleIdManager.getInstance().getNextId();
        Player player = PlayerManager.getInstance().get(player_id);
        
        player.setRank_battle_id(nextId);
        player.setFocsUpdate();
        DbService.getInstance().add2Queue(player);
        
        EventAttrChange eventAttrChange = new EventAttrChange(EventType.BATTLE_ID_START, player_id);
        eventAttrChange.setSource_evtType(event.getEventType());
        eventAttrChange.setMoney1_change(nextId);
        EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送战斗开始事件
    }
    
    @EventHandler(value=EventType.BATTLE_WIN)
    public void onBattleWin(EventRankBattle event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        int battle_id = event.getBattle_id();
        // 计算奖励
        RankManager.getInstance().executeRankBattle(player_id, battle_id, EventType.BATTLE_WIN, event.getEventType());

    }
    
    @EventHandler(value=EventType.BATTLE_LOSE)
    public void onBattleLose(EventRankBattle event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        int battle_id = event.getBattle_id();
        
        // 计算奖励
        RankManager.getInstance().executeRankBattle(player_id, battle_id, EventType.BATTLE_LOSE, event.getEventType());
    }
    
    
    @EventHandler(value=EventType.ENTER_ZHANDOU)
    public void onEnterZhandou(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        
        // 发送战斗开始事件
        EventDispatcher.getInstance().fireEvent(new EventRankBattle(EventType.BATTLE_START, player_id, 0));

    }
    
    @EventHandler(value=EventType.ENTER_DATING)
    public void onEnterDating(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(player_id);
        
        if(player.getRank_battle_id() == 0) {
            
        } else {
            
            RankManager.getInstance().executeRankBattle(player_id, player.getRank_battle_id(), EventType.BATTLE_LOSE, event.getEventType());
        }
    }

}
