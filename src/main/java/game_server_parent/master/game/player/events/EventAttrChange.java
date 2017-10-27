package game_server_parent.master.game.player.events;


import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventAttrChange.java</p>
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
public class EventAttrChange extends PlayerEvent {

    // 金币1 变化值
    private int money1_change;
    // 金币1 变化值
    private int money2_change;
    // 积分 变化值
    private int bonus_points_change;
    // 来源事件类型
    private EventType source_evtType;
    
    public EventAttrChange(EventType evtType, long playerId) {
        super(evtType, playerId);
    }

    public int getMoney1_change() {
        return money1_change;
    }

    public void setMoney1_change(int money1_change) {
        this.money1_change = money1_change;
    }

    public int getMoney2_change() {
        return money2_change;
    }

    public void setMoney2_change(int money2_change) {
        this.money2_change = money2_change;
    }

    public EventType getSource_evtType() {
        return source_evtType;
    }

    public void setSource_evtType(EventType source_evtType) {
        this.source_evtType = source_evtType;
    }

    public int getBonus_points_change() {
        return bonus_points_change;
    }

    public void setBonus_points_change(int bonus_points_change) {
        this.bonus_points_change = bonus_points_change;
    }

    @Override
    public String toString() {
        return "EventAttrChange [money1_change=" + money1_change +", money2_change=" + money2_change + ", bonus_points_change=" 
                + bonus_points_change + ", source_evtType=" + source_evtType +  ", playerId="
                + getPlayerId() + ", EventType=" + getEventType() + "]";
    }

    @Override
    public boolean isSynchronized() {
        // asynchronous
        return false;
    }
    
}
