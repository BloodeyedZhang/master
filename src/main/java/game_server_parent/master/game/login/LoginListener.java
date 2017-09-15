package game_server_parent.master.game.login;

import java.util.List;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.kapai.message.ResSelectPlayerKapaiMessage;
import game_server_parent.master.game.login.events.EventLogin;
import game_server_parent.master.game.login.events.EventReLogin;
import game_server_parent.master.game.scene.message.ResPlayerEnterSceneMessage;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.combine.CombineMessage;

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
            CombineMessage combineMessage = new CombineMessage();   
            combineMessage.addMessage(new ResPlayerEnterSceneMessage());  
           // combineMessage.addMessage(ResGmResultMessage.buildSuccResult("执行gm成功"));
            List<Kapai> kapais = KapaiManager.getInstance().getPlayerKapaiList(accountId);
            combineMessage.addMessage(new ResSelectPlayerKapaiMessage(kapais));
            
            MessagePusher.pushMessage(session, combineMessage);
        }
    }
    
    @EventHandler(value=EventType.RELOGIN)
    public void onReLogin(EventReLogin event) {
        long accountId = event.getMessage().getAccountId();
        String password = event.getMessage().getPassword();
        LoginManager.getInstance().handleAccountLogin(event.getSession(), accountId, password);
    }
}
