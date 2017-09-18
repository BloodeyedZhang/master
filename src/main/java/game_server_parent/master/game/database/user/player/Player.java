package game_server_parent.master.game.database.user.player;

import org.apache.mina.core.session.IoSession;

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
    private long id;
    
    @Column
    private String name;
    
    /**
     * 职业
     */
    @Column 
    private int job;
    
    @Column
    private int level;
    
    @Column
    private long exp;
    
    /**
     * 上一次每日重置的时间戳
     */
    @Column
    private long lastDailyReset;
    
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
    
    @Override
    public int distributeKey() {
        IoSession session = SessionManager.INSTANCE.getSessionBy(id);
        return (int)session.getAttribute(SessionProperties.DISTRIBUTE_KEY);
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", job=" + job
                + ", level=" + level + ", exp=" + exp + ", lastDailyReset=" + lastDailyReset + "]";
    }
}
