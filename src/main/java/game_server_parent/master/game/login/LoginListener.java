package game_server_parent.master.game.login;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.login.events.EventLogin;
import game_server_parent.master.game.login.events.EventReLogin;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;

/**
 * <p>Filename:LoginListener.java</p>
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
@Listener
public class LoginListener {
    
    @EventHandler(value=EventType.LOGIN)
    public void onLogin(EventLogin event) {
        long accountId = event.getMessage().getAccountId();
        String password = event.getMessage().getPassword();
        IoSession session = event.getSession();
        if(LoginManager.getInstance().handleAccountLogin(event.getSession(), accountId, password)) {
            LoginManager.getInstance().handleSelectPlayer(session, accountId);

        }
    }
    
    @EventHandler(value=EventType.RELOGIN)
    public void onReLogin(EventReLogin event) {
        long accountId = event.getMessage().getAccountId();
        String password = event.getMessage().getPassword();
        LoginManager.getInstance().handleAccountLogin(event.getSession(), accountId, password);
    }
}
