package game_server_parent.master.game.kapai;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.kapai.events.EventKapai;
import game_server_parent.master.game.kapai.events.EventKapaiNew;
import game_server_parent.master.game.kapai.events.EventKapaiUpdate;
import game_server_parent.master.game.kapai.message.ReqKapaiNewMessage;
import game_server_parent.master.game.kapai.message.ReqKapaiSellMessage;
import game_server_parent.master.game.kapai.message.ReqKapaiUpdateMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.SessionManager;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:KapaiContriller.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Controller
public class KapaiController {
    @RequestMapping
    public void reqKapaiNew(IoSession session, ReqKapaiNewMessage request) {
        long player_id = SessionManager.INSTANCE.getPlayerIdBy(session);
        EventDispatcher.getInstance().fireEvent(new EventKapaiNew(EventType.KAPAI_NEW, player_id, request.getDalei(), request.getBingzhong()));
    }
    
    @RequestMapping
    public void reqKapaiUpdate(IoSession session, ReqKapaiUpdateMessage request) {
        long player_id = SessionManager.INSTANCE.getPlayerIdBy(session);
        EventDispatcher.getInstance().fireEvent(new EventKapaiUpdate(EventType.KAPAI_UPDATE, player_id, request.getKapais()));
    }
    
    @RequestMapping
    public void reqKapaiSell(IoSession session, ReqKapaiSellMessage request) {
        long player_id = SessionManager.INSTANCE.getPlayerIdBy(session);
        EventDispatcher.getInstance().fireEvent(new EventKapai(EventType.KAPAI_SELL, player_id, request.getId()));
    }
}
