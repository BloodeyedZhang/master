package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigAi;
import game_server_parent.master.game.database.config.bean.Configbingzhongfight;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigbingzhongfightContainer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月23日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ConfigbingzhongfightContainer implements Reloadable {
    private Map<Integer,Configbingzhongfight> pinzhi_zhonglei =new HashMap<Integer,Configbingzhongfight>();
    
    @Override
    public void reload() {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM configbingzhongfight";
        List<Configbingzhongfight> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, Configbingzhongfight.class);
        //使用jdk8，将list转为map
        pinzhi_zhonglei = datas.stream().collect(
                Collectors.toMap(Configbingzhongfight::getId, Function.identity()));
    }

    public Configbingzhongfight getConfigBy(int bingzhong) {
        return pinzhi_zhonglei.get(bingzhong);
    }
    
}
