package game_server_parent.master.listener;

/**
 * <p>Filename:EventType.java</p>
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
public enum EventType {
    
    /** 心跳事件 */
    HEARTBEAT,
    /** 心跳超时事件 */
    HEARTBEAT_TIMEOUT,
    
    /** 创建角色事件 */
    PLAYER_CREATE,
    /** 角色更新金币事件 */
    PLAYER_UPDATE_MONEY,
    
    /** 登录事件 */
    LOGIN,
    /** 重登事件 */
    RELOGIN,
    
    /** GM 添加金币，钻石，宝库钥匙事件 */
    GM_ADD_MONEY,
    
    /** 升级事件 */
    LEVEL_UP,
    /** 货币1 增加事件*/
    MONEY1_ADD,
    /** 货币1 减少事件*/
    MONEY1_DEDUCT,
    /** 货币2 增加事件*/
    MONEY2_ADD,
    /** 货币2 减少事件*/
    MONEY2_DEDUCT,
    /** 积分 增加事件*/
    BONUS_POINT_ADD,
    /** 积分 减少事件*/
    BONUS_POINTS_DEDUCT,
    /** 钥匙增加事件*/
    KEYSNUM_ADD,
    /** 钥匙减少事件*/
    KEYSNUM_DEDUCT,
    /** 战斗标识开始事件 **/
    BATTLE_ID_START,
    /** 战斗标识胜利事件 **/
    BATTLE_ID_WIN,
    /** 战斗标识失败事件 **/
    BATTLE_ID_LOSE,
    
    /** 获得新卡牌事件 **/
    KAPAI_NEW,
    /** 获得卡牌升级事件 **/
    KAPAI_UPDATE,
    /** 获得卡牌升级加成事件 **/
    KAPAI_UPDATE_JIACHENG,
    /** 出售卡牌事件 **/
    KAPAI_SELL,
    /** 删除卡牌事件 **/
    KAPAI_REMOVE,
    
    /** 获得队伍更新事件 **/
    SOILDER_TEAM_UPDATE,
    
    /** 宝库摧毁宝箱事件 **/
    TREASURY_DESTROY_BOX,
    /** 宝库战斗结束事件 **/
    TREASURY_BATTLE_END,
    /** 宝库升级事件 **/
    TREASURY_UPDATE,
    /** 宝库升级奖励显示事件 **/
    TREASURY_UPDATE_SHOW,
    
    /** 商城购买事件 **/
    MALL_BUY,
    /** 商城购买成功，发放商品事件 **/
    MALL_BUY_SUC,
    /** 商城购买记录 **/
    MALL_BUY_RECORD,
    
    /** 即将进入大厅事件 **/
    PRE_ENTER_DATING,
    /** 即将进入战斗事件 **/
    PRE_ENTER_ZHANDOU,
    /** 即将进入金库事件 **/
    PRE_ENTER_JINKU,
    /** 即将进入副本战斗事件 **/
    PRE_ENTER_FUBEN_ZHANDOU,
    /** 进入大厅事件 **/
    ENTER_DATING,
    /** 进入战斗事件 **/
    ENTER_ZHANDOU,
    /** 进入金库事件 **/
    ENTER_JINKU,
    /** 进入副本关卡事件 **/
    ENTER_FUBEN_LEVEL,
    /** 进入副本战斗事件 **/
    ENTER_FUBEN_ZHANDOU,
    
    /** 排行榜积分变化事件 **/
    CROSSRANK_BP_UPDATE,
    
    /** 战斗开始事件 **/
    BATTLE_START,
    /** 战斗胜利事件 **/
    BATTLE_WIN,
    /** 战斗失败事件 **/
    BATTLE_LOSE;
}
