package game_server_parent.master.game.fuben.message;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.storage.Fuben;
import game_server_parent.master.game.fuben.FubenDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResFubenListMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月7日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.FUBEN, cmd=FubenDataPool.RES_SELECT_FUBEN_LIST)
public class ResFubenListMessage extends Message {

    @Protobuf(fieldType = FieldType.OBJECT, order=1)
    private List<Fuben> fubens;

    public List<Fuben> getFubens() {
        return fubens;
    }

    public void setFubens(List<Fuben> fubens) {
        this.fubens = fubens;
    }
    
    @Override
    public String toString() {
        return "ResFubenListMessage [fubens count=" + fubens.size() +  "]";
    }
}
