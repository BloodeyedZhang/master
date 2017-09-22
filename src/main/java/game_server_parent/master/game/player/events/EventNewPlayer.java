package game_server_parent.master.game.player.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventNewPlayer.java</p>
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
public class EventNewPlayer extends PlayerEvent {

    /**
     * @param evtType
     * @param playerId
     */
    public EventNewPlayer(EventType evtType, long playerId) {
        super(evtType, playerId);
    }

}
