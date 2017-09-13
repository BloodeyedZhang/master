package game_server_parent.master.net;

import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.net.dispatch.MessageDispatcher;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Filename:ServerSocketIoHandler.java</p>
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
public class ServerSocketIoHandler extends IoHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ServerSocketIoHandler.class);
    @Override 
    public void sessionCreated(IoSession session) { 
        //显示客户端的ip和端口 
        //System.out.println(session.getRemoteAddress().toString()); 
        logger.info("打开一个客户端连接{}",session.getRemoteAddress().toString());
        session.setAttributeIfAbsent(SessionProperties.DISTRIBUTE_KEY,
                SessionManager.INSTANCE.getNextDistributeKey());
    } 
    
    @Override 
    public void messageReceived(IoSession session, Object data ) throws Exception 
    { 
        Message message = (Message)data;
        System.err.println("收到消息-->" + message); 
        logger.info("收到消息-->" + message);
        //交由消息分发器处理
        MessageDispatcher.getInstance().dispatch(session, message);
        
    } 
    
     public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
         LoggerUtils.error("客户端连接错误{}， server exception {}", session.getRemoteAddress().toString(), cause.getLocalizedMessage());
     }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("客户端{} 断开连接", session.getRemoteAddress().toString());
    }
}
