package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigPlayerLevel;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigPlayerLevelContainer.java</p>
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
public class ConfigPlayerLevelContainer implements Reloadable {

    private Map<Integer, ConfigPlayerLevel> levels = new HashMap<Integer, ConfigPlayerLevel>();

    @Override
    public void reload() {
        String sql = "SELECT * FROM ConfigPlayerLevel";
        List<ConfigPlayerLevel> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigPlayerLevel.class);
        //使用jdk8，将list转为map
        levels = datas.stream().collect(
                Collectors.toMap(ConfigPlayerLevel::getLevel, Function.identity()));

    }

    public ConfigPlayerLevel getConfigBy(int level) {
        return levels.get(level);
    }

}
