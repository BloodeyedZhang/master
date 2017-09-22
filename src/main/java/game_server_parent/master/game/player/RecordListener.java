package game_server_parent.master.game.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.player.AttrChangeRecord;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;

/**
 * <p>Filename:RecordListener.java</p>
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
public class RecordListener {

    private Logger logger = LoggerFactory.getLogger(RecordListener.class);
    
    @EventHandler(value= {EventType.MONEY1_ADD, EventType.MONEY1_DEDUCT})
    public void onAttrChange(EventAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件 属性变化 统计"+event);
        
        AttrChangeRecord attrChangeRecord = new AttrChangeRecord();
        int record_id = AttrChangeRecordManager.getInstance().getNextId();
        attrChangeRecord.setRecord_id(record_id);
        attrChangeRecord.setSourceEvtType(event.getSource_evtType().name());
        attrChangeRecord.setTargetEvtType(event.getEventType().name());
        attrChangeRecord.setAttrChange(event.getMoney1_change());
        
        attrChangeRecord.setInsert();
        
        DbService.getInstance().add2Queue(attrChangeRecord);
    }
}
