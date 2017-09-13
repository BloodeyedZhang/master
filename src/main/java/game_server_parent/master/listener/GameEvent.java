package game_server_parent.master.listener;

/**
 * <p>Filename:GameEvent.java</p>
 * <p>Description: 监听器监听的事件抽象类 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class GameEvent {

    /** 创建时间 */
    private long createTime;
    /** 事件类型 */
    private final EventType eventType;
    
    public GameEvent(EventType evtType) {
        this.createTime = System.currentTimeMillis();
        this.eventType  = evtType;
    }
    
    public long getCreateTime() {
        return this.createTime;
    }
    
    public EventType getEventType() {
        return this.eventType;
    }
    
    /**
     * 是否在消息主线程同步执行
     * @return
     */
    public boolean isSynchronized() {
        return true;
    }
}
