package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigJiachengVT.java
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
 * Created: 2017年10月17日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
@Entity(readOnly = true)
public class ConfigJiachengVT {

    @Column
    private int jiacheng;

    @Column
    private int vt;

    public int getJiacheng() {
        return jiacheng;
    }

    public void setJiacheng(int jiacheng) {
        this.jiacheng = jiacheng;
    }

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    @Override
    public String toString() {
        return "ConfigJiachengVT [" + "jiacheng=" + jiacheng + "," + "vt=" + vt + "," + "]";

    }

}
