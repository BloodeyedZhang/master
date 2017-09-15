package game_server_parent.master.listener;

/**
 * <p>Filename:EventType.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public enum EventType {
    
    /** 心跳事件 */
    HEARTBEAT,
    /** 心跳超时事件 */
    HEARTBEAT_TIMEOUT,
    
    /** 登录事件 */
    LOGIN,
    /** 重登事件 */
    RELOGIN,
    
    /** 升级事件 */
    LEVEL_UP,
    /** 货币1 增加事件*/
    MONEY1_ADD,
    /** 货币1 减少事件*/
    MONEY1_DEDUCT,
    
    /** 获得新卡牌事件 **/
    KAPAI_NEW,
    /** 获得卡牌升级事件 **/
    KAPAI_UPDATE,
    /** 出售卡牌事件 **/
    KAPAI_SELL,
    /** 删除卡牌事件 **/
    KAPAI_REMOVE;
}
