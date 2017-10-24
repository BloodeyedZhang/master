package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigJiachengTypePR.java
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
public class ConfigJiachengTypePR {

    @Column
    private int jiachengtype;

    @Column
    private float pr;

    public int getJiachengtype() {
        return jiachengtype;
    }

    public void setJiachengtype(int jiachengtype) {
        this.jiachengtype = jiachengtype;
    }

    public float getPr() {
        return pr;
    }

    public void setPr(float pr) {
        this.pr = pr;
    }

    @Override
    public String toString() {
        return "ConfigJiachengTypePR [" + "jiachengtype=" + jiachengtype + "," + "pr=" + pr + "," + "]";

    }

}
