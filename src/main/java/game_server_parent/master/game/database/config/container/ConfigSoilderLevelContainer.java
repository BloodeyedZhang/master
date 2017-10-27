package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;

import game_server_parent.master.game.database.config.bean.ConfigSoilderLevel;
import game_server_parent.master.orm.utils.DbUtils;

public class ConfigSoilderLevelContainer implements Reloadable{

	 private Map<String, ConfigSoilderLevel> ids = new HashMap<String, ConfigSoilderLevel>();

	    @Override
	    public void reload() {
	        String sql = "SELECT * FROM configsoilderlevel";
	        List<ConfigSoilderLevel> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigSoilderLevel.class);
	        //使用jdk8，将list转为map
	        ids = datas.stream().collect(
	                Collectors.toMap(ConfigSoilderLevel::getKey, Function.identity()));

	    }

	    public ConfigSoilderLevel getConfigBy(String id) {
	        return ids.get(id);
	    }

}
