package game_server_parent.master.game.kapai;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigBingzhong;
import game_server_parent.master.game.database.config.bean.ConfigSoilderLevel;
import game_server_parent.master.game.database.config.bean.ConfigXingji;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.KapaiId;
import game_server_parent.master.game.player.events.EventAttrChange;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:KapaiManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月13日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class KapaiManager extends CacheService<Long, Kapai> {
    
    private Logger logger = LoggerFactory.getLogger(KapaiManager.class);

    private static KapaiManager instance = new KapaiManager();

    public static KapaiManager getInstance() {
        return instance;
    }
    
    private long kapai_id(long player_id, int id) {
        if(id>=1000) id-=1000;
        StringBuffer sb = new StringBuffer();
        sb.append(player_id).append((int)id/100).append((int)id%100);
        return Long.parseLong(sb.toString());
    }

    public Kapai createNewKapai(long player_id, int pingzhi, int bingzhong, int dengji, int jiachengzhonglei, float jiachengbi, int xingji) {
        Kapai kapai = new Kapai();
        
        ConfigBingzhong configBy = ConfigDatasPool.getInstance().configBingzhongContainer.getConfigBy(bingzhong);
        
        dengji = dengji<1?1:dengji; // 等级最低为1级
        
        kapai.setPlayer_id(player_id);
        kapai.setDalei(configBy.getDalei());
        kapai.setBingzhong(bingzhong);
        kapai.setShengmingzhi(configBy.getShengming_base());
        kapai.setGongjizhi(configBy.getGongji_base());
        kapai.setZhiliaozhi(configBy.getZhiliao_base());
        kapai.setPinzhi(pingzhi);
        kapai.setS_dengji(dengji); // 设置等级
        kapai.setJiachengzhonglei(jiachengzhonglei);
        kapai.setJiachengbi(jiachengbi); // 设置加成比
        kapai.setXingji(xingji);
        
        
        int sumJinyanByLv = getSumJinyanByLv(dengji-1);//当前等级最低经验值
        kapai.setJingyan(sumJinyanByLv); // 设置实际经验
        
        int[] vals2 = this.getShengming_Gongji_Zhiliao(kapai.getBingzhong(), kapai.getJiachengzhonglei(), kapai.getJiachengbi(), kapai.getS_dengji());
        kapai.setShengmingzhi(vals2[0]); // 设置实际生命值
        kapai.setGongjizhi(vals2[1]); // 设置实际攻击值
        kapai.setZhiliaozhi(vals2[2]); // 设置实际治疗值
        
        ConfigSoilderLevel configBy2 = ConfigDatasPool.getInstance().configSoilderLevelContainer.getConfigBy(kapai.getKey(configBy.getBingzhong()));
        
        kapai.setSpeed(configBy2.getSpeed());
        /** 精准度和伤害范围 由卡牌等级表更改为兵种表控制  update by zhangjiajun at 2017/11/6  **/
        //kapai.setJingzun(configBy2.getJingzun());
        //kapai.setFanwei(configBy2.getFanwei());
        kapai.setJingzun(configBy.getA_jingzhundu());
        kapai.setFanwei(configBy.getA_fanweishanghai());
        
        ConfigXingji configBy3 = ConfigDatasPool.getInstance().configXingjiContainer.getConfigBy(kapai.getXingji());
        kapai.setJingyan_shangxian(configBy3.getJingyan_shangxian());
        
        int nextId = this.getNextId();
        //long kapai_id = kapai_id(player_id, bingzhong);
        kapai.setKapai_id(nextId);
        //设为插入状态
        kapai.setInsert();

        return kapai;
    }
    
    @Override
    public Kapai load(Long id) throws Exception {
        String sql = "SELECT * FROM kapai where kapai_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(id));
        Kapai kapai = DbUtils.queryOne(DbUtils.DB_USER, sql, Kapai.class);
        return kapai;
    }
    
    public List<Kapai> getPlayerKapaiList(long player_id){
        String sql = "SELECT * FROM kapai where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(player_id));
        List<Kapai> kapai_list = DbUtils.queryMany(DbUtils.DB_USER, sql, Kapai.class);
        return kapai_list;
    }
    
    public synchronized int getNextId() {
        String sql = "select nextval('seq_kapai_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
    
    public int getCurId() {
        String sql = "select currval('seq_kapai_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
    
    // 获得单位培养基准经验 
    // 培养基准经验=单位经验*0.2+单位星级^2*100
    private float getPeiyangJizhunJingyan(Kapai crazy_info) {
        float val = (float)(crazy_info.getJingyan() * 0.2f + 100.0 * Math.pow(crazy_info.getXingji(), 2));
        logger.debug("单位经验"+ crazy_info.getJingyan() + ";获得单位培养基准经验 ：" + val);
        return val;
    }

    // 获得培养消耗金钱 input:培养基准经验 
    private int getPeiyangGold(float input) {
        logger.debug("获得培养消耗金钱: 培养基准经验:" + input);
        return (int)Math.round(10 * Math.sqrt(input));
    }
    
    // 出售获得金钱
    private int getSellGold(int input) {
        return input;
    }
    
    // 兵种比较
    private boolean compareBingzhong(Kapai beiRonghe, Kapai ronghe) {
        return beiRonghe.getDalei() == ronghe.getDalei();
    }

    // 获得培养经验
    private int getPeiyangJingyan(boolean flag, float jizhun_jingyan) {
        float val = 0.0f;
        if (flag) // 同大类
        {
            val = jizhun_jingyan;
        } else // 不同大类
        {
            val = 0.7f * jizhun_jingyan;
        }
        logger.debug("获得培养经验:" + val);
        return (int)Math.round(val);
    }
    
    /*
    * 升到X级所需总经验=
    * 50*1.1^1+50*1.1^2+……+5.*1.1^(x-1)，四舍五入
    * 如：
    * 升到3级所需经验=50*1.1+50*1.1^2=115.5，取整得到116
    */
    public static int getSumJinyanByLv(int lv)
    {
        double sum = 0;
        for (int i = 1; i <= lv; i++) {
            sum += getCurJinyanByLv(i);
        }
        return (int)Math.round(sum);
    }

    /**
     * @param jinyan 实际经验
     * @param xingji 星级
     * @return 获得经验后的等级，等级总经验、实际经验
     */
    public int[] getSumAndLvByJinyan(int jinyan, int xingji) {
        
        ConfigXingji config_xingji = ConfigDatasPool.getInstance().configXingjiContainer.getConfigBy(xingji);
        
        if (jinyan >= config_xingji.getJingyan_shangxian())
        {
            logger.debug("经验到达当前星级上限:" + config_xingji.getJingyan_shangxian());
            jinyan = config_xingji.getJingyan_shangxian();
        }
        double sum = 0;
        boolean flag = true;
        int lv = 0;
        for (int i = 1; flag; i++)
        {
            lv = i;
            sum += getCurJinyanByLv(i);
            if (sum >= jinyan) {
                flag = false;
                break;
            }
        }
        logger.debug("等级{} 等级总经验{} 实际经验{}",lv, (int)Math.round(sum), jinyan);
        return new int[] {lv, (int)Math.round(sum), jinyan };
    }

    /**
     * 当前等级升级所需经验
     * @param lv
     * @return
     */
    public static double getCurJinyanByLv(int lv) {
        return 50 * Math.pow(1.1, lv);
    }

    /**
     * 当前卡牌的实际生命值、攻击值、治疗值
     * @param binzhong
     * @param pinzhi
     * @param lv
     * @return
     */
    public  int[] getShengming_Gongji_Zhiliao(int binzhong, int jiachengzhonglei, float jiachengbi, int lv) {
        /*
        Dictionary<string, CRAZY_INFO> d_crazy_infos = Global.GetInstance().d_crazy_infos;
        ConfigEnumManager configenum_soilder = ConfigEnumManager.getConfigEnumManager(1010 + dalei);
        Debug.Log("大类：" + configenum_soilder.id.ToString());
        CRAZY_INFO config_1 = d_crazy_infos[configenum_soilder.id.ToString()]; // 按大类
        CRAZY_INFO config_3 = d_crazy_infos[(1010 + pinzhi).ToString()]; // 按品质
        CRAZY_INFO config_2 = d_crazy_infos[configenum_soilder.val + "" + lv];
        */
        
        ConfigBingzhong configByBingzhong = ConfigDatasPool.getInstance().configBingzhongContainer.getConfigBy(binzhong);
        //ConfigPinzhi configByPinzhi = ConfigDatasPool.getInstance().configPinzhiContainer.getConfigBy(pinzhi);
        ConfigSoilderLevel configByLv = ConfigDatasPool.getInstance().configSoilderLevelContainer.getConfigBy(configByBingzhong.getBingzhong()+""+lv);
        
        int zhiliao_base = configByBingzhong.getZhiliao_base();
        int gongji_base = configByBingzhong.getGongji_base();
        
        int xishu_gongji = 0;
        int xishu_zhiliao = 0;
        int xishu_shengming = 0;
        if(jiachengzhonglei== KapaiDataPool.ADDITION_ATTACK) {
            if(zhiliao_base > 0) {
                xishu_zhiliao = 1;
            }
            if(gongji_base > 0) {
                xishu_gongji = 1;
            }
        } else if(jiachengzhonglei==KapaiDataPool.ADDITION_HP) {
            xishu_shengming = 1;
        } else {
            
        }
        
        //治疗值=（基础治疗+等级治疗）*（1+品质治疗*加成比/100）
        int zhiliao = (int)Math.round((configByBingzhong.getZhiliao_base() + configByLv.getZhiliao_dengji()) * (1 + configByLv.getZhiliao_pinzhi() * xishu_zhiliao * jiachengbi / 100));
        // 攻击值 攻击力=（基础攻击+等级攻击）*（1+品质攻击*加成比/100）
        int gongji = (int)Math.round((configByBingzhong.getGongji_base() + configByLv.getGongjili_dengji()) * (1 + configByLv.getGongjili_pinzhi() * xishu_gongji*jiachengbi/ 100));
        // 生命值 生命值=（基础生命+等级生命）*（1+品质生命*加成比/100）
        int shengming = (int)Math.round((configByBingzhong.getShengming_base() + configByLv.getShengming_dengji()) * (1 + configByLv.getShengming_pinzhi() * xishu_shengming*jiachengbi / 100));
        
        return new int[] {shengming, gongji, zhiliao};
    }
    
    /**
     * 更新卡牌的数据：等级，经验,生命值，攻击值，治疗值
     * @param kapai 更新卡牌
     * @param kapai_ronghe   被消耗的卡牌
     */
    public void updateKapai(Kapai kapai, Kapai kapai_ronghe) {
        
        // 培养基准经验
        float peiyangJizhunJingyan = this.getPeiyangJizhunJingyan(kapai_ronghe);
        
        // 获取消耗的金币
        int peiyangGold = this.getPeiyangGold(peiyangJizhunJingyan);
        EventAttrChange eventAttrChange = new EventAttrChange(EventType.MONEY1_DEDUCT, kapai.getPlayer_id());
        eventAttrChange.setMoney1_change(peiyangGold);
        eventAttrChange.setSource_evtType(EventType.KAPAI_UPDATE);
        EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送金币消耗事件
        
        
        int peiyangJingyan = this.getPeiyangJingyan(compareBingzhong(kapai, kapai_ronghe), peiyangJizhunJingyan); // 获得培养经验
        int jinyan = kapai.getJingyan()+peiyangJingyan;
        int[] vals = this.getSumAndLvByJinyan(jinyan, kapai.getXingji());
        kapai.setS_dengji(vals[0]);
        kapai.setJingyan(vals[2]);
        
        int[] vals2 = this.getShengming_Gongji_Zhiliao(kapai.getBingzhong(), kapai.getJiachengzhonglei(), kapai.getJiachengbi(), kapai.getS_dengji());
        kapai.setShengmingzhi(vals2[0]);
        kapai.setGongjizhi(vals2[1]);
        kapai.setZhiliaozhi(vals2[2]);
        
//      kapai2.setInsert();
//      kapai2.setDelete();
//      kapai2.setUpdate();
        kapai.setFocsUpdate();
        DbService.getInstance().add2Queue(kapai);
    }
    
    public void sell(Kapai kapai) {
        // 培养基准经验
        float peiyangJizhunJingyan = this.getPeiyangJizhunJingyan(kapai);
        
        // 获取消耗的金币
        int peiyangGold = this.getPeiyangGold(peiyangJizhunJingyan);
        // 获得出售的金币
        int sellGold = this.getSellGold(peiyangGold);
        EventAttrChange eventAttrChange = new EventAttrChange(EventType.MONEY1_ADD, kapai.getPlayer_id());
        eventAttrChange.setMoney1_change(sellGold);
        eventAttrChange.setSource_evtType(EventType.KAPAI_SELL);
        EventDispatcher.getInstance().fireEvent(eventAttrChange); // 发送金币增加事件
        
    }
}
