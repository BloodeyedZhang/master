package game_server_parent.master.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.login.LoginDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResLoginMessage.java</p>
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

@MessageMeta(module=Modules.LOGIN, cmd=LoginDataPool.RES_LOGIN)
public class ResLoginMessage extends Message {
    
    @Protobuf(order = 1)
    private int code;
    @Protobuf(order = 2)
    private String tips;
    
    public ResLoginMessage() {
        
    }
    
    public ResLoginMessage(int resultCode, String tips) {
        this.code = resultCode;
        this.tips = tips;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "ResLoginMessage [code=" + code + ", tips=" + tips + "]";
    }
}
