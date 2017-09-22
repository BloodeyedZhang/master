package game_server_parent.master.game.login;

import java.util.List;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.gm.message.ResGmResultMessage;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.kapai.message.ResSelectPlayerKapaiMessage;
import game_server_parent.master.game.login.events.EventLogin;
import game_server_parent.master.game.login.message.ResLoginMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.game.scene.message.ResPlayerEnterSceneMessage;
import game_server_parent.master.game.team.TeamManager;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionManager;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.combine.CombineMessage;

/**
 * <p>Filename:LoginManager.java</p>
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
public class LoginManager {

    private static LoginManager instance = new LoginManager();
    
    private LoginManager() {}
    
    public static LoginManager getInstance() {
        return instance;
    }
    
    /**
     * 
     * @param accoundId 账号流水号
     * @param password  账号密码
     */
    public boolean handleAccountLogin(IoSession session, long accountId, String password) {
        if("winturn".equals(password)) {
           // Player player = PlayerManager.getInstance().get(accountId);
            //if(player==null) {
             //   MessagePusher.pushMessage(session, new ResLoginMessage(LoginDataPool.LOGIN_FAIL, "用户不存在"));
           // } else {
               // player.setId(accountId);
               // SessionManager.INSTANCE.registerNewPlayer(accountId, session);
               // PlayerManager.getInstance().add2Online(player);
                
                ResLoginMessage response = new ResLoginMessage(LoginDataPool.LOGIN_SUCC, accountId+"登录成功");
                
                MessagePusher.pushMessage(session, response);
                return true;
           // }
        } else {
            MessagePusher.pushMessage(session, new ResLoginMessage(LoginDataPool.LOGIN_FAIL, "登录失败"));
        }
        return false;
    }
    
    /**
     * 选角登录
     * @param session
     * @param playerId
     */
    public void handleSelectPlayer(IoSession session, long playerId) {
        Player player = PlayerManager.getInstance().get(playerId);
        if(player.getPlayer_id()==0) {
            //player = PlayerManager.getInstance().createNewPlayer("test"+playerId, (byte)1);
            
            //EventDispatcher.getInstance().fireEvent(new EventNewPlayer(EventType.PLAYER_CREATE, player.getPlayer_id()));

           // DbService.getInstance().add2Queue(player);

        }
        if (player.getPlayer_id()>0) {
            
            IoSession sessionBy = SessionManager.INSTANCE.getSessionBy(playerId);
            if(sessionBy!=null) {
                sessionBy.close(true);
            }
            
            //绑定session与玩家id
            session.setAttribute(SessionProperties.PLAYER_ID, playerId);
            //加入在线列表
            PlayerManager.getInstance().add2Online(player);
            SessionManager.INSTANCE.registerNewPlayer(playerId, session);
            //推送进入场景
            ResPlayerEnterSceneMessage response = new ResPlayerEnterSceneMessage();
            response.setMapId(0);
            MessagePusher.pushMessage(session, response);
            //检查日重置
            PlayerManager.getInstance().checkDailyReset(player);
        } else {
            MessagePusher.pushMessage(session, new ResLoginMessage(LoginDataPool.LOGIN_FAIL, "用户不存在"));
        }
    }
    

}
