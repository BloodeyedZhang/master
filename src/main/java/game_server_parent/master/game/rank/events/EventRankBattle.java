package game_server_parent.master.game.rank.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventRankBattle.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月22日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventRankBattle extends PlayerEvent {

    private int battle_id;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventRankBattle(EventType evtType, long playerId, int battle_id) {
        super(evtType, playerId);
        this.battle_id = battle_id;
    }

    public int getBattle_id() {
        return battle_id;
    }

    public void setBattle_id(int battle_id) {
        this.battle_id = battle_id;
    }
    
    @Override
    public String toString() {
        return "EventRankBattle [battle_id=" + battle_id + ", playerId="
                + getPlayerId() + ", EventType=" + getEventType() + "]";
    }


}
