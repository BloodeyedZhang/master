package game_server_parent.master.game.kapai.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.kapai.KapaiDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqKapaiNewMessage.java</p>
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
@MessageMeta(module=Modules.KAPAI, cmd=KapaiDataPool.REQ_KAPAI_NEW)
public class ReqKapaiNewMessage extends Message {

    @Protobuf(order = 1)
    private int id;
    
    @Protobuf(order = 2)
    private int dalei;
    
    @Protobuf(order = 3)
    private int bingzhong;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDalei() {
        return dalei;
    }

    public void setDalei(int dalei) {
        this.dalei = dalei;
    }

    public int getBingzhong() {
        return bingzhong;
    }

    public void setBingzhong(int bingzhong) {
        this.bingzhong = bingzhong;
    }

    @Override
    public String toString() {
        return "ReqKapaiNewMessage [id=" + id+", dalei=" + dalei + ", bingzhong=" + bingzhong + "]";
    }
}
