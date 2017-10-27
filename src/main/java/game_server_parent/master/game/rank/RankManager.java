package game_server_parent.master.game.rank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.rank.message.ResRankBattleEndMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.MessagePusher;
import sun.security.util.ECKeySizeParameterSpec;

/**
 * <p>Filename:RankManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月22日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class RankManager {

    private Logger logger = LoggerFactory.getLogger(RankManager.class);
    
    private static RankManager instance = new RankManager();

    public static RankManager getInstance() {
        return instance;
    }
    
    /** 计算战斗奖励或惩罚 */
    private int[] executeRankBattle(EventType eventType) {
        if(eventType.equals(EventType.BATTLE_WIN)) {
            // 战斗胜利获得100金币和100积分
            return new int[] {100,100};
        } else {
         // 战斗失败扣除50金币和50积分
            return new int[] {50,50};
        }
    }
    
    /** 发送金币变化事件 */
    public void  executeRankBattle(long player_id, int battle_id, EventType eventType) {
        
        int money1_change = 0;
        int bpc_change = 0;
        EventAttrChange eventAttrChange_money1 = null;
        EventAttrChange eventAttrChange_bpc = null;
        int[] vals = executeRankBattle(eventType);
        money1_change = vals[0];
        bpc_change = vals[1];
        Player player = PlayerManager.getInstance().get(player_id);
        int money1 = player.getMoney1();
        int bonus_points = player.getBonus_points();
        
        if(eventType.equals(EventType.BATTLE_WIN)) {
            eventAttrChange_money1 = new EventAttrChange(EventType.MONEY1_ADD, player_id);
            eventAttrChange_money1.setMoney1_change(money1_change);
            eventAttrChange_money1.setSource_evtType(eventType);
            
            eventAttrChange_bpc = new EventAttrChange(EventType.BONUS_POINT_ADD, player_id);
            eventAttrChange_bpc.setMoney1_change(bpc_change);
            eventAttrChange_bpc.setSource_evtType(eventType);
            
            money1+=money1_change;
            bonus_points+=bpc_change;
        } else {
            eventAttrChange_money1 = new EventAttrChange(EventType.MONEY1_DEDUCT, player_id);
            eventAttrChange_money1.setMoney1_change(money1_change);
            eventAttrChange_money1.setSource_evtType(eventType);
            
            eventAttrChange_bpc = new EventAttrChange(EventType.BONUS_POINTS_DEDUCT, player_id);
            eventAttrChange_bpc.setMoney1_change(bpc_change);
           
            eventAttrChange_bpc.setSource_evtType(eventType);
            
            money1-=money1_change;
            bonus_points-=bpc_change;
        }
        
        //player.setMoney1(money1);
        //player.setBonus_points(bonus_points);
        
        // 下发 战斗结果数据包
        this.sendRankBattleMessage(player_id,money1_change,bpc_change,eventType);
        

        
        EventDispatcher.getInstance().fireEvent(eventAttrChange_money1); // 发送金币增加/减少事件
        EventDispatcher.getInstance().fireEvent(eventAttrChange_bpc); // 发送积分增加/减少事件
        
        
        // 修改数值
        this.updateBattleId(player_id);
        

    }
    
    /**
     * 发送战斗胜利/失败 角色Battle_Id变化事件
     * 记录角色Battle_id变化
     * @param player_id
     * @param rank_battle_id
     * @param battleLose
     * @param eventType
     */
    public void executeRankBattle(long player_id, int rank_battle_id, EventType battle_result, EventType source_eventType) {
        
        
        // 发送战斗胜利/失败 角色Battle_Id变化事件
        EventType et = null;
        if(battle_result.equals(EventType.BATTLE_WIN)) {
            et = EventType.BATTLE_ID_WIN;
        } else if(battle_result.equals(EventType.BATTLE_LOSE)) {
            et = EventType.BATTLE_ID_LOSE;
        }
        
        executeRankBattle(player_id, rank_battle_id, battle_result);
        
        EventAttrChange eventAttrChange = new EventAttrChange(et, player_id);
        eventAttrChange.setSource_evtType(source_eventType);
        eventAttrChange.setMoney1_change(rank_battle_id);
        EventDispatcher.getInstance().fireEvent(eventAttrChange);
        
        
    }
    
    /** 修改角色Battle_id数值 */
    public void updateBattleId(long player_id) {
        Player player = PlayerManager.getInstance().get(player_id);
        player.setRank_battle_id(0);
        player.setFocsUpdate();
        //DbService.getInstance().add2Queue(player);
    }
    
    /** 下发战斗结果包到客户端*/
    public void sendRankBattleMessage(long player_id, int money1_change, int bpc_change, EventType battle_result) {
        int code = battle_result.equals(EventType.BATTLE_WIN) ? RankDataPool.BATTLE_WIN : RankDataPool.BATTLE_LOSE;
        
        ResRankBattleEndMessage rrbe = new ResRankBattleEndMessage();
        rrbe.setCode(code);
        rrbe.setMoney1(money1_change);
        rrbe.setBonus_points(bpc_change);
        MessagePusher.pushMessage(player_id, rrbe);
    }
}
