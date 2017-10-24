package game_server_parent.master.game.treasury.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.treasury.TreasuryDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqTreasuryUpdateShow.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.TREASURY, cmd=TreasuryDataPool.REQ_TREASURY_UPATE_SHOW)
public class ReqTreasuryUpdateShowMessage extends Message {

    @Protobuf(order=1)
    private int treasury_level;

    public int getTreasury_level() {
        return treasury_level;
    }

    public void setTreasury_level(int treasury_level) {
        this.treasury_level = treasury_level;
    }
    
    @Override
    public String toString() {
        return "ReqTreasuryUpdateShowMessage [treasury_level="+treasury_level + "]";
    }
}
