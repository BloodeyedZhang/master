package game_server_parent.master.game.kapai;

/**
 * <p>Filename:KapaiDataPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class KapaiDataPool {
    
    // 加成种类枚举
    /** 攻击/治疗 加成 */
    public static final int ADDITION_ATTACK = 1;
    /** 生命 加成 */
    public static final int ADDITION_HP = 2;
    
    //cmd请求协议枚举
    /** 请求－获得新卡牌 */
    public static final int REQ_KAPAI_NEW = 2;
    /** 请求－升级卡牌 */
    public static final int REQ_KAPAI_UPDATE = 3;
    /** 请求－出售卡牌 */
    public static final int REQ_KAPAI_SELL = 4;
    
    //cmd响应协议枚举
    /** 响应－角色卡牌组 */
    public static final int RES_SELECT_PLAYER_KAPAI_LIST = 501;
    /** 响应－移除卡牌 */
    public static final int RES_KAPAI_REMOVE = 502;
}
