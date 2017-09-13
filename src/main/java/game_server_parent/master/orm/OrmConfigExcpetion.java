package game_server_parent.master.orm;

/**
 * <p>Filename:OrmConfigExcpetion.java</p>
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
public class OrmConfigExcpetion extends RuntimeException {
    private static final long serialVersionUID = 1788051162447455031L;

    public OrmConfigExcpetion(Exception e) {
        super(e);
    }
    
    public OrmConfigExcpetion(String message) {
        super(message);
    }
}
