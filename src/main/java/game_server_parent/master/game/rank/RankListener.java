package game_server_parent.master.game.rank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.team.TeamListener;
import game_server_parent.master.game.team.event.EventSoilderTeamUpdate;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;

/**
 * <p>Filename:RamkListener.java</p>
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
@Listener
public class RankListener {
    private Logger logger = LoggerFactory.getLogger(RankListener.class);

    @EventHandler(value=EventType.SOILDER_TEAM_UPDATE)
    public void onTeamUpdate(EventSoilderTeamUpdate event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        SoilderTeam st = event.getSt();
        RankSoilderTeamManager.getInstance().updateSoilderTeam(st, player_id);
    }
}
