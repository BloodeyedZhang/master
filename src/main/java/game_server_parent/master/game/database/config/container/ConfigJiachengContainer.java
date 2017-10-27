package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigPinzhi;
import game_server_parent.master.game.database.config.bean.ConfigJiacheng;
import game_server_parent.master.orm.utils.DbUtils;

public class ConfigJiachengContainer implements Reloadable {
	
	private Map<Integer,ConfigJiacheng> config =new HashMap<Integer,ConfigJiacheng>();
	@Override
	public void reload() {
		// TODO 自动生成的方法存根
		 String sql = "SELECT * FROM configpinzhi";
	        List<ConfigJiacheng> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigJiacheng.class);
	        //使用jdk8，将list转为map
	        config = datas.stream().collect(
	                Collectors.toMap(ConfigJiacheng::getJiacheng_zhonglei, Function.identity()));
	}
	 public ConfigJiacheng getConfigBy(int zhonglei) {
	        return config.get(zhonglei);
	    }
}
