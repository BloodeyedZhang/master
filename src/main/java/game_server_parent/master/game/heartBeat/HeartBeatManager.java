package game_server_parent.master.game.heartBeat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.ServerConfig;
import game_server_parent.master.game.heartBeat.message.ReqClientHeartBeatMessage;
import game_server_parent.master.net.MessagePusher;

/**
 * <p>Filename:HeartBeatManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月15日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class HeartBeatManager {
    private static HeartBeatManager instance = new HeartBeatManager();

    public static HeartBeatManager getInstance() {
        return instance;
    }
    
    //客户端超时次数  
    private Map<IoSession,Integer> clientOvertimeMap = new ConcurrentHashMap<>();  
    //private final int MAX_OVERTIME  = 3;  //超时次数超过该值则注销连接  
    
    public void removeUserOvertime(IoSession session) {
        clientOvertimeMap.remove(session);//只要接受到数据包，则清空超时次数 
    }
    
    public void addUserOvertime(IoSession ctx){  
        int oldTimes = 0;  
        if(clientOvertimeMap.containsKey(ctx)){  
            oldTimes = clientOvertimeMap.get(ctx);  
        }
        clientOvertimeMap.put(ctx, (int)(oldTimes+1));  
    } 
    
    public boolean isUserOvertime(IoSession session) {
        int overtimeTimes = clientOvertimeMap.getOrDefault(session,0); 
        System.err.println("session="+session.getId()+"overtimeTimes="+overtimeTimes);
        return (overtimeTimes >= ServerConfig.MAX_RECONNECT_TIMES);
    }
}
