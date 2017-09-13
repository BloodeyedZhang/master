package game_server_parent.master.net.combine;

import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:CombineMessage.java</p>
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
@MessageMeta(module=0, cmd=0)
public final class CombineMessage extends Message {

    @Protobuf(order = 1)
    private final List<Packet> packets = new ArrayList<>();

    public CombineMessage(){

    }

    /**
     * 添加新的消息对象
     * @param message
     */
    public void addMessage(Message message){
        this.packets.add(Packet.valueOf(message));
    }

    public List<Packet> getPackets() {
        return packets;
    }

    public int getCacheSize(){
        return this.packets.size();
    }
    
}
