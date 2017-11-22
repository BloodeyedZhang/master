package game_server_parent.master.game.crossrank.impl;

import game_server_parent.master.game.crossrank.AbstractCrossRank;
import game_server_parent.master.game.crossrank.CrossRankKinds;
import game_server_parent.master.game.crossrank.annotation.CrossHandler;

/**
 * <p>Filename:CrossUUID.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@CrossHandler(kind=CrossRankKinds.UUID)
public class CrossUUID extends AbstractCrossRank {

    public CrossUUID() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CrossUUID(long playerId, int score) {
        super(playerId, score);
        // TODO Auto-generated constructor stub
    }

    public CrossUUID(long playerId, int aid, String name) {
        super(playerId, 1, aid, name);
    }

}
