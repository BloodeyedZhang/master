package game_server_parent.master.listener;

/**
 * <p>Filename:PlayerEvent.java</p>
 * <p>Description: 玩家事件抽象类 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class PlayerEvent extends GameEvent {

    /** 玩家id */
    private final long playerId;
    
    public PlayerEvent(EventType evtType, long playerId) {
        super(evtType);
        this.playerId = playerId;
    }

    public long getPlayerId() {
        return this.playerId;
    }
}
