package game_server_parent.master.game.gm.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.gm.GmConstant;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResGmResultMessage.java</p>
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
@MessageMeta(module=Modules.LOGIN,cmd=GmConstant.RES_GM_RESULT)
public class ResGmResultMessage extends Message {
    /** 执行失败 */
    public static final byte FAIL = 0;
    /** 执行成功 */
    public static final byte SUCC = 1;
    
    @Protobuf(order = 1)
    private int result;
    @Protobuf(order = 2)
    private String message;
    
    public ResGmResultMessage() {
        super();
    }

    private ResGmResultMessage(byte result, String message) {
        this.result  = result;
        this.message = message;
    }
    
    public static ResGmResultMessage buildSuccResult(String msg) {
        return new ResGmResultMessage(SUCC, msg);
    }
    
    public static ResGmResultMessage buildFailResult(String msg) {
        return new ResGmResultMessage(FAIL, msg);
    }

    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResGmResultMessage [result=" + result + ", message="
                        + message + "]";
    }
    
}
