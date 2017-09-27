package game_server_parent.master.game.rank;

import game_server_parent.master.game.database.user.storage.KapaiId;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:BattleIdManager.java</p>
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
public class BattleIdManager {

    private static BattleIdManager instance = new BattleIdManager();
    
    public static BattleIdManager getInstance() {
        return instance;
    }
    
    public int getNextId() {
        String sql = "select nextval('seq_battle_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
}
