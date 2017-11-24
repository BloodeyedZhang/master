package game_server_parent.master.game.player;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.core.SystemParameters;
import game_server_parent.master.game.crossrank.CrossRankService;
import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
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
    
    private Logger logger = LoggerFactory.getLogger(PlayerManager.class);
    
    private static PlayerManager instance = new PlayerManager();
    
    private ConcurrentMap<Long, Player> onlines = new ConcurrentHashMap<Long, Player>();

    public static PlayerManager getInstance() {
        return instance;
    }
    
    /**
     * 创建普通玩家
     * @param player_id
     * @param name
     * @param job
     * @return
     */
    public Player createNewPlayer(long player_id, String name, byte job) {
        Player player = createPlayer(player_id, name, job);
        player.setIs_ai(PlayerDataPool.NOT_IS_AI);
        player.setBonus_points(1000); // 初始积分
        //设为插入状态
        player.setInsert();
        return player;
    }
    
    /**
     * 创建AI玩家
     * @param player_id
     * @param naem
     * @param job
     * @return
     */
    public Player createNewAIPlayer(long player_id, String name, byte job) {
        Player player = createPlayer(player_id, name, job);
        player.setIs_ai(PlayerDataPool.IS_AI);
        //设为插入状态
        player.setInsert();
        return player;
    }
    
    private Player createPlayer(long player_id, String name, byte job) {
        Player player = new Player();
        player.setName(name);
        player.setJob(job);
        player.setKeyNum(player.getMaxKeyNum());
        //int nextId = this.getNextId();
        player.setPlayer_id(player_id);
        return player;
    } 
    
    /*
     * 从用户表里读取玩家数据
     */
    @Override
    public Player load(Long playerId) throws Exception {
        String sql = "SELECT * FROM player where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(playerId));
        Player player = DbUtils.queryOne(DbUtils.DB_USER, sql, Player.class);
        if(player==null) player = new Player();
        return player;
    }
    
    /**
     * 从用户表里读取所有AI玩家
     * @return
     */
    public List<Player> loadAI() {
        String sql = "SELECT * FROM player where is_ai = 1";
        List<Player> players = DbUtils.queryMany(DbUtils.DB_USER, sql, Player.class);
        return players;
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
    
    public synchronized int getNextId() {
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
        long lastDailyReset = player.getLastDailyReset();
        if (lastDailyReset < resetTimestamp) {
            int d = (int)((resetTimestamp - lastDailyReset)/86400000); // 隔了几天
            player.setLastDailyReset(SystemParameters.dailyResetTimestamp);
            onDailyReset(player, d);
            
            //player.setFocsUpdate();
        }
    }

    /**
     * 各个模块的业务日重置
     * @param player
     */
    private void onDailyReset(Player player, int day) {
        System.err.println("玩家"+player.getName()+"进行各个模块的业务日重置");
        
        // 重置玩家购买钥匙的次数
        player.setBuy_key_num(0);
        
        // 计算玩家当前战绩
        resetRankScore(player, day);
        // 计算玩家当前积分
        resetRankBp(player);
    }
    
    /**
     * 业务重置点，重置当前战绩
     */
    private void resetRankScore(Player player, int day) {
        int rank_score = player.getRank_score();
        if(rank_score > 0) {
            rank_score -= day*3;
            rank_score = rank_score<0?0:rank_score;
        }
        else if(rank_score < 0) {
            rank_score += day*3;
            rank_score = rank_score>0?0:rank_score;
        }
        player.setRank_score(rank_score);
    }
    
    /**
     * 业务重置点,重置当前积分
     */
    private void resetRankBp(Player player) {
        /*
         * 积分规则（AI）——
         * 每日24点更新，如果当前战绩>0，则Rn=Ro+当前战绩*15*胜利对手平均战力比值；
         * 如果当前战绩<0，则Rn=Ro+当前战绩*15*失败对手平均战力比值
         * AI不会主动挑战，只有被动战斗时计算战绩
         */
        if(player.getIs_ai()==PlayerDataPool.IS_AI) {
            int Rn = 0;
            int Ro = player.getBonus_points();
            int rank_score = player.getRank_score();
            float rank_win_average_fight_percent = player.getRank_win_average_fight_percent();
            float rank_lose_average_fight_percent = player.getRank_lose_average_fight_percent();
            if(rank_score>0) {
                Rn = (int) (Ro+rank_score*15*rank_win_average_fight_percent);
            } else if(rank_score<0) {
                Rn = (int) (Ro+rank_score*15*rank_lose_average_fight_percent);
            } else {
                Rn = Ro;
            }
            player.setBonus_points(Rn);
        }
    }
    
    /**
     * 计算战绩
     * @param player
     * @param battleResult
     */
    public void calcuteRankScore(Player player, int battleResult) {
        int rank_score = player.getRank_score();
        if(battleResult == PlayerDataPool.BATTLE_WIN) {
            player.setRank_score(++rank_score);
        } else {
            player.setRank_score(--rank_score);
        }
        
    }
    
    /**
     * 计算积分
     * @param player
     * @param Re 对手积分
     * @param t 己方与对手的战力比值
     */
    public int calcuteBonusPoints(Player player, int Re, float t, int battleResult) {
        int Ro = player.getBonus_points();
        int Rn = 0;
        double a = 10.0;
        double b = ((double)Re-(double)Ro)/400;
        double W = 1/(1+Math.pow(a, b));
        if(battleResult == PlayerDataPool.BATTLE_WIN) {
            Rn = (int) (Ro+30*(1-W)*t);
        } else if(battleResult == PlayerDataPool.BATTLE_LOSE) {
            Rn = (int) (Ro-30*W*t);
        }
        player.setBonus_points(Rn);
        
        int change_score = Rn - Ro;
        int treasury_level = player.getTreasuryLevel();
        int bp = player.getBonus_points();
        String name = player.getName();
        CrossRankService.getInstance().addRank(new CrossBonusPointsRank(player.getPlayer_id(), change_score, treasury_level, name, bp));
        return change_score;
    }
    
    /**
     * 处理战斗胜利
     * @param player_id
     */
    public void executeBattleWin(Long player_id) {
        Player player = get(player_id);
        int rank_win_num = player.getRank_win_num();
        player.setRank_win_num(++rank_win_num);
        if(player.getIs_ai() == PlayerDataPool.NOT_IS_AI) {
            int fight_enemy = player.getFight_enemy();
            float t = fight_enemy==0?0:player.getFight() / player.getFight_enemy();
            calcutePlayerRankWinAverageFightPercent(player, t);
        } else {
            int f = player.getFight_enemy();
            calcutePlayerRankWinAverageFight(player, f);
        }
        
        if(player.getIs_enemy_ai() != PlayerDataPool.NOT_IS_AI) {
            Player enemy = get((long)player.getIs_enemy_ai());
            int fight_enemy = player.getFight();
            float t = fight_enemy==0?0:enemy.getFight() / player.getFight();
            calcutePlayerRankWLoseAverageFightPercent(enemy, t);
        }
    }
    
    /**
     * 处理战斗失败
     * @param player_id
     */
    public void executeBattleLose(Long player_id) {
        Player player = get(player_id);
        int rank_lose_num = player.getRank_lose_num();
        player.setRank_lose_num(++rank_lose_num);
        if(player.getIs_ai() == PlayerDataPool.NOT_IS_AI) {
            int fight_enemy = player.getFight_enemy();
            float t = fight_enemy==0?0:player.getFight() / player.getFight_enemy();
            calcutePlayerRankWLoseAverageFightPercent(player, t);
        } else {
            int f = player.getFight_enemy();
            calcutePlayerRankLoseAverageFight(player, f);
        }
        
        if(player.getIs_enemy_ai() != PlayerDataPool.NOT_IS_AI) {
            Player enemy = get((long)player.getIs_enemy_ai());
            int fight_enemy = player.getFight();
            float t = fight_enemy==0?0:enemy.getFight() / player.getFight();
            calcutePlayerRankWinAverageFightPercent(enemy, t);
        }
    }
    
    /**
     * 计算胜利对手平均战力比值
     * @param player
     * @param t 本场己方与对手的战力比值
     */
    public void calcutePlayerRankWinAverageFightPercent(Player player, float t) {
        // Tn = (To*Go+t) / Gn
        float Tn = 0.0f; // 新的胜利对手平均战力比值
        float To = player.getRank_win_average_fight_percent(); // 旧的胜利对手平均战力比值
        int Go = player.getRank_win_num()-1; // 旧的胜利场次
        int Gn = player.getRank_win_num(); // 新的胜利场次
//        if(Gn<=0) {
//            logger.error("Gn:"+Gn+",Gn不能小于0");
//            return;
//        }
        Tn = (To*Go+t) / Gn;
        player.setRank_win_average_fight_percent(Tn);
    }
    
    /**
     * 计算失败对手平均战力比值
     * @param player
     * @param t 本场己方与对手的战力比值
     */
    public void calcutePlayerRankWLoseAverageFightPercent(Player player, float t) {
        float Tn = 0.0f; // 新的失败对手平均战力比值
        float To = player.getRank_lose_average_fight_percent(); // 旧的失败对手平均战力比值
        int Go = player.getRank_lose_num()-1; // 旧的失败场次
        int Gn = player.getRank_lose_num(); // 新的失败场次
//        if(Gn<=0) {
//            logger.error("Gn:"+Gn+",Gn不能小于0");
//            return;
//        }
        Tn = (To*Go+t) / Gn;
        player.setRank_lose_average_fight_percent(Tn);
    }
    
    /**
     * 计算胜利对手平均战力
     * @param player
     * @param f 本场战斗敌方战力
     */
    public void calcutePlayerRankWinAverageFight(Player player, float f) {
        // Fn = (Fo*Go+f)/Gn
        float Fn = 0.0F; // 新的胜利对手平均战力
        float Fo = player.getRank_win_average_fight(); // 旧的胜利对手平均战力
        int Go = player.getRank_win_num()-1; // 旧的胜利场次
        int Gn = player.getRank_win_num(); // 新的胜利场次
        Fn = (Fo*Go+f)/Gn;
        player.setRank_win_average_fight(Fn);
    }
    
    /**
     * 计算失败对手平均战力
     * @param player
     * @param f 本场战斗敌方战力
     */
    public void calcutePlayerRankLoseAverageFight(Player player, float f) {
        float Fn = 0.0F; // 新的失败对手平均战力
        float Fo = player.getRank_lose_average_fight(); // 旧的失败对手平均战力
        int Go = player.getRank_lose_num()-1; // 旧的失败场次
        int Gn = player.getRank_lose_num(); // 新的失败场次
        Fn = (Fo*Go+f)/Gn;
        player.setRank_lose_average_fight(Fn);
    }

}
