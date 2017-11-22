package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigFuben;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigFubenContainer.java</p>
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
public class ConfigFubenContainer implements Reloadable {

    private Map<Integer,ConfigFuben> pinzhi_zhonglei =new HashMap<Integer,ConfigFuben>();
    
    @Override
    public void reload() {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM configfuben";
        List<ConfigFuben> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigFuben.class);
        //使用jdk8，将list转为map
        pinzhi_zhonglei = datas.stream().collect(
                Collectors.toMap(ConfigFuben::getId, Function.identity()));
    }
    
    public ConfigFuben getConfigBy(int id) {
        return pinzhi_zhonglei.get(id);
    }
    
    public Set<Integer> getKeys() {
        return pinzhi_zhonglei.keySet();
    } 

}
