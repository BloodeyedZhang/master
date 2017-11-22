package game_server_parent.master.game.database.user.storage;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;

/**
 * <p>
 * Filename:Fuben.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.
 * </p>
 * <p>
 * Company: WinTurn Network Technology
 * </p>
 * <p>
 * Summary:
 * </p>
 * <p>
 * Created: 2017年11月6日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
@Entity(table="fuben")
public class Fuben extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -5388048650923035051L;

    @Id
    @Column
    @Protobuf(order=1)
    private long fuben_id;
    
    @Column
    private long id;

    @Column
    @Protobuf(order=2)
    private long player_id;

    @Column
    @Protobuf(order=3)
    private int fuben_isopen=1;

    @Column
    @Protobuf(order=4)
    private int fuben_level=1;

    @Column
    @Protobuf(order=5)
    private int fuben_win_num;

    @Column
    @Protobuf(order=6)
    private int fuben_lose_num;

    @Column
    @Protobuf(order=7)
    private int fuben_reward;

    @Column
    @Protobuf(order=8)
    private int fuben_canfight=1;
    
    @Column
    @Protobuf(order=9)
    private int fuben_reward_type;
    
    @Column
    @Protobuf(order=10)
    private int fuben_daojishi;
    
    @Column
    private long update_time;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(long player_id) {
        this.player_id = player_id;
    }

    public int getFuben_isopen() {
        return fuben_isopen;
    }

    public void setFuben_isopen(int fuben_isopen) {
        this.fuben_isopen = fuben_isopen;
    }

    public int getFuben_level() {
        return fuben_level;
    }

    public void setFuben_level(int fuben_level) {
        this.fuben_level = fuben_level;
    }

    public int getFuben_win_num() {
        return fuben_win_num;
    }

    public void setFuben_win_num(int fuben_win_num) {
        this.fuben_win_num = fuben_win_num;
    }

    public int getFuben_lose_num() {
        return fuben_lose_num;
    }

    public void setFuben_lose_num(int fuben_lose_num) {
        this.fuben_lose_num = fuben_lose_num;
    }

    public int getFuben_reward() {
        return fuben_reward;
    }

    public void setFuben_reward(int fuben_reward) {
        this.fuben_reward = fuben_reward;
    }

    public int getFuben_canfight() {
        return fuben_canfight;
    }

    public void setFuben_canfight(int fuben_canfight) {
        this.fuben_canfight = fuben_canfight;
    }

    public long getFuben_id() {
        return fuben_id;
    }

    public void setFuben_id(long fuben_id) {
        this.fuben_id = fuben_id;
    }

    public int getFuben_reward_type() {
        return fuben_reward_type;
    }

    public void setFuben_reward_type(int fuben_reward_type) {
        this.fuben_reward_type = fuben_reward_type;
    }

    public int getFuben_daojishi() {
        return fuben_daojishi;
    }

    public void setFuben_daojishi(int fuben_daojishi) {
        this.fuben_daojishi = fuben_daojishi;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

}
