package game_server_parent.master.game.record;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.database.user.record.AttrChangeRecord;
import game_server_parent.master.game.database.user.record.TreasuryRecord;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.record.events.EventTreasuryRecord;
import game_server_parent.master.game.treasury.events.EventTreasuryEnd;
import game_server_parent.master.game.treasury.message.ResTreasuryBattleEndMessage;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.utils.DateUtil;

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
    
    @EventHandler(value= {EventType.MONEY1_ADD, EventType.MONEY1_DEDUCT,EventType.MONEY2_ADD, EventType.MONEY2_DEDUCT, 
            EventType.BONUS_POINT_ADD, EventType.BONUS_POINTS_DEDUCT,
            EventType.BATTLE_ID_START, EventType.BATTLE_ID_LOSE, EventType.BATTLE_ID_WIN})
    public void onAttrChange(EventAttrChange event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件 属性变化 统计"+event);
        
        AttrChangeRecord attrChangeRecord = new AttrChangeRecord();
        int record_id = AttrChangeRecordManager.getInstance().getNextId();
        attrChangeRecord.setPlayer_id(event.getPlayerId());
        attrChangeRecord.setRecord_id(record_id);
        attrChangeRecord.setSourceEvtType(event.getSource_evtType().name());
        attrChangeRecord.setTargetEvtType(event.getEventType().name());
        attrChangeRecord.setAttrChange(event.getMoney1_change());
        
        String currentTime = DateUtil.getCurrentTime();
        attrChangeRecord.setExtra_param(currentTime);
        
        attrChangeRecord.setInsert();
        
       // DbService.getInstance().add2Queue(attrChangeRecord);
    }
    
    @EventHandler(value=EventType.TREASURY_DESTROY_BOX)
    public void onBoxDestroy(EventTreasuryRecord event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件 宝箱摧毁 统计宝库掉落"+event);
        long treasury_id = event.getTreasury_id();
        long player_id = event.getPlayerId();
        int index = event.getIndex();
        int coins = event.getCoins();
        int diamonds = event.getDiamonds();
        List<int[]> params = event.getParams();
        TreasuryRecordManager.getInstance().create(treasury_id, player_id, index, coins, diamonds, params);
    } 
    
    @EventHandler(value=EventType.TREASURY_BATTLE_END)
    public void onTreasuryEnd(EventTreasuryEnd event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件 宝库战斗结束  统计宝库掉落"+event);
        long treasury_id = event.getTreasury_id();
        long player_id = event.getPlayerId();
        
        List<TreasuryRecord> treasuryRecords = TreasuryRecordManager.getInstance().queryMany(treasury_id);
        int coin = 0;
        int diamond = 0;
        List<Kapai> kapais = new ArrayList<Kapai>();
        try {
            for (TreasuryRecord treasuryRecord : treasuryRecords) {
                coin += treasuryRecord.getCoins();
                diamond += treasuryRecord.getDiamonds();
                String bingzhongs = treasuryRecord.getBingzhongs();
                String[] s_bingzhong = bingzhongs.split(",");
                for(int i=0;i<s_bingzhong.length;i++) {
                    if(!s_bingzhong[i].equals("0")) {
                        int bingzhong = Integer.parseInt(s_bingzhong[i]);
                        
                        String[] s_jiachengbis = treasuryRecord.getJiachengbis().split(",");
                        String[] s_jiachengtypes = treasuryRecord.getJiachengtypes().split(",");
                        String[] s_pinzhis = treasuryRecord.getPinzhis().split(",");
                        String[] s_xingji = treasuryRecord.getXingjis().split(",");
                        
                        float jiachengbi = Float.parseFloat(s_jiachengbis[i]);
                        int jiachengzhonglei = Integer.parseInt(s_jiachengtypes[i]);
                        int pinzhi = Integer.parseInt(s_pinzhis[i]);
                        int xingji = Integer.parseInt(s_xingji[i]);
                        Kapai kapai = new Kapai();
                        kapai.setBingzhong(bingzhong);
                        kapai.setJiachengbi(jiachengbi);
                        kapai.setJiachengzhonglei(jiachengzhonglei);
                        kapai.setPinzhi(pinzhi);
                        kapai.setXingji(xingji);
                        kapais.add(kapai);
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("宝库战斗结束  统计宝库掉落", e);
        }
        
        logger.info(getClass().getSimpleName()+"发送返回客户端消息包");
        
        ResTreasuryBattleEndMessage message = new ResTreasuryBattleEndMessage();
        message.setCode(event.getBattle_result());
        message.setCoin(coin);
        message.setDiamond(diamond);
        message.setKapais(kapais);
        
        MessagePusher.pushMessage(player_id, message);
    }
}
