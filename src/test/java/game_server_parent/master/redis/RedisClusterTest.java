package game_server_parent.master.redis;

import org.junit.Test;

/**
 * <p>Filename:RedisClusterTest.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月10日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class RedisClusterTest {
    @Test
    public void test() {
        
        RedisCluster cluster = RedisCluster.INSTANCE;
        
        cluster.init();
        
        System.err.print(cluster.zrangeWithScores("rank", 0, 100).size());
    }
}
