package game_server_parent.master.client;

import game_server_parent.master.ServerConfig;
import game_server_parent.master.game.gm.message.ReqGmExecMessage;
import game_server_parent.master.listener.ListenerManager;
import game_server_parent.master.net.MessageFactory;
import game_server_parent.master.net.context.TaskHandlerContext;
import game_server_parent.master.robot.SocketRobot;

/**
 * <p>Filename:ClientStartup.java</p>
 * <p>Description: 客户端启动程序 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ClientStartup {
    public static void main(String[] args) throws InterruptedException {
        //初始化协议池  
        MessageFactory.INSTANCE.initMeesagePool();
      //读取服务器配置
        ServerConfig.getInstance().initFromConfigFile();
        //初始化消息工作线程池
        TaskHandlerContext.INSTANCE.initialize();
        //加载监听器
        ListenerManager.INSTANCE.initalize();
        
        SocketRobot robot = new SocketRobot("kingston");
        robot.buildConnection();
        robot.login();
//        robot.selectedPlayer(10000L);
//        
//        Thread.sleep(3000L);
//        ReqGmExecMessage reqGm = new ReqGmExecMessage();
//        reqGm.command = "playerLv 2";
//        robot.sendMessage(reqGm);
    }
}
