package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigAi.java
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
 * Created: 2017年11月20日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
@Entity(readOnly=true)
public class ConfigAi {
    @Column
    private int bingzhong;

    @Column
    private float fight;

    @Column
    private float add_fight;

    public int getBingzhong() {
        return bingzhong;
    }

    public void setBingzhong(int bingzhong) {
        this.bingzhong = bingzhong;
    }

    public float getFight() {
        return fight;
    }

    public void setFight(float fight) {
        this.fight = fight;
    }

    public float getAdd_fight() {
        return add_fight;
    }

    public void setAdd_fight(float add_fight) {
        this.add_fight = add_fight;
    }

    @Override
    public String toString() {
        return "ConfigAi [" + "bingzhong=" + bingzhong + "," + "fight=" + fight + "," + "add_fight=" + add_fight + ","
                + "]";

    }
}
