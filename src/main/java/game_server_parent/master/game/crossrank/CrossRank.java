package game_server_parent.master.game.crossrank;

/**
 * <p>Filename:CrossRank.java</p>
 * <p>Description: 
 *  cross server rank based on Redis SortedSet
 *  redis sortedset's score is  double type, which has only 52 bits
 * </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月10日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public interface CrossRank {

    int getRankType();

    /**
     * local server id
     * @return
     */
    int getServerId();

    long getCreateTime() ;
    
    void setPlayerId(long playerId);
    
    long getPlayerId();

    /** 
     *  first level rank score
     * @return
     */
    int getScore() ;

    /** 
     *  second level rank score
     * @return
     */
    int getAid() ;
    
    /**
     * @return
     */
    String getName();

    /** redis rank type key */
    String buildRankKey();
    
    /** redis rank record key */
    String buildResultKey();

    /** redis rank score */
    double buildRankScore();
    
    long getResult();
    
}
