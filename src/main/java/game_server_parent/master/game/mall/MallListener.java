package game_server_parent.master.game.mall;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigMall;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.mall.events.EventBuyGoodSuc;
import game_server_parent.master.game.mall.message.ResBuyMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;

/**
 * <p>Filename:MallListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月17日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class MallListener {

    private Logger logger = LoggerFactory.getLogger(MallListener.class);
    
    @EventHandler(value=EventType.MALL_BUY_SUC)
    public void onBuyGoodSuc(EventBuyGoodSuc event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件 "+event);
        
        long player_id = event.getPlayerId();
        int good_id = event.getGood_id();
        ConfigMall configMall = ConfigDatasPool.getInstance().configMallContainer.getConfigBy(good_id);
        int coinType = configMall.getCoinType();
        Player player = PlayerManager.getInstance().get(player_id);
        ResBuyMessage message = new ResBuyMessage();
        Object obj = null;
        if(coinType==MallDataPool.COINTYPE_CNY) {
            obj = MallManager.getInstance().openPackage(player, configMall);
        }
        
        if(obj!=null) {
            int type = configMall.getType();
            if(type==MallDataPool.TYPE_DIAMOND) {
                message.setDiamond((int)obj);
            } else if(type==MallDataPool.TYPE_KEYS) {
                message.setKeyNum(player.getKeyNum());
                int buy_key_num = player.getBuy_key_num();
                int feibonaqie = MallManager.getInstance().getFeibonaqie(buy_key_num);
                message.setDiamond(feibonaqie);
                message.setCode(good_id);
            } else if(type==MallDataPool.TYPE_KAPAI) {
                message.setKapais((List<Kapai>)obj);
                message.setCode(good_id);
            }
        }
        
        MessagePusher.pushMessage(player_id, message);
    }
}
