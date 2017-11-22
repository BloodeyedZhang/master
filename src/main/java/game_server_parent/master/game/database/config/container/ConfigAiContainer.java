package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigAi;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigAiContainer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ConfigAiContainer implements Reloadable {

    private Map<Integer,ConfigAi> pinzhi_zhonglei =new HashMap<Integer,ConfigAi>();
    
    @Override
    public void reload() {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM configai";
        List<ConfigAi> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigAi.class);
        //使用jdk8，将list转为map
        pinzhi_zhonglei = datas.stream().collect(
                Collectors.toMap(ConfigAi::getBingzhong, Function.identity()));
    }

    public ConfigAi getConfigBy(int bingzhong) {
        return pinzhi_zhonglei.get(bingzhong);
    }
    
    public Set<Integer> getKeys() {
        return pinzhi_zhonglei.keySet();
    }
}
