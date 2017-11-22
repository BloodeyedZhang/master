package game_server_parent.master.game.fuben.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.fuben.FubenDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqBattleEndMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月9日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.FUBEN, cmd=FubenDataPool.REQ_BATTLE_END)
public class ReqBattleEndMessage extends Message {
    @Protobuf(order=1)
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ReqBattleEndMessage [ code=" + code +  "]";
    }
}
