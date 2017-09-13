package game_server_parent.master.game.skill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.player.events.EventPlayerLevelUp;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;

/**
 * <p>Filename:SkillListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class SkillListener {
    
    private Logger logger = LoggerFactory.getLogger(SkillListener.class);

    @EventHandler(value=EventType.LEVEL_UP)
    public void onPlayerLevelup(EventPlayerLevelUp levelUpEvent) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+levelUpEvent);
        System.err.println(getClass().getSimpleName()+"捕捉到事件"+levelUpEvent);
    }
}
