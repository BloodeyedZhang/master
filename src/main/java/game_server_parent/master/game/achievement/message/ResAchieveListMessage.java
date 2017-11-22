package game_server_parent.master.game.achievement.message;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.achievement.AchievementDataPool;
import game_server_parent.master.game.database.user.storage.KapaiAchievement;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResAchieveListMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.ACHIEVE, cmd=AchievementDataPool.RES_ACHIEVE_LIST)
public class ResAchieveListMessage extends Message {

    @Protobuf(fieldType=FieldType.OBJECT, order=1)
    private List<KapaiAchievement> achieveList;

    public List<KapaiAchievement> getAchieveList() {
        return achieveList;
    }

    public void setAchieveList(List<KapaiAchievement> achieveList) {
        this.achieveList = achieveList;
    }
    
}
