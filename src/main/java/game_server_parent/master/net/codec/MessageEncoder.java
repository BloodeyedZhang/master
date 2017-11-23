package game_server_parent.master.net.codec;

import java.io.IOException;

import game_server_parent.master.logs.LoggerSystem;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.MessageFactory;
import game_server_parent.master.net.SessionProperties;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

/**
 * <p>Filename:MessageEncoder.java</p>
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
public class MessageEncoder implements ProtocolEncoder {

    private static final Logger logger = LoggerSystem.NET.getLogger();
    
    @Override
    public void dispose(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        // TODO Auto-generated method stub
        _encode(session, message, out);
    }
    
    public void _encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        logger.info("_encode");
        CodecContext context = (CodecContext) session.getAttribute(SessionProperties.CODEC_CONTEXT);
        if (context == null) {
            context = new CodecContext();
            session.setAttribute(SessionProperties.CODEC_CONTEXT, context);
        }
        IoBuffer buffer = writeMessage((Message) message);
        out.write(buffer);
    }

    private IoBuffer writeMessage(Message message) {
        //----------------消息协议格式-------------------------
        // packetLength | moduleId | cmd   |  body
        // int            short      short   byte[]

        IoBuffer buffer = IoBuffer.allocate(CodecContext.WRITE_CAPACITY);
        buffer.setAutoExpand(true);

        //消息内容长度，先占个坑
        buffer.putInt(0);
        short moduleId = message.getModule();
        short cmd = message.getCmd();
        //写入module类型
        buffer.putShort(moduleId);
        //写入cmd类型
        buffer.putShort(cmd);

        //写入具体消息的内容
        byte[] body = null;

        @SuppressWarnings("unchecked")
        Class<Message> msgClazz = (Class<Message>) MessageFactory.INSTANCE.getMessage(moduleId, cmd);
        try {
            System.out.println("moduleId:"+moduleId+";cmd:"+cmd+"msgClazz:"+msgClazz);
            Codec<Message> codec = ProtobufProxy.create(msgClazz);
            body = codec.encode(message);
            logger.info("_encode moduleId:"+moduleId+";cmd:"+cmd+"msgClazz:"+msgClazz +"; body_length:"+body.length);
        } catch (IOException e) {
            e.printStackTrace();
            //logger
        }
        buffer.put(body);
        //回到buff字节数组头部
        buffer.flip();
        //重新写入包体长度
        buffer.putInt(buffer.limit()-4);
        buffer.rewind();

        return buffer;
    }
}
