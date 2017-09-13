package game_server_parent.master.utils;



import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Filename:XmlUtils.java</p>
 * <p>Description:xml相关工具栏 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class XmlUtils {
    private static Logger logger = LoggerFactory.getLogger(XmlUtils.class.getSimpleName());
    
    private static final String ROOT_CONFIG_PATH = "configs"+ File.separator;
    
    public static Document loadConfigFile(String cfgFile) {
        Document dom = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
//            InputStream is = new FileInputStream(new File(cfgFile));  
            dom = db.parse(cfgFile);
        } /*catch (ParserConfigurationException | SAXException pce) {
            logger.error("Parse xml config file error.", pce);
        }*/ catch (IOException ioe) {
            ioe.printStackTrace();
            logger.error("Can't open configuration file.", ioe);
        } catch(Exception e){
            logger.error("Parse xml config file error.", e);
        }
//        NodeList users = dom.getChildNodes();
//        System.out.println(users.getLength());
//        for(int i=0;i<users.getLength();i++){
//            NodeList childNodes = users.item(i).getChildNodes();
//            System.out.println(childNodes.getLength());
//        }
        return dom;
    }
    
    /**
     * 读取xml配置文件的根节点（注意文件的配置路径）
     * @param configFile
     * @return
     */
    public static Element loadConfigRootElement(String configFile) {
        //必须在JavaBuildPath-->Source 路径把 src/main/resource的输出目录设 跟项目src同级别的configs目录下
        Document configDoc = loadConfigFile(ROOT_CONFIG_PATH + configFile);
        return configDoc.getDocumentElement();
    }
}
