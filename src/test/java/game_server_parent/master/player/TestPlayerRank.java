package game_server_parent.master.player;

import org.junit.Before;
import org.junit.Test;

import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.rank.RankDataPool;
import game_server_parent.master.game.rank.RankManager;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.orm.OrmProcessor;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:TestPlayerRank.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月23日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class TestPlayerRank {

    @Before
    public void init() {
        //初始化orm框架
        OrmProcessor.INSTANCE.initOrmBridges();
        //初始化数据库连接池
        DbUtils.init();
        //读取所有策划配置
        ConfigDatasPool.getInstance().loadAllConfigs();
    }
    
    @Test
    public void testExecuteRank() {
        //RankManager.getInstance().executeRankBattle(player_id, battle_id, eventType);
        Player player = PlayerManager.getInstance().get((long)1000886);
        int Re = player.getBp_enemy();
        int fight_enemy = player.getFight_enemy();
        float t = fight_enemy==0?0:(float)player.getFight() / player.getFight_enemy();
        player.getFight_enemy();
        //int battleResult = eventType.equals(EventType.BATTLE_WIN)?RankDataPool.BATTLE_WIN:RankDataPool.BATTLE_LOSE;
        int battleResult = 1;
       // PlayerManager.getInstance().calcuteRankScore(player, battleResult);
        PlayerManager.getInstance().calcuteBonusPoints(player, Re , t, battleResult);
    }
}
