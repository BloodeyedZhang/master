package game_server_parent.master;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.lang3.time.StopWatch;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.Service;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.ai.AiManager;
import game_server_parent.master.game.core.SchedulerHelper;
import game_server_parent.master.game.core.SystemParameters;
import game_server_parent.master.game.crossrank.CrossRankService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.http.HttpServer;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.persistence.PlayerPeriodSaveService;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.ListenerManager;
import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.monitor.jmx.Controller;
import game_server_parent.master.monitor.jmx.ControllerMBean;
import game_server_parent.master.net.MessageFactory;
import game_server_parent.master.net.SocketServer;
import game_server_parent.master.net.context.TaskHandlerContext;
import game_server_parent.master.orm.OrmProcessor;
import game_server_parent.master.orm.utils.DbUtils;
import game_server_parent.master.redis.RedisCluster;
import game_server_parent.master.utils.ArrayUtils;
import game_server_parent.master.utils.TimeUtils;

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
    
    public void start() throws Exception {

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
    
    private void frameworkStart() throws Exception {
        //加载服务版本号
        ServerVersion.load();
        //初始化协议池
        MessageFactory.INSTANCE.initMeesagePool();
        //读取服务器配置
        ServerConfig.getInstance().initFromConfigFile();
        //初始化redis集群服务连接,在读取服务器配置之后
        RedisCluster.INSTANCE.init();
        //初始化orm框架
        OrmProcessor.INSTANCE.initOrmBridges();
        //初始化消息工作线程池
        TaskHandlerContext.INSTANCE.initialize();
        //初始化数据库连接池
        DbUtils.init();
        //初始化job定时任务
        SchedulerHelper.initAndStart();
        //读取所有策划配置
        ConfigDatasPool.getInstance().loadAllConfigs();
        //异步持久化服务
        DbService.getInstance().init();
/*        if(dbService.state() != Service.State.RUNNING) {
            logger.error("dbService start failed.");
           // throw new RuntimeException("SaveGameDataService start failed.");
        } else {
            logger.info("dbService was started.");
        }*/
        ListenerManager.INSTANCE.initalize();
        //读取系统参数
        loadSystemRecords();
        

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
    
    private void loadSystemRecords() throws Exception {
        SystemParameters.load();
        // 启动时检查每日重置
        long now = System.currentTimeMillis();
        if (now - SystemParameters.dailyResetTimestamp > 24 * TimeUtils.ONE_HOUR) {
            logger.info("启动时每日重置");
            SystemParameters.update("dailyResetTimestamp", now);
        }
    }


    private void gameLogicStart() {
        
        Service playerSaveService = PlayerPeriodSaveService.getInstance().startAsync();
        /*
        if(playerSaveService.state() != Service.State.RUNNING) {
            logger.error("playerSaveService start failed.");
            throw new RuntimeException("playerSaveService start failed.");
        } else {
            logger.info("playerSaveService was started.");
        }*/
        
        //初始化排行业务服务
        CrossRankService.getInstance();
        
        EventDispatcher.getInstance();
        
        // 初始化商城斐波那契数列
        ArrayUtils.initFeibonaqie();
        
        // 初始化商城抽卡兵种总权重
        
        // 初始化AI
        try {
            AiManager.getInstance().loadAll();
        } catch (Exception e) {
            logger.error("初始化AI ERROR：",e);
        }
        
        // 加载AI 放入online
        List<Player> loadAIs = PlayerManager.getInstance().loadAI();
        for(Player player : loadAIs) {
            PlayerManager.getInstance().add2Online(player);
        }
    }
    
    private void gameLogicShutDown() {
        ConcurrentMap<Long,Player> onlinePlayers = PlayerManager.getInstance().getOnlinePlayers();
        for(Player player : onlinePlayers.values()) {
            PlayerManager.getInstance().removeFromOnline(player);
        }
    }
    
    public void shutdown() {
        logger.error("游戏进程准备关闭");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //各种业务逻辑的关闭写在这里。。。
        gameLogicShutDown();
        socketServer.shutdown();
        httpServer.shutdown();
        
        Service dbService = DbService.getInstance().stopAsync();
        if(dbService.state() != Service.State.TERMINATED) {
            logger.error("dbService start failed.");
            throw new RuntimeException("SaveGameDataService stop failed.");
        } else {
            logger.info("dbService was terminated.");
        }
        
        stopWatch.stop();
        logger.error("游戏服务正常关闭，耗时[{}]毫秒", stopWatch.getTime());
    }
}
