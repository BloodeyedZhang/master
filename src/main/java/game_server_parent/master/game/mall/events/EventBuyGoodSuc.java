package game_server_parent.master.game.mall.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:BuyGoodSucEvent.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月17日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventBuyGoodSuc extends PlayerEvent {

    private int good_id;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventBuyGoodSuc(EventType evtType, long playerId) {
        super(evtType, playerId);
    }
    
    public EventBuyGoodSuc(EventType evtType, long playerId, int good_id) {
        super(evtType, playerId);
        this.good_id = good_id;
    }

    @Override
    public boolean isSynchronized() {
        return false; // 异步消息
    }

    public int getGood_id() {
        return good_id;
    }

    @Override
    public String toString() {
        return "通知发放商品 [player_id="+getPlayerId()+", good_id="+good_id+"]";
    }

}
