package game_server_parent.master.game.player;

import org.apache.mina.core.session.IoSession;

import com.baidu.bjf.remoting.protobuf.utils.StringUtils;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.message.ReqPlayerRenameCheckMessage;
import game_server_parent.master.game.player.message.ReqPlayerRenameMessage;
import game_server_parent.master.game.player.message.ResPlayerNameCheckMessage;
import game_server_parent.master.game.player.message.ResPlayerRenameMessage;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:PlayerController.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Controller
public class PlayerController {

    @RequestMapping
    public void onPlayerRename(IoSession session, ReqPlayerRenameCheckMessage request) {
        String name = request.getName();
        // 重命名检查
        boolean flag = PlayerNameManager.getInstance().check(name); // true:不重名; false:重名
        ResPlayerNameCheckMessage resp = new ResPlayerNameCheckMessage();
        resp.setCode(flag?PlayerDataPool.CAN_RENAME:PlayerDataPool.CANNOT_RENAME);
        MessagePusher.pushMessage(session, resp);
    }
    
    @RequestMapping
    public void onPlayerRename(IoSession session, ReqPlayerRenameMessage request) {
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        Player player = PlayerManager.getInstance().get(player_id);
        int is_rename = player.getIs_rename();
        
        ResPlayerRenameMessage resp = new ResPlayerRenameMessage();
        
        if(is_rename==PlayerDataPool.CAN_RENAME) {
            // 可以改名
            String name = request.getName();
            // 重命名检查
            if(StringUtils.isEmpty(name) || name.trim().startsWith("test") || name.trim().startsWith("ai_") || name.contains("#")) {
                // false
                resp.setCode(PlayerDataPool.CANNOT_RENAME);
            } else if(player.getName().equals(name)) {
                resp.setCode(PlayerDataPool.CAN_RENAME);
                resp.setName(name);
            }
            else if(PlayerNameManager.getInstance().check(name)) {
                // 可以改名
                player.setName(name);
                player.setIs_rename(PlayerDataPool.CANNOT_RENAME);
                player.setFocsUpdate();
                
                PlayerNameManager.getInstance().add(name);
                resp.setCode(PlayerDataPool.CAN_RENAME);
                resp.setName(name);
            } else {
                resp.setCode(PlayerDataPool.CANNOT_RENAME);
            }
        } else {
            resp.setCode(PlayerDataPool.CANNOT_RENAME);
        }
        
        MessagePusher.pushMessage(session, resp);
    }
}
