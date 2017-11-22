package game_server_parent.master.game.fuben;

/**
 * <p>Filename:FubenDataPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月7日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class FubenDataPool {

    //cmd请求协议枚举
    /** 请求－获得新卡牌 */
    public static final int REQ_KAPAI_NEW = 10;
    /** 请求——战斗结束 */
    public static final int REQ_BATTLE_END = 12;
    
    //cmd响应协议枚举
    /** 响应－角色副本组 */
    public static final int RES_SELECT_FUBEN_LIST = 510;
    /** 响应－副本队伍 */
    public static final int RES_FUBEN_TEAM = 511;
    /** 响应——战斗结束 */
    public static final int RES_BATTLE_END = 512;
}
