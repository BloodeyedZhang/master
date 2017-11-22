package game_server_parent.master.game.mall;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.database.config.bean.ConfigMall;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.kapai.events.EventKapaiNew;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.treasury.ChoukaDataPool;
import game_server_parent.master.game.treasury.ChoukaManager;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.utils.ArrayUtils;
import javassist.expr.NewArray;

/**
 * <p>
 * Filename:MallManager.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.
 * </p>
 * <p>
 * Company: WinTurn Network Technology
 * </p>
 * <p>
 * Summary:
 * </p>
 * <p>
 * Created: 2017年10月20日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
public class MallManager {

    private Logger logger = LoggerFactory.getLogger(MallManager.class);
    

    private static MallManager instance = new MallManager();

    public static MallManager getInstance() {
        return instance;
    }

    /**
     * 开包
     * 
     * @return 0:keysNum;1:diamond2:List<Kapai>
     */
    public Object openPackage(Player player, ConfigMall configMall) {
        int type = configMall.getType();
        int num = configMall.getNum();
        long player_id = player.getPlayer_id();
        if (type == MallDataPool.TYPE_DIAMOND) {

            EventAttrChange eventAttrChange = new EventAttrChange(EventType.MONEY2_ADD, player_id);
            eventAttrChange.setMoney1_change(num);
            eventAttrChange.setSource_evtType(EventType.MALL_BUY);
            EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送宝石增加事件

            return num;
        } else if (type == MallDataPool.TYPE_KEYS) {

            EventAttrChange eventAttrChange = new EventAttrChange(EventType.KEYSNUM_ADD, player_id);
            eventAttrChange.setMoney1_change(num);
            eventAttrChange.setSource_evtType(EventType.MALL_BUY);
            EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送钥匙增加事件

            return num;
        } else if (type == MallDataPool.TYPE_KAPAI) {

            int id = configMall.getId();
            String type_name = ChoukaDataPool.SMALL_KABAO;
            if (id == 10001) {
                type_name = ChoukaDataPool.SMALL_KABAO;
            } else if (id == 10002) {
                type_name = ChoukaDataPool.LARGE_KABAO;
            } else if (id == 10003) {
                type_name = ChoukaDataPool.SUPERLARGE_KABAO;
            } else {
                return null;
            }
            List<Kapai> kapais = ChoukaManager.getInstance().getKapais(num, player.getTreasuryLevel(), type_name, player.getPlayer_id());

            int count = kapais.size();

            for (int i = 0; i < count; i++) {
                // 发送新卡牌事件
                Kapai kapai = kapais.get(i);
                EventDispatcher.getInstance().fireEvent(new EventKapaiNew(EventType.KAPAI_NEW, player_id, kapai.getPinzhi(),
                        kapai.getBingzhong(), kapai.getS_dengji(), kapai.getJiachengzhonglei(), kapai.getJiachengbi() / 100F, kapai.getXingji()));
            }
            return kapais;
        }
        return null;
    }
    

    public int getFeibonaqie(int index) {
        return ArrayUtils.getFeibonaqie(index);
    }
}
