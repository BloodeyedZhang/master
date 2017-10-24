package game_server_parent.master.game.crossrank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.crossrank.events.EventBpUpdate;
import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.AttrChangeListener;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import groovyjarjarantlr.debug.Event;

/**
 * <p>Filename:CrossRankListener.java</p>
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
@Listener
public class CrossRankListener {
    
    private Logger logger = LoggerFactory.getLogger(CrossRankListener.class);

    @EventHandler(value= {EventType.CROSSRANK_BP_UPDATE})
    public void onCrossBonusPintsRank(EventBpUpdate event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(playerId);
        int bonusPints = player.getBonus_points();
        int treasury_level = player.getTreasuryLevel();
        String name = player.getName();
        CrossRankService.getInstance().addRank(new CrossBonusPointsRank(playerId, bonusPints, treasury_level, name));
    }
}
