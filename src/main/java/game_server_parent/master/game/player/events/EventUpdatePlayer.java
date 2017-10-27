package game_server_parent.master.game.player.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventUpdatePlayer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventUpdatePlayer extends PlayerEvent {

    public EventUpdatePlayer(EventType evtType, long playerId) {
        super(evtType, playerId);
    }

    @Override
    public boolean isSynchronized() {
        // TODO Auto-generated method stub
        return false;
    }

}
