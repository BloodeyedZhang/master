package game_server_parent.master.player;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigBingzhongVT;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.rank.RankSoilderTeamManager;
import game_server_parent.master.game.treasury.ChoukaManager;
import game_server_parent.master.orm.OrmProcessor;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:TestGetBingzhong.java</p>
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
public class TestGetBingzhong {
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
    public void testRollBingzhong() {
        for(int i=0;i<1000;i++) {
            int bingzhong = ChoukaManager.getInstance().getBingzhong(3, 1000886);
            ConfigBingzhongVT configBingzhongVT = ConfigDatasPool.getInstance().configBingzhongVTContainer.getConfigBy(bingzhong);
            System.err.println(bingzhong + " level:"+configBingzhongVT.getLevel());
            assertTrue(bingzhong<1034 && bingzhong!=1029 && configBingzhongVT.getLevel()<=3);
        }
        
    }
    
    /*
    @Test
    public void testQueryRankTeam() {
        long player_id = 1000886;
        Player player = PlayerManager.getInstance().get(player_id);
        
        RankSoilderTeam rst_enemy = RankSoilderTeamManager.getInstance().queryOneEnemy(player_id);
        Player enemy = PlayerManager.getInstance().get(rst_enemy.getPlayer_id());
        
        System.err.println("player:"+player.getFight()+"；enemy:"+enemy.getFight());
    }
    */
}
