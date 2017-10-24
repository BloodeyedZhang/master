package game_server_parent.master.game.treasury.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventTreasuryUpdate.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventTreasuryUpdate extends PlayerEvent {

    private int treasury_level;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventTreasuryUpdate(EventType evtType, long playerId, int treasury_level) {
        super(evtType, playerId);
        // TODO Auto-generated constructor stub
        this.treasury_level = treasury_level;
    }

    public int getTreasury_level() {
        return treasury_level;
    }

    public void setTreasury_level(int treasury_level) {
        this.treasury_level = treasury_level;
    }
    
    @Override
    public String toString() {
        return "EventTreasuryUpdate [player_id="+getPlayerId() 
        + ", eventType=" + getEventType() 
        + ", treasury_level=" + treasury_level 
        + "]";
    }
}
