package game_server_parent.master.game.player;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.KapaiId;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:AttrChangeRecordManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月19日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class AttrChangeRecordManager {

    private static AttrChangeRecordManager instance = new AttrChangeRecordManager();
    

    public static AttrChangeRecordManager getInstance() {
        return instance;
    }
    
    public int getNextId() {
        String sql = "select nextval('seq_AttrChangeRecord_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
}
