package game_server_parent.master.game.crossrank;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
import game_server_parent.master.game.crossrank.impl.CrossUUID;
import game_server_parent.master.game.crossrank.message.CrossBpRank;
import game_server_parent.master.game.crossrank.message.ReqCrossBPRankMessage;
import game_server_parent.master.game.crossrank.message.ReqCrossUUIDMessage;
import game_server_parent.master.game.crossrank.message.ResCrossBpRankMessage;
import game_server_parent.master.game.crossrank.message.ResCrossUUIDMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
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
    private Logger logger = LoggerFactory.getLogger(CrossRankController.class);
    
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
    
    @RequestMapping
    public void reqCrossUUID(IoSession session, ReqCrossUUIDMessage request) {
       // long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        
        int platform = request.getPlatform(); //android:1;ios:2;
        String code = request.getCode();
        if(code==null) {
            ResCrossUUIDMessage resp = new ResCrossUUIDMessage(0);
            MessagePusher.pushMessage(session, resp);
            logger.debug("收到客户端发来的UUID为NULL.");
            return;
        }
        CrossUUID crossUUID = new CrossUUID(0, platform, code);
        CrossRank crossRank = CrossRankService.getInstance().queryOneByName(CrossRankKinds.UUID, crossUUID);
        
        int nextId = 0;
        // 不存在
        if(crossRank==null || crossRank.getPlayerId()==0) {
            nextId = PlayerManager.getInstance().getNextId();
            crossUUID.setPlayerId(nextId);
            CrossRankService.getInstance().addRankOnName(crossUUID);
            
            // 发送新用户事件
            EventDispatcher.getInstance().fireEvent(new EventNewPlayer(EventType.PLAYER_CREATE, nextId));
        } else {
            nextId = (int)crossRank.getPlayerId();
        }
        // 下发客户端消息
        ResCrossUUIDMessage resp = new ResCrossUUIDMessage(nextId);
        MessagePusher.pushMessage(session, resp);
        

      
    }
}
