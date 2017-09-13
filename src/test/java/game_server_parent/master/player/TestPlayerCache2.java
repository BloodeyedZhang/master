package game_server_parent.master.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.orm.OrmProcessor;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:TestPlayerCache2.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class TestPlayerCache2 {

    @Before
    public void init() {
        //初始化orm框架
        OrmProcessor.INSTANCE.initOrmBridges();
        //初始化数据库连接池
        DbUtils.init();
    }
    
    @Test
    public void testQueryPlayer() {
        try {
            long playerId = 10000L;
            //预先保证用户数据表playerId = 10000的数据存在
            Player player = PlayerManager.getInstance().get(playerId);
            //改变内存里的玩家名称
            player.setName("newPlayerName");
            //内存里玩家的新名称
            String playerName = player.getName();
            //通过同一个id再次获取玩家数据
            Player player2 = PlayerManager.getInstance().get(playerId);
            //验证新的玩家就是内存里的玩家，因为如果又是从数据库里读取，那么名称肯定跟内存的不同！！
            assertTrue(playerName.equals(player2.getName()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
