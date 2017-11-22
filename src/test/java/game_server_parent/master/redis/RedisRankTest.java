package game_server_parent.master.redis;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import game_server_parent.master.ServerConfig;
import game_server_parent.master.game.crossrank.CrossRank;
import game_server_parent.master.game.crossrank.CrossRankKinds;
import game_server_parent.master.game.crossrank.CrossRankService;
import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
import game_server_parent.master.game.crossrank.impl.CrossLevelRank;

/**
 * <p>Filename:RedisRankTest.java</p>
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
public class RedisRankTest {
    
    /*
    @Test
    public void test() {
        ServerConfig.getInstance().initFromConfigFile();
        RedisCluster cluster = RedisCluster.INSTANCE;
        cluster.init();
        //cluster.clearAllData();
        CrossRankService rankService = CrossRankService.getInstance();
        
        final int N_RECORD =  10;
        for (int i=1;i<N_RECORD*2;i++) {
            rankService.addRank(new CrossLevelRank(i, 100+i));
        }
        
        List<CrossRank> ranks = rankService.queryRank(CrossRankKinds.FIGHTING, 0, N_RECORD);
        for (CrossRank rank:ranks) {
            System.err.println(rank);
        }
        assertTrue(ranks.size() == N_RECORD);
        assertTrue(ranks.get(0).getScore() >= ranks.get(1).getScore());
        
    }
    */
    
    
    @Test
    public void testCrossBonusRank() {
        ServerConfig.getInstance().initFromConfigFile();
        RedisCluster cluster = RedisCluster.INSTANCE;
        cluster.init();
        cluster.clearAllData();
        /*
        CrossRankService rankService = CrossRankService.getInstance();
        
        final int N_RECORD =  10;
        rankService.addRank(new CrossBonusPointsRank(1000000001, 0, 1, "test1000000001"));
        rankService.addRank(new CrossBonusPointsRank(1000000002, 0, 3, "test1000000002"));
        rankService.addRank(new CrossBonusPointsRank(1000000003, 0, 3, "test1000000003"));
       // rankService.addRank(new CrossBonusPointsRank(1000000001, 350, 1, "王五"));
        
        List<CrossRank> ranks = rankService.queryRank(CrossRankKinds.BONUS_POINTS, 0, 100);
        for (CrossRank rank:ranks) {
            System.err.println(rank);
        }
        
        //CrossRank queryOne = rankService.queryOne(CrossRankKinds.BONUS_POINTS, new CrossBonusPointsRank(1000000002));
        CrossBonusPointsRank cbpr = new CrossBonusPointsRank(1000000002);
        CrossRank queryOne = CrossRankService.getInstance().queryOne(CrossRankKinds.BONUS_POINTS, cbpr);
        Long revrank = CrossRankService.getInstance().queryRevrank(CrossRankKinds.BONUS_POINTS, cbpr);
        System.err.println("rank="+(revrank+1));
        //assertTrue(ranks.size() == N_RECORD);
        assertTrue(ranks.get(0).getScore() >= ranks.get(1).getScore());
        */
    }

    
}
