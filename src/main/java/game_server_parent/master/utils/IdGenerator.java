package game_server_parent.master.utils;

import java.util.concurrent.atomic.AtomicLong;

import game_server_parent.master.ServerConfig;

/**
 * <p>Filename:IdGenerator.java</p>
 * <p>Description: 全局id生成器 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月28日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class IdGenerator {

    private static AtomicLong generator = new AtomicLong(0);
    
    /**
     * 生成全局唯一id
     */
    public static long getNextId() {
        //----------------id格式 -------------------------
        //----------long类型8个字节64个比特位----------------
        // 高16位          | 中32位          | 低16位
        // serverId   系统毫秒数        自增长号
        
        long serverId = (long)ServerConfig.getInstance().getServerId(); 
        //临时策略
        return  (serverId << 48)
              | (((System.currentTimeMillis()/1000) & 0xFFFFFFFF) << 16)
              | (generator.getAndIncrement() & 0xFFFF);
    }
}
