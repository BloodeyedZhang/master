package game_server_parent.master.game.database.user.record;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;
import game_server_parent.master.utils.IdGenerator;

/**
 * <p>Filename:TreasuryRecord.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Entity(table="treasuryrecord")
public class TreasuryRecord extends BaseEntity<Long> {

    @Id
    @Column
    private long treasury_id;
    @Column
    private long id;
    @Column
    private long player_id;
    @Column
    private int index;
    @Column
    private int coins;
    @Column
    private int diamonds;
    @Column
    private String bingzhongs;
    @Column
    private String jiachengbis;
    @Column
    private String xingjis;
    @Column
    private String jiachengtypes;
    @Column
    private String pinzhis;
    @Column
    private String createtime;

    public TreasuryRecord() {
        this.id = IdGenerator.getNextId();
    }
    
    @Override
    public Long getId() {
        return id;
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public String getBingzhongs() {
        return bingzhongs;
    }

    public void setBingzhongs(String bingzhongs) {
        this.bingzhongs = bingzhongs;
    }

    public String getJiachengbis() {
        return jiachengbis;
    }

    public void setJiachengbis(String jiachengbis) {
        this.jiachengbis = jiachengbis;
    }

    public String getXingjis() {
        return xingjis;
    }

    public void setXingjis(String xingjis) {
        this.xingjis = xingjis;
    }

    public String getJiachengtypes() {
        return jiachengtypes;
    }

    public void setJiachengtypes(String jiachengtypes) {
        this.jiachengtypes = jiachengtypes;
    }

    public String getPinzhis() {
        return pinzhis;
    }

    public void setPinzhis(String pinzhis) {
        this.pinzhis = pinzhis;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
