package game_server_parent.master.redis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * <p>Filename:RedisTest.java</p>
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
public class RedisTest {

    Jedis jedis ;

    @Before
    public void init() {
        //连接本地的 Redis 服务
        //jedis = new Jedis("localhost");
        jedis = new Jedis("182.92.69.140",7002);
        jedis.auth("foobared-winturn");
    }

    @Test
    public void testConnection() {
        System.out.println("connected succ");
        System.out.println("服务正在运行: "+jedis.ping());
    }

    @Test
    public void testSortedSet() {

        String key = "rank";
        jedis.zremrangeByRank(key, 0, 100);
        jedis.zadd("rank", 0, "a");
        jedis.zadd("rank", 0, "b");
        jedis.zadd("rank", 0, "c");
        jedis.zadd("rank", 5, "d");
        jedis.zadd("rank", 2, "e");
        System.out.println(jedis.clientList());

        assertTrue(jedis.zcount(key, 0, 100) == 5);
    }

}
