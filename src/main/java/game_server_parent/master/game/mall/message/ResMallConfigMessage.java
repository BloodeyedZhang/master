package game_server_parent.master.game.mall.message;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.config.bean.ConfigMall;
import game_server_parent.master.game.mall.MallDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResMallConfigMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.MALL, cmd=MallDataPool.RES_Mall_Config)
public class ResMallConfigMessage extends Message {

    @Protobuf(fieldType = FieldType.OBJECT, order=1)
    private List<ConfigMall> goods;
 
    public List<ConfigMall> getGoods() {
        return goods;
    }

    public void setGoods(List<ConfigMall> goods) {
        this.goods = goods;
    }
    @Override
    public String toString() {
        return "ResMallConfigMessage [goods.count=" + goods.size() + "]";
    }
}
