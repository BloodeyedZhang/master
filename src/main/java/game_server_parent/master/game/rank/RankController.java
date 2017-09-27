package game_server_parent.master.game.rank;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.rank.events.EventRankBattle;
import game_server_parent.master.game.rank.message.ReqRankBattleEndMessage;
import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:RankController.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月22日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Controller
public class RankController {

    @RequestMapping
    public void reqRankBattleEnd(IoSession session, ReqRankBattleEndMessage request) {
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        Player player = PlayerManager.getInstance().get(player_id);
        int battle_id = player.getRank_battle_id();
        int code = request.getCode();
        if(code == RankDataPool.BATTLE_WIN) {
            // 发送战斗胜利事件
            EventDispatcher.getInstance().fireEvent(new EventRankBattle(EventType.BATTLE_WIN, player_id, battle_id));
        } else if(code == RankDataPool.BATTLE_LOSE) {
            // 发送战斗失败事件
            EventDispatcher.getInstance().fireEvent(new EventRankBattle(EventType.BATTLE_LOSE, player_id, battle_id));
        }
    }
    
}
