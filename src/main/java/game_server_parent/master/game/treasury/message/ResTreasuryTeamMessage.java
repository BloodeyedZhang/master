package game_server_parent.master.game.treasury.message;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.database.user.storage.Treasury;
import game_server_parent.master.game.treasury.TreasuryDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResTreasuryTeamMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月16日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.TREASURY, cmd=TreasuryDataPool.RES_TREASURY_TEAM)
public class ResTreasuryTeamMessage extends Message {
    @Protobuf(fieldType = FieldType.OBJECT,order=1)
    private RankSoilderTeam rst;
    
    @Protobuf(fieldType = FieldType.OBJECT,order=2)
    private List<Kapai> kapais;
    
    @Protobuf(order=3)
    private Treasury treasury;

    public RankSoilderTeam getRst() {
        return rst;
    }

    public void setRst(RankSoilderTeam rst) {
        this.rst = rst;
    }

    public List<Kapai> getKapais() {
        return kapais;
    }

    public void setKapais(List<Kapai> kapais) {
        this.kapais = kapais;
    }

    public Treasury getTreasury() {
        return treasury;
    }

    public void setTreasury(Treasury treasury) {
        this.treasury = treasury;
    }
    
    @Override
    public String toString() {
        return "ResTreasuryTeamMessage [kapais count=" + kapais.size() +  "]";
    }
}
