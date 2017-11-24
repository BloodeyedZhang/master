package game_server_parent.master.player;

import org.junit.Before;
import org.junit.Test;

import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.user.storage.Treasury;
import game_server_parent.master.game.treasury.TreasuryManager;
import game_server_parent.master.orm.OrmProcessor;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:TestPlayerTreasury.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月24日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class TestPlayerTreasury {
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
    public void onGetTreasury() {
        long player_id = 1000887;
        Treasury treasury = TreasuryManager.getInstance().resetTreasury(player_id);
    }
}
