package game_server_parent.master.game;

/**
 * <p>Filename:Modules.java</p>
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
public interface Modules {
    //------------------底层功能支持模块（从0到100）-----------------
    int BASE = 2;
    int GM = 1;
    
    //------------------业务功能模块（101开始）---------------------
    
    /**　登录 */
    int LOGIN = 101;
    /** 玩家 */
    int PLAYER = 102;
    /** 场景 */
    int SCENE = 103;
    /** 卡牌 */
    int KAPAI = 104;
    /** 士兵队伍 */
    int SOILDER_TEAM = 105;
    /** 排行战斗 */
    int RANK = 106;
    /** 宝库 */
    int TREASURY = 107;
    /** 商城 */
    int MALL = 108;
    /** 排行榜 */
    int CROSS_RANK = 109;
}
