package game_server_parent.master.game.heartBeat;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.heartBeat.events.EventHeartBeat;
import game_server_parent.master.game.heartBeat.message.ReqClientHeartBeatMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:HeartBeatController.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月15日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Controller
public class HeartBeatController {

    @RequestMapping
    public void reqClientHeartBeat(IoSession session, ReqClientHeartBeatMessage request) {
        EventDispatcher.getInstance().fireEvent(new EventHeartBeat(EventType.HEARTBEAT, session));
    }
}
