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

	 private Map<Integer, ConfigSoilderLevel> ids = new HashMap<Integer, ConfigSoilderLevel>();

	    @Override
	    public void reload() {
	        String sql = "SELECT * FROM ConfigSoilderLevel";
	        List<ConfigSoilderLevel> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigSoilderLevel.class);
	        //使用jdk8，将list转为map
	        ids = datas.stream().collect(
	                Collectors.toMap(ConfigSoilderLevel::getId, Function.identity()));

	    }

	    public ConfigSoilderLevel getConfigBy(int id) {
	        return ids.get(id);
	    }

}