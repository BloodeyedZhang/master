package game_server_parent.master.game.kapai;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.KapaiId;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:KapaiManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class KapaiManager extends CacheService<Long, Kapai> {

    private static KapaiManager instance = new KapaiManager();

    public static KapaiManager getInstance() {
        return instance;
    }

    public Kapai createNewKapai(long player_id, int dalei, int bingzhong) {
        Kapai kapai = new Kapai();
        kapai.setPlayer_id(player_id);
        kapai.setDalei(dalei);
        kapai.setBingzhong(bingzhong);
        kapai.setPinzhi(1);
        kapai.setS_dengji(1);
        kapai.setXingji(1);
        int nextId = this.getNextId();
        kapai.setKapai_id(nextId);
        //设为插入状态
        kapai.setInsert();

        return kapai;
    }
    
    @Override
    public Kapai load(Long id) throws Exception {
        String sql = "SELECT * FROM Kapai where kapai_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(id));
        Kapai kapai = DbUtils.queryOne(DbUtils.DB_USER, sql, Kapai.class);
        return kapai;
    }
    
    public List<Kapai> getPlayerKapaiList(long player_id){
        String sql = "SELECT * FROM Kapai where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(player_id));
        List<Kapai> kapai_list = DbUtils.queryMany(DbUtils.DB_USER, sql, Kapai.class);
        return kapai_list;
    }
    
    public int getNextId() {
        String sql = "select nextval('seq_kapai_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
    
    public int getCurId() {
        String sql = "select currval('seq_kapai_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
    

}
