package game_server_parent.master.game.crossrank.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.crossrank.CrossRankDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqCrossUUIDMessage.java</p>
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
@MessageMeta(module=Modules.CROSS_RANK, cmd=CrossRankDataPool.REQ_CrossRank_UUID)
public class ReqCrossUUIDMessage extends Message {

    @Protobuf(order=1)
    private int platform; // android:1;ios:2;
    
    @Protobuf(order=2)
    private String code;
    
    @Override
    public String toString() {
        return "ReqCrossUUIDMessage [platform="+ platform +", code=" + code + "]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

}
