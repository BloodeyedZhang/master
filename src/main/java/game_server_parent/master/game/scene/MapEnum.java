package game_server_parent.master.game.scene;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

/**
 * <p>Filename:MapEnum.java</p>
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
public enum MapEnum {
    // 登录地图
    Login(1),
    // 大厅地图
    Dating(1001),
    // 战斗地图
    Zhandou(1002),
    // 金库地图
    Treasury(1003),
    // 排行榜
    CrossRank(1004),
    // 商城
    Mall(1005);
    
    private int id;
    private MapEnum(int id)
    {
        this.id = id;
    }
    public int value() {
        return id; 
    }
}
