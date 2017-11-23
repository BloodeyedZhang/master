package game_server_parent.master.game.record.events;

import java.util.ArrayList;
import java.util.List;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventTreasuryRecord.java</p>
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
public class EventTreasuryRecord extends PlayerEvent {

    private long treasury_id;
    private int index;
    private int coins;
    private int diamonds;
    private List<int[]> params = new ArrayList<int[]>();
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventTreasuryRecord(EventType evtType, long playerId) {
        super(evtType, playerId);
    }

    public long getTreasury_id() {
        return treasury_id;
    }

    public void setTreasury_id(long treasury_id) {
        this.treasury_id = treasury_id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public List<int[]> getParams() {
        return params;
    }

    public void setParams(List<int[]> params) {
        this.params = params;
    }
    
    

    @Override
    public boolean isSynchronized() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        return "EventTreasuryRecord [ treasury_id=" + treasury_id 
                + ", index=" + index 
                + ", coins=" + coins 
                + ", diamonds=" + diamonds 
                + ", params.size=" + params.size() 
                + ", player_id=" + getPlayerId() 
                + ", eventType=" + getEventType() + "]";
    }
}
