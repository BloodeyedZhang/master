package game_server_parent.master.game.player.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.player.PlayerDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqPlayerRenameMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.PLAYER, cmd=PlayerDataPool.REQ_PLAYER_RENAME)
public class ReqPlayerRenameMessage extends Message {

    @Protobuf(order=1)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "ReqPlayerRenameMessage [name=" + name+ "]";
    }
}
