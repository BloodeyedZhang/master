package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigFubenboss;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigFubenbossContainer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月6日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ConfigFubenbossContainer implements Reloadable {

    private Map<String,ConfigFubenboss> pinzhi_zhonglei =new HashMap<String,ConfigFubenboss>();
    
    @Override
    public void reload() {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM configfubenboss";
        List<ConfigFubenboss> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigFubenboss.class);
        //使用jdk8，将list转为map
        pinzhi_zhonglei = datas.stream().collect(
                Collectors.toMap(ConfigFubenboss::getKey, Function.identity()));
    }
    
    public ConfigFubenboss getConfigBy(String id) {
        return pinzhi_zhonglei.get(id);
    }

}
