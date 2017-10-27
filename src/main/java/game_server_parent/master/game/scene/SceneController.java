package game_server_parent.master.game.scene;

import org.apache.mina.core.session.IoSession;

import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLService;

import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.game.scene.message.ReqPlayerEnterSceneMessage;
import game_server_parent.master.game.scene.message.ReqPlayerPreEnterSceneMessage;
import game_server_parent.master.game.scene.message.ResPlayerEnterSceneMessage;
import game_server_parent.master.game.scene.message.ResPlayerPreEnterSceneMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:SceneController.java</p>
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
@Controller
public class SceneController {

    /*
    @RequestMapping
    public void resPlayerEnterScene(IoSession session, ResPlayerEnterSceneMessage response) {
        System.out.println("收到进入场景消息");
    }
    */
    
    @RequestMapping
    public void ReqPlayerPreEnterScene(IoSession session, ReqPlayerPreEnterSceneMessage req) {
        System.out.println("收到准备进入场景消息 mapId="+req.getMapId());
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        int mapId = req.getMapId();
        if(mapId == MapEnum.Dating.value()) {
            //EventDispatcher.getInstance().fireEvent(new EventEnterScene(EventType.PRE_ENTER_DATING, player_id, mapId));
            ResPlayerPreEnterSceneMessage resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_SUCC);
            MessagePusher.pushMessage(session, resp);
        } else if(mapId == MapEnum.Zhandou.value()) {
            EventDispatcher.getInstance().fireEvent(new EventEnterScene(EventType.PRE_ENTER_ZHANDOU, player_id, mapId));
        } else if(mapId == MapEnum.Treasury.value()) {
            EventDispatcher.getInstance().fireEvent(new EventEnterScene(EventType.PRE_ENTER_JINKU, player_id, mapId));
        } else {
            ResPlayerPreEnterSceneMessage resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_SUCC);
            MessagePusher.pushMessage(session, resp);
        }
    }
    
    @RequestMapping
    public void reqPlayerEnterScene(IoSession session, ReqPlayerEnterSceneMessage req) {
        System.out.println("收到进入场景消息 mapId="+req.getMapId());
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        int mapId = req.getMapId();

        if(mapId == MapEnum.Dating.value()) {
            EventDispatcher.getInstance().fireEvent(new EventEnterScene(EventType.ENTER_DATING, player_id, mapId));
        } else if(mapId == MapEnum.Zhandou.value()) {
            EventDispatcher.getInstance().fireEvent(new EventEnterScene(EventType.ENTER_ZHANDOU, player_id, mapId));
        } else if(mapId == MapEnum.Treasury.value()) {
            EventDispatcher.getInstance().fireEvent(new EventEnterScene(EventType.ENTER_JINKU, player_id, mapId));
        }
    }
}
