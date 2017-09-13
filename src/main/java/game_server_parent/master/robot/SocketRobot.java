package game_server_parent.master.robot;

import java.net.InetSocketAddress;

import game_server_parent.master.ServerConfig;
import game_server_parent.master.game.login.message.ReqLoginMessage;
import game_server_parent.master.game.login.message.ReqSelectPlayerMessage;
import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.SessionManager;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.codec.MessageCodecFactory;
import game_server_parent.master.net.dispatch.MessageDispatcher;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Filename:SocketRobot.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class SocketRobot {
    private Logger logger = LoggerFactory.getLogger(SocketRobot.class);
    private String name;

    private IoSession session;

    public SocketRobot(String name) {
        this.name = name;
    }

    public void buildConnection() {
        NioSocketConnector connector = new NioSocketConnector();  
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(MessageCodecFactory.getInstance()));  
        connector.setHandler(new ClientHandler());  
  
        int port = ServerConfig.getInstance().getServerPort();
        
        logger.info("开始连接socket服务端{}",port);
        ConnectFuture future = connector.connect(new InetSocketAddress(port));  
          
        future.awaitUninterruptibly();  
  
        IoSession session = future.getSession();  
        this.session = session;  
        session.setAttributeIfAbsent(SessionProperties.DISTRIBUTE_KEY,
                SessionManager.INSTANCE.getNextDistributeKey());

    }

    public void login() {
        ReqLoginMessage request = new ReqLoginMessage();
        request.setPassword("winturn");
        request.setAccountId(10000L);
        this.sendMessage(request);
    }


    public void selectedPlayer(long playerId) {
        ReqSelectPlayerMessage request = new ReqSelectPlayerMessage();
        request.setPlayerId(playerId);
        this.sendMessage(request);
    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(Message message) {
        this.session.write(message);
    }

    private class ClientHandler extends IoHandlerAdapter {
        public void messageReceived(IoSession session, Object data) {
            Message message = (Message)data;
            System.out.println("收到响应-->"+session+";" + message); 
            MessageDispatcher.getInstance().dispatch(session, message);
        }

        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            LoggerUtils.error("client exception", cause);
        }
    }
}
