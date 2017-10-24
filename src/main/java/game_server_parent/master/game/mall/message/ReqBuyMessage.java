package game_server_parent.master.game.mall.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.mall.MallDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqBuyMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.MALL, cmd=MallDataPool.REQ_Mall_BUY)
public class ReqBuyMessage extends Message {

    @Protobuf(order=1)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "ReqBuyMessage [id=" + id+ "]";
    }
}
