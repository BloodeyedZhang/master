package game_server_parent.master.orm.cache;

/**
 * <p>Filename:Cacheable.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月28日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class Cacheable {
    /** 当前实体对象的db状态 */
    protected DbStatus status;
    
    public abstract DbStatus getStatus();
    
    public abstract boolean isInsert();
    
    public abstract boolean isUpdate();
    
    public abstract boolean isDelete();
    
    public abstract void setInsert();
    
    public abstract void setUpdate();
    
    public abstract void setDelete();
    
    /**
     * 进行持久化
     */
    public abstract void save();
}
