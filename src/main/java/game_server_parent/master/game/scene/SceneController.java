package game_server_parent.master.game.scene;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.scene.message.ResPlayerEnterSceneMessage;
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

    @RequestMapping
    public void resPlayerEnterScene(IoSession session, ResPlayerEnterSceneMessage response) {
        System.out.println("收到进入场景消息");
    }
}
