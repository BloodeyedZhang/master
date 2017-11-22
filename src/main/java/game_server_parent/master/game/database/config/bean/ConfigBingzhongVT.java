package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigBingzhongVT.java
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
public class ConfigBingzhongVT {

    @Column
    private int id;

    @Column
    private String bingzhong;

    @Column
    private int vt;
    
    @Column
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBingzhong() {
        return bingzhong;
    }

    public void setBingzhong(String bingzhong) {
        this.bingzhong = bingzhong;
    }

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString(){
    return "ConfigBingzhongVT ["
    + "id="+id+","
    + "bingzhong="+bingzhong+","
    + "vt="+vt+","
    + "level="+level
    +"]";

    }

}
