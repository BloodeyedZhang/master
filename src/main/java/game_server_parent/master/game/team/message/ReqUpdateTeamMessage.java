package game_server_parent.master.game.team.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.team.TeamDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqPlayerTeamMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.SOILDER_TEAM, cmd=TeamDataPool.REQ_UPDATE_TEAM)
public class ReqUpdateTeamMessage extends Message {
    @Protobuf(order=1)
    private int teamId;
    
    @Protobuf(order=2)
    private String soilderIds;
    
    @Override
    public String toString() {
        return "ReqUpdateTeamMessage [teamId=" + teamId+", soilderIds=" + soilderIds + "]";
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getSoilderIds() {
        return soilderIds;
    }

    public void setSoilderIds(String soilderIds) {
        this.soilderIds = soilderIds;
    }
}
