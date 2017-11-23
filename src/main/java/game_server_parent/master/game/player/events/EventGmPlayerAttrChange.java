package game_server_parent.master.game.player.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventGmPlayerAttrChange.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月23日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventGmPlayerAttrChange extends PlayerEvent {

    private int money_coin;
    private int money_diamond;
    private int keynum;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventGmPlayerAttrChange(EventType evtType, long playerId) {
        super(evtType, playerId);
    }

    public int getMoney_coin() {
        return money_coin;
    }

    public void setMoney_coin(int money_coin) {
        this.money_coin = money_coin;
    }

    public int getMoney_diamond() {
        return money_diamond;
    }

    public void setMoney_diamond(int money_diamond) {
        this.money_diamond = money_diamond;
    }

    public int getKeynum() {
        return keynum;
    }

    public void setKeynum(int keynum) {
        this.keynum = keynum;
    }

}
