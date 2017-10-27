package game_server_parent.master.net;

import game_server_parent.master.ServerConfig;
import game_server_parent.master.net.codec.MessageCodecFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioProcessor;
import org.apache.mina.transport.socket.nio.NioSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Filename:SocketServer.java</p>
 * <p>Description: 网关服务器
 * 1. 创建NioSocketAcceptor，用于监听客户端连接；
 * 2. 指定通信编解码处理器；
 * 3. 指定处理业务逻辑器，主要是接受消息之后的业务逻辑；
 * 4. 指定监听端口，启动NioSocket服务
 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月24日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class SocketServer {

    private Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private static final int CPU_CORE_SIZE = Runtime.getRuntime().availableProcessors();

    private static final Executor executor = Executors.newCachedThreadPool();

    private static final SimpleIoProcessorPool<NioSession> pool = new SimpleIoProcessorPool<NioSession>(
            NioProcessor.class, executor, CPU_CORE_SIZE);

    private NioSocketAcceptor acceptor;
    
    private int serverPort = ServerConfig.getInstance().getServerPort();

    /**
     * 开始启动mina serversocket
     * @throws IOException 
     */
    public void start() throws IOException {

        IoBuffer.setUseDirectBuffer(false);
        IoBuffer.setAllocator(new SimpleBufferAllocator());

        acceptor = new NioSocketAcceptor(pool);
        acceptor.setReuseAddress(true);
        acceptor.getSessionConfig().setAll(getSessionConfig());
        
        logger.info("socket启动端口为{},正在监听客户端的连接", serverPort);  
        DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();  
        filterChain.addLast("codec", new ProtocolCodecFilter(MessageCodecFactory.getInstance()));
        acceptor.setHandler( new ServerSocketIoHandler() );//指定业务逻辑处理器   
        acceptor.setDefaultLocalAddress(new InetSocketAddress(serverPort) );//设置端口号   
        acceptor.bind();//启动监听 
    }
    
    private SocketSessionConfig getSessionConfig() {
        SocketSessionConfig config = new DefaultSocketSessionConfig();
        config.setKeepAlive(true);
        config.setIdleTime(IdleStatus.BOTH_IDLE, 30);
        config.setReuseAddress(true);
        config.setTcpNoDelay(true);

        return config;
    }
    
    public void shutdown() {
        if (acceptor != null) {
            acceptor.unbind();
            acceptor.dispose();
        }
        logger.error("---------> socket server stop at port:{}", serverPort);
    }
}
