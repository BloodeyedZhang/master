package game_server_parent.master.player;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.orm.OrmProcessor;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:TestKapaiId.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class TestKapaiId {
    @Before
    public void init() {
        //初始化orm框架
        OrmProcessor.INSTANCE.initOrmBridges();
        //初始化数据库连接池
        DbUtils.init();
    }
    
    @Test
    public void testKapaiNextId() {
        
        int nextId = KapaiManager.getInstance().getNextId();
        
        assertTrue(nextId>0);
    }
}
