package game_server_parent.master.game.login.events;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.login.message.ReqLoginMessage;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.GameEvent;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventLogin.java</p>
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
public class EventLogin extends GameEvent {

    private IoSession session;
    
    private ReqLoginMessage message;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventLogin(EventType evtType, IoSession session, ReqLoginMessage request) {
        super(evtType);
        this.session = session;
        this.message = request;
    }
    public IoSession getSession() {
        return session;
    }
    public ReqLoginMessage getMessage() {
        return message;
    }
    @Override
    public String toString() {
        return "EventLogin [EventType=" + getEventType() + "]";
    }

}
