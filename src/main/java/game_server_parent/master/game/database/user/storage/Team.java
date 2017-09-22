package game_server_parent.master.game.database.user.storage;

import game_server_parent.master.db.BaseEntity;

/**
 * <p>Filename:Team.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月21日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class Team extends BaseEntity {

    /* (non-Javadoc)
     * @see game_server_parent.master.db.BaseEntity#getId()
     */
    @Override
    public Long getId() {
        return null;
    }

    /**
     * @param sum_shengming
     */
    public abstract void setShengmingzhi(int sum_shengming);

    /**
     * @param sum_gongji
     */
    public abstract void setGongjizhi(int sum_gongji);

    /**
     * @return
     */
    public abstract String getSoilderIds();

}
