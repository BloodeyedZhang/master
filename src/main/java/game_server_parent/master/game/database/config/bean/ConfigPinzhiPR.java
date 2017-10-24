package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigPinzhiPR.java
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
public class ConfigPinzhiPR {

    @Column
    private String type;

    @Column
    private float meihua;

    @Column
    private float fangpian;

    @Column
    private float hongtao;

    @Column
    private float heitao;

    @Column
    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMeihua() {
        return meihua;
    }

    public void setMeihua(float meihua) {
        this.meihua = meihua;
    }

    public float getFangpian() {
        return fangpian;
    }

    public void setFangpian(float fangpian) {
        this.fangpian = fangpian;
    }

    public float getHongtao() {
        return hongtao;
    }

    public void setHongtao(float hongtao) {
        this.hongtao = hongtao;
    }

    public float getHeitao() {
        return heitao;
    }

    public void setHeitao(float heitao) {
        this.heitao = heitao;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ConfigPinzhiPR [type="+type+ ", meihua="+meihua+ ", fangpian="+fangpian+", hongtao="+hongtao+", heitao="+heitao+"]";
    }

    
}
