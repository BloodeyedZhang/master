package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;

/**
 * <p>
 * Filename:configTreasury.java
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
@Entity(readOnly = true)
public class ConfigTreasury {
    @Id
    @Column
    private int treasuryLevel;

    @Column
    private int baseHP;

    @Column
    private int level1HP;

    @Column
    private int level2HP;

    @Column
    private int level3HP;

    @Column
    private int level4HP;

    @Column
    private float diamondMiu1;

    @Column
    private float diamondSigma1;

    @Column
    private float coinMiu1;

    @Column
    private float coinSigma1;

    @Column
    private float cardMiu1;

    @Column
    private float cardSigma1;

    @Column
    private float diamondMiu2;

    @Column
    private float diamondSigma2;

    @Column
    private float coinMiu2;

    @Column
    private float coinSigma2;

    @Column
    private float cardMiu2;

    @Column
    private float cardSigma2;

    @Column
    private float diamondMiu3;

    @Column
    private float diamondSigma3;

    @Column
    private float coinMiu3;

    @Column
    private float coinSigma3;

    @Column
    private float cardMiu3;

    @Column
    private float cardSigma3;

    @Column
    private float diamondMiu4;

    @Column
    private float diamondSigma4;

    @Column
    private float coinMiu4;

    @Column
    private float coinSigma4;

    @Column
    private float cardMiu4;

    @Column
    private float cardSigma4;

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

    public float getDiamondMiu1() {
        return diamondMiu1;
    }

    public void setDiamondMiu1(float diamondMiu1) {
        this.diamondMiu1 = diamondMiu1;
    }

    public float getDiamondSigma1() {
        return diamondSigma1;
    }

    public void setDiamondSigma1(float diamondSigma1) {
        this.diamondSigma1 = diamondSigma1;
    }

    public float getCoinMiu1() {
        return coinMiu1;
    }

    public void setCoinMiu1(float coinMiu1) {
        this.coinMiu1 = coinMiu1;
    }

    public float getCoinSigma1() {
        return coinSigma1;
    }

    public void setCoinSigma1(float coinSigma1) {
        this.coinSigma1 = coinSigma1;
    }

    public float getCardMiu1() {
        return cardMiu1;
    }

    public void setCardMiu1(float cardMiu1) {
        this.cardMiu1 = cardMiu1;
    }

    public float getCardSigma1() {
        return cardSigma1;
    }

    public void setCardSigma1(float cardSigma1) {
        this.cardSigma1 = cardSigma1;
    }

    public float getDiamondMiu2() {
        return diamondMiu2;
    }

    public void setDiamondMiu2(float diamondMiu2) {
        this.diamondMiu2 = diamondMiu2;
    }

    public float getDiamondSigma2() {
        return diamondSigma2;
    }

    public void setDiamondSigma2(float diamondSigma2) {
        this.diamondSigma2 = diamondSigma2;
    }

    public float getCoinMiu2() {
        return coinMiu2;
    }

    public void setCoinMiu2(float coinMiu2) {
        this.coinMiu2 = coinMiu2;
    }

    public float getCoinSigma2() {
        return coinSigma2;
    }

    public void setCoinSigma2(float coinSigma2) {
        this.coinSigma2 = coinSigma2;
    }

    public float getCardMiu2() {
        return cardMiu2;
    }

    public void setCardMiu2(float cardMiu2) {
        this.cardMiu2 = cardMiu2;
    }

    public float getCardSigma2() {
        return cardSigma2;
    }

    public void setCardSigma2(float cardSigma2) {
        this.cardSigma2 = cardSigma2;
    }

    public float getDiamondMiu3() {
        return diamondMiu3;
    }

    public void setDiamondMiu3(float diamondMiu3) {
        this.diamondMiu3 = diamondMiu3;
    }

    public float getDiamondSigma3() {
        return diamondSigma3;
    }

    public void setDiamondSigma3(float diamondSigma3) {
        this.diamondSigma3 = diamondSigma3;
    }

    public float getCoinMiu3() {
        return coinMiu3;
    }

    public void setCoinMiu3(float coinMiu3) {
        this.coinMiu3 = coinMiu3;
    }

    public float getCoinSigma3() {
        return coinSigma3;
    }

    public void setCoinSigma3(float coinSigma3) {
        this.coinSigma3 = coinSigma3;
    }

    public float getCardMiu3() {
        return cardMiu3;
    }

    public void setCardMiu3(float cardMiu3) {
        this.cardMiu3 = cardMiu3;
    }

    public float getCardSigma3() {
        return cardSigma3;
    }

    public void setCardSigma3(float cardSigma3) {
        this.cardSigma3 = cardSigma3;
    }

    public float getDiamondMiu4() {
        return diamondMiu4;
    }

    public void setDiamondMiu4(float diamondMiu4) {
        this.diamondMiu4 = diamondMiu4;
    }

    public float getDiamondSigma4() {
        return diamondSigma4;
    }

    public void setDiamondSigma4(float diamondSigma4) {
        this.diamondSigma4 = diamondSigma4;
    }

    public float getCoinMiu4() {
        return coinMiu4;
    }

    public void setCoinMiu4(float coinMiu4) {
        this.coinMiu4 = coinMiu4;
    }

    public float getCoinSigma4() {
        return coinSigma4;
    }

    public void setCoinSigma4(float coinSigma4) {
        this.coinSigma4 = coinSigma4;
    }

    public float getCardMiu4() {
        return cardMiu4;
    }

    public void setCardMiu4(float cardMiu4) {
        this.cardMiu4 = cardMiu4;
    }

    public float getCardSigma4() {
        return cardSigma4;
    }

    public void setCardSigma4(float cardSigma4) {
        this.cardSigma4 = cardSigma4;
    }
}
