package game_server_parent.master.game.fuben.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventFuben.java</p>
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
public class EventFuben extends PlayerEvent {

    private int map_id;
    
    public EventFuben(EventType evtType, long playerId, int map_id) {
        super(evtType, playerId);
        this.map_id = map_id;
    }

    public int getMap_id() {
        return map_id;
    }
    @Override
    public String toString() {
        return "EventFuben [playerId=" + getPlayerId() + ",eventType="+ getEventType() + ",map_id="+ map_id + "]";
    }
}
