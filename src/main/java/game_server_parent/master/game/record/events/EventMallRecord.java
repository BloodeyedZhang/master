package game_server_parent.master.game.record.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;
import game_server_parent.master.orm.annotation.Column;

/**
 * <p>Filename:EventMallRecord.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月23日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventMallRecord extends PlayerEvent {

    private int goods_id;
    private String createtime;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventMallRecord(EventType evtType, long playerId) {
        super(evtType, playerId);
    }

    @Override
    public boolean isSynchronized() {
        return false;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "EventTreasuryRecord [ createtime=" + createtime 
                + ", goods_id=" + goods_id 
                + ", player_id=" + getPlayerId() 
                + ", eventType=" + getEventType() + "]";
    }
}
