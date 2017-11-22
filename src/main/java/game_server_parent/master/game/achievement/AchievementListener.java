package game_server_parent.master.game.achievement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.bjf.remoting.protobuf.utils.StringUtils;

import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigFuben;
import game_server_parent.master.game.database.config.bean.ConfigTreasuryReward;
import game_server_parent.master.game.fuben.events.EventFuben;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.game.treasury.events.EventTreasuryUpdate;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;

/**
 * <p>Filename:AchievementListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class AchievementListener {

    private Logger logger = LoggerFactory.getLogger(AchievementListener.class);
    
    @EventHandler(value=EventType.PLAYER_CREATE)
    public void onPlayerNew(EventNewPlayer event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event+"\r创建卡牌成就初始化信息.");
        // 角色初始化时 初始化成就业务
        long player_id = event.getPlayerId();
        AchievementManager.getInstance().create(player_id);
    }
    
    @EventHandler(value=EventType.TREASURY_UPDATE)
    public void onTreasuryBattleEnd(EventTreasuryUpdate event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event+"\r宝库升级");
        
        long player_id = event.getPlayerId();
        int level = event.getTreasury_level();
        ConfigTreasuryReward ctr = ConfigDatasPool.getInstance().configTreasuryRewardContainer.getConfigBy(level);
        String gotArmy = ctr.getGotArmy();
        if(StringUtils.isEmpty(gotArmy)) {
            return;
        }
        int kapai_id = Integer.parseInt(gotArmy);
        AchievementManager.getInstance().update(player_id, kapai_id);
    }
    
    @EventHandler(value=EventType.BATTLE_WIN)
    public void onBattleWin(EventFuben event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event+"\r副本战斗胜利");
        
        long player_id = event.getPlayerId();
        int map_id = event.getMap_id();
        ConfigFuben configFuben = ConfigDatasPool.getInstance().configFubenContainer.getConfigBy(map_id-1100);
        AchievementManager.getInstance().update(player_id, configFuben.getBingzhong(), configFuben);
    }
}
