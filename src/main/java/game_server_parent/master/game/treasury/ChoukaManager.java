package game_server_parent.master.game.treasury;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.achievement.AchievementManager;
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
import game_server_parent.master.game.database.config.container.ConfigTreasuryVTContainer;
import game_server_parent.master.game.database.config.container.ConfigXingjiPRContainer;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.KapaiAchievement;
import game_server_parent.master.game.kapai.KapaiDataPool;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.utils.ByteUtils;
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
    
    public void init() {
        ConfigTreasuryVTContainer configTreasuryVTContainer = ConfigDatasPool.getInstance().configTreasuryVTContainer;
        Map<Integer, ConfigTreasuryVT> all_treasury = configTreasuryVTContainer.getAll();
        Collection<ConfigTreasuryVT> values = all_treasury.values();
        for(ConfigTreasuryVT configTreasuryVT : values) {
            configTreasuryVT.setVt(0);
        }
        
        ConfigBingzhongVTContainer configBingzhongVTContainer = ConfigDatasPool.getInstance().configBingzhongVTContainer;
        List<ConfigBingzhongVT> all_bingzhong = configBingzhongVTContainer.getAll();
        
        for(ConfigBingzhongVT configBingzhongVT : all_bingzhong) {
            int level = configBingzhongVT.getLevel();
            if(level>0) {
                ConfigTreasuryVT configTreasuryVT = all_treasury.get(level);
                int vt = configTreasuryVT.getVt();
                vt = vt + configBingzhongVT.getVt();
                configTreasuryVT.setVt(vt);
            }
        }
        
        Set<Integer> keySet = all_treasury.keySet();
        int count = keySet.size();
        for(int i=2;i<=count;i++) {
            ConfigTreasuryVT configTreasuryVT = all_treasury.get(i);
            if(configTreasuryVT.getVt()==0) {
                ConfigTreasuryVT configTreasuryVT2 = all_treasury.get(i-1);
                configTreasuryVT.setVt(configTreasuryVT2.getVt());
            }
        }
        
        configTreasuryVTContainer.setAll(all_treasury);
    } 
    
    /**
     * 商城开包
     * @param count 数量
     * @param level 宝库等级
     * @param type 卡包类型
     * @param player_id 
     * @return
     */
    public List<Kapai> getKapais(int count, int level, String type, long player_id) {
        
        List<Kapai> list = new ArrayList<Kapai>(count);
        
        int[] pinzhi = getPinzhi(count, type);
        List<int[]> params = getParams(count, level, pinzhi, player_id);
        
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
     * @param player_id 
     * @return
     */
    public List<int[]> getParams(int count, int level, int[] pingzhi, long player_id) {
        int[] bingzhong = new int[count];
        int[] jiachengbi = new int[count];
        int[] xingji = new int[count];
        int[] jiachengype = new int[count];
        
        for(int i=0;i<count;i++) {
            bingzhong[i] = getBingzhong(level, player_id);
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
     * @param player_id 
     * @param level 宝库等级
     * @return
     */
    public int getBingzhong(int level, long player_id) {
        
        // 获得总权重
        ConfigTreasuryVT configTreasuryVT = ConfigDatasPool.getInstance().configTreasuryVTContainer.getConfigBy(level);
        
        ConfigTreasuryBingzhong configTB = ConfigDatasPool.getInstance().configTreasuryBingzhongContainer.getConfigBy(level);
        ConfigBingzhongVTContainer configBingzhongVTContainer = ConfigDatasPool.getInstance().configBingzhongVTContainer;
        
        KapaiAchievement kapaiAchieve1 = AchievementManager.getInstance().get(AchievementManager.getInstance().achieve_id(player_id, 1038));
        KapaiAchievement kapaiAchieve2 = AchievementManager.getInstance().get(AchievementManager.getInstance().achieve_id(player_id, 1039));
        KapaiAchievement kapaiAchieve3 = AchievementManager.getInstance().get(AchievementManager.getInstance().achieve_id(player_id, 1040));
        
        /**  添加BOSS新卡权重 BEGIN **/
        int add_vt = 0;
        
        // 获取副本领取情况
        Player player = PlayerManager.getInstance().get(player_id);
        int fuben_achieve = player.getFuben_achieve();
        boolean bit1 = ByteUtils.getBit(fuben_achieve, 0);
        boolean bit2 = ByteUtils.getBit(fuben_achieve, 1);
        boolean bit3 = ByteUtils.getBit(fuben_achieve, 2);
        
        if(bit1) {
            add_vt += configBingzhongVTContainer.getConfigBy(1038).getVt();
        }
        if(bit2) {
            add_vt += configBingzhongVTContainer.getConfigBy(1039).getVt();
        }
        if(bit3) {
            add_vt += configBingzhongVTContainer.getConfigBy(1040).getVt();
        }
        
        /**  END **/
        
        // 玩家总权重
        int vt2 = configTreasuryVT.getVt();
        vt2 += add_vt;
        
        // 获取宝库等级情况
        int treasuryLevel = player.getTreasuryLevel();
        
        // 掷骰子
        List<ConfigBingzhongVT> all = configBingzhongVTContainer.getAll();
        
        int n = 5;
        for(int i=0;i<n;i++) {
            double rollDouble = RollUtils.rollDouble();
            //ConfigBingzhongVT getConfigBinzhong = null;
            for(ConfigBingzhongVT cBingzhongVT : all) {
                int tmp_level = cBingzhongVT.getLevel();
                // 副本相关
                int id = cBingzhongVT.getId();
                if(id==1038 && !bit1) continue; 
                if(id==1039 && !bit2) continue; 
                if(id==1040 && !bit3) continue; 
                
                if(tmp_level==0) {
                    // 不考虑宝库等级

                    boolean isbingzhongVT = getBingzhongVT(rollDouble, (double)vt2, cBingzhongVT);
                    if(isbingzhongVT) {
                       // getConfigBinzhong = cBingzhongVT;
                        return id;
                    }
                } else if(tmp_level<=treasuryLevel) {
                    boolean isbingzhongVT = getBingzhongVT(rollDouble, (double)vt2, cBingzhongVT);
                    if(isbingzhongVT) {
                        //getConfigBinzhong = cBingzhongVT;
                        return cBingzhongVT.getId();
                    }
                }
            }
        }

        int size = all.size();
        int roll = RollUtils.roll(8);
        
        logger.error("返回默认兵种: 卫兵");
        return all.get(roll).getId(); // 返回默认兵种: 卫兵
    }
    
    public boolean getBingzhongVT(Double rollDouble, Double vt2, ConfigBingzhongVT cBingzhongVT) {
        int vt = cBingzhongVT.getVt();
        if(vt>0) {
            Double d = (double)vt/vt2;
            if((rollDouble -= d) <=0) {
                return true;
            }
        }
        return false;
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
