package game_server_parent.master.game.treasury.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.treasury.TreasuryDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResTreasuryUpdate.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.TREASURY, cmd=TreasuryDataPool.RES_TREASURY_UPDATE)
public class ResTreasuryUpdateMessage extends Message {

    @Protobuf(order=1)
    private int coin;
    @Protobuf(order=2)
    private int diamond;
    @Protobuf(order=3)
    private String army;
    public int getCoin() {
        return coin;
    }
    public void setCoin(int coin) {
        this.coin = coin;
    }
    public int getDiamond() {
        return diamond;
    }
    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }
    public String getArmy() {
        return army;
    }
    public void setArmy(String army) {
        this.army = army;
    }
    
    @Override
    public String toString() {
        return "ResTreasuryUpdateMessage [coin=" + coin
                + ", diamond=" + diamond
                + ", army=" + army 
                + "]";
    }
}
