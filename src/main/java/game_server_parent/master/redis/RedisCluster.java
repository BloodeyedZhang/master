package game_server_parent.master.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import game_server_parent.master.ServerConfig;
import game_server_parent.master.logs.LoggerUtils;
import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisException;

/**
 * <p>Filename:RedisCluster.java</p>
 * <p>Description: 对RedisCluster的简单封装，包括对Redis的各种数据操作 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月10日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public enum RedisCluster {

    INSTANCE;
    
    private JedisCluster cluster;
    
    protected static final int DEFAULT_MAX_REDIRECTIONS = 5;
    protected static final String DEFAULT_PASSWORD = "foobared-winturn";
    
    public  void init() {
        //String url = "127.0.0.1:8001";
        //String url = "182.92.69.140:7002";
        //ServerConfig.getInstance().initFromConfigFile();
        String[] ips = ServerConfig.getInstance().getIps();
        String pwd = ServerConfig.getInstance().getPwd();
        if(pwd.trim().equals("")) {
            pwd = DEFAULT_PASSWORD;
        }
        HashSet<HostAndPort> hostAndPorts = new HashSet<>();
        
        for (String url : ips) {
            String[] hostPort = url.split(":");
            HostAndPort hostAndPort = new HostAndPort(hostPort[0], Integer.parseInt(hostPort[1]));
            hostAndPorts.add(hostAndPort);
        }
        /*
        String[] hostPort = url.split(":");
        HostAndPort hostAndPort = new HostAndPort(hostPort[0], Integer.parseInt(hostPort[1]));
        hostAndPorts.add(hostAndPort);
        */
        
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(50);
        poolConfig.setMinIdle(2);
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxWaitMillis(5000);
        this.cluster = new JedisCluster(hostAndPorts, 2000, 2000, DEFAULT_MAX_REDIRECTIONS, DEFAULT_PASSWORD, poolConfig);
    }
    
    public  void destory() {
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private  TreeSet<String> keys(String pattern){  
        TreeSet<String> keys = new TreeSet<>();  
        //获取所有的节点
        Map<String, JedisPool> clusterNodes = cluster.getClusterNodes();  
        //遍历节点 获取所有符合条件的KEY 
        for (String k : clusterNodes.keySet()) {  
            JedisPool jp = clusterNodes.get(k);  
            Jedis connection = jp.getResource();  
            try {  
                keys.addAll(connection.keys(pattern));  
            } catch(Exception e) {  
            } finally{  
                connection.close();//用完一定要close这个链接！！！  
            }  
        }  
        return keys;  
    }  

    public  void clearAllData() {
        TreeSet<String> keys=keys("*");
        //遍历key  进行删除  可以用多线程
        for(String key:keys){
            cluster.del(key);
        }
    }
    

    public Double zscore(String key, String member) {
        try {
            return cluster.zscore(key, member);
        } catch (JedisException e) {
            LoggerUtils.error("", e);
            throw new JedisException(e);
        }
    }

    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        try {
            return cluster.zrangeWithScores(key, start, end);
        } catch (JedisException e) {
            LoggerUtils.error("", e);
            throw new JedisException(e);
        }
    }

    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        try {
            return cluster.zrevrangeWithScores(key, start, end);
        } catch (JedisException e) {
            LoggerUtils.error("", e);
            return new HashSet<>(0);
        }
    }
    
    public Double zincrby(String key, double score, String member) {
        try {
            return cluster.zincrby(key, score, member);
        } catch (JedisException e) {
            LoggerUtils.error("", e);
            return null;
        }
    }

    public Long zrank(String key, String member) {
        try {
            return cluster.zrank(key, member);
        } catch (JedisException e) {
            LoggerUtils.error("", e);
            return -1L;
        }
    }
    
    public Long zrevrank(String key, String member) {
        try {
            return cluster.zrevrank(key, member);
        } catch (JedisException e) {
            LoggerUtils.error("", e);
            return -1L;
        }
    }
    
    public long hset(String key, String field, String value) {
        try {
            return cluster.hset(key, field, value);
        } catch (JedisException e) {
            LoggerUtils.error("", e);
        }
        return -1L;
    }

    public String hget(String key, String field) {
        try {
            return cluster.hget(key, field);
        } catch (JedisException e) {
            LoggerUtils.error("", e);
            return null;
        }
    }
}
