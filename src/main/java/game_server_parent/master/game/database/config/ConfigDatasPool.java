package game_server_parent.master.game.database.config;

import java.lang.reflect.Field;

import game_server_parent.master.game.database.config.container.ConfigPlayerLevelContainer;
import game_server_parent.master.logs.LoggerUtils;

/**
 * <p>Filename:ConfigDatasPool.java</p>
 * <p>Description: 所有策划配置的数据池 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ConfigDatasPool {

    private static ConfigDatasPool instance =  new ConfigDatasPool();
    
    private ConfigDatasPool() {}
    
    public static ConfigDatasPool getInstance() {
        return instance;
    }
    
    public ConfigPlayerLevelContainer configPlayerLevelContainer = new ConfigPlayerLevelContainer();
    
    /**
     * 起服读取所有的配置数据
     */
    public void loadAllConfigs() {
        Field[] fields = ConfigDatasPool.class.getDeclaredFields();
        ConfigDatasPool instance = getInstance();
        for (Field f:fields) {
            try {
            if (Reloadable.class.isAssignableFrom(f.getType())) {
                Reloadable container = (Reloadable) f.getType().newInstance();
                container.reload();
                f.set(instance, container);
            }
            }catch (Exception e) {
                LoggerUtils.error("策划配置数据有误，请检查", e);
                System.exit(0);
            }
        }
        
    }
}
