package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigFuben.java
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
public class ConfigFuben {
    @Column
    private int id;
    
    @Column
    private String scene;

    @Column
    private int bingzhong;

    @Column
    private int jiachengzhonglei;

    @Column
    private int dengjishangxian;

    @Column
    private int wincold;

    @Column
    private int losecold;

    @Column
    private int reward;

    @Column
    private int star;

    @Column
    private int pingzhi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBingzhong() {
        return bingzhong;
    }

    public void setBingzhong(int bingzhong) {
        this.bingzhong = bingzhong;
    }

    public int getJiachengzhonglei() {
        return jiachengzhonglei;
    }

    public void setJiachengzhonglei(int jiachengzhonglei) {
        this.jiachengzhonglei = jiachengzhonglei;
    }

    public int getDengjishangxian() {
        return dengjishangxian;
    }

    public void setDengjishangxian(int dengjishangxian) {
        this.dengjishangxian = dengjishangxian;
    }

    public int getWincold() {
        return wincold;
    }

    public void setWincold(int wincold) {
        this.wincold = wincold;
    }

    public int getLosecold() {
        return losecold;
    }

    public void setLosecold(int losecold) {
        this.losecold = losecold;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getPingzhi() {
        return pingzhi;
    }

    public void setPingzhi(int pingzhi) {
        this.pingzhi = pingzhi;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return "ConfigFuben [" + "id=" + id + ", scene=" + scene + ", bingzhong=" + bingzhong + "," + "jiachengzhonglei="
                + jiachengzhonglei + "," + "dengjishangxian=" + dengjishangxian + "," + "wincold=" + wincold + ","
                + "losecold=" + losecold + "," + "reward=" + reward + "," + "star=" + star + "," + "pingzhi=" + pingzhi
                + "," + "]";

    }
}
