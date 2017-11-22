package game_server_parent.master.game.treasury.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.treasury.TreasuryDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqTreasuryaddkeyMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月10日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.TREASURY, cmd=TreasuryDataPool.REQ_TREASURY_ADD_KEY)
public class ReqTreasuryaddkeyMessage extends Message {

    @Protobuf(order=1)
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
