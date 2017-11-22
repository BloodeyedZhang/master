package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigFubenlevel.java
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
 * Created: 2017年11月6日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
@Entity(readOnly = true)
public class ConfigFubenlevel {
    @Column
    private int level;

    @Column
    private int jiacheng;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getJiacheng() {
        return jiacheng;
    }

    public void setJiacheng(int jiacheng) {
        this.jiacheng = jiacheng;
    }

    @Override
    public String toString() {
        return "ConfigFubenlevel [" + "level=" + level + "," + "jiacheng=" + jiacheng + "," + "]";

    }
}
