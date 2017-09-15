package game_server_parent.master.game.kapai.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventKapaiNew.java</p>
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
public class EventKapaiNew extends PlayerEvent {

    private int dalei;
    private int bingzhong;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventKapaiNew(EventType evtType, long playerId, int dalei, int bingzhong) {
        super(evtType, playerId);
        this.dalei = dalei;
        this.bingzhong = bingzhong;
    }
    
    public int getDalei() {
        return dalei;
    }

    public int getBingzhong() {
        return bingzhong;
    }

    @Override
    public String toString() {
        return "EventKapaiNew [playerId=" + getPlayerId() + ",eventType="+ getEventType()+", dalei=" + dalei + ", bingzhong=" + bingzhong + "]";
    }

}
