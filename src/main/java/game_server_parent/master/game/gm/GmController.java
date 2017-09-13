package game_server_parent.master.game.gm;

import game_server_parent.master.game.gm.message.ReqGmExecMessage;
import game_server_parent.master.game.gm.message.ResGmResultMessage;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:GmController.java</p>
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
@Controller
public class GmController {

    @RequestMapping
    public void reqExecGm(long playerId, ReqGmExecMessage msg) {
        GmManager.getInstance().receiveCommand(playerId, msg.command);
    }
    
    @RequestMapping
    public void resResultGm(ResGmResultMessage reps) {
        System.out.println("收到GM执行消息"+reps.toString());
    }
}
