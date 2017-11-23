package game_server_parent.master.game.player;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.crossrank.CrossRankKinds;
import game_server_parent.master.game.crossrank.CrossRankService;
import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.record.AttrChangeRecord;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.player.events.EventGmPlayerAttrChange;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.game.player.events.EventUpdatePlayer;
import game_server_parent.master.game.player.message.ResPlayerMessage;
import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionManager;

/**
 * <p>Filename:PlayerListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class PlayerListener {

    private Logger logger = LoggerFactory.getLogger(PlayerListener.class);
    
    @EventHandler(value= {EventType.PLAYER_UPDATE_MONEY})
    public void onPlayerUpdate(EventUpdatePlayer event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        Player player = PlayerManager.getInstance().get(player_id);
        
        CrossBonusPointsRank cbpr = new CrossBonusPointsRank(player_id);
        Long revrank = CrossRankService.getInstance().queryRevrank(CrossRankKinds.BONUS_POINTS, cbpr);
        if(revrank!=null)
            player.setRank((int)(revrank+1));
        
        ResPlayerMessage resPlayerMessage = new ResPlayerMessage();
        resPlayerMessage.setPlayer(player);
        
        IoSession session = SessionManager.INSTANCE.getSessionBy(player_id);
        MessagePusher.pushMessage(session, resPlayerMessage);
        
        
    }
    
    @EventHandler(value=EventType.PLAYER_CREATE)
    public void onPlayerNew(EventNewPlayer event) {
        long playerId = event.getPlayerId();
        Player player = PlayerManager.getInstance().createNewPlayer(playerId, "test"+playerId, (byte)1);
        DbService.getInstance().add2Queue(player);
        //PlayerNameManager.getInstance().add(player.getName());
    }
    
    @EventHandler(value=EventType.GM_ADD_MONEY)
    public void onGmPlayerAttrChange(EventGmPlayerAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件 GM 添加金币，钻石，宝库钥匙事件 "+event);
        
        Player player = PlayerManager.getInstance().get(event.getPlayerId());
        
        int money1 = player.getMoney1();
        int money2 = player.getMoney2();
        int keyNum = player.getKeyNum();
        money1+=event.getMoney_coin();
        money2+=event.getMoney_diamond();
        keyNum+=event.getKeynum();
        player.setMoney1(check(money1));
        player.setMoney2(check(money2));
        player.setKeyNum(check(keyNum));
        
        ResPlayerMessage resPlayerMessage = new ResPlayerMessage();
        resPlayerMessage.setPlayer(player);
        
        MessagePusher.pushMessage(event.getPlayerId(), resPlayerMessage);
    }
    
    private int check(int num) {
        return num<0?0:num;
    }

}
