package game_server_parent.master.game.player.events;


import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventPlayerLevelUp.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventPlayerLevelUp extends PlayerEvent {

    /** 新的等级 */
    private int upLevel;
    
    public EventPlayerLevelUp(EventType evtType, long playerId, int newLevel) {
        super(evtType, playerId);
        this.upLevel = newLevel;
    }
    
    public int getUpLevel() {
        return this.upLevel;
    }

    @Override
    public String toString() {
        return "EventPlayerLevelUp [upLevel=" + upLevel + ", playerId="
                + getPlayerId() + ", EventType=" + getEventType() + "]";
    }

}
