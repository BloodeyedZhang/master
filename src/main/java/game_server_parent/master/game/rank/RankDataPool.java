package game_server_parent.master.game.rank;

/**
 * <p>Filename:RankDataPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月21日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class RankDataPool {

    /** 战斗失败标识 */
    public static final int BATTLE_LOSE = 0;
    /** 战斗胜利标识 */
    public static final int BATTLE_WIN = 1;
    
    //cmd请求协议枚举
    /** 请求——排行战斗结束 */
    public static final int REQ_RANK_BATTLE_END = 10;
    
    //cmd响应协议枚举
    /** 响应——排行队伍 */
    public static final int RES_RANK_SOILDET_TEAM = 501;
    /** 响应——排行战斗结束 */
    public static final int RES_RANK_BATTLE_END = 510;
}
