package game_server_parent.master;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Filename:ServerStartsup.java</p>
 * <p>Description: 服务端启动程序 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月24日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ServerStartup {
    private static Logger logger = LoggerFactory.getLogger(ServerStartup.class);

    public static void main(String args[]) {

        try{
            GameServer.getInstance().start();
        }catch(Exception e){
            logger.error("服务启动报错", e);
        }finally {
            //增加关闭钩子
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    GameServer.getInstance().shutdown();
                }
            }));
        }
    } 
}
