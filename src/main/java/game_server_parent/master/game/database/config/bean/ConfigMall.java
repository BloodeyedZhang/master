package game_server_parent.master.game.database.config.bean;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigMall.java
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
 * Created: 2017年10月20日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
@Entity(readOnly = true)
public class ConfigMall {

    @Protobuf(order=1)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int type;

    @Column
    @Protobuf(order=2)
    private int num;

    @Column
    private int coinType;

    @Protobuf(order=3)
    @Column
    private float money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCoinType() {
        return coinType;
    }

    public void setCoinType(int coinType) {
        this.coinType = coinType;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ConfigMall [" + "id=" + id + "," + "name=" + name + "," + "type=" + type + "," + "num=" + num + ","
                + "coinType=" + coinType + "," + "money=" + money + "]";

    }

}
