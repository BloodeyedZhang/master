package game_server_parent.master.game.crossrank;

/**
 * <p>Filename:CrossRankDataPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月19日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class CrossRankDataPool {

    //cmd请求协议枚举
    /** 请求——设备唯一码 */
    public static final int REQ_CrossRank_UUID = 9;
    /** 请求——排行榜 */
    public static final int REQ_CrossRank_BP = 10;
    
    //cmd响应协议枚举
    /** 响应——设备唯一码 */
    public static final int RES_CrossRank_UUID = 509;
    /** 响应——排行榜 */
    public static final int RES_CrossRank_BP = 510;

}
