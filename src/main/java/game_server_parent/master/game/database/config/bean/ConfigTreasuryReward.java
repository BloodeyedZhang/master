package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>
 * Filename:ConfigTreasuryReward.java
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
@Entity(readOnly=true)
public class ConfigTreasuryReward {

    @Column
    private int treasuryLevel;

    @Column
    private int levelupDiamonds;

    @Column
    private int levelupCoins;

    @Column
    private String gotArmy;

    public int getTreasuryLevel() {
        return treasuryLevel;
    }

    public void setTreasuryLevel(int treasuryLevel) {
        this.treasuryLevel = treasuryLevel;
    }

    public int getLevelupDiamonds() {
        return levelupDiamonds;
    }

    public void setLevelupDiamonds(int levelupDiamonds) {
        this.levelupDiamonds = levelupDiamonds;
    }

    public int getLevelupCoins() {
        return levelupCoins;
    }

    public void setLevelupCoins(int levelupCoins) {
        this.levelupCoins = levelupCoins;
    }

    public String getGotArmy() {
        return gotArmy;
    }

    public void setGotArmy(String gotArmy) {
        this.gotArmy = gotArmy;
    }
}
