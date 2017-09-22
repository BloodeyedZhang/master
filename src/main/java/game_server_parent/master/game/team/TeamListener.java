package game_server_parent.master.game.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.game.team.event.EventSoilderTeamUpdate;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;

/**
 * <p>Filename:TeamListener.java</p>
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
@Listener
public class TeamListener {
    private Logger logger = LoggerFactory.getLogger(TeamListener.class);
    
    @EventHandler(value=EventType.PLAYER_CREATE)
    public void onPlayerNew(EventNewPlayer event) {
        long playerId = event.getPlayerId();
        SoilderTeam st1 = TeamManager.getInstance().createNewKapai(playerId);
        SoilderTeam st2 = TeamManager.getInstance().createNewKapai(playerId);
        SoilderTeam st3 = TeamManager.getInstance().createNewKapai(playerId);
        
        DbService.getInstance().add2Queue(st1);
        DbService.getInstance().add2Queue(st2);
        DbService.getInstance().add2Queue(st3);
    }
    
    @EventHandler(value=EventType.SOILDER_TEAM_UPDATE)
    public void onSoilderTeamUpdate(EventSoilderTeamUpdate event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        SoilderTeam st = event.getSt();
        TeamManager.getInstance().updateTeam(st);
    }
}
