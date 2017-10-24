package game_server_parent.master.game.player;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.core.SystemParameters;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.KapaiId;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:PlayerManager.java</p>
 * <p>Description: 玩家业务管理器  </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class PlayerManager extends CacheService<Long, Player> {
    
    private static PlayerManager instance = new PlayerManager();
    
    private ConcurrentMap<Long, Player> onlines = new ConcurrentHashMap<Long, Player>();

    public static PlayerManager getInstance() {
        return instance;
    }
    
    public Player createNewPlayer(long player_id, String name, byte job) {
        Player player = new Player();
        player.setName(name);
        player.setJob(job);
        player.setKeyNum(player.getMaxKeyNum());
        //int nextId = this.getNextId();
        player.setPlayer_id(player_id);
        //设为插入状态
        player.setInsert();

        return player;
    }
    
    /*
     * 从用户表里读取玩家数据
     */
    @Override
    public Player load(Long playerId) throws Exception {
        String sql = "SELECT * FROM Player where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(playerId));
        Player player = DbUtils.queryOne(DbUtils.DB_USER, sql, Player.class);
        if(player==null) player = new Player();
        return player;
    }
    
    /**
     * 添加进在线列表
     * @param player
     */
    public void add2Online(Player player) {
        this.onlines.put(player.getId(), player);
    }
    
    /**
     * 返回在线玩家列表的拷贝
     * @return
     */
    public ConcurrentMap<Long, Player> getOnlinePlayers() {
        return new ConcurrentHashMap<>(this.onlines);
    }
    
    /**
     * 从在线列表中移除
     * @param player
     */
    public void removeFromOnline(Player player) {
        if (player != null) {
            player.setFocsUpdate();
            DbService.getInstance().add2Queue(player);
            this.onlines.remove(player.getId());
        }
    }
    
    public int getNextId() {
        String sql = "select nextval('seq_player_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
    
    public int getCurId() {
        String sql = "select currval('seq_player_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
    
    public void checkDailyReset(Player player) {
        long resetTimestamp = SystemParameters.dailyResetTimestamp;
        if (player.getLastDailyReset() < resetTimestamp) {
            player.setLastDailyReset(SystemParameters.dailyResetTimestamp);
            onDailyReset(player);
            
            //player.setFocsUpdate();
        }
    }

    /**
     * 各个模块的业务日重置
     * @param player
     */
    private void onDailyReset(Player player) {
        System.err.println("玩家"+player.getName()+"进行各个模块的业务日重置");
    }

}
