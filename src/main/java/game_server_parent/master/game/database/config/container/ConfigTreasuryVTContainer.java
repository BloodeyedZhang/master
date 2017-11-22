package game_server_parent.master.game.database.config.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigTreasuryVT;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigTreasuryVTContainer.java</p>
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
public class ConfigTreasuryVTContainer implements Reloadable {
    private Map<Integer,ConfigTreasuryVT> pinzhi_zhonglei =new HashMap<Integer,ConfigTreasuryVT>();
    @Override
    public void reload() {
        // TODO 自动生成的方法存根
         String sql = "SELECT * FROM configtreasuryvt";
            List<ConfigTreasuryVT> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigTreasuryVT.class);
            //使用jdk8，将list转为map
            pinzhi_zhonglei = datas.stream().collect(
                    Collectors.toMap(ConfigTreasuryVT::getLevel, Function.identity()));
    }
    
    public Map<Integer,ConfigTreasuryVT> getAll() {
        return pinzhi_zhonglei;
    }
    
    public void setAll(Map<Integer, ConfigTreasuryVT> all) {
        this.pinzhi_zhonglei = all;
    }
    
    public ConfigTreasuryVT getConfigBy(int level) {
            return pinzhi_zhonglei.get(level);
    }
}
