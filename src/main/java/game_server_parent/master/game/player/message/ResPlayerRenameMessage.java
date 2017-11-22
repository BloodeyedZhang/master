package game_server_parent.master.game.player.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.player.PlayerDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResPlayerRenameMessage.java</p>
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
@MessageMeta(module=Modules.PLAYER, cmd=PlayerDataPool.RES_PLAYER_RENAME)
public class ResPlayerRenameMessage extends Message {

    
    @Protobuf(order=1)
    private int code;
    
    @Protobuf(order=2)
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "ResPlayerNameCheckMessage [code=" + code+ ", name="+name+ "]";
    }
}
