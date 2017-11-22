package game_server_parent.master.game.database.user.player;

import org.apache.mina.core.session.IoSession;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.net.SessionManager;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.context.IDistributable;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;
import game_server_parent.master.utils.IdGenerator;

/**
 * <p>Filename:Player.java</p>
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
@Entity
public class Player extends BaseEntity implements IDistributable {

    private static final long serialVersionUID = 8913056963732639062L;

    @Id
    @Column
    @Protobuf(order=1)
    private long player_id;
    
    //@Id
    @Column
    @Protobuf(order=2)
    private long id;
    
    @Column
    @Protobuf(order=3)
    private String name;
    
    /**
     * 职业
     */
    @Column 
    @Protobuf(order=4)
    private int job;
    
    @Column
    @Protobuf(order=5)
    private int level;
    
    @Column
    @Protobuf(order=6)
    private long exp;
    
    /**
     * 上一次每日重置的时间戳
     */
    @Column
    @Protobuf(order=17)
    private long lastDailyReset;
    
    @Column
    @Protobuf(order=7)
    private int money1;
    
    @Column
    @Protobuf(order=8)
    private int money2;
    
    /**
     * 排行积分
     */
    @Column
    @Protobuf(order=9)
    private int bonus_points;
    
    /**
     * 进行排行战斗标识,没有在战斗，则为0
     */
    @Column
    @Protobuf(order=10)
    private int rank_battle_id;
    
    /**
     * 宝库等级
     */
    @Column
    @Protobuf(order=11)
    private int treasuryLevel = 1;
    
    /**
     * 宝库等级上限
     */
    @Column
    @Protobuf(order=12)
    private int maxTreasuryLevel = 25;
    
    /**
     * 等级进度
     */
    @Column
    @Protobuf(order=13)
    private int treasuryLevelProgress = 0;
    
    /**
     * 持有钥匙数量
     */
    @Column
    @Protobuf(order=14)
    private int keyNum;
    
    /**
     * 持有钥匙上限
     */
    @Column
    @Protobuf(order=15)
    private int maxKeyNum = 24;
    
    @Protobuf(order=16)
    private int rank = -1;
    
    @Protobuf(order=18)
    private long now; //服务器当前时间
    
    @Column
    private int fuben_map_id;
    
    @Column
    @Protobuf(order=19)
    private int is_rename; //进入大厅是否改名
    
    @Column
    private int fuben_achieve; // 是否领取了副本奖励 二进制表示 0为未领取，1为领取。
    
    @Column
    private int buy_key_num;
    
    @Column
    private int is_ai; // 0:不是AI；1：是AI
    @Column
    private int rank_win_num; // 排行战斗胜利场次
    @Column
    private float rank_win_average_fight_percent; // 胜利对手平均战力比值
    @Column
    private float rank_win_average_fight; // 胜利对手平均战力 只有AI有
    @Column
    private int rank_lose_num; // 排行战斗失败场次
    @Column
    private float rank_lose_average_fight_percent; // 失败对手平均战力比值
    @Column
    private float rank_lose_average_fight; // 失败对手平均战力 只有AI有
    @Column
    private int rank_score; // 当前战绩
    @Column
    private int fight; // 排行队伍战力
    @Column
    private int fight_enemy; // 对战对手的战力
    @Column
    private int bp_enemy; // 对战对手的积分
    @Column
    private int is_enemy_ai; // 对战对手是否是AI
    
    public Player() {
        this.id = IdGenerator.getNextId();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getLastDailyReset() {
        return lastDailyReset;
    }

    public void setLastDailyReset(long lastDailyReset) {
        this.lastDailyReset = lastDailyReset;
    }
    
    public int getMoney1() {
        return money1;
    }

    public void setMoney1(int money1) {
        this.money1 = money1;
    }

    public int getMoney2() {
        return money2;
    }

    public void setMoney2(int money2) {
        this.money2 = money2;
    }

    public long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(long player_id) {
        this.player_id = player_id;
    }

    public int getBonus_points() {
        return bonus_points;
    }

    public void setBonus_points(int bonus_points) {
        this.bonus_points = bonus_points;
    }

    public int getRank_battle_id() {
        return rank_battle_id;
    }

    public void setRank_battle_id(int rank_battle_id) {
        this.rank_battle_id = rank_battle_id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getTreasuryLevel() {
        return treasuryLevel;
    }

    public void setTreasuryLevel(int treasuryLevel) {
        this.treasuryLevel = treasuryLevel;
    }

    public int getMaxTreasuryLevel() {
        return maxTreasuryLevel;
    }

    public void setMaxTreasuryLevel(int maxTreasuryLevel) {
        this.maxTreasuryLevel = maxTreasuryLevel;
    }

    public int getTreasuryLevelProgress() {
        return treasuryLevelProgress;
    }

    public void setTreasuryLevelProgress(int treasuryLevelProgress) {
        this.treasuryLevelProgress = treasuryLevelProgress;
    }

    public int getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(int keyNum) {
        this.keyNum = keyNum;
    }

    public int getMaxKeyNum() {
        return maxKeyNum;
    }

    public void setMaxKeyNum(int maxKeyNum) {
        this.maxKeyNum = maxKeyNum;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public int getFuben_map_id() {
        return fuben_map_id;
    }

    public void setFuben_map_id(int fuben_map_id) {
        this.fuben_map_id = fuben_map_id;
    }

    public int getIs_rename() {
        return is_rename;
    }

    public void setIs_rename(int is_rename) {
        this.is_rename = is_rename;
    }

    public int getBuy_key_num() {
        return buy_key_num;
    }

    public void setBuy_key_num(int buy_key_num) {
        this.buy_key_num = buy_key_num;
    }

    public int getFuben_achieve() {
        return fuben_achieve;
    }

    public void setFuben_achieve(int fuben_achieve) {
        this.fuben_achieve = fuben_achieve;
    }

    public int getIs_ai() {
        return is_ai;
    }

    public void setIs_ai(int is_ai) {
        this.is_ai = is_ai;
    }

    public int getRank_win_num() {
        return rank_win_num;
    }

    public void setRank_win_num(int rank_win_num) {
        this.rank_win_num = rank_win_num;
    }

    public float getRank_win_average_fight_percent() {
        return rank_win_average_fight_percent;
    }

    public void setRank_win_average_fight_percent(float rank_win_average_fight_percent) {
        this.rank_win_average_fight_percent = rank_win_average_fight_percent;
    }

    public float getRank_win_average_fight() {
        return rank_win_average_fight;
    }

    public void setRank_win_average_fight(float rank_win_average_fight) {
        this.rank_win_average_fight = rank_win_average_fight;
    }

    public int getRank_lose_num() {
        return rank_lose_num;
    }

    public void setRank_lose_num(int rank_lose_num) {
        this.rank_lose_num = rank_lose_num;
    }

    public float getRank_lose_average_fight_percent() {
        return rank_lose_average_fight_percent;
    }

    public void setRank_lose_average_fight_percent(float rank_lose_average_fight_percent) {
        this.rank_lose_average_fight_percent = rank_lose_average_fight_percent;
    }

    public float getRank_lose_average_fight() {
        return rank_lose_average_fight;
    }

    public void setRank_lose_average_fight(float rank_lose_average_fight) {
        this.rank_lose_average_fight = rank_lose_average_fight;
    }

    public int getRank_score() {
        return rank_score;
    }

    public void setRank_score(int rank_score) {
        this.rank_score = rank_score;
    }

    public int getFight() {
        return fight;
    }

    public void setFight(int fight) {
        this.fight = fight;
    }

    public int getFight_enemy() {
        return fight_enemy;
    }

    public void setFight_enemy(int fight_enemy) {
        this.fight_enemy = fight_enemy;
    }

    public int getBp_enemy() {
        return bp_enemy;
    }

    public void setBp_enemy(int bp_enemy) {
        this.bp_enemy = bp_enemy;
    }

    public int getIs_enemy_ai() {
        return is_enemy_ai;
    }

    public void setIs_enemy_ai(int is_enemy_ai) {
        this.is_enemy_ai = is_enemy_ai;
    }

    @Override
    public int distributeKey() {
        IoSession session = SessionManager.INSTANCE.getSessionBy(id);
        return (int)session.getAttribute(SessionProperties.DISTRIBUTE_KEY);
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", player_id=" + player_id + ", name=" + name + ", job=" + job
                + ", level=" + level + ", exp=" + exp + ", lastDailyReset=" + lastDailyReset 
                + ", money1=" + money1 + ", money2=" + money2 + ", bonus_points=" + bonus_points 
                + ", rank_battle_id=" + rank_battle_id + ", treasuryLevel=" + treasuryLevel 
                + ", maxTreasuryLevel=" + maxTreasuryLevel + ", treasuryLevelProgress=" + treasuryLevelProgress 
                + ", keyNum=" + keyNum + ", maxKeyNum=" + maxKeyNum + ", rank=" + rank 
                + ", now=" + now + ", fuben_map_id=" + fuben_map_id + ", is_name=" + is_rename 
                + ", buy_key_num=" + buy_key_num + ", fuben_achieve=" + fuben_achieve + "]";
    }
}
