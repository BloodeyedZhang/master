package game_server_parent.master.game.kapai.events;

import java.util.ArrayList;
import java.util.List;

import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventKapaiUpdate.java</p>
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
public class EventKapaiUpdate extends PlayerEvent {

    private List<Kapai> kapais = new ArrayList<Kapai>();
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventKapaiUpdate(EventType evtType, long playerId, List<Kapai> kapais) {
        super(evtType, playerId);
        this.kapais = kapais;
    }

    public List<Kapai> getKapais() {
        return kapais;
    }

    @Override
    public String toString() {
        return "EventKapaiUpdate [playerId=" + getPlayerId() + ",eventType="+ getEventType() + "]";
    }
}
