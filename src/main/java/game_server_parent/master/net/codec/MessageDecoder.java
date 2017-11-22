package game_server_parent.master.net.codec;

import java.io.IOException;
import java.util.List;

import game_server_parent.master.logs.LoggerSystem;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.MessageFactory;
import game_server_parent.master.net.SessionManager;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.combine.CombineMessage;
import game_server_parent.master.net.combine.Packet;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

/**
 * <p>Filename:MessageDecoder.java</p>
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
public class MessageDecoder implements ProtocolDecoder {
    private static final Logger logger = LoggerSystem.NET.getLogger();

    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        _decode(session, in, out);

    }

    private void _decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) {
        //必须保证每一个数据包的字节缓存都和session绑定在一起，不然就读取不了上一次剩余的数据了
        logger.info("_decode");
        CodecContext context = SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.CODEC_CONTEXT, CodecContext.class);
        if (context == null) {
            context = new CodecContext();
            session.setAttribute(SessionProperties.CODEC_CONTEXT, context);
        }
        IoBuffer ioBuffer = context.getBuffer();
        ioBuffer.put(in);

        //在循环里迭代，以处理数据粘包
        for (; ;) {
            ioBuffer.flip();
            //常量4表示消息body前面的两个short字段，一个表示moduel，一个表示cmd,
            //一个short字段有两个字节，总共4个字节
            if (ioBuffer.remaining() < 4) {
                ioBuffer.compact();
                return;
            }
            //----------------消息协议格式-------------------------
            // packetLength | moduleId | cmd   |  body
            // int            short      short   byte[]
            int length = ioBuffer.getInt();
            int remaining = ioBuffer.remaining();
            //int packLen = length + 4;
            //大于消息body长度，说明至少有一条完整的message消息
            if (ioBuffer.remaining() >= length) {
                short moduleId =  ioBuffer.getShort();
                short cmd = ioBuffer.getShort();
                byte[] body = new byte[length-4];
                ioBuffer.get(body);

                Message msg = readMessage(moduleId, cmd, body);
                
                if (moduleId > 0) {  
                    out.write(msg);  
                } else { //属于组合包  
                    CombineMessage combineMessage = (CombineMessage)msg;  
                    List<Packet> packets = combineMessage.getPackets();  
                    for (Packet packet :packets) {  
                        //依次拆包反序列化为具体的Message  
                        out.write(Packet.asMessage(packet));  
                    }  
                }

                if (ioBuffer.remaining() == 0) {
                    ioBuffer.clear();
                    break;
                }
                ioBuffer.compact();
            } else{
                //数据包不完整，继续等待数据到达
                ioBuffer.rewind();
                ioBuffer.compact();
                break;
            }
        }
    }

    private Message readMessage(short module, short cmd, byte[] body) {
        Class<?> msgClazz = MessageFactory.INSTANCE.getMessage(module, cmd);
        try {
            Codec<?> codec = ProtobufProxy.create(msgClazz);
            Message message = (Message) codec.decode(body);
            return message;
        } catch (Exception e) {
            logger.error("读取消息出错,模块号{}，类型{},异常{}", new Object[]{module, cmd ,e});
        }
        return null;
    }

    public void dispose(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub

    }

    public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1) throws Exception {
        // TODO Auto-generated method stub

    }
}
