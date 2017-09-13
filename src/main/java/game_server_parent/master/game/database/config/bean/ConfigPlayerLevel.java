package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

/**
 * <p>Filename:ConfigPlayerLevel.java</p>
 * <p>Description: 玩家等级配置表 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Entity(readOnly=true)
public class ConfigPlayerLevel {
    /**
     * 等级
     */
    @Column
    private int level;
    
    /**
     * 升到下一级别需要的经验
     */
    @Column
    private long needExp;
    
    /**
     * 最大体力
     */
    @Column
    private int vitality;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getNeedExp() {
        return needExp;
    }

    public void setNeedExp(long needExp) {
        this.needExp = needExp;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }
}
