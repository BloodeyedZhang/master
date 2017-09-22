package game_server_parent.master.game.http;

/**
 * <p>Filename:HttpCommands.java</p>
 * <p>Description: 后台命令类型枚举 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月12日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public final class HttpCommands {

    /**  停服 */
    public static final int CLOSE_SERVER = 1;
    /**  查看开服时间 */
    public static final int QUERY_SERVER_OPEN_TIME = 2;
    /**  创建新角色 */
    public static final int CREATE_NEW_PLAYER = 3;
}
