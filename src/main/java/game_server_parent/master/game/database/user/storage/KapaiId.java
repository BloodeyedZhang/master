package game_server_parent.master.game.database.user.storage;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.utils.IdGenerator;

/**
 * <p>Filename:KapaiId.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Entity(readOnly=true)
public class KapaiId extends BaseEntity<Long> {
    
    @Column
    private int nextId;
    
    @Override
    public Long getId() {
        return IdGenerator.getNextId();
    }
    
    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    @Override
    public String toString() {
        return "Kapai [nextId=" + nextId + "]";
    }

}
