package game_server_parent.master.game.server;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>Filename:ServerRecordPool.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public enum ServerRecordPool {
    INSTANCE;
    
    private ConcurrentMap<String,String> serverRecords = new ConcurrentHashMap<>();
    
    public void loadAllRecords() {
        
    }
}
