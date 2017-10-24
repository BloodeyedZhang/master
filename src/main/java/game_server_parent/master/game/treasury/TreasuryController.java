package game_server_parent.master.game.treasury;

import org.apache.mina.core.session.IoSession;

import com.sun.media.jfxmedia.control.VideoDataBuffer;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.record.events.EventTreasuryRecord;
import game_server_parent.master.game.treasury.events.EventTreasuryEnd;
import game_server_parent.master.game.treasury.events.EventTreasuryUpdate;
import game_server_parent.master.game.treasury.message.ReqDestroyBoxMessage;
import game_server_parent.master.game.treasury.message.ReqTreasuryBattleEndMessage;
import game_server_parent.master.game.treasury.message.ReqTreasuryUpdateShowMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;
import game_server_parent.master.net.dispatch.MessageDispatcher;

/**
 * <p>
 * Filename:TreasuryController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.
 * </p>
 * <p>
 * Company: WinTurn Network Technology
 * </p>
 * <p>
 * Summary:
 * </p>
 * <p>
 * Created: 2017年10月17日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
@Controller
public class TreasuryController {

    @RequestMapping
    public void reqBoxDestroy(IoSession session, ReqDestroyBoxMessage request) {
        long player_id = (long) session.getAttribute(SessionProperties.PLAYER_ID);
        int index = request.getIndex();
        TreasuryManager.getInstance().destroyBox(player_id, index);
    }

    @RequestMapping
    public void reqTreasuryBattleEnd(IoSession session, ReqTreasuryBattleEndMessage request) {
        long player_id = (long) session.getAttribute(SessionProperties.PLAYER_ID);
        int code = request.getCode();
        if (code == TreasuryDataPool.BATTLE_WIN) {
            TreasuryManager.getInstance().executeBattleEnd(player_id, code);
        } else if (code == TreasuryDataPool.BATTLE_LOSE) {
            TreasuryManager.getInstance().executeBattleEnd(player_id, code);
        }
    }

    @RequestMapping
    public void reqTreasuryUpdateShow(IoSession session, ReqTreasuryUpdateShowMessage request) {
        long player_id = (long) session.getAttribute(SessionProperties.PLAYER_ID);
        EventDispatcher.getInstance().fireEvent(
                (new EventTreasuryUpdate(EventType.TREASURY_UPDATE_SHOW, player_id, request.getTreasury_level())));
    }
}
