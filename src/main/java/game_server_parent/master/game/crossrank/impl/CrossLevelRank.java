package game_server_parent.master.game.crossrank.impl;

import game_server_parent.master.game.crossrank.AbstractCrossRank;
import game_server_parent.master.game.crossrank.CrossRankKinds;
import game_server_parent.master.game.crossrank.annotation.CrossHandler;

/**
 * <p>Filename:CrossLevelRank.java</p>
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
@CrossHandler(kind=CrossRankKinds.FIGHTING)
public class CrossLevelRank extends AbstractCrossRank {

    // just for jprotobuf
    public CrossLevelRank() {
        
    }

    public CrossLevelRank(long playerId, int score) {
        super(playerId, score);
//      System.err.println("score=="+score+"|"+String.valueOf(buildRankScore()));
    }
}
