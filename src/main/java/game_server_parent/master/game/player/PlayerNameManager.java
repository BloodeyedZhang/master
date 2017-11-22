package game_server_parent.master.game.player;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import com.baidu.bjf.remoting.protobuf.utils.StringUtils;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.crossrank.CrossRankService;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.player.PlayerName;
import game_server_parent.master.orm.utils.DbUtils;
import javassist.bytecode.Descriptor.Iterator;

/**
 * <p>Filename:PlayerNameManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class PlayerNameManager {
    private static PlayerNameManager instance = new PlayerNameManager();
    
    public static PlayerNameManager getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (CrossRankService.class) {
            if (instance == null) {
                instance = new PlayerNameManager();
                instance.init();
            }
        }
        return instance;
    }
    
    
    private Set<String> name_set = new HashSet<String>();
    
    private void init() {
        String sql = "SELECT * FROM player_name where id = 0";
        //sql = MessageFormat.format(sql, String.valueOf(playerId));
        PlayerName player = DbUtils.queryOne(DbUtils.DB_USER, sql, PlayerName.class);
        String name = player.getName();
        if(name!=null) {
            String[] strs = name.split("#");
            for(String str : strs) {
                if(StringUtils.isEmpty(str))
                    name_set.add(str);
            }
        }
    } 
    
    public void save() {
        StringBuffer sb = new StringBuffer();
        int size = name_set.size();
        java.util.Iterator<String> it = name_set.iterator();
        while(it.hasNext()) {
            sb.append(it.next()).append("#");
        }
        PlayerName playerName = new PlayerName();
        playerName.setName(sb.toString());
        
        DbService.getInstance().add2Queue(playerName);
    } 

    
    public synchronized boolean check(String name) {
        return !name_set.contains(name);
    }
    
    public synchronized boolean add(String name) {
        if(name_set.contains(name)) {
            return false;
        }
        name_set.add(name);
        return true;
    }
}
