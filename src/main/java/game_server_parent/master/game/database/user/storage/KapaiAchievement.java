package game_server_parent.master.game.database.user.storage;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;

/**
 * <p>Filename:kapaiAchievement.java</p>
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
@Entity(table="kapai_achievement")
public class KapaiAchievement extends BaseEntity<Long> {
    /**
     * 
     */
    private static final long serialVersionUID = -337530329106626358L;

    @Id
    @Column
    @Protobuf(order=1)
    private long achieve_id;
    
    @Column
    private long id;

    @Column
    @Protobuf(order=2)
    private long player_id;

    @Column
    @Protobuf(order=3)
    private int kapai_id;

    @Column
    @Protobuf(order=4)
    private int achieve_state;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAchieve_id() {
        return achieve_id;
    }

    public void setAchieve_id(long achieve_id) {
        this.achieve_id = achieve_id;
    }

    public long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(long player_id) {
        this.player_id = player_id;
    }

    public int getKapai_id() {
        return kapai_id;
    }

    public void setKapai_id(int kapai_id) {
        this.kapai_id = kapai_id;
    }

    public int getAchieve_state() {
        return achieve_state;
    }

    public void setAchieve_state(int achieve_state) {
        this.achieve_state = achieve_state;
    }
}
