package game_server_parent.master.game.team;

/**
 * <p>Filename:TeamDataPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class TeamDataPool {

    //cmd请求协议枚举
    /** 请求——角色队伍列表 */
    public static final int REQ_PLAYER_TEAM_LIST = 1;
    /** 请求——更新队伍 */
    public static final int REQ_UPDATE_TEAM = 2;
    /** 请求——获取快速战斗的队伍 */
    public static final int REQ_QUICK_BATTLE_TEAM = 3;
    
    //cmd响应协议枚举
    /** 响应——角色队伍列表 */
    public static final int RES_PLAYER_TEAM_LIST = 501;
    /** 响应——更新队伍 */
    public static final int RES_UPDATE_TEAM = 502;
    /** 响应——获取快速战斗的队伍 */
    public static final int RES_QUICK_BATTLE_TEAM = 503;
    
}
