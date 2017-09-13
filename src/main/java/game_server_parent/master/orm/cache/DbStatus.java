package game_server_parent.master.orm.cache;

/**
 * <p>Filename:DbStatus.java</p>
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
public enum DbStatus {
    /** 无需入库 */
    NORMAL,
    /** 需要更新 */
    UPDATE,
    /** 需要插入 */
    INSERT,
    /** 需要删除 */
    DELETE,
    
    ;
}
