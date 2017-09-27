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

    @Override
    public int distributeKey() {
        IoSession session = SessionManager.INSTANCE.getSessionBy(id);
        return (int)session.getAttribute(SessionProperties.DISTRIBUTE_KEY);
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", player_id=" + player_id + ", name=" + name + ", job=" + job
                + ", level=" + level + ", exp=" + exp + ", lastDailyReset=" + lastDailyReset 
                + ", money1=" + money1 + ", money2=" + money2 + ", bonus_points" + bonus_points 
                + ", rank_battle_id" + rank_battle_id + "]";
    }
}
