package game_server_parent.master.game.kapai;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.kapai.events.EventKapaiNew;
import game_server_parent.master.game.kapai.events.EventKapai;
import game_server_parent.master.game.kapai.events.EventKapaiUpdate;
import game_server_parent.master.game.kapai.message.ResKapaiRemoveMessage;
import game_server_parent.master.game.kapai.message.ResSelectPlayerKapaiMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionManager;

/**
 * <p>Filename:KapaiListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class KapaiListener {
    private Logger logger = LoggerFactory.getLogger(KapaiListener.class);

    @EventHandler(value=EventType.KAPAI_NEW)
    public void onKapaiNew(EventKapaiNew kapaiNewEvent) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+kapaiNewEvent);
        
        Kapai kapai = KapaiManager.getInstance().createNewKapai(kapaiNewEvent.getPlayerId(), kapaiNewEvent.getDalei(), kapaiNewEvent.getBingzhong());
        DbService.getInstance().add2Queue(kapai);
        
        ArrayList<Kapai> list = new ArrayList<Kapai>();
        list.add(kapai);
        
        IoSession session = SessionManager.INSTANCE.getSessionBy(kapaiNewEvent.getPlayerId());
        MessagePusher.pushMessage(session, new ResSelectPlayerKapaiMessage(list));
    }
    
    @EventHandler(value=EventType.KAPAI_UPDATE)
    public void onKapaiUpdate(EventKapaiUpdate kapaiUpdateEvent) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+kapaiUpdateEvent);
        
        List<Kapai> kapais = kapaiUpdateEvent.getKapais();
        if(kapais.size()==2) {
            Kapai kapai = kapais.get(0);
            Kapai kapai2 = KapaiManager.getInstance().get((long)kapai.getKapai_id());
            kapai2.setS_dengji(kapai.getS_dengji());
            kapai2.setJiachengbi(kapai.getJiachengbi());
            kapai2.setJingyan(kapai.getJingyan());
            kapai2.setPinzhi(kapai.getPinzhi());
            kapai2.setXingji(kapai.getXingji());
            
            kapai2.setInsert();
            kapai2.setDelete();
            kapai2.setUpdate();
            DbService.getInstance().add2Queue(kapai2);
            
            ArrayList<Kapai> list = new ArrayList<Kapai>();
            list.add(kapai2);
            
            IoSession session = SessionManager.INSTANCE.getSessionBy(kapaiUpdateEvent.getPlayerId());
            MessagePusher.pushMessage(session, new ResSelectPlayerKapaiMessage(list));
            
            kapais.remove(kapai);
            EventDispatcher.getInstance().fireEvent(new EventKapaiUpdate(EventType.KAPAI_REMOVE, kapaiUpdateEvent.getPlayerId(), kapais));
        }
    }
    
    @EventHandler(value= EventType.KAPAI_SELL)
    public void onKapaiSell(EventKapai eventKapai) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+eventKapai);
        
        Kapai kapai = KapaiManager.getInstance().get((long)eventKapai.getKapai_id());
        ArrayList<Kapai> list = new ArrayList<Kapai>();
        list.add(kapai);

        EventDispatcher.getInstance().fireEvent(new EventKapaiUpdate(EventType.KAPAI_REMOVE, eventKapai.getPlayerId(), list));
    }
    
    @EventHandler(value= {EventType.KAPAI_REMOVE})
    public void onKapaiRemove(EventKapaiUpdate kapaiUpdateEvent) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+kapaiUpdateEvent);
        
        IoSession session = SessionManager.INSTANCE.getSessionBy(kapaiUpdateEvent.getPlayerId());
        
        List<Kapai> kapais2 = new ArrayList<Kapai>();
        
        long playerId = kapaiUpdateEvent.getPlayerId();
        List<Kapai> kapais = kapaiUpdateEvent.getKapais();
        for (Kapai kapai : kapais) {
            Kapai kapai2 = KapaiManager.getInstance().get((long)kapai.getKapai_id());
            if(kapai2.getPlayer_id()==playerId) {
                if(kapai2.isInsert()) {
                    kapai2.setDelete();
                }
                kapai2.setDelete();
                DbService.getInstance().add2Queue(kapai2);
                
                kapais2.add(kapai2);
            }
            
        }
        MessagePusher.pushMessage(session, new ResKapaiRemoveMessage(kapais));
    }
}
