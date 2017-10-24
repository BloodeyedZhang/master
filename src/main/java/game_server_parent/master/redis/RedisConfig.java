package game_server_parent.master.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import game_server_parent.master.ServerConfig;
import game_server_parent.master.utils.XmlUtils;

/**
 * <p>Filename:RedisConfig.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月11日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class RedisConfig {

    private Logger logger = LoggerFactory.getLogger(RedisConfig.class.getSimpleName());

    private static RedisConfig instance = new RedisConfig();
    
    public static RedisConfig getInstance() {
        return instance;
    }
    
    private String[] ips;
    
    private String pwd;
    
    public void initFromConfigFile() {
        String configFile = "redis.xml";
        Element rootElement = XmlUtils.loadConfigRootElement(configFile);
        NodeList nodes = rootElement.getChildNodes();
        for(int i=0; i<nodes.getLength(); i++ ) {
            Node node = nodes.item(i);
            if("redis_cluster".equals(node.getNodeName())) {
                NodeList subNodes = node.getChildNodes();
                for(int j=0;j<subNodes.getLength();j++) {
                    if ("pwd".equals(subNodes.item(j).getNodeName())) {
                        this.pwd = subNodes.item(j).getTextContent();
                    } else if ("ips".equals(subNodes.item(j).getNodeName())) {
                        logger.info("连接redis集群 ip为{}", subNodes.item(j).getTextContent());
                        String[] ips = subNodes.item(j).getTextContent().split(";");
                        this.ips = ips;
                    }
                }
            } 
        }
    }

    public String[] getIps() {
        return ips;
    }

    public String getPwd() {
        return pwd;
    }
}
