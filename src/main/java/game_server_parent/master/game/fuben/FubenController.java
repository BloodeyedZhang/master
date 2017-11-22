package game_server_parent.master.game.fuben;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.fuben.events.EventFuben;
import game_server_parent.master.game.fuben.message.ReqBattleEndMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.rank.RankDataPool;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:FubenController.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月7日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Controller
public class FubenController {

    @RequestMapping
    public void onReqBattleEnd(IoSession session, ReqBattleEndMessage request) {
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        Object map_id_o = session.getAttribute(SessionProperties.PLAYER_MAP_ID);
        session.setAttribute(SessionProperties.PLAYER_MAP_ID, 0); // 副本战斗结束标志
        if(map_id_o==null) return;
        int map_id = (int) map_id_o;
        int code = request.getCode();
        if(code == RankDataPool.BATTLE_WIN) {
            // 发送战斗胜利事件
            FubenManager.getInstance().execute_win(map_id, player_id);
            EventDispatcher.getInstance().fireEvent(new EventFuben(EventType.BATTLE_WIN, player_id, map_id));
        } else if(code == RankDataPool.BATTLE_LOSE) {
            // 发送战斗失败事件
            //EventDispatcher.getInstance().fireEvent(new EventRankBattle(EventType.BATTLE_LOSE, player_id, battle_id));
            FubenManager.getInstance().execute_lose(map_id, player_id);
        }
    }
}
