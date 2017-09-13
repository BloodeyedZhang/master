package game_server_parent.master;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.http.HttpServer;
import game_server_parent.master.listener.ListenerManager;
import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.monitor.jmx.Controller;
import game_server_parent.master.monitor.jmx.ControllerMBean;
import game_server_parent.master.net.MessageFactory;
import game_server_parent.master.net.SocketServer;
import game_server_parent.master.net.context.TaskHandlerContext;
import game_server_parent.master.orm.OrmProcessor;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:GameServer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月12日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class GameServer {
    private static Logger logger = LoggerFactory.getLogger(GameServer.class);

    private static GameServer gameServer = new GameServer();

    private SocketServer socketServer;
    
    private HttpServer httpServer;

    public static GameServer getInstance() {
        return gameServer;
    }
    
    public void start() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //游戏框架服务启动
        frameworkStart();
        //游戏业务初始化
        gameLogicStart();

        stopWatch.stop();
        logger.error("游戏服务启动，耗时[{}]毫秒", stopWatch.getTime());

        //mbean监控
        try{
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();  
            ControllerMBean controller = new Controller();    
            //将MBean注册到MBeanServer中    
            mbs.registerMBean(controller, new ObjectName("GameMBean:name=controller")); 
        }catch(Exception e){
            LoggerUtils.error("register mbean failed ", e);
        }

    }
    
    private void frameworkStart() {
        //初始化协议池
        MessageFactory.INSTANCE.initMeesagePool();
        //读取服务器配置
        ServerConfig.getInstance().initFromConfigFile();
        //初始化orm框架
        OrmProcessor.INSTANCE.initOrmBridges();
        //初始化消息工作线程池
        TaskHandlerContext.INSTANCE.initialize();
        //初始化数据库连接池
        DbUtils.init();
        //读取所有策划配置
        ConfigDatasPool.getInstance().loadAllConfigs();
        //异步持久化服务
        DbService.getInstance().init();
        ListenerManager.INSTANCE.initalize();

        //启动socket服务
        try{
            socketServer = new SocketServer();
            socketServer.start();
        }catch(Exception e) {
            LoggerUtils.error("ServerStarter failed ", e);
        }
        //启动http服务
        try{
            httpServer = new HttpServer();
            httpServer.start();
        }catch(Exception e) {
            LoggerUtils.error("HttpServer failed ", e);
        }
    }


    private void gameLogicStart() {

    }
    
    public void shutdown() {
        logger.error("游戏进程准备关闭");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //各种业务逻辑的关闭写在这里。。。
        socketServer.shutdown();
        httpServer.shutdown();
        
        stopWatch.stop();
        logger.error("游戏服务正常关闭，耗时[{}]毫秒", stopWatch.getTime());
    }
}
