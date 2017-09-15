package game_server_parent.master.game.kapai.message;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.kapai.KapaiDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqKapaiUpdateMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.KAPAI, cmd=KapaiDataPool.REQ_KAPAI_UPDATE)
public class ReqKapaiUpdateMessage extends Message {
    @Protobuf(fieldType = FieldType.OBJECT,order=1)
    private List<Kapai> kapais;
    
    public  ReqKapaiUpdateMessage() {}
    
    public ReqKapaiUpdateMessage(List<Kapai> kapais) {
        this.kapais = kapais;
    }

    public List<Kapai> getKapais() {
        return kapais;
    }

    public void setKapais(List<Kapai> kapais) {
        this.kapais = kapais;
    }
    
    @Override
    public String toString() {
        return "ReqKapaiUpdateMessage [kapais count=" + kapais.size() +  "]";
    }
}
