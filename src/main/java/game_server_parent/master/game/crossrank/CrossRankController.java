package game_server_parent.master.game.crossrank;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
import game_server_parent.master.game.crossrank.message.CrossBpRank;
import game_server_parent.master.game.crossrank.message.ReqCrossBPRankMessage;
import game_server_parent.master.game.crossrank.message.ResCrossBpRankMessage;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;

/**
 * <p>Filename:CrossRankController.java</p>
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
@Controller
public class CrossRankController {

    @RequestMapping
    public void reqCrossBonusPontsRank(IoSession session, ReqCrossBPRankMessage request) {
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        // 取前一百名
        List<CrossRank> queryRank = CrossRankService.getInstance().queryRank(CrossRankKinds.BONUS_POINTS, 0, 100);
        CrossBonusPointsRank cbpr = new CrossBonusPointsRank(player_id);
        CrossRank queryOne = CrossRankService.getInstance().queryOne(CrossRankKinds.BONUS_POINTS, cbpr);
        Long revrank = CrossRankService.getInstance().queryRevrank(CrossRankKinds.BONUS_POINTS, cbpr);
        int count = queryRank.size();
        List<CrossBpRank> cbrs = new ArrayList<CrossBpRank>(count);
        for(int i=0; i<count;i++) {
            CrossBpRank crossBpRank = new CrossBpRank(i+1, queryRank.get(i));
            cbrs.add(crossBpRank);
        }
        
        CrossBpRank player_cbr = new CrossBpRank(revrank+1, queryOne);
        
        ResCrossBpRankMessage resCrossBpRankMessage = new ResCrossBpRankMessage(player_cbr, cbrs);
        
        MessagePusher.pushMessage(player_id, resCrossBpRankMessage);
    }
}
