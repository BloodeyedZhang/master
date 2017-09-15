package game_server_parent.master.game.login;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.login.events.EventLogin;
import game_server_parent.master.game.login.events.EventReLogin;
import game_server_parent.master.game.login.message.ReqLoginMessage;
import game_server_parent.master.game.login.message.ReqReLoginMessage;
import game_server_parent.master.game.login.message.ReqSelectPlayerMessage;
import game_server_parent.master.game.login.message.ResLoginMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:LoginController.java</p>
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
public class LoginController {

    @RequestMapping
    public void reqAccountLogin(IoSession session, ReqLoginMessage request) {
        //LoginManager.getInstance().handleAccountLogin(session, request.getAccountId(), request.getPassword());
        
        EventDispatcher.getInstance().fireEvent(new EventLogin(EventType.LOGIN, session, request));
    }
    
    @RequestMapping
    public void reqAccountRelogin(IoSession session, ReqReLoginMessage request) {
        EventDispatcher.getInstance().fireEvent(new EventReLogin(EventType.RELOGIN, session, request));
    }
    
    @RequestMapping
    public void reqSelectLogin(IoSession session, ReqSelectPlayerMessage request) {
        LoginManager.getInstance().handleSelectPlayer(session, request.getPlayerId());
    }
    
    @RequestMapping
    public void resLogin(IoSession session, ResLoginMessage request) {
        //LoginManager.getInstance().handleSelectPlayer(session, request.getPlayerId());
        System.out.println("收到登录消息");
    }
}
