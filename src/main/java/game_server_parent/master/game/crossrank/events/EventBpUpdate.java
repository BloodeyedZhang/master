package game_server_parent.master.game.crossrank.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventBpUpdate.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventBpUpdate extends PlayerEvent {

    /**
     * @param evtType
     * @param playerId
     */
    public EventBpUpdate(EventType evtType, long playerId) {
        super(evtType, playerId);
    }

}
