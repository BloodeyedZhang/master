package game_server_parent.master.game.team.message;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
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
@MessageMeta(module=Modules.SOILDER_TEAM, cmd=TeamDataPool.RES_PLAYER_TEAM_LIST)
public class ResPlayerTeamMessage extends Message {
    
    @Protobuf(fieldType = FieldType.OBJECT,order=1)
    private List<SoilderTeam> teamList;
    
    public ResPlayerTeamMessage() {}
    
    public ResPlayerTeamMessage(List<SoilderTeam> teamList) {
        this.teamList = teamList;
    }
    
    public List<SoilderTeam> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<SoilderTeam> teamList) {
        this.teamList = teamList;
    }

    @Override
    public String toString() {
        return "ResPlayerTeamMessage [count=" + teamList.size()+ "]";
    }

}
