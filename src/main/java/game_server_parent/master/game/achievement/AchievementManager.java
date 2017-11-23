package game_server_parent.master.game.achievement;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigFuben;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.KapaiAchievement;
import game_server_parent.master.game.kapai.KapaiDataPool;
import game_server_parent.master.game.kapai.events.EventKapaiNew;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.orm.utils.DbUtils;
import game_server_parent.master.utils.RollUtils;
import groovy.sql.Sql;

/**
 * <p>Filename:AchievementManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class AchievementManager extends CacheService<Long, KapaiAchievement>  {

    private Logger logger = LoggerFactory.getLogger(AchievementManager.class);
    
    private ConcurrentMap<Long, List<KapaiAchievement>> onlines = new ConcurrentHashMap<Long, List<KapaiAchievement>>();

    private static AchievementManager instance = new AchievementManager();
    
    public static AchievementManager getInstance() {
        return instance;
    }
    
    // 成就未达成
    public final static int CLOSE = 0;
    // 成就达成
    public final static int OPEN = 1;
    // 成就达成
    public final static int OPEN_CLOSE = 2;
    
    // 成就的卡牌数组
    public static int[] achieve_kapai_id = new int[] {1021,1019,1025,1028,1031,1030,1029,1038,1039,1040};
    
    public long achieve_id(long player_id, int id) {
        
        if(id>=1000) id-=1000;
        StringBuffer sb = new StringBuffer();
        sb.append(player_id).append((int)id/100).append((int)id%100);
        
//        StringBuffer sb = new StringBuffer();
//        sb.append(player_id).append("00").append(id);
        return Long.parseLong(sb.toString());
    }

    @Override
    public KapaiAchievement load(Long id) throws Exception {
        String sql = "SELECT * FROM kapai_achievement where achieve_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(id));
        KapaiAchievement player = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiAchievement.class);
        return player;
    } 
    
    /**
     * 缓存从数据库查到的数据列表
     * @param list
     */
    private void cache(Long player_id, List<KapaiAchievement> list) {
        this.onlines.put(player_id, list);
    }
    
    /**
     * 从缓存中拿数据
     * @param player_id
     */
    private List<KapaiAchievement> getFromCache(Long player_id) {
        return this.onlines.get(player_id);
    }
    
    /**
     * 获得玩家的成就信息
     * @param player_id
     * @return
     */
    public List<KapaiAchievement> getList(long player_id) {
        
        List<KapaiAchievement> kapaiAchievements = getFromCache(player_id);
        if(kapaiAchievements!=null) {
            // 从缓存中拿到了数据，直接返回
            return kapaiAchievements;
        }
        
        // 从数据库中拿数据
        StringBuffer sql = new StringBuffer("SELECT * FROM kapai_achievement where achieve_id in (0");
        int count = achieve_kapai_id.length;
        for(int i=0;i<count;i++) {
            long achieve_id = achieve_id(player_id, achieve_kapai_id[i]);
            sql.append(",").append(achieve_id);
        }
        sql.append(")");
        List<KapaiAchievement> objList = DbUtils.queryMany(DbUtils.DB_USER, sql.toString(), KapaiAchievement.class);
        // 将从数据库中拿到的数据缓存起来
        cache(player_id, objList);
        return objList;
    }
    
    public void upList(long player_id) {
        List<KapaiAchievement> kapaiAchievements = getFromCache(player_id);
        if(kapaiAchievements!=null) {
            int count = kapaiAchievements.size();
            for(int i=0;i<count;i++) {
                KapaiAchievement kapaiAchievement = kapaiAchievements.get(i);
                if(kapaiAchievement.getAchieve_state()==OPEN) {
                    kapaiAchievement.setAchieve_state(OPEN_CLOSE);
                    KapaiAchievement kapaiAchieve = get(achieve_id(player_id, kapaiAchievement.getKapai_id()));
                    kapaiAchieve.setAchieve_state(OPEN_CLOSE);
                    kapaiAchieve.setFocsUpdate();
                }
            }
        }
    }
    
    // 创建成就列表信息，只在角色创建时调用
    public void create(Long player_id) {
        int count = achieve_kapai_id.length;
        for(int i=0;i<count;i++) {
            int kapai_id = achieve_kapai_id[i];
            long achieve_id = achieve_id(player_id, kapai_id);
            KapaiAchievement kapaiAchievement = new KapaiAchievement();
            kapaiAchievement.setAchieve_id(achieve_id);
            kapaiAchievement.setAchieve_state(CLOSE);
            kapaiAchievement.setKapai_id(kapai_id);
            kapaiAchievement.setPlayer_id(player_id);
            kapaiAchievement.setInsert();
            
            DbService.getInstance().add2Queue(kapaiAchievement);
        }
    }
    
    // 更新成就信息，设置开放
    public boolean update(Long player_id, int kapai_id) {
        
        KapaiAchievement kapaiAchieve = get(achieve_id(player_id, kapai_id));
        if(kapaiAchieve.getAchieve_state()==OPEN||kapaiAchieve.getAchieve_state()==OPEN_CLOSE) {
            // 已经开放
            return false;
        }
        kapaiAchieve.setAchieve_state(OPEN);
        kapaiAchieve.setFocsUpdate();
        
        List<KapaiAchievement> list = this.onlines.get(player_id);
        int count = list.size();
        for(int i=0;i<count;i++) {
            KapaiAchievement kapaiAchievement = list.get(i);
            if(kapaiAchievement.getKapai_id()==kapai_id) {
                kapaiAchievement.setAchieve_state(OPEN);
                DbService.getInstance().add2Queue(kapaiAchievement);
            }
        }
        cache(player_id, list);
        return true;
    }

    /**
     * @param player_id
     * @param bingzhong
     * @param configFuben
     */
    public void update(long player_id, int bingzhong, ConfigFuben configFuben) {
        if(update(player_id, bingzhong)) {

        }
        
    }
}
