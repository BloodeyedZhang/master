package game_server_parent.master.game.player;

/**
 * <p>Filename:PlayerDataPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class PlayerDataPool {
    
    /** 战斗失败标识 */
    public static final int BATTLE_LOSE = 0;
    /** 战斗胜利标识 */
    public static final int BATTLE_WIN = 1;
    /** 可以改名 */
    public static final int CAN_RENAME = 0;
    /** 不可以改名 */
    public static final int CANNOT_RENAME = 1;
    /** 不是AI */
    public static final int NOT_IS_AI = 0;
    /** 是AI */
    public static final int IS_AI = 1;
    
    //cmd请求协议枚举
    /** 请求－角色名称重命名 */
    public static final int REQ_PLAYER_RENAME = 10;
    /** 请求－角色名称重命名检查 */
    public static final int REQ_PLAYER_RENAME_CHECK = 11;
    
    //cmd响应协议枚举
    /** 响应－角色数据 */
    public static final int RES_PLAYER = 501;
    /** 响应－角色名称重命名 */
    public static final int RES_PLAYER_RENAME = 510;
    /** 响应－角色名称重命名检查 */
    public static final int RES_PLAYER_RENAME_CHECK = 511;
}
