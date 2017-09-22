package game_server_parent.master.game.scene;

/**
 * <p>Filename:SceneDataPool.java</p>
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
public class SceneDataPool {
    
    //cmd请求协议枚举
    /** 请求——进入场景 */
    public static final int REQ_ENTER_SCENE = 1;
    /** 请求——即将进入场景 */
    public static final int REQ_PRE_ENTER_SCENE = 2;
    
    //cmd响应协议枚举
    /** 响应——进入场景 */
    public static final int RES_ENTER_SCENE = 501;
    /** 响应——将进入场景 */
    public static final int RES_PRE_ENTER_SCENE = 502;
    
    /** 进入失败标识 */
    public static final int ENTER_FAIL = 0;
    /** 进入成功标识 */
    public static final int ENTER_SUCC = 1;
}
