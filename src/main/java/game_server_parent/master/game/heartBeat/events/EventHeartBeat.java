package game_server_parent.master.game.heartBeat.events;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.GameEvent;

/**
 * <p>Filename:EventHeartBeat.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月15日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventHeartBeat extends GameEvent {

    private IoSession session;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventHeartBeat(EventType evtType, IoSession session) {
        super(evtType);
        this.session = session;
    }
    public IoSession getSession() {
        return session;
    }
    @Override
    public String toString() {
        return "EventHeartBeat [EventType=" + getEventType() + "]";
    }
}
