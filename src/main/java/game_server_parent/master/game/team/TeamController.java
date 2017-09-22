package game_server_parent.master.game.team;

import java.util.List;

import org.apache.mina.core.session.IoSession;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.kapai.events.EventKapaiUpdate;
import game_server_parent.master.game.team.event.EventSoilderTeamUpdate;
import game_server_parent.master.game.team.message.ReqPlayerTeamMessage;
import game_server_parent.master.game.team.message.ReqQuickBattleTeamMessage;
import game_server_parent.master.game.team.message.ReqUpdateTeamMessage;
import game_server_parent.master.game.team.message.ResPlayerTeamMessage;
import game_server_parent.master.game.team.message.ResQuickBattleTeamMessage;
import game_server_parent.master.game.team.message.ResUpdateTeamMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:TeamController.java</p>
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
@Controller
public class TeamController {

    @RequestMapping
    public void reqPlayerTeam(IoSession session, ReqPlayerTeamMessage request) {
        List<SoilderTeam> playerTeamList = TeamManager.getInstance().getPlayerTeamList((Long)session.getAttribute(SessionProperties.PLAYER_ID));
        
        MessagePusher.pushMessage(session, new ResPlayerTeamMessage(playerTeamList));
    }
    
    @RequestMapping
    public void reqUpdatePlayerTeam(IoSession session, ReqUpdateTeamMessage request) {
        SoilderTeam soilderTeam = TeamManager.getInstance().get((long)request.getTeamId());
        soilderTeam.setSoilderIds(request.getSoilderIds());
        
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        EventDispatcher.getInstance().fireEvent(new EventSoilderTeamUpdate(EventType.SOILDER_TEAM_UPDATE, player_id, soilderTeam));
        
        ResUpdateTeamMessage resUpdateTeamMessage = new ResUpdateTeamMessage();
        resUpdateTeamMessage.setSoilderIds(soilderTeam.getSoilderIds());
        resUpdateTeamMessage.setTeamId(soilderTeam.getTeam_id());
        resUpdateTeamMessage.setShengmingzhi(soilderTeam.getShengmingzhi());
        resUpdateTeamMessage.setGongjizhi(soilderTeam.getGongjizhi());
        MessagePusher.pushMessage(session, resUpdateTeamMessage);
    }
    
    @RequestMapping
    public void reqQuickBattleTeam(IoSession session, ReqQuickBattleTeamMessage request) {
        //TODO 获取一只快速对战用的地方队伍
        
        
        /*
        ResQuickBattleTeamMessage res = new ResQuickBattleTeamMessage();
        res.setTeamId(teamId);
        res.setSoilderIds(soilderIds);
        res.setKapais(kapais);
        MessagePusher.pushMessage(session, res);
        */
    }
}
