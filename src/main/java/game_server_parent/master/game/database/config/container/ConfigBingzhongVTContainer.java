package game_server_parent.master.game.database.config.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigBingzhongVT;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigBingzhongVTContainer.java</p>
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
public class ConfigBingzhongVTContainer implements Reloadable {
    private Map<Integer,ConfigBingzhongVT> pinzhi_zhonglei =new HashMap<Integer,ConfigBingzhongVT>();
    @Override
    public void reload() {
        // TODO 自动生成的方法存根
         String sql = "SELECT * FROM configbingzhongvt";
            List<ConfigBingzhongVT> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigBingzhongVT.class);
            //使用jdk8，将list转为map
            pinzhi_zhonglei = datas.stream().collect(
                    Collectors.toMap(ConfigBingzhongVT::getId, Function.identity()));
    }
    public ConfigBingzhongVT getConfigBy(int id) {
            return pinzhi_zhonglei.get(id);
    }
    
    public List<ConfigBingzhongVT> getAll() {
        return new ArrayList<>(pinzhi_zhonglei.values());
    }
     
     public int size() {
         return pinzhi_zhonglei.size();
     }
}
