package game_server_parent.master.game.kapai.events;

import java.util.ArrayList;
import java.util.List;

import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventKapaiRemove.java</p>
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
public class EventKapai extends PlayerEvent {

    private int kapai_id;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventKapai(EventType evtType, long playerId, int kapai_id) {
        super(evtType, playerId);
        this.kapai_id = kapai_id;
    }

    public int getKapai_id() {
        return kapai_id;
    }

    @Override
    public String toString() {
        return "EventKapaiRemove [playerId=" + getPlayerId() + ",eventType="+ getEventType() + ",kapai_id="+ kapai_id + "]";
    }

}
