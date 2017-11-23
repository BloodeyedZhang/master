package game_server_parent.master.game.mall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.mina.core.session.IoSession;

import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigMall;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.mall.message.ReqBuyMessage;
import game_server_parent.master.game.mall.message.ReqMallConfigMessage;
import game_server_parent.master.game.mall.message.ResBuyMessage;
import game_server_parent.master.game.mall.message.ResMallConfigMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.record.events.EventMallRecord;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionProperties;
import game_server_parent.master.net.annotation.Controller;
import game_server_parent.master.net.annotation.RequestMapping;
import game_server_parent.master.utils.DateUtil;

/**
 * <p>Filename:MallController.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Controller
public class MallController {

    @RequestMapping
    public void onReqMallConfig(IoSession session, ReqMallConfigMessage request) {
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        
        Collection<ConfigMall> all = ConfigDatasPool.getInstance().configMallContainer.getAll();
        List<ConfigMall> malls = new ArrayList<ConfigMall>(all);
        
        // 重新设置价格
        Player player = PlayerManager.getInstance().get(player_id);
        int buy_key_num = player.getBuy_key_num();
        int feibonaqie = MallManager.getInstance().getFeibonaqie(buy_key_num);
        for(int i=0, l=malls.size(); i<l;i++) {
            ConfigMall configMall = malls.get(i);
            if(configMall.getName().equals("宝库钥匙")) {
                configMall.setMoney(feibonaqie);
                break;
            }
        }
        ResMallConfigMessage resMallConfigMessage = new ResMallConfigMessage();
        resMallConfigMessage.setGoods(malls);
        
        MessagePusher.pushMessage(player_id, resMallConfigMessage);
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping
    public void onReqBuy(IoSession session, ReqBuyMessage request) {
        long player_id = (long)session.getAttribute(SessionProperties.PLAYER_ID);
        int id = request.getId();
        ConfigMall configMall = ConfigDatasPool.getInstance().configMallContainer.getConfigBy(id);
        int coinType = configMall.getCoinType();
        Player player = PlayerManager.getInstance().get(player_id);
        
        ResBuyMessage message = new ResBuyMessage();
        Object obj = null;
        if(coinType==MallDataPool.COINTYPE_COIN) {
            int money1 = player.getMoney1();
            if((money1-=configMall.getMoney()) >= 0) {
                EventAttrChange eventAttrChange = new EventAttrChange(EventType.MONEY1_DEDUCT, player_id);
                eventAttrChange.setMoney1_change((int)configMall.getMoney());
                eventAttrChange.setSource_evtType(EventType.MALL_BUY);
                EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送金币减少事件
                
                message.setDeduct_type(1);
                message.setDeduct_num((int)configMall.getMoney());
                obj = MallManager.getInstance().openPackage(player, configMall);
            } else {
                message.setCode(MallDataPool.BUY_FAI);
                message.setTips("NOT_ENOUGH_COINS");
            }
        } else if(coinType==MallDataPool.COINTYPE_DIAMOND) {
            int money2 = player.getMoney2();
            if((money2-=configMall.getMoney()) >= 0) {
                EventAttrChange eventAttrChange = new EventAttrChange(EventType.MONEY2_DEDUCT, player_id);
                eventAttrChange.setMoney1_change((int)configMall.getMoney());
                eventAttrChange.setSource_evtType(EventType.MALL_BUY);
                EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送宝石减少事件
                
                message.setDeduct_type(2);
                message.setDeduct_num((int)configMall.getMoney());
                
                if(configMall.getName().equals("宝库钥匙")) {
                    int buy_key_num = player.getBuy_key_num();
                    player.setBuy_key_num(++buy_key_num);
                    int keyNum = player.getKeyNum();
                    player.setKeyNum(++keyNum);
                    obj = configMall.getNum();
                } else {
                    obj = MallManager.getInstance().openPackage(player, configMall);
                }
                
                
            } else {
                message.setCode(MallDataPool.BUY_FAI);
                message.setTips("NOT_ENOUGH_DIAMONDS");
            }
        } else if(coinType==MallDataPool.COINTYPE_CNY) {
            //obj = MallManager.getInstance().openPackage(player, configMall);
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
                message.setCode(id);
            } else if(type==MallDataPool.TYPE_KAPAI) {
                message.setKapais((List<Kapai>)obj);
                message.setCode(id);
            }
            
            // 发送记录商品购买记录事件
            
            EventMallRecord eventMallRecord = new EventMallRecord(EventType.MALL_BUY_RECORD, player_id);
            eventMallRecord.setGoods_id(configMall.getId());
            eventMallRecord.setCreatetime(DateUtil.getCurrentTime());
            EventDispatcher.getInstance().fireEvent(eventMallRecord);
            
        }
        
        
        MessagePusher.pushMessage(player_id, message);
        

    }
}
