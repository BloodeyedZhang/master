package game_server_parent.master.game.database.config.container;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigMall;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigMallContainer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ConfigMallContainer implements Reloadable {

    private Map<Integer,ConfigMall> pinzhi_zhonglei =new HashMap<Integer,ConfigMall>();
    @Override
    public void reload() {
        // TODO 自动生成的方法存根
         String sql = "SELECT * FROM configmall";
            List<ConfigMall> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigMall.class);
            //使用jdk8，将list转为map
            pinzhi_zhonglei = datas.stream().collect(
                    Collectors.toMap(ConfigMall::getId, Function.identity()));
    }
     public ConfigMall getConfigBy(int id) {
            return pinzhi_zhonglei.get(id);
    }
     
     public Collection<ConfigMall> getAll(){
         return pinzhi_zhonglei.values();
     } 
}
