package game_server_parent.master.game.ai;

import java.awt.geom.Ellipse2D;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.Element;

import org.codehaus.groovy.transform.sc.transformers.RangeExpressionTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigAi;
import game_server_parent.master.game.database.config.bean.ConfigBingzhongVT;
import game_server_parent.master.game.database.config.container.ConfigAiContainer;
import game_server_parent.master.game.database.config.container.ConfigBingzhongVTContainer;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.rank.RankSoilderTeamManager;
import game_server_parent.master.game.team.TeamListener;
import game_server_parent.master.game.team.TeamManager;
import game_server_parent.master.game.treasury.ChoukaManager;
import game_server_parent.master.logs.LoggerSystem;
import game_server_parent.master.orm.utils.DbUtils;
import game_server_parent.master.utils.RollUtils;

/**
 * <p>Filename:AiManager.java</p>
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
public class AiManager {
    
//    private Logger logger = LoggerFactory.getLogger(TeamListener.class);
//    private static final Logger logger_exception = LoggerSystem.EXCEPTION.getLogger();
    private static final Logger logger = LoggerSystem.DB.getLogger();
    
    private static AiManager instance = new AiManager();
    
    private ConcurrentMap<Long, Player> onlines = new ConcurrentHashMap<Long, Player>();

    public static AiManager getInstance() {
        return instance;
    }
    
    public static int begin = 2000; // 起始总战力13500
    public static int end = 90000; // 最大总战力
    public static int interval = 500;
    public static int num = 5; // 一个阶段的AI数目
    public static int num_kapai =5; //每个AI的卡牌数目
    
    private static int[] arr;
    private List<Player> ai = null;

    private ConfigAiContainer configAiContainer;

    private Set<Integer> keys;

    private ConfigBingzhongVTContainer configBingzhongVTContainer;

    private List<ConfigBingzhongVT> all_ConfigBingzhongVT;
    
    /**
     * 创建AI玩家
     */
    public Player createAIPlayer() {
        int player_id = PlayerManager.getInstance().getNextId();
        Player player = PlayerManager.getInstance().createNewAIPlayer(player_id, "ai_"+player_id, (byte)1);
        
        return player;
    }
    
    /**
     * 获取所有AI玩家
     * @return
     * @throws Exception
     */
    private List<Player> loadAllAi() throws Exception {
        String sql = "SELECT * FROM player where is_ai = 1 order by fight"; // 升序排序
        List<Player> player = DbUtils.queryMany(DbUtils.DB_USER, sql, Player.class);
        return player;
    }
    
    public void loadAll() throws Exception {
        List<Player> ai = loadAllAi();
        if(ai==null) {
            ai = new ArrayList<>();
        }
        if(ai.size()==0) {
            init();
        }
    }
    
    private void init() {
        int count = (end - begin)/interval + 1;
        if(arr==null || arr.length!= count) {
            arr = new int[count];
            for(int i=0, f=begin;i<count;i++) {
                arr[i] = f;
                f+=interval;
            }
        }
        
        configAiContainer = ConfigDatasPool.getInstance().configAiContainer;
        keys = configAiContainer.getKeys();
        configBingzhongVTContainer = ConfigDatasPool.getInstance().configBingzhongVTContainer;
        all_ConfigBingzhongVT = configBingzhongVTContainer.getAll();
        
        for(int i=0;i<count;i++) {
            analysis(arr[i]);
        }
    }
    
    // 分析每一阶段总战力
    public void analysis(int fightSum) {
        int expect_fight = 0;
        if(fightSum>=2000 && fightSum<=3500) {
            num_kapai = 1;
            expect_fight = fightSum / num_kapai;
        } else if(fightSum>=4000 && fightSum<=7000) {
            num_kapai = 2;
            expect_fight = fightSum/num_kapai;
        } else if(fightSum>=7500 && fightSum<=10000) {
            num_kapai = 3;
            expect_fight = fightSum/num_kapai;
        } else if(fightSum>=10500 && fightSum<=13000) {
            num_kapai = 4;
            expect_fight = fightSum/num_kapai;
        } else {
            num_kapai = 5;
            expect_fight = fightSum/num_kapai; // 预期战力
        }
        
        logger.debug("预期战力="+expect_fight);
        // 符合条件的部队
        List<Kapai> kapais = new ArrayList<>();
        
        Iterator<Integer> iterator = keys.iterator();
        while(iterator.hasNext()) {
            Integer next = iterator.next();
            List<Kapai> kapai = getKapai(next, expect_fight);
            kapais.addAll(kapai);
        }
        
        // 生产AI
        for(int i=0;i<num;i++) {
            newAI(kapais);
            logger.info("num:"+i+";已生产AI数量："+(5*(i+1)));
        }
    }
    
    /**
     * 获得满足条件的部队
     * @param kapai_id 部队编号
     * @param expect_fight 预期战力
     * @return
     */
    public List<Kapai> getKapai(int kapai_id, int expect_fight) {
        List<Kapai> result = new ArrayList<>();
        int expect_fight_begin = expect_fight-250;
        int expect_fight_end = expect_fight+250;
        int level = 50;
        ConfigAi configAi = configAiContainer.getConfigBy(kapai_id);
        int bingzhong = configAi.getBingzhong();
        float fight = configAi.getFight();
        float add_fight = configAi.getAdd_fight();
        
        for(int i=0;i<level;i++) {
            int fight_tmp = (int) (fight + add_fight*i);
            if(fight> expect_fight_end) {
                break;
            }
            if(fight_tmp>=expect_fight_begin && fight_tmp<expect_fight_end) {
                logger.debug("符合战力["+expect_fight+"]的部队为 fight="+fight+";figh_level="+fight_tmp+";bingzhong="+bingzhong+";level="+(i+1));
                Kapai kapai = new Kapai();
                kapai.setBingzhong(bingzhong);
                kapai.setS_dengji(i+1);
                result.add(kapai);
            }
        }
        return result;
    }
    
    /**
     * @param kapais
     */
    private void newAI(List<Kapai> kapais) {
        // TODO Auto-generated method stub
        if(kapais==null || kapais.size()==0) {
            return ;
        }
        
        Player ai_Player = createAIPlayer();
        int treasuryLevel = ai_Player.getTreasuryLevel();
        long player_id = ai_Player.getPlayer_id();
        
        // 创建卡牌
        List<Kapai> player_kapais = new ArrayList<>(5);
        ConfigAi configAi = null;
        StringBuffer sb = new StringBuffer();
        int sum_fight = 0;
        
        for(int i=0;i<num_kapai;i++) {
            Kapai kapai = getBingzhong(kapais, player_id);
            Kapai createNewKapai = KapaiManager.getInstance().createNewKapai(player_id, kapai.getPinzhi(), kapai.getBingzhong(), kapai.getS_dengji(),
                    kapai.getJiachengzhonglei(), kapai.getJiachengbi(), 1);
            createNewKapai.setPlayer_id(player_id);
            player_kapais.add(createNewKapai);
            sb.append(createNewKapai.getKapai_id());
            if(i<num_kapai-1) {
                sb.append(",");
            }
            configAi = configAiContainer.getConfigBy(createNewKapai.getBingzhong());
            sum_fight += configAi.getFight()+configAi.getAdd_fight()*(createNewKapai.getS_dengji()-1);
            
            DbService.getInstance().add2Queue(createNewKapai);
        }
        
        ai_Player.setFight(sum_fight); // 更新玩家战力值
        int bonus_points = 0;
        
        
        // 创建排行队伍
        RankSoilderTeam rankSoilderTeam = new RankSoilderTeam();
        if(num_kapai==1) {
            sb.append(",0,0,0,0");
            bonus_points = 350;
        } else if(num_kapai==2) {
            sb.append(",0,0,0");
            bonus_points = 500;
        } else if(num_kapai==3) {
            sb.append(",0,0");
            bonus_points = 650;
        } else if(num_kapai==4) {
            sb.append(",0");
            bonus_points = 800;
        } else {
            bonus_points = (int) ((Math.pow(sum_fight/140, 0.5))*100);
        }
        String soilderIds = sb.toString();
        ai_Player.setBonus_points(bonus_points);
        DbService.getInstance().add2Queue(ai_Player);
        
        rankSoilderTeam.setSoilderIds(soilderIds);
        
        int nextTeamId = TeamManager.getInstance().getNextId();
        rankSoilderTeam.setTeam_id(nextTeamId);
        rankSoilderTeam.setPlayer_id(player_id);
        rankSoilderTeam.setInsert();
        
        DbService.getInstance().add2Queue(rankSoilderTeam);
        
    }
    
    private Kapai getBingzhong(List<Kapai> kapais, long playerId) {
        int vt_sum = 0; // 总权重
        int size = 0;
        if(kapais==null || (size=kapais.size())==0) {
            return null;
        }
        Kapai kapai = null;
        ConfigBingzhongVT configBingzhongVT = null;
        for(int i=0;i<size;i++) {
            kapai = kapais.get(i);
            configBingzhongVT = configBingzhongVTContainer.getConfigBy(kapai.getBingzhong());
            vt_sum += configBingzhongVT.getVt();
        }
        
        int vt2 = vt_sum;
        // 掷骰子
        // 按权重取卡牌
        double rollDouble = RollUtils.rollDouble();
        for(int i=0;i<size;i++) {
            kapai = kapais.get(i);
            configBingzhongVT = configBingzhongVTContainer.getConfigBy(kapai.getBingzhong());
            boolean isbingzhongVT = ChoukaManager.getInstance().getBingzhongVT(rollDouble, (double)vt2, configBingzhongVT);
            if(isbingzhongVT) {
                return kapai;
            }
        }
        
        // 随机取卡牌
        int roll = RollUtils.roll(size);
        return kapais.get(roll-1);
    }
}
