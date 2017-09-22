package game_server_parent.master.game.player.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResPlayerMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.PLAYER, cmd=PlayerDataPool.RES_PLAYER)
public class ResPlayerMessage extends Message {

    @Protobuf(order = 1)
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @Override
    public String toString() {
        return "ResPlayerMessage [player=" + player.toString()+ "]";
    }
}
