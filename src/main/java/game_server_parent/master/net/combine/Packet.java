package game_server_parent.master.net.combine;

import java.io.IOException;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.MessageFactory;

/**
 * <p>Filename:Packet.java</p>
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
public class Packet {
    @Protobuf(order = 10)
    private int module;
    @Protobuf(order = 11)
    private int cmd;
    @Protobuf(order = 12,fieldType = FieldType.BYTES)
    /** 序列化的消息体 */
    private byte[] body ;

    public Packet(){

    }
    
    public static Packet valueOf(Message message) {
        Packet packet  = new Packet();
        packet.module = message.getModule();
        packet.cmd        = message.getCmd();
        try {
            Codec codec = ProtobufProxy.create(message.getClass());
            packet.body = codec.encode(message);
        }catch (Exception e){
            LoggerUtils.error("生成Packet出错", e);
            throw new IllegalArgumentException("parse packet attachment failed",e);
        }
        
        return packet;
    }
    
    public static Message asMessage(Packet packet) {
        Class<?> msgClazz = MessageFactory.INSTANCE.getMessage((short)packet.module,  (short)packet.cmd);
        try {
            Codec<?> codec = ProtobufProxy.create(msgClazz);
            Message message = (Message) codec.decode(packet.body);
            return message;
        } catch (IOException e) {
            LoggerUtils.error("读取消息出错,模块号{}，类型{},异常{}",  packet.module,  packet.cmd );
        }
        return null;
    }
}
