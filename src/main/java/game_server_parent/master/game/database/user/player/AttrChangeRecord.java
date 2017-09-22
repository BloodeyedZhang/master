package game_server_parent.master.game.database.user.player;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;
import game_server_parent.master.utils.IdGenerator;

/**
 * <p>Filename:AttrChangeStatistics.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月19日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Entity
public class AttrChangeRecord extends BaseEntity<Long> {

    
    @Id
    @Column
    private long record_id;
    
    @Column
    private long id;
    
    @Column
    private String sourceEvtType;
    @Column
    private String targetEvtType;
    @Column
    private int attrChange;
    
    public AttrChangeRecord() {
        this.id = IdGenerator.getNextId();
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public String getSourceEvtType() {
        return sourceEvtType;
    }

    public void setSourceEvtType(String sourceEvtType) {
        this.sourceEvtType = sourceEvtType;
    }

    public String getTargetEvtType() {
        return targetEvtType;
    }

    public void setTargetEvtType(String targetEvtType) {
        this.targetEvtType = targetEvtType;
    }

    public int getAttrChange() {
        return attrChange;
    }

    public void setAttrChange(int attrChange) {
        this.attrChange = attrChange;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecord_id() {
        return record_id;
    }

    public void setRecord_id(long record_id) {
        this.record_id = record_id;
    }

}
