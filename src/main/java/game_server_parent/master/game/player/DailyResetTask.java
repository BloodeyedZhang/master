package game_server_parent.master.game.player;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.net.context.TimerTask;

/**
 * <p>Filename:DailyResetTask.java</p>
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
public class DailyResetTask extends TimerTask {

    private Player player;
    
    /**
     * @param distributeKey
     * @param maxLoop
     */
    public DailyResetTask(int distributeKey, Player player) {
        super(distributeKey);
        this.player = player;
    }

    @Override
    public void action() {
        System.err.println("玩家"+player.getName()+"进行每日重置");
        
        PlayerManager.getInstance().checkDailyReset(player); 
    }

}
