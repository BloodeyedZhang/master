package game_server_parent.master.game.login;

/**
 * <p>Filename:LoginDataPool.java</p>
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
public class LoginDataPool {
    
    //cmd请求协议枚举
    /** 请求－登录 */
    public static final int REQ_LOGIN = 1;
    /** 请求－选择角色 */
    public static final int REQ_SELECT_PLAYER = 2;
    
    //cmd响应协议枚举
    /** 响应－登录 */
    public static final int RES_LOGIN = 501;
    
    /** 登录失败标识 */
    public static final int LOGIN_FAIL = 0;
    /** 登录成功标识 */
    public static final int LOGIN_SUCC = 1;
}
