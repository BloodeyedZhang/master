package game_server_parent.master.game.crossrank.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.crossrank.CrossRankDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResCrossUUIDMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月31日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.CROSS_RANK, cmd=CrossRankDataPool.RES_CrossRank_UUID)
public class ResCrossUUIDMessage extends Message {

    @Protobuf(order=1)
    private int code;
    
    public ResCrossUUIDMessage() {
        super();
    }
    
    public ResCrossUUIDMessage(int code) {
        this();
        this.code = code;
    }
    
    @Override
    public String toString() {
        return "ResCrossUUIDMessage [code=" + code + "]";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
