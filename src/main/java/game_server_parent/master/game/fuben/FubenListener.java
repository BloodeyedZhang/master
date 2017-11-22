package game_server_parent.master.game.fuben;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.game.team.TeamListener;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;

/**
 * <p>Filename:FubenListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月7日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class FubenListener {

    private Logger logger = LoggerFactory.getLogger(TeamListener.class);
    
    @EventHandler(value=EventType.PLAYER_CREATE)
    public void onPlayerCreate(EventNewPlayer event) {
        long player_id = event.getPlayerId();
        FubenManager.getInstance().createNewFuben(player_id);
    }
    
    @EventHandler(value=EventType.ENTER_DATING)
    public void onEnterDating(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(player_id);
        int fuben_map_id = player.getFuben_map_id();
        if(fuben_map_id>0) {
            FubenManager.getInstance().execute_win(fuben_map_id, player_id);
        }
    }
}
