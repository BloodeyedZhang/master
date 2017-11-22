package game_server_parent.master.game.database.config;

import java.lang.reflect.Field;

import game_server_parent.master.game.database.config.container.ConfigAiContainer;
import game_server_parent.master.game.database.config.container.ConfigBingzhongContainer;
import game_server_parent.master.game.database.config.container.ConfigBingzhongVTContainer;
import game_server_parent.master.game.database.config.container.ConfigFubenContainer;
import game_server_parent.master.game.database.config.container.ConfigFubenbossContainer;
import game_server_parent.master.game.database.config.container.ConfigFubenlevelContainer;
import game_server_parent.master.game.database.config.container.ConfigJiachengTypePRContainer;
import game_server_parent.master.game.database.config.container.ConfigJiachengVTContainer;
import game_server_parent.master.game.database.config.container.ConfigMallContainer;
import game_server_parent.master.game.database.config.container.ConfigPinzhiContainer;
import game_server_parent.master.game.database.config.container.ConfigPinzhiPRContainer;
import game_server_parent.master.game.database.config.container.ConfigPlayerLevelContainer;
import game_server_parent.master.game.database.config.container.ConfigSoilderLevelContainer;
import game_server_parent.master.game.database.config.container.ConfigTreasuryBingzhongContainer;
import game_server_parent.master.game.database.config.container.ConfigTreasuryContainer;
import game_server_parent.master.game.database.config.container.ConfigTreasuryRewardContainer;
import game_server_parent.master.game.database.config.container.ConfigTreasuryVTContainer;
import game_server_parent.master.game.database.config.container.ConfigXingjiContainer;
import game_server_parent.master.game.database.config.container.ConfigXingjiPRContainer;
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
    
    public ConfigSoilderLevelContainer configSoilderLevelContainer = new ConfigSoilderLevelContainer();
    
    public ConfigPinzhiContainer configPinzhiContainer = new ConfigPinzhiContainer();
    
    public ConfigBingzhongContainer configBingzhongContainer = new ConfigBingzhongContainer();
    
    public ConfigXingjiContainer configXingjiContainer = new ConfigXingjiContainer();
    
    public ConfigTreasuryContainer configTreasuryContainer = new ConfigTreasuryContainer();
    
    public ConfigTreasuryRewardContainer configTreasuryRewardContainer = new ConfigTreasuryRewardContainer();
    
    public ConfigPinzhiPRContainer configPinzhiPRContainer = new ConfigPinzhiPRContainer();
    
    public ConfigTreasuryBingzhongContainer configTreasuryBingzhongContainer = new ConfigTreasuryBingzhongContainer();
    public ConfigTreasuryVTContainer configTreasuryVTContainer = new ConfigTreasuryVTContainer();
    public ConfigBingzhongVTContainer configBingzhongVTContainer = new ConfigBingzhongVTContainer();
    
    public ConfigJiachengTypePRContainer configJiachengTypePRContainer = new ConfigJiachengTypePRContainer();
    public ConfigJiachengVTContainer configJiachengVTContainer = new ConfigJiachengVTContainer();
    public ConfigXingjiPRContainer configXingjiPRContainer = new ConfigXingjiPRContainer();
    public ConfigMallContainer configMallContainer = new ConfigMallContainer();
    
    public ConfigFubenContainer configFubenContainer = new ConfigFubenContainer();
    public ConfigFubenbossContainer configFubenbossContainer = new ConfigFubenbossContainer();
    public ConfigFubenlevelContainer configFubenlevelContainer = new ConfigFubenlevelContainer();
    
    public ConfigAiContainer configAiContainer = new ConfigAiContainer();
    
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
