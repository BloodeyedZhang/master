package game_server_parent.master.game.treasury;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.tidy.ParserImpl.ParseBlock;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigTreasury;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.KapaiId;
import game_server_parent.master.game.database.user.storage.Treasury;
import game_server_parent.master.game.kapai.KapaiDataPool;
import game_server_parent.master.game.kapai.events.EventKapaiNew;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.game.record.events.EventTreasuryRecord;
import game_server_parent.master.game.treasury.events.EventTreasuryEnd;
import game_server_parent.master.game.treasury.message.ResTreasuryBattleEndMessage;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.orm.utils.DbUtils;
import game_server_parent.master.utils.RollUtils;

/**
 * <p>
 * Filename:TreasuryManager.java
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
 * Created: 2017年10月13日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
public class TreasuryManager extends CacheService<Long, Treasury> {
    private Logger logger = LoggerFactory.getLogger(TreasuryManager.class);

    private static TreasuryManager instance = new TreasuryManager();

    public static TreasuryManager getInstance() {
        return instance;
    }

    @Override
    public Treasury load(Long player_id) throws Exception {
        String sql = "SELECT * FROM treasury where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(player_id));
        Treasury player = DbUtils.queryOne(DbUtils.DB_USER, sql, Treasury.class);
        return player;
    }

    public int getNextId() {
        String sql = "select nextval('seq_treasury_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }

    public int getCurId() {
        String sql = "select currval('seq_treasury_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }

    public long getTreasuryId(Long player_id, int treasuryLevel) {
        // return Long.parseLong(player_id+"00"+treasuryLevel);
        return getNextId();
    }

    public Treasury getByPlayer(Long player_id, int level) {
        String sql = "SELECT * FROM treasury where player_id = {0}, and treasuryLevel = {1}";
        sql = MessageFormat.format(sql, String.valueOf(player_id), String.valueOf(level));
        Treasury player = DbUtils.queryOne(DbUtils.DB_USER, sql, Treasury.class);
        return player;
    }

    /**
     * 实例化宝库
     * 
     * @param player_id
     * @return
     */
    public Treasury create(Long player_id) {
        // 获取宝库等级
        Player player = PlayerManager.getInstance().get(player_id);
        int treasuryLevel = player.getTreasuryLevel();
        // 获取宝库配置数据
        ConfigTreasury configTreasury = ConfigDatasPool.getInstance().configTreasuryContainer
                .getConfigBy(treasuryLevel);

        long nextId = getTreasuryId(player_id, treasuryLevel);
        Treasury treasury_base;
        try {
            treasury_base = get(player_id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            treasury_base = null;
        }

        Treasury treasury = getTreasury(configTreasury);
        treasury.setTreasuryLevel(treasuryLevel);

        treasury.setTreasury_id(nextId);
        treasury.setPlayer_id(player_id);
        if (treasury_base == null) {
            treasury.setInsert();
        } else {
            treasury.setFocsUpdate();
        }
        return treasury;
    }
    
    /**
     * 重置宝库宝箱
     */
    public Treasury resetTreasury(long player_id) {
        Treasury treasury = get(player_id);
        // 获取宝库配置数据
        ConfigTreasury configTreasury = ConfigDatasPool.getInstance().configTreasuryContainer
                .getConfigBy(treasury.getTreasuryLevel());
        
        treasury = getTreasury(treasury, configTreasury);
        long nextId = getTreasuryId(player_id, treasury.getTreasuryLevel());
        treasury.setTreasury_id(nextId);
        
        treasury.setFocsUpdate();
        DbService.getInstance().add2Queue(treasury);
        return treasury;
    }

    /**
     * 摧毁宝箱
     * 
     * @param index
     *            宝箱位置 1-5
     */
    public void destroyBox(Long player_id, int index) {
        Treasury treasury = get(player_id);
        int getGold = 0, getDiamond = 0;
        String cardPinzhi = null;
        switch (index) {
        case 1:
            getGold = treasury.getCoin1();
            getDiamond = treasury.getDiamond1();
            cardPinzhi = treasury.getCard1_pinzhi();
            break;
        case 2:
            getGold = treasury.getCoin2();
            getDiamond = treasury.getDiamond2();
            cardPinzhi = treasury.getCard2_pinzhi();
            break;
        case 3:
            getGold = treasury.getCoin3();
            getDiamond = treasury.getDiamond3();
            cardPinzhi = treasury.getCard3_pinzhi();
            break;
        case 4:
            getGold = treasury.getCoin4();
            getDiamond = treasury.getDiamond4();
            cardPinzhi = treasury.getCard4_pinzhi();
            break;
        case 5:
            getGold = treasury.getCoin5();
            getDiamond = treasury.getDiamond5();
            cardPinzhi = treasury.getCard5_pinzhi();
            break;

        default:
            break;
        }

        EventAttrChange eventAttrChange = new EventAttrChange(EventType.MONEY1_ADD, player_id);
        eventAttrChange.setMoney1_change(getGold);
        eventAttrChange.setSource_evtType(EventType.TREASURY_DESTROY_BOX);
        EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送金币增加事件

        EventAttrChange eventAttrChange2 = new EventAttrChange(EventType.MONEY2_ADD, player_id);
        eventAttrChange2.setMoney1_change(getDiamond);
        eventAttrChange2.setSource_evtType(EventType.TREASURY_DESTROY_BOX);
        EventDispatcher.getInstance().fireEvent(eventAttrChange2); // 发送宝石增加事件

        // 掉落抽卡
        List<int[]> cards = getCards(player_id, cardPinzhi, treasury.getTreasuryLevel());
        if(cards == null) {
            cards = new ArrayList<int[]>();
        }
        
        EventTreasuryRecord event = new EventTreasuryRecord(EventType.TREASURY_DESTROY_BOX, player_id);
        event.setCoins(getGold);
        event.setDiamonds(getDiamond);
        event.setIndex(index);
        event.setParams(cards);
        event.setTreasury_id(treasury.getTreasury_id());
        EventDispatcher.getInstance().fireEvent(event); // 发送宝库宝箱摧毁事件
    }
    

    /**
     * 掉落抽卡 抽卡获得兵种、加成比、星级参数、加成种类、品质
     * @param player_id
     * @param cardPinzhi
     * @param level
     */
    private List<int[]> getCards(Long player_id, String cardPinzhi, int level) {
        List<Integer> pinzhis = new ArrayList<Integer>();

        String[] strs1 = cardPinzhi.split(",");
        if (strs1.length == 4) {
            for (String str : strs1) {
                String[] str2 = str.split(":");
                if (str2.length == 2) {
                    int count = Integer.parseInt(str2[1]);
                    if (count > 0) {
                        int pinzhi = Integer.parseInt(str2[0]);
                        for (int i = 0; i < count; i++) {
                            pinzhis.add(pinzhi);
                        }
                    }
                }
            }
        }
        int count = pinzhis.size();
        if(count==0) return null;
        int[] pinzhi = new int[count];
        for (int i = 0; i < count; i++) {
            pinzhi[i] = pinzhis.get(i);
        }

        List<int[]> params = ChoukaManager.getInstance().getParams(count, level, pinzhi);
        if (params.size() < 4) {
            logger.error("获得抽卡参数出错 params.size=" + params.size());
            return null;
        }
        // 概率获得兵种
        int[] bingzhong = params.get(0);
        // 概率获得加成比
        int[] jiachengbi = params.get(1);
        // 概率获得星级
        int[] xingji = params.get(2);
        // 概率获得加成种类
        int[] jiachengtype = params.get(3);
        // 默认等级
        int default_level = 1;
        
        for(int i=0;i<count;i++) {
         // 发送新卡牌事件
            EventDispatcher.getInstance().fireEvent(new EventKapaiNew(EventType.KAPAI_NEW, player_id,
                    pinzhi[i], bingzhong[i], default_level, jiachengtype[i], jiachengbi[i]/100F, xingji[i]));
        }
        
        params.add(pinzhi);
        return params;
    }
    

    /**
     * 得到宝库实例
     */
    private Treasury getTreasury(ConfigTreasury configTreasury) {
        Treasury treasury = new Treasury();

        return getTreasury(treasury, configTreasury);
    }
    
    /**
     * 得到宝库实例
     * @param treasury
     * @param configTreasury
     * @return
     */
    private Treasury getTreasury(Treasury treasury, ConfigTreasury configTreasury) {
        
        getBox(treasury, configTreasury, 1);
        getBox(treasury, configTreasury, 2);
        getBox(treasury, configTreasury, 3);
        getBox(treasury, configTreasury, 4);
        getBox(treasury, configTreasury, 5);

        return treasury;
    } 

    /**
     * 得到对应位置的宝箱
     * 
     * @param boxIndex
     */
    private void getBox(Treasury treasury, ConfigTreasury configTreasury, int boxIndex) {
        int boxLevel = getBoxLevel();
        int HP = 0;
        if (boxIndex == 1) {
            treasury.setLevel1(boxLevel);
            HP = getLeevelHP(configTreasury, boxLevel);
            treasury.setLevel1HP(HP);
            treasury.setDiamond1(getDiamond(configTreasury, boxLevel));
            treasury.setCard1(getCard(configTreasury, boxLevel));
            treasury.setCoin1(getCoin(configTreasury, boxLevel));
            treasury.setCard1_pinzhi(getCardPinzhi(treasury.getCard1(), boxLevel));
        } else if (boxIndex == 2) {
            treasury.setLevel2(boxLevel);
            HP = getLeevelHP(configTreasury, boxLevel);
            treasury.setLevel2HP(HP);
            treasury.setDiamond2(getDiamond(configTreasury, boxLevel));
            treasury.setCard2(getCard(configTreasury, boxLevel));
            treasury.setCoin2(getCoin(configTreasury, boxLevel));
            treasury.setCard2_pinzhi(getCardPinzhi(treasury.getCard2(), boxLevel));
        } else if (boxIndex == 3) {
            treasury.setLevel3(boxLevel);
            HP = getLeevelHP(configTreasury, boxLevel);
            treasury.setLevel3HP(HP);
            treasury.setDiamond3(getDiamond(configTreasury, boxLevel));
            treasury.setCard3(getCard(configTreasury, boxLevel));
            treasury.setCoin3(getCoin(configTreasury, boxLevel));
            treasury.setCard3_pinzhi(getCardPinzhi(treasury.getCard3(), boxLevel));
        } else if (boxIndex == 4) {
            treasury.setLevel4(boxLevel);
            HP = getLeevelHP(configTreasury, boxLevel);
            treasury.setLevel4HP(HP);
            treasury.setDiamond4(getDiamond(configTreasury, boxLevel));
            treasury.setCard4(getCard(configTreasury, boxLevel));
            treasury.setCoin4(getCoin(configTreasury, boxLevel));
            treasury.setCard4_pinzhi(getCardPinzhi(treasury.getCard4(), boxLevel));
        } else if (boxIndex == 5) {
            treasury.setLevel5(boxLevel);
            HP = getLeevelHP(configTreasury, boxLevel);
            treasury.setLevel5HP(HP);
            treasury.setDiamond5(getDiamond(configTreasury, boxLevel));
            treasury.setCard5(getCard(configTreasury, boxLevel));
            treasury.setCoin5(getCoin(configTreasury, boxLevel));
            treasury.setCard5_pinzhi(getCardPinzhi(treasury.getCard5(), boxLevel));
        }
    }

    /**
     * @param configTreasury
     * @param boxLevel
     * @return
     */
    private String getCardPinzhi(int cardNum, int boxLevel) {
        // TODO Auto-generated method stub
        HashMap<Integer, Integer> Map = new HashMap<Integer, Integer>();
        Map.put(1, 0);
        Map.put(2, 0);
        Map.put(3, 0);
        Map.put(4, 0);
        for (int i = 0; i < cardNum; i++) {
            int pinzhi = ChoukaManager.getInstance().getPinzhi(ChoukaDataPool.BOX + boxLevel);
            if (Map.containsKey(pinzhi)) {
                Map.put(pinzhi, Map.get(pinzhi) + 1);
            } else {
                Map.put(pinzhi, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("1:").append(Map.get(1)).append(",2:").append(Map.get(2)).append(",3:").append(Map.get(3))
                .append(",4:").append(Map.get(4));

        return sb.toString();
    }

    private int getLeevelHP(ConfigTreasury configTreasury, int boxLevel) {
        int a = 1;
        switch (boxLevel) {
        case 1:
            a = 1;
            break;
        case 2:
            a = 3;
            break;
        case 3:
            a = 6;
            break;
        case 4:
            a = 10;
            break;

        default:
            break;
        }
        return configTreasury.getBaseHP() * a;
    }

    /**
     * 得到掉落宝石的数量
     * 
     * @param configTreasury
     * @param boxIndex
     * @return
     */
    private int getDiamond(ConfigTreasury configTreasury, int boxLevel) {
        double a = 0;
        double b = 0;

        if (boxLevel == 1) {
            a = configTreasury.getDiamondMiu1();
            b = configTreasury.getDiamondSigma1();
        } else if (boxLevel == 2) {
            a = configTreasury.getDiamondMiu2();
            b = configTreasury.getDiamondSigma2();
        } else if (boxLevel == 3) {
            a = configTreasury.getDiamondMiu3();
            b = configTreasury.getDiamondSigma3();
        } else if (boxLevel == 4) {
            a = configTreasury.getDiamondMiu4();
            b = configTreasury.getDiamondSigma4();
        }
        double ans = (float) RollUtils.ans(a, b);
        int num = getNum(ans);
        return num;
    }

    /**
     * 得到掉落卡牌的数量
     * 
     * @param configTreasury
     * @param boxIndex
     * @return
     */
    private int getCard(ConfigTreasury configTreasury, int boxLevel) {
        double a = 0;
        double b = 0;

        if (boxLevel == 1) {
            a = configTreasury.getCardMiu1();
            b = configTreasury.getCardSigma1();
        } else if (boxLevel == 2) {
            a = configTreasury.getCardMiu2();
            b = configTreasury.getCardSigma2();
        } else if (boxLevel == 3) {
            a = configTreasury.getCardMiu3();
            b = configTreasury.getCardSigma3();
        } else if (boxLevel == 4) {
            a = configTreasury.getCardMiu4();
            b = configTreasury.getCardSigma4();
        }
        double ans = (float) RollUtils.ans(a, b);
        int num = getNum(ans);
        return num;
    }

    /**
     * 得到掉落金币的数量
     * 
     * @param configTreasury
     * @param boxIndex
     * @return
     */
    private int getCoin(ConfigTreasury configTreasury, int boxLevel) {
        double a = 0;
        double b = 0;

        if (boxLevel == 1) {
            a = configTreasury.getCoinMiu1();
            b = configTreasury.getCoinSigma1();
        } else if (boxLevel == 2) {
            a = configTreasury.getCoinMiu2();
            b = configTreasury.getCoinSigma2();
        } else if (boxLevel == 3) {
            a = configTreasury.getCoinMiu3();
            b = configTreasury.getCoinSigma3();
        } else if (boxLevel == 4) {
            a = configTreasury.getCoinMiu4();
            b = configTreasury.getCoinSigma4();
        }
        double ans = (float) RollUtils.ans(a, b);
        int num = getNum(ans);
        return num;
    }

    /**
     * ans<0.5 均置为0， ans>=0.5 四舍五入取整
     * 
     * @param ans
     * @return
     */
    public static int getNum(double ans) {
        int num = 0;
        if (ans < 0.5) {
            num = 0;
        } else {
            num = RollUtils.round(ans);
        }
        return num;
    }

    /**
     * 每个宝箱位置：1级宝箱45%，2级宝箱30%，3级宝箱20%，4级宝箱5%概率出现
     * 
     * @return
     */
    private int getBoxLevel() {
        int level1 = 45;
        int level2 = level1 + 30;
        int level3 = level2 + 20;
        int level4 = level3 + 5;

        int roll = RollUtils.roll(level4);
        if (roll <= level1) {
            return 1;
        } else if (roll <= level2) {
            return 2;
        } else if (roll <= level3) {
            return 3;
        } else if (roll <= level4) {
            return 4;
        }
        return 1;
    }

    public static void main(String[] args) {
        /*
        for (int i = 0; i < 10; i++) {
            double ans = RollUtils.ans(0.8, 0.4);
            System.out.println("ans=" + ans);
            System.out.println("int=" + getNum(ans));
        }
        */
        System.out.println(20/100F);
    }

    /**
     * @param player_id
     * @param code
     */
    public void executeBattleEnd(long player_id, int code) {
        Treasury treasury = get(player_id);
        EventTreasuryEnd event = new EventTreasuryEnd(EventType.TREASURY_BATTLE_END, player_id, treasury.getTreasury_id(), code);
        EventDispatcher.getInstance().fireEvent(event); // 发送宝库战斗结束事件
    }
}
