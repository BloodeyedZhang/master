package game_server_parent.master.game.scene;

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
    Mall(1005),
    // 副本关卡
    Fuben_Level(1006),
    
    /** 副本战斗地图添加 必须要在1100以后添加，如 1100+id **/
    Fuben_Zhandou(1100),
    // 副本战斗_1
    Fuben_Zhandou_1(1101),
    // 副本战斗_2
    Fuben_Zhandou_2(1102),
    // 副本战斗_3
    Fuben_Zhandou_3(1103);
    
    private int id;
    private MapEnum(int id)
    {
        this.id = id;
    }
    public int value() {
        return id; 
    }
}
