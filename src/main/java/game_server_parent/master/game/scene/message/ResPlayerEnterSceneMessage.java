package game_server_parent.master.game.scene.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.scene.SceneDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ReqPlayerEnterSceneMessage.java</p>
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
@MessageMeta(module=Modules.SCENE,cmd=SceneDataPool.RES_ENTER_SCENE)
public class ResPlayerEnterSceneMessage extends Message {
    
    @Protobuf(order = 1)
    private int mapId;
    
    @Protobuf(order = 2)
    private int code;
    
    public ResPlayerEnterSceneMessage() {}
    
    public ResPlayerEnterSceneMessage(int mapId) {
        this.mapId = mapId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResPlayerEnterSceneMessage [mapId=" + mapId + ", code=" + code + "]";
    }
}
