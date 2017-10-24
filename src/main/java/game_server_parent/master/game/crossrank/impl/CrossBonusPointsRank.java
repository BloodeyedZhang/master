package game_server_parent.master.game.crossrank.impl;

import game_server_parent.master.game.crossrank.AbstractCrossRank;
import game_server_parent.master.game.crossrank.CrossRankKinds;
import game_server_parent.master.game.crossrank.annotation.CrossHandler;

/**
 * <p>Filename:CrossBonusPointsRank.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月19日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@CrossHandler(kind=CrossRankKinds.BONUS_POINTS)
public class CrossBonusPointsRank extends AbstractCrossRank {
    // just for jprotobuf
    public CrossBonusPointsRank() {
        
    }

    public CrossBonusPointsRank(long playerId) {
        super(playerId, 0);
    }
    
    public CrossBonusPointsRank(long playerId, int bonusPints, int treasury_level, String name) {
        super(playerId, bonusPints, treasury_level, name);
    }

    @Override
    public int getRankType() {
        // TODO Auto-generated method stub
        return CrossRankKinds.BONUS_POINTS;
    }
    
}
