package game_server_parent.master.game.fuben.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.fuben.FubenDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResBattleEndMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月9日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.FUBEN, cmd=FubenDataPool.RES_BATTLE_END)
public class ResBattleEndMessage extends Message {
    @Protobuf(order=1)
    private int code;
    @Protobuf(order=2)
    private int money1;
    @Protobuf(order=3)
    private int bonus_points;
    @Protobuf(order=4)
    private int jiacheng_type;
    @Protobuf(order=5)
    private float jiachengzhi;

    
    @Override
    public String toString() {
        return "ResBattleEndMessage [ code=" + code + ", money1=" + money1 + ", bonus_points=" + bonus_points 
                + ", jiacheng_type=" + jiacheng_type + ", jiachengzhi=" + jiachengzhi + "]";
    }

    public int getMoney1() {
        return money1;
    }

    public void setMoney1(int money1) {
        this.money1 = money1;
    }

    public int getBonus_points() {
        return bonus_points;
    }

    public void setBonus_points(int bonus_points) {
        this.bonus_points = bonus_points;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getJiacheng_type() {
        return jiacheng_type;
    }

    public void setJiacheng_type(int jiacheng_type) {
        this.jiacheng_type = jiacheng_type;
    }

    public float getJiachengzhi() {
        return jiachengzhi;
    }

    public void setJiachengzhi(float jiachengzhi) {
        this.jiachengzhi = jiachengzhi;
    }
}
