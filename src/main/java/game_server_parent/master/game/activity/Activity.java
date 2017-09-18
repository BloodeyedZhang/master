package game_server_parent.master.game.activity;

/**
 * <p>Filename:Activity.java</p>
 * <p>Description: 活动数据载体 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class Activity {
    /** 活动id */
    private int id;
    /** 活动类型 {@link ActivityTypes#getType()} */
    private int type;
    /** 当前是否开放 */
    private boolean opened;
    
    public Activity(int type, int id) {
        this.type = type;
        this.id   = id;
    }
    
    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }
    
    public String getSerializeKey() {
        return this.type + "_" + this.id;
    }
    
    protected String serializeTostring() {
        return null;
    }
    
    public void setOpened(boolean open) {
        this.opened = open;
    }
    
    public boolean isOpened() {
        return this.opened;
    }
}
