package game_server_parent.master.game.crossrank;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import game_server_parent.master.game.crossrank.annotation.CrossHandler;
import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
import game_server_parent.master.game.crossrank.impl.CrossLevelRank;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.annotation.MessageMeta;
import game_server_parent.master.redis.RedisCluster;
import game_server_parent.master.redis.RedisCodecHelper;
import game_server_parent.master.utils.ClassFilter;
import game_server_parent.master.utils.ClassScanner;
import redis.clients.jedis.Tuple;

/**
 * <p>Filename:CrossRankService.java</p>
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
public class CrossRankService {

    private static CrossRankService instance;

    private RedisCluster cluster = RedisCluster.INSTANCE;

    private Map<Integer, Class<? extends AbstractCrossRank>> rank2Class = new HashMap<>();
    
    private final String SCAN_PATH = "game_server_parent.master.game.crossrank.impl";

    public static CrossRankService getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (CrossRankService.class) {
            if (instance == null) {
                instance = new CrossRankService();
                instance.init();
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void init() {
        Set<Class<?>> crossHanders = ClassScanner.listAllSubclasses(SCAN_PATH, AbstractCrossRank.class);
        
        for(Class<?> crossHander : crossHanders) {
            Class<? extends AbstractCrossRank> clazz = (Class<? extends AbstractCrossRank>) crossHander;
            CrossHandler protocol = crossHander.getAnnotation(CrossHandler.class);
            rank2Class.put(new Integer(protocol.kind()), clazz);
        }
    }
    
    public void addRank(CrossRank rank) {
        String key = rank.buildRankKey();
        String member = buildRankMember(rank.getPlayerId());
        double score = rank.buildRankScore();
        cluster.zincrby(key, score, member); 

        // add challenge result data.
        String data = RedisCodecHelper.serialize(rank);
        cluster.hset(rank.buildResultKey(), member, data);
    }
    
    private String buildRankMember(long  playerId) {
        return String.valueOf(playerId);
    }
    
    public List<CrossRank> queryRank(int rankType, int start, int end) {
        List<CrossRank> ranks = new ArrayList<>();
        Set<Tuple> tupleSet = cluster.zrevrangeWithScores("CrossRank_"  + rankType, start , end );
        
        Class<? extends AbstractCrossRank> rankClazz = rank2Class.get(rankType);
        for (Tuple record:tupleSet) {
            try{
                String element = record.getElement();
                AbstractCrossRank rankProto = rankClazz.newInstance();
                String resultKey = rankProto.buildResultKey();
                String data = cluster.hget(resultKey, element);
                CrossRank rank = unserialize(data, rankClazz);
                ranks.add(rank);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return ranks;
    }
    
    public CrossRank queryOne(int rankType, CrossRank rank) {
        
        String member = buildRankMember(rank.getPlayerId());
        String data = cluster.hget(rank.buildResultKey(), member);
        Class<? extends AbstractCrossRank> rankClazz = rank2Class.get(rankType);
        return unserialize(data, rankClazz);
    }
    
    /**
     * 从大到小返回排名
     * @param rankType
     * @param rank
     * @return
     */
    public Long queryRevrank(int rankType, CrossRank rank) {
        String key = rank.buildRankKey();
        String member = buildRankMember(rank.getPlayerId());
        return cluster.zrevrank(key, member);
    } 

    public <T extends CrossRank>  T unserialize(String rankData, Class<T> clazz) {
        return RedisCodecHelper.deserialize(rankData, clazz);
    }
}
