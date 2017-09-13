package game_server_parent.master.net.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * <p>Filename:MessageCodecFactory.java</p>
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
public class MessageCodecFactory implements ProtocolCodecFactory {

    private static MessageCodecFactory instance = new MessageCodecFactory();
    
    private MessageDecoder decoder;
    
    private MessageEncoder encoder;
    
    private MessageCodecFactory() {
        decoder = new MessageDecoder();
        encoder = new MessageEncoder();
    }
    
    public static MessageCodecFactory getInstance() {
        return instance;
    }
    
    @Override
    public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        return decoder;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        return encoder;
    }
}
