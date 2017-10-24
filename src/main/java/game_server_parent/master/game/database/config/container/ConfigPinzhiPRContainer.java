package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigPinzhiPR;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigPinzhiPRContainer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月17日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ConfigPinzhiPRContainer implements Reloadable {
    private Map<String, ConfigPinzhiPR> ids = new HashMap<String, ConfigPinzhiPR>();

    @Override
    public void reload() {
        String sql = "SELECT * FROM ConfigPinzhiPR";
        List<ConfigPinzhiPR> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigPinzhiPR.class);
        //使用jdk8，将list转为map
        ids = datas.stream().collect(
                Collectors.toMap(ConfigPinzhiPR::getType, Function.identity()));

    }

    public ConfigPinzhiPR getConfigBy(String type) {
        return ids.get(type);
    }
}
