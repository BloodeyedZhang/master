package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigTreasuryReward;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigTreasuryRewardContainer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ConfigTreasuryRewardContainer implements Reloadable {
    private Map<Integer, ConfigTreasuryReward> ids = new HashMap<Integer, ConfigTreasuryReward>();

    @Override
    public void reload() {
        String sql = "SELECT * FROM ConfigTreasuryReward";
        List<ConfigTreasuryReward> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigTreasuryReward.class);
        //使用jdk8，将list转为map
        ids = datas.stream().collect(
                Collectors.toMap(ConfigTreasuryReward::getTreasuryLevel, Function.identity()));

    }

    public ConfigTreasuryReward getConfigBy(int level) {
        return ids.get(level);
    }
}
