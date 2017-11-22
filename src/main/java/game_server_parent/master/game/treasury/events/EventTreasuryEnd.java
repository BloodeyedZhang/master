package game_server_parent.master.game.treasury.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventTreasuryEnd.java</p>
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
public class EventTreasuryEnd extends PlayerEvent {

    private long treasury_id;
    
    private int battle_result;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventTreasuryEnd(EventType evtType, long playerId, long treasury_id, int battle_result) {
        super(evtType, playerId);
        this.treasury_id = treasury_id;
        this.battle_result = battle_result;
    }

    public long getTreasury_id() {
        return treasury_id;
    }

    public void setTreasury_id(long treasury_id) {
        this.treasury_id = treasury_id;
    }

    public int getBattle_result() {
        return battle_result;
    }

    public void setBattle_result(int battle_result) {
        this.battle_result = battle_result;
    }
    
    
    
    @Override
    public boolean isSynchronized() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        return "EventTreasuryEnd [treasury_id="+ treasury_id 
                + ", battle_result=" + battle_result 
                + ", player_id=" + getPlayerId() 
                + "， eventType=" + getEventType() + "]";
    }
}
