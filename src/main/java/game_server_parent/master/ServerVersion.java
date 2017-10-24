package game_server_parent.master;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Filename:ServerVersion.java</p>
 * <p>Description: 服务版本号 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月10日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ServerVersion {

    private static Logger logger = LoggerFactory.getLogger(ServerVersion.class);
    
    private static final String CONFIG_PATH = "configs/version.properties";

    private static int BIG_VERSION;
    private static int SMALL_VERSION;
    
    public static void load() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIG_PATH));
        String version = properties.getProperty("server.version");
        try {
            String[] splits = version.trim().split("\\.");
            BIG_VERSION = Integer.parseInt(splits[0].trim());
            if (splits.length == 2) {
                SMALL_VERSION = Integer.parseInt(splits[1].trim());
            }
            logger.info("加载服务版本号成功, bigVersion={}, smallVersion={}", BIG_VERSION, SMALL_VERSION);
        } catch (Exception e) {
            throw new Exception("服务器版本号解析异常", e);
        }
    }
    
    public static int getBigVersion() {
        return BIG_VERSION;
    }

    public static int getSmallVersion() {
        return SMALL_VERSION;
    }
}
