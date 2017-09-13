package game_server_parent.master.game.gm.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;
import game_server_parent.master.game.Modules;
import game_server_parent.master.game.gm.GmConstant;

/**
 * <p>Filename:ReqGmExecMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.GM, cmd=GmConstant.REQ_GM_EXEC)
public class ReqGmExecMessage extends Message {

    @Protobuf(order = 1)
    public String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "ReqGmExecMessage [command=" + command + "]";
    }
}
