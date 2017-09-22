package game_server_parent.master.game.team.event;

import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventSoilderTeamUpdate.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月19日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventSoilderTeamUpdate extends PlayerEvent {

    private SoilderTeam st;
    
    public EventSoilderTeamUpdate(EventType evtType, long playerId, SoilderTeam st) {
        super(evtType, playerId);
        this.st = st;
    }

    public SoilderTeam getSt() {
        return st;
    }

    public void setSt(SoilderTeam st) {
        this.st = st;
    }

    @Override
    public String toString() {
        return "EventSoilderTeamUpdate [st=" + st.toString() + ", playerId="
                + getPlayerId() + ", EventType=" + getEventType() + "]";
    }
}
