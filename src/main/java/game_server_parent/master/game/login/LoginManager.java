package game_server_parent.master.game.login;

import java.util.List;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.gm.message.ResGmResultMessage;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.kapai.message.ResSelectPlayerKapaiMessage;
import game_server_parent.master.game.login.message.ResLoginMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.scene.message.ResPlayerEnterSceneMessage;
import game_server_parent.master.net.MessagePusher;
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
    public void handleAccountLogin(IoSession session, long accountId, String password) {
        if("winturn".equals(password)) {
            Player player = PlayerManager.getInstance().get(accountId);
            if(player==null) {
                MessagePusher.pushMessage(session, new ResLoginMessage(LoginDataPool.LOGIN_FAIL, "用户不存在"));
            } else {
                PlayerManager.getInstance().add2Online(player);
                
                ResLoginMessage response = new ResLoginMessage(LoginDataPool.LOGIN_SUCC, "登录成功");
                
                CombineMessage combineMessage = new CombineMessage();  
                combineMessage.addMessage(response);  
                combineMessage.addMessage(new ResPlayerEnterSceneMessage());  
               // combineMessage.addMessage(ResGmResultMessage.buildSuccResult("执行gm成功"));
                List<Kapai> kapais = KapaiManager.getInstance().getPlayerKapaiList(accountId);
                combineMessage.addMessage(new ResSelectPlayerKapaiMessage(kapais));
                
                MessagePusher.pushMessage(session, combineMessage);
            }
        } else {
            MessagePusher.pushMessage(session, new ResLoginMessage(LoginDataPool.LOGIN_FAIL, "登录失败"));
        }
    }
    
    /**
     * 选角登录
     * @param session
     * @param playerId
     */
    public void handleSelectPlayer(IoSession session, long playerId) {
        Player player = PlayerManager.getInstance().get(playerId);
        if(player != null) {
            // 绑定session和玩家id
            session.setAttribute(SessionProperties.PLAYER_ID, playerId);
            // 推送进入场景
            ResPlayerEnterSceneMessage response = new ResPlayerEnterSceneMessage();
            response.setMapId(1001);
            MessagePusher.pushMessage(session, response);
        }
    }
}
