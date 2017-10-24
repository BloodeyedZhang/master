package game_server_parent.master.game.crossrank.message;

import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.crossrank.CrossRankDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResCrossBpRankMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月19日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.CROSS_RANK, cmd=CrossRankDataPool.RES_CrossRank_BP)
public class ResCrossBpRankMessage extends Message {

    @Protobuf(order=1)
    private CrossBpRank player_cbr;
    @Protobuf(fieldType = FieldType.OBJECT, order=2)
    private List<CrossBpRank> cbrs = new ArrayList<CrossBpRank>();
    
    public ResCrossBpRankMessage() {}
    
    public ResCrossBpRankMessage(CrossBpRank player_cbr, List<CrossBpRank> cbrs) {
        super();
        this.player_cbr = player_cbr;
        this.cbrs = cbrs;
    }

    public CrossBpRank getPlayer_cbr() {
        return player_cbr;
    }

    public void setPlayer_cbr(CrossBpRank player_cbr) {
        this.player_cbr = player_cbr;
    }

    public List<CrossBpRank> getCbrs() {
        return cbrs;
    }

    public void setCbrs(List<CrossBpRank> cbrs) {
        this.cbrs = cbrs;
    }
    
    @Override
    public String toString() {
        return "ResCrossBpRankMessage [cbrs.count=" + cbrs.size() + ", player_cbr=" + player_cbr.toString() + "]";
    }
}
