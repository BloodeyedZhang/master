package game_server_parent.master.game.database.config.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.config.bean.ConfigJiachengVT;
import game_server_parent.master.game.database.config.bean.ConfigXingjiPR;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:ConfigXingjiPRContainer.java</p>
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
public class ConfigXingjiPRContainer implements Reloadable {
    private Map<Integer,ConfigXingjiPR> pinzhi_zhonglei =new HashMap<Integer,ConfigXingjiPR>();
    @Override
    public void reload() {
        // TODO 自动生成的方法存根
         String sql = "SELECT * FROM configxingjipr";
            List<ConfigXingjiPR> datas = DbUtils.queryMany(DbUtils.DB_DATA, sql, ConfigXingjiPR.class);
            //使用jdk8，将list转为map
            pinzhi_zhonglei = datas.stream().collect(
                    Collectors.toMap(ConfigXingjiPR::getLevel, Function.identity()));
    }
     public ConfigXingjiPR getConfigBy(int id) {
            return pinzhi_zhonglei.get(id);
    }
     
     public ConfigXingjiPR getSumVT() {
         return pinzhi_zhonglei.get(999);
     }
     
     public int size() {
         return pinzhi_zhonglei.size()-1;
     }
}
