package game_server_parent.master.game.player;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.game.database.user.player.Player;
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
    
    public Player createNewPlayer(String name, byte job) {
        Player player = new Player();
        player.setName(name);
        player.setJob(job);
        //设为插入状态
        player.setInsert();

        return player;
    }
    
    /*
     * 从用户表里读取玩家数据
     */
    @Override
    public Player load(Long playerId) throws Exception {
        String sql = "SELECT * FROM Player where Id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(playerId));
        Player player = DbUtils.queryOne(DbUtils.DB_USER, sql, Player.class);
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
            this.onlines.remove(player.getId());
        }
    }

}
