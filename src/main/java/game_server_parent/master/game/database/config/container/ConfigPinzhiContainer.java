package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigPinzhi;

import game_server_parent.master.orm.utils.DbUtils;

public class ConfigPinzhiContainer implements Reloadable {
	
	private Map<Integer,ConfigPinzhi> pinzhi_zhonglei =new HashMap<Integer,ConfigPinzhi>();
	@Override
	public void reload() {
		// TODO 自动生成的方法存根
		 String sql = "SELECT * FROM configpinzhi";
	        List<ConfigPinzhi> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigPinzhi.class);
	        //使用jdk8，将list转为map
	        pinzhi_zhonglei = datas.stream().collect(
	                Collectors.toMap(ConfigPinzhi::getPinzhi_zhonglei, Function.identity()));
	}
	 public ConfigPinzhi getConfigBy(int zhonglei) {
	        return pinzhi_zhonglei.get(zhonglei);
	    }
}
