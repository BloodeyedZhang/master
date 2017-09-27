package game_server_parent.master.game.rank.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.rank.RankDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResRankSoilderTeam.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月21日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.RANK, cmd=RankDataPool.REQ_RANK_BATTLE_END)
public class ReqRankBattleEndMessage extends Message {

    @Protobuf(order=1)
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ReqRankBattleEndMessage [ code=" + code +  "]";
    }
}
