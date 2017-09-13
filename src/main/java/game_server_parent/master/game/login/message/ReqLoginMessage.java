package game_server_parent.master.game.login.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.login.LoginDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqLoginMessage.java</p>
 * <p>Description: 请求－账号登录 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.LOGIN, cmd=LoginDataPool.REQ_LOGIN)
public class ReqLoginMessage extends Message {

    /** 账号流水号 */
    @Protobuf(order = 1)
    private long accountId;
    
    @Protobuf(order = 2)
    private String password;
    
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long playerId) {
        this.accountId = playerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ReqLoginMessage [accountId=" + accountId + ", password="
                + password + "]";
    }
}
