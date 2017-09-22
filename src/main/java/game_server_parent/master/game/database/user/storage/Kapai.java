package game_server_parent.master.game.database.user.storage;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;
import game_server_parent.master.utils.IdGenerator;

/**
 * <p>Filename:Kapai.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Entity
public class Kapai extends BaseEntity<Long> {
    @Id
    @Column
    @Protobuf(order=2)
    private int kapai_id;
    //@Id
    @Column
    @Protobuf(fieldType = FieldType.INT64, order=1, required = true)
    private long id;
    

    
    @Column
    @Protobuf(fieldType = FieldType.INT64, order=3, required = true)
    private long player_id;
    
    @Column
    @Protobuf(order=4)
    private int dalei;
    
    @Column
    @Protobuf(order=5)
    private int bingzhong;
    
    @Column
    @Protobuf(order=6)
    private int pinzhi;
    
    @Column
    @Protobuf(order=7)
    private int jiachengbi;
    
    @Column
    @Protobuf(order=8)
    private int s_dengji;
    
    @Column
    @Protobuf(order=9)
    private int jingyan;
    @Column
    @Protobuf(order=10)
    private int xingji;
    
    @Column
    @Protobuf(order=11)
    private int shengmingzhi;
    
    @Column
    @Protobuf(order=12)
    private int gongjizhi;
    
    @Column
    @Protobuf(order=13)
    private int zhiliaozhi;

    public Kapai() {
        this.id = IdGenerator.getNextId();
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(long player_id) {
        this.player_id = player_id;
    }

    public int getDalei() {
        return dalei;
    }

    public void setDalei(int dalei) {
        this.dalei = dalei;
    }

    public int getBingzhong() {
        return bingzhong;
    }

    public void setBingzhong(int bingzhong) {
        this.bingzhong = bingzhong;
    }

    public int getPinzhi() {
        return pinzhi;
    }

    public void setPinzhi(int pinzhi) {
        this.pinzhi = pinzhi;
    }

    public int getJiachengbi() {
        return jiachengbi;
    }

    public void setJiachengbi(int jiachengbi) {
        this.jiachengbi = jiachengbi;
    }

    public int getS_dengji() {
        return s_dengji;
    }

    public void setS_dengji(int s_dengji) {
        this.s_dengji = s_dengji;
    }

    public int getJingyan() {
        return jingyan;
    }

    public void setJingyan(int jingyan) {
        this.jingyan = jingyan;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getXingji() {
        return xingji;
    }

    public void setXingji(int xingji) {
        this.xingji = xingji;
    }

    public int getKapai_id() {
        return kapai_id;
    }

    public void setKapai_id(int kapai_id) {
        this.kapai_id = kapai_id;
    }

    public int getShengmingzhi() {
        return shengmingzhi;
    }

    public void setShengmingzhi(int shengmingzhi) {
        this.shengmingzhi = shengmingzhi;
    }

    public int getGongjizhi() {
        return gongjizhi;
    }

    public void setGongjizhi(int gongjizhi) {
        this.gongjizhi = gongjizhi;
    }

    public int getZhiliaozhi() {
        return zhiliaozhi;
    }

    public void setZhiliaozhi(int zhiliaozhi) {
        this.zhiliaozhi = zhiliaozhi;
    }

    @Override
    public String toString() {
        return "Kapai [id=" + id +", kapai_id=" + kapai_id+ ", player_id=" + player_id + ", dalei=" + dalei
                + ", bingzhong=" + bingzhong + ", pinzhi=" + pinzhi + ", jiachengbi=" + jiachengbi
                + ", s_dengji=" + s_dengji+ ", jingyan=" + jingyan+ ", xingji=" + xingji 
                + ", shengmingzhi=" + shengmingzhi + ", gongjizhi=" + gongjizhi + ", zhiliaozhi=" + zhiliaozhi +"]";
    }
}
