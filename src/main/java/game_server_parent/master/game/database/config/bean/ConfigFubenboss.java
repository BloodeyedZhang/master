package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigFubenboss.java
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
public class ConfigFubenboss {

    @Column
    private int level;

    @Column
    private int bingzhong;

    @Column
    private int hp;

    @Column
    private int attack;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBingzhong() {
        return bingzhong;
    }

    public void setBingzhong(int bingzhong) {
        this.bingzhong = bingzhong;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getKey() {
        return this.level+"-"+this.bingzhong;
    }
    
    @Override
    public String toString() {
        return "ConfigFubenboss [" + "level=" + level + "," + "bingzhong=" + bingzhong + "," + "hp=" + hp + ","
                + "attack=" + attack + "," + "]";

    }
}
