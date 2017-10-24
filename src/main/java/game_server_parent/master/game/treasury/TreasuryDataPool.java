package game_server_parent.master.game.treasury;

/**
 * <p>Filename:TreasuryDataPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月16日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class TreasuryDataPool {
 
    /** 战斗失败标识 */
    public static final int BATTLE_LOSE = 0;
    /** 战斗胜利标识 */
    public static final int BATTLE_WIN = 1;
    
    //cmd请求协议枚举
    /** 请求——战斗结束 */
    public static final int REQ_TREASURY_BATTLE_END = 10;
    /** 请求--摧毁宝箱 **/
    public static final int REQ_TREASURY_DESTROY_BOX = 11;
    /** 请求--宝库升级显示 **/
    public static final int REQ_TREASURY_UPATE_SHOW = 12;
    
    //cmd响应协议枚举
    /** 响应——宝库队伍 */
    public static final int RES_TREASURY_TEAM = 501;
    /** 响应——战斗结束 */
    public static final int RES_TREASURY_BATTLE_END = 510;
    /** 响应--摧毁宝箱 **/
    public static final int RES_TREASURY_DESTROY_BOX = 511;
    /** 响应--宝库升级**/
    public static final int RES_TREASURY_UPDATE = 512;
}
