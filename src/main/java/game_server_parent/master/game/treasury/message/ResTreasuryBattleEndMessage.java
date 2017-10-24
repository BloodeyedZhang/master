package game_server_parent.master.game.treasury.message;

import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.treasury.TreasuryDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResTreasuryBattleEndMessage.java</p>
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
@MessageMeta(module=Modules.TREASURY, cmd=TreasuryDataPool.RES_TREASURY_BATTLE_END)
public class ResTreasuryBattleEndMessage extends Message {
    @Protobuf(order=1)
    private int code;
    @Protobuf(order=2)
    private int coin;

    @Protobuf(order=3)
    private int diamond;
    
    @Protobuf(fieldType = FieldType.OBJECT, order=4)
    private List<Kapai> kapais = new ArrayList<Kapai>();

    @Override
    public String toString() {
        return "ResTreasuryBattleEndMessage [ code=" + code + ", coin=" + coin + ", diamond=" + diamond 
                + ", kapais count=" + kapais.size() 
                +"]";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    public List<Kapai> getKapais() {
        return kapais;
    }

    public void setKapais(List<Kapai> kapais) {
        this.kapais = kapais;
    }


}
