package game_server_parent.master.game.mall;

/**
 * <p>Filename:MallDataPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class MallDataPool {
    
    // 购买结果标识
    /** 允许购买标识 **/
    public static final int BUY_SUC = 1;
    /** 不允许购买标识 **/
    public static final int BUY_FAI = 2;

    //商品内容类型枚举
    /** 商品内容类型——卡牌 */
    public static final int TYPE_KAPAI = 0;
    /** 商品内容类型——宝石 */
    public static final int TYPE_DIAMOND = 1;
    /** 商品内容类型——钥匙 */
    public static final int TYPE_KEYS = 2;
    
    //商品需求货币类型枚举
    /** 商品需求货币类型——人民币 */
    public static final int COINTYPE_CNY = 0;
    /** 商品需求货币类型——宝石 */
    public static final int COINTYPE_DIAMOND = 1;
    /** 商品需求货币类型——金币 */
    public static final int COINTYPE_COIN = 2;
    
    //cmd请求协议枚举
    /** 请求——商店配置信息 */
    public static final int REQ_Mall_Config = 10;
    /** 请求——购买商品 */
    public static final int REQ_Mall_BUY = 11;
    
    //cmd响应协议枚举
    /** 响应——商店配置信息 */
    public static final int RES_Mall_Config = 510;
    /** 响应——购买商品 */
    public static final int RES_Mall_BUY = 520;
}
