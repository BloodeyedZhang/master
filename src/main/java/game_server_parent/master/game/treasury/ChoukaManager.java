package game_server_parent.master.game.treasury;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigBingzhongVT;
import game_server_parent.master.game.database.config.bean.ConfigJiachengTypePR;
import game_server_parent.master.game.database.config.bean.ConfigJiachengVT;
import game_server_parent.master.game.database.config.bean.ConfigPinzhi;
import game_server_parent.master.game.database.config.bean.ConfigPinzhiPR;
import game_server_parent.master.game.database.config.bean.ConfigTreasuryBingzhong;
import game_server_parent.master.game.database.config.bean.ConfigTreasuryVT;
import game_server_parent.master.game.database.config.bean.ConfigXingjiPR;
import game_server_parent.master.game.database.config.container.ConfigBingzhongVTContainer;
import game_server_parent.master.game.database.config.container.ConfigJiachengVTContainer;
import game_server_parent.master.game.database.config.container.ConfigXingjiPRContainer;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.kapai.KapaiDataPool;
import game_server_parent.master.orm.utils.StringUtils;
import game_server_parent.master.utils.RollUtils;

/**
 * <p>Filename:ChoukaManager.java</p>
 * <p>Description: 抽卡相关逻辑  </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月16日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ChoukaManager {
    private Logger logger = LoggerFactory.getLogger(ChoukaManager.class);

    private static ChoukaManager instance = new ChoukaManager();

    public static ChoukaManager getInstance() {
        return instance;
    }
    
    /**
     * 商城开包
     * @param count 数量
     * @param level 宝库等级
     * @param type 卡包类型
     * @return
     */
    public List<Kapai> getKapais(int count, int level, String type) {
        
        List<Kapai> list = new ArrayList<Kapai>(count);
        
        int[] pinzhi = getPinzhi(count, type);
        List<int[]> params = getParams(count, level, pinzhi);
        
        // 概率获得兵种
        int[] bingzhong = params.get(0);
        // 概率获得加成比
        int[] jiachengbi = params.get(1);
        // 概率获得星级
        int[] xingji = params.get(2);
        // 概率获得加成种类
        int[] jiachengtype = params.get(3);
        
        for(int i=0;i<count;i++) {
            Kapai kapai = new Kapai();
            kapai.setBingzhong(bingzhong[i]);
            kapai.setJiachengbi(jiachengbi[i]);
            kapai.setJiachengzhonglei(jiachengtype[i]);
            kapai.setPinzhi(pinzhi[i]);
            kapai.setXingji(xingji[i]);
            list.add(kapai);
        }
        return list;
    } 
    
    /**
     * 抽卡获得兵种、加成比、星级参数、加成种类
     * @param count
     * @param level
     * @param pingzhi
     * @return
     */
    public List<int[]> getParams(int count, int level, int[] pingzhi) {
        int[] bingzhong = new int[count];
        int[] jiachengbi = new int[count];
        int[] xingji = new int[count];
        int[] jiachengype = new int[count];
        
        for(int i=0;i<count;i++) {
            bingzhong[i] = getBingzhong(level);
            xingji[i] = getXingji();
            jiachengbi[i] = getJiachengbi(pingzhi[i]);
            jiachengype[i] = getJiachengzhonglei();
        }
        
        List<int[]> list = new ArrayList<int[]>(4);
        list.add(bingzhong);
        list.add(jiachengbi);
        list.add(xingji);
        list.add(jiachengype);
        return list;
    }
    
    
    public int[] getPinzhi(int count, String type) {
        int[] pinzhi = new int[count];
        int index = -1;
        if(type.equals(ChoukaDataPool.SUPERLARGE_KABAO)) {
            index = RollUtils.roll(count)-1;
        }
        for(int i=0;i<count;i++) {
            if(i==index) {
                pinzhi[i] = KapaiDataPool.PINZHI_HEITAO;
            } else {
                pinzhi[i] = getPinzhi(type);
            }
        }
        return pinzhi;
    }
    
    
    /**
     * 品质概率获得
     */
    public int getPinzhi(String type) {
        ConfigPinzhiPR configPinzhiPR = ConfigDatasPool.getInstance().configPinzhiPRContainer.getConfigBy(type);
        double rollDouble = RollUtils.rollDouble();
        double tmp = rollDouble;
        if((rollDouble -= configPinzhiPR.getMeihua()) < 0) {
            return KapaiDataPool.PINZHI_MEIHUA;
        } else if((rollDouble -= configPinzhiPR.getFangpian()) < 0) {
            return KapaiDataPool.PINZHI_FANGPIAN;
        } else if((rollDouble -= configPinzhiPR.getHongtao()) < 0) {
            return KapaiDataPool.PINZHI_HONGTAO;
        } else if((rollDouble -= configPinzhiPR.getHeitao()) < 0) {
            return KapaiDataPool.PINZHI_HEITAO;
        } else {
            logger.error("概率找不到？"+tmp + "->"+configPinzhiPR.toString());
        }
        return KapaiDataPool.PINZHI_FANGPIAN;
    } 
    
    /**
     * 兵种概率获得
     * @return
     */
    public int getBingzhong(int level) {
        
        // 获得总权重
        ConfigTreasuryVT configTreasuryVT = ConfigDatasPool.getInstance().configTreasuryVTContainer.getConfigBy(level);
        // 掷骰子
        int roll = RollUtils.roll(configTreasuryVT.getVt());
        
        ConfigTreasuryBingzhong configTB = ConfigDatasPool.getInstance().configTreasuryBingzhongContainer.getConfigBy(level);
        ConfigBingzhongVTContainer configBingzhongVTContainer = ConfigDatasPool.getInstance().configBingzhongVTContainer;
        int size = configBingzhongVTContainer.size();
        try {
            for (int i = 1; i <= size; i++) {
                String bingzhong = "s"+i;
                Method method = configTB.getClass().getMethod("get" + StringUtils.firstLetterToUpperCase(bingzhong));
                float isHave = (float) method.invoke(configTB);
                if (isHave > 0) {
                    ConfigBingzhongVT configBingzhongVT = configBingzhongVTContainer.getConfigBy(bingzhong);
                    if((roll -= configBingzhongVT.getVt()) <= 0) {
                        return configBingzhongVT.getId();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        logger.error("返回默认兵种: 卫兵");
        return 1011; // 返回默认兵种: 卫兵
    }
    
    /**
     * 加成种类概率获得
     * @return
     */
    public int getJiachengzhonglei() {
        ConfigJiachengTypePR jtpr = ConfigDatasPool.getInstance().configJiachengTypePRContainer.getConfigBy(KapaiDataPool.ADDITION_ATTACK);
        int roll = RollUtils.roll(100);
        if(roll-jtpr.getPr() <= 0) {
            return KapaiDataPool.ADDITION_ATTACK;
        } else {
            return KapaiDataPool.ADDITION_HP;
        }
        
    } 
    
    /**
     * 最终加成比获得
     * @param pinzhi
     * @return
     */
    public int getJiachengbi(int pinzhi) {
        int jiachengbi = getJiachengbi();
        
        ConfigPinzhi configBy = ConfigDatasPool.getInstance().configPinzhiContainer.getConfigBy(pinzhi);
        int result = (int) (jiachengbi + configBy.getJiacheng_base());
        return result;
    }
    
    /**
     * 随机加成比概率获得
     * @return
     */
    public int getJiachengbi() {
        
        int jiachengbi = 0;
        
        ConfigJiachengVTContainer configJiachengVTContainer = ConfigDatasPool.getInstance().configJiachengVTContainer;
        ConfigJiachengVT sumVT = configJiachengVTContainer.getSumVT();
        int roll = RollUtils.roll(sumVT.getVt());
        int size = configJiachengVTContainer.size();
        for(int i=0;i<size;i++) {
            ConfigJiachengVT configJiachengVT = configJiachengVTContainer.getConfigBy(i);
            if(configJiachengVT!= null) {
                if((roll-=configJiachengVT.getVt()) <=0) {
                    jiachengbi = configJiachengVT.getJiacheng();
                    break;
                }
            }
        }
        
        
        return jiachengbi;
    } 
    
    /**
     * 星级概率获得
     * @return
     */
    public int getXingji() {
        int xingji = 1;
        ConfigXingjiPRContainer configXingjiPRContainer = ConfigDatasPool.getInstance().configXingjiPRContainer;
        ConfigXingjiPR sumVT = configXingjiPRContainer.getSumVT();
        int size = configXingjiPRContainer.size();
        int roll = RollUtils.roll((int)sumVT.getPr());
        for(int i=0;i<size;i++) {
            ConfigXingjiPR configBy = configXingjiPRContainer.getConfigBy(i+1);
            if(configBy!=null) {
                if((roll-=configBy.getPr())<=0) {
                    xingji = configBy.getLevel();
                    break;
                }
            }
        }
        return xingji;
    }
}
