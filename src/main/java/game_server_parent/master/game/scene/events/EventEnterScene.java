package game_server_parent.master.game.scene.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventEnterScene.java</p>
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
public class EventEnterScene extends PlayerEvent {

    private int mapId;
    /**
     * @param evtType
     * @param playerId
     */
    public EventEnterScene(EventType evtType, long playerId, int mapId) {
        super(evtType, playerId);
        this.mapId = mapId;
    }
    public int getMapId() {
        return mapId;
    }
    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
    
    @Override
    public String toString() {
        return "EventEnterScene [mapId=" + mapId + ", playerId="
                + getPlayerId() + ", EventType=" + getEventType() + "]";
    }

}
