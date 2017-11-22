package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigFubenlevel;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigFubenlevelContainer.java</p>
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
public class ConfigFubenlevelContainer implements Reloadable {

    private Map<Integer,ConfigFubenlevel> pinzhi_zhonglei =new HashMap<Integer,ConfigFubenlevel>();

    @Override
    public void reload() {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM configfubenlevel";
        List<ConfigFubenlevel> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigFubenlevel.class);
        //使用jdk8，将list转为map
        pinzhi_zhonglei = datas.stream().collect(
                Collectors.toMap(ConfigFubenlevel::getLevel, Function.identity()));
    }

    public ConfigFubenlevel getConfigBy(int id) {
        return pinzhi_zhonglei.get(id);
    }
}
