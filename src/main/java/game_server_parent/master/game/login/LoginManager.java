package game_server_parent.master.game.login;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.crossrank.CrossRankListener;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.login.message.ResLoginMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.scene.message.ResPlayerEnterSceneMessage;
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

    private Logger logger = LoggerFactory.getLogger(CrossRankListener.class);
    
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
                
                //ResLoginMessage response = new ResLoginMessage(LoginDataPool.LOGIN_SUCC, String.valueOf(accountId));
                
               // MessagePusher.pushMessage(session, response);
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
            if(sessionBy!=null&&!sessionBy.equals(session)) {
                //sessionBy.close(true);
                logger.error("一个playerId有两条session?");
            }
            
            //绑定session与玩家id
            session.setAttribute(SessionProperties.PLAYER_ID, playerId);
            //加入在线列表
            PlayerManager.getInstance().add2Online(player);
            SessionManager.INSTANCE.registerNewPlayer(playerId, session);
            //推送进入场景
            ResPlayerEnterSceneMessage response = new ResPlayerEnterSceneMessage();
            response.setMapId(0);

            
            ResLoginMessage response_login = new ResLoginMessage(LoginDataPool.LOGIN_SUCC, String.valueOf(playerId));
            
         // 下发组合包
            CombineMessage combineMessage = new CombineMessage();
            combineMessage.addMessage(response_login);
            combineMessage.addMessage(response);
            MessagePusher.pushMessage(session, combineMessage);
            
            //检查日重置
            PlayerManager.getInstance().checkDailyReset(player);
        } else {
            MessagePusher.pushMessage(session, new ResLoginMessage(LoginDataPool.LOGIN_FAIL, "用户不存在 playerId["+playerId+"]"));
            PlayerManager.getInstance().remove(playerId);
        }
    }
    
    public void handleUUID(IoSession session, String uuid) {
        
    } 

}
