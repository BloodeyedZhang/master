package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigBingzhong;
import game_server_parent.master.orm.utils.DbUtils;

public class ConfigBingzhongContainer implements Reloadable {

	private Map<Integer,ConfigBingzhong> pinzhi_zhonglei =new HashMap<Integer,ConfigBingzhong>();
	@Override
	public void reload() {
		// TODO 自动生成的方法存根
		 String sql = "SELECT * FROM ConfigBingzhong";
	        List<ConfigBingzhong> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigBingzhong.class);
	        //使用jdk8，将list转为map
	        pinzhi_zhonglei = datas.stream().collect(
	                Collectors.toMap(ConfigBingzhong::getId, Function.identity()));
	}
	 public ConfigBingzhong getConfigBy(int zhonglei) {
	        return pinzhi_zhonglei.get(zhonglei);
	    }
}
