package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigXingji;
import game_server_parent.master.orm.utils.DbUtils;

public class ConfigXingjiContainer implements Reloadable{
	 private Map<Integer, ConfigXingji> ids = new HashMap<Integer, ConfigXingji>();

	    @Override
	    public void reload() {
	        String sql = "SELECT * FROM ConfigXingji";
	        List<ConfigXingji> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigXingji.class);
	        //使用jdk8，将list转为map
	        ids = datas.stream().collect(
	                Collectors.toMap(ConfigXingji::getXingji, Function.identity()));

	    }

	    public ConfigXingji getConfigBy(int xingji) {
	        return ids.get(xingji);
	    }
}
