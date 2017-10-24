package game_server_parent.master.game.database.user.storage;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;
import game_server_parent.master.utils.IdGenerator;

/**
 * <p>
 * Filename:Box.java
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
 * Created: 2017年10月13日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
@Entity
public class Treasury extends BaseEntity {

    @Id
    @Column
    @Protobuf(order = 1)
    private long player_id;
    
    @Column
    @Protobuf(order = 2)
    private long treasury_id;

    @Column
    @Protobuf(order = 3)
    private long id;

    @Column
    @Protobuf(order = 4)
    private int treasuryLevel;

    @Column
    private int baseHP;
    
    @Column
    @Protobuf(order = 5)
    private int level1;
    
    @Column
    @Protobuf(order = 6)
    private int level2;
    
    @Column
    @Protobuf(order = 7)
    private int level3;
    
    @Column
    @Protobuf(order = 8)
    private int level4;
    
    @Column
    @Protobuf(order = 9)
    private int level5;

    @Column
    @Protobuf(order = 10)
    private int level1HP;

    @Column
    @Protobuf(order = 11)
    private int level2HP;

    @Column
    @Protobuf(order = 12)
    private int level3HP;

    @Column
    @Protobuf(order = 13)
    private int level4HP;
    
    @Column
    @Protobuf(order = 14)
    private int level5HP;

    @Column
    @Protobuf(order = 15)
    private int diamond1;

    @Column
    @Protobuf(order = 16)
    private int coin1;

    @Column
    @Protobuf(order = 17)
    private int card1;

    @Column
    @Protobuf(order = 18)
    private int diamond2;

    @Column
    @Protobuf(order = 19)
    private int coin2;

    @Column
    @Protobuf(order = 20)
    private int card2;

    @Column
    @Protobuf(order = 21)
    private int diamond3;

    @Column
    @Protobuf(order = 22)
    private int coin3;

    @Column
    @Protobuf(order = 23)
    private int card3;

    @Column
    @Protobuf(order = 24)
    private int diamond4;

    @Column
    @Protobuf(order = 25)
    private int coin4;

    @Column
    @Protobuf(order = 26)
    private int card4;

    @Column
    @Protobuf(order = 27)
    private int diamond5;

    @Column
    @Protobuf(order = 28)
    private int coin5;

    @Column
    @Protobuf(order = 29)
    private int card5;
    
    @Column
    @Protobuf(order = 30)
    private String card1_pinzhi = "1:0,2:0,3:0,4:0";
    @Column
    @Protobuf(order = 31)
    private String card2_pinzhi = "1:0,2:0,3:0,4:0";
    @Column
    @Protobuf(order = 32)
    private String card3_pinzhi = "1:0,2:0,3:0,4:0";
    @Column
    @Protobuf(order = 33)
    private String card4_pinzhi = "1:0,2:0,3:0,4:0";
    @Column
    @Protobuf(order = 34)
    private String card5_pinzhi = "1:0,2:0,3:0,4:0";
    
    public Treasury() {
        this.id = IdGenerator.getNextId();
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTreasury_id() {
        return treasury_id;
    }

    public void setTreasury_id(long treasury_id) {
        this.treasury_id = treasury_id;
    }

    public long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(long player_id) {
        this.player_id = player_id;
    }

    public int getTreasuryLevel() {
        return treasuryLevel;
    }

    public void setTreasuryLevel(int treasuryLevel) {
        this.treasuryLevel = treasuryLevel;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public int getLevel1HP() {
        return level1HP;
    }

    public void setLevel1HP(int level1HP) {
        this.level1HP = level1HP;
    }

    public int getLevel2HP() {
        return level2HP;
    }

    public void setLevel2HP(int level2HP) {
        this.level2HP = level2HP;
    }

    public int getLevel3HP() {
        return level3HP;
    }

    public void setLevel3HP(int level3HP) {
        this.level3HP = level3HP;
    }

    public int getLevel4HP() {
        return level4HP;
    }

    public void setLevel4HP(int level4HP) {
        this.level4HP = level4HP;
    }

    public int getDiamond1() {
        return diamond1;
    }

    public void setDiamond1(int diamond1) {
        this.diamond1 = diamond1;
    }

    public int getCoin1() {
        return coin1;
    }

    public void setCoin1(int coin1) {
        this.coin1 = coin1;
    }

    public int getCard1() {
        return card1;
    }

    public void setCard1(int card1) {
        this.card1 = card1;
    }

    public int getDiamond2() {
        return diamond2;
    }

    public void setDiamond2(int diamond2) {
        this.diamond2 = diamond2;
    }

    public int getCoin2() {
        return coin2;
    }

    public void setCoin2(int coin2) {
        this.coin2 = coin2;
    }

    public int getCard2() {
        return card2;
    }

    public void setCard2(int card2) {
        this.card2 = card2;
    }

    public int getDiamond3() {
        return diamond3;
    }

    public void setDiamond3(int diamond3) {
        this.diamond3 = diamond3;
    }

    public int getCoin3() {
        return coin3;
    }

    public void setCoin3(int coin3) {
        this.coin3 = coin3;
    }

    public int getCard3() {
        return card3;
    }

    public void setCard3(int card3) {
        this.card3 = card3;
    }

    public int getDiamond4() {
        return diamond4;
    }

    public void setDiamond4(int diamond4) {
        this.diamond4 = diamond4;
    }

    public int getCoin4() {
        return coin4;
    }

    public void setCoin4(int coin4) {
        this.coin4 = coin4;
    }

    public int getCard4() {
        return card4;
    }

    public void setCard4(int card4) {
        this.card4 = card4;
    }

    public int getLevel5HP() {
        return level5HP;
    }

    public void setLevel5HP(int level5hp) {
        level5HP = level5hp;
    }

    public int getDiamond5() {
        return diamond5;
    }

    public void setDiamond5(int diamond5) {
        this.diamond5 = diamond5;
    }

    public int getCoin5() {
        return coin5;
    }

    public void setCoin5(int coin5) {
        this.coin5 = coin5;
    }

    public int getCard5() {
        return card5;
    }

    public void setCard5(int card5) {
        this.card5 = card5;
    }

    public int getLevel1() {
        return level1;
    }

    public void setLevel1(int level1) {
        this.level1 = level1;
    }

    public int getLevel2() {
        return level2;
    }

    public void setLevel2(int level2) {
        this.level2 = level2;
    }

    public int getLevel3() {
        return level3;
    }

    public void setLevel3(int level3) {
        this.level3 = level3;
    }

    public int getLevel4() {
        return level4;
    }

    public void setLevel4(int level4) {
        this.level4 = level4;
    }

    public int getLevel5() {
        return level5;
    }

    public void setLevel5(int level5) {
        this.level5 = level5;
    }

    public String getCard1_pinzhi() {
        return card1_pinzhi;
    }

    public void setCard1_pinzhi(String card1_pinzhi) {
        this.card1_pinzhi = card1_pinzhi;
    }

    public String getCard2_pinzhi() {
        return card2_pinzhi;
    }

    public void setCard2_pinzhi(String card2_pinzhi) {
        this.card2_pinzhi = card2_pinzhi;
    }

    public String getCard3_pinzhi() {
        return card3_pinzhi;
    }

    public void setCard3_pinzhi(String card3_pinzhi) {
        this.card3_pinzhi = card3_pinzhi;
    }

    public String getCard4_pinzhi() {
        return card4_pinzhi;
    }

    public void setCard4_pinzhi(String card4_pinzhi) {
        this.card4_pinzhi = card4_pinzhi;
    }

    public String getCard5_pinzhi() {
        return card5_pinzhi;
    }

    public void setCard5_pinzhi(String card5_pinzhi) {
        this.card5_pinzhi = card5_pinzhi;
    }

}