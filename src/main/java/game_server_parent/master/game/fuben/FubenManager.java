package game_server_parent.master.game.fuben;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigBingzhong;
import game_server_parent.master.game.database.config.bean.ConfigFuben;
import game_server_parent.master.game.database.config.bean.ConfigFubenboss;
import game_server_parent.master.game.database.config.bean.ConfigFubenlevel;
import game_server_parent.master.game.database.config.bean.ConfigSoilderLevel;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Fuben;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.fuben.message.ResBattleEndMessage;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.kapai.events.EventKapaiNew;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.rank.RankDataPool;
import game_server_parent.master.game.rank.RankSoilderTeamManager;
import game_server_parent.master.game.scene.SceneDataPool;
import game_server_parent.master.game.scene.message.ResPlayerPreEnterSceneMessage;
import game_server_parent.master.game.team.TeamManager;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.orm.utils.DbUtils;
import game_server_parent.master.utils.ByteUtils;
import game_server_parent.master.utils.RollUtils;

/**
 * <p>Filename:FubenManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月7日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class FubenManager extends CacheService<Long, Fuben> {
    
    private Logger logger = LoggerFactory.getLogger(FubenManager.class);

    private static FubenManager instance = new FubenManager();
    
    private ConcurrentMap<Long, Fuben> onlines = new ConcurrentHashMap<Long, Fuben>();

    public static FubenManager getInstance() {
        return instance;
    }

    @Override
    public Fuben load(Long id) throws Exception {
        String sql = "SELECT * FROM fuben where fuben_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(id));
        Fuben player = DbUtils.queryOne(DbUtils.DB_USER, sql, Fuben.class);
        return player;
    }

    
    public List<Fuben> getList(Long player_id) throws Exception {
        String sql = "SELECT * FROM fuben where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(player_id));
        List<Fuben> fubens = DbUtils.queryMany(DbUtils.DB_USER, sql, Fuben.class);
        return fubens;
    }
    
    private long fuben_id(long player_id, int id) {
        StringBuffer sb = new StringBuffer();
        sb.append(player_id).append("00").append(id);
        return Long.parseLong(sb.toString());
    } 
    
    public List<Fuben> createNewFuben(Long player_id) {
        Set<Integer> keys = ConfigDatasPool.getInstance().configFubenContainer.getKeys();
        Iterator<Integer> it = keys.iterator();
        List<Fuben> fubens = new ArrayList<>(keys.size());
        while(it.hasNext()) {
            int id = it.next();
            Fuben fuben = new Fuben();
            fuben.setFuben_id(fuben_id(player_id, id));
            fuben.setPlayer_id(player_id);
            ConfigFuben configBy = ConfigDatasPool.getInstance().configFubenContainer.getConfigBy(id);
            fuben.setFuben_reward_type(configBy.getJiachengzhonglei());
            fuben.setFuben_daojishi(configBy.getWincold());
            long now = System.currentTimeMillis();
            fuben.setUpdate_time(now);
            fuben.setInsert();
            
            fubens.add(fuben);
            //add2Online(fuben);
            put(fuben.getFuben_id(), fuben);
            DbService.getInstance().add2Queue(fuben);
        }
        
        return fubens;
    }
    
    public List<Fuben> getFubenList(Long player_id) {
        Set<Integer> keys = ConfigDatasPool.getInstance().configFubenContainer.getKeys();
        Iterator<Integer> it = keys.iterator();
        List<Fuben> fubens = new ArrayList<>(keys.size());
        long now = System.currentTimeMillis();
        while(it.hasNext()) {
            Fuben fuben = get(fuben_id(player_id, it.next()));
            long update_time = fuben.getUpdate_time();
            int mix = (int)(now-update_time)/1000;
            int fuben_daojishi = fuben.getFuben_daojishi();
            fuben_daojishi -= mix;
            if(fuben_daojishi<0) fuben_daojishi = 0;
            /** 以下测试参数 **/
            // fuben_daojishi = 20;
            /** 以上 **/
            fuben.setFuben_daojishi(fuben_daojishi);
            fubens.add(fuben);
        }
        return fubens;
    }
    
    /**
     * 是否允许进入该地图
     * @return
     */
    public ResPlayerPreEnterSceneMessage allowInMap(Long player_id, int mapId) {
        ResPlayerPreEnterSceneMessage resp = null;
        String msg = "";
        int id = mapId-1100;
        if(id<=0) {
            msg = "不允许进入副本地图. ==> 请求进入的地图ID["+mapId+"]错误. 地图ID必须大于1100. 玩家ID["+player_id+"]";
            logger.error(msg);
            resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_FAIL,msg);
           // return false;
        }
        //ConfigDatasPool.getInstance().configFubenContainer
        Fuben fuben = get(fuben_id(player_id, id));
        
        long now = System.currentTimeMillis();
        long update_time = fuben.getUpdate_time();
        int mix = (int)(now-update_time)/1000;
        int fuben_daojishi = fuben.getFuben_daojishi();
        fuben_daojishi -= mix;
        if(fuben_daojishi<=0) {
            // 满足进入地图的条件
            fuben.setUpdate_time(now);
            fuben.setFocsUpdate();
            put(fuben.getFuben_id(), fuben);
            Player player = PlayerManager.getInstance().get(player_id);
            player.setFuben_map_id(mapId);
            DbService.getInstance().add2Queue(fuben);
            resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_SUCC);
        } else {
            msg = "不允许进入副本地图. ==> 请求进入的地图ID["+mapId+"] 倒计时不为零.玩家ID["+player_id+"]";
            logger.error(msg);
            resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_FAIL,msg);
        }
        return  resp;
    }
    
    public Kapai queryOneEnemy(long player_id, int map_id) {
        
        int id = map_id-1100;
        Fuben fuben = get(fuben_id(player_id, id));
        
        ConfigFuben configFuben = ConfigDatasPool.getInstance().configFubenContainer.getConfigBy(id);
        ConfigBingzhong configBingzhong = ConfigDatasPool.getInstance().configBingzhongContainer.getConfigBy(configFuben.getBingzhong());
        
        ConfigFubenboss configFubenboss = new ConfigFubenboss();
        configFubenboss.setLevel(fuben.getFuben_level());
        configFubenboss.setBingzhong(configFuben.getBingzhong());
        ConfigFubenboss fubenboss = ConfigDatasPool.getInstance().configFubenbossContainer.getConfigBy(configFubenboss.getKey());
        Kapai kapai = new Kapai();
        kapai.setShengmingzhi(fubenboss.getHp());
        kapai.setGongjizhi(fubenboss.getAttack());
        kapai.setBingzhong(configFuben.getBingzhong());
        kapai.setS_dengji(fuben.getFuben_level());
        
        ConfigSoilderLevel configSoilderLevel = ConfigDatasPool.getInstance().configSoilderLevelContainer.getConfigBy(kapai.getKey(configBingzhong.getBingzhong()));
        kapai.setSpeed(configSoilderLevel.getSpeed());
        /** 精准度和伤害范围 由卡牌等级表更改为兵种表控制  update by zhangjiajun at 2017/11/6  **/
        //kapai.setJingzun(configBy2.getJingzun());
        //kapai.setFanwei(configBy2.getFanwei());
        kapai.setJingzun(configBingzhong.getA_jingzhundu());
        kapai.setFanwei(configBingzhong.getA_fanweishanghai());
        return kapai;
    }
    
    public RankSoilderTeam getTeam(long player_id, int map_id) {
        RankSoilderTeam rst = new RankSoilderTeam();
        int id = map_id-1100;
        ConfigFuben configFuben = ConfigDatasPool.getInstance().configFubenContainer.getConfigBy(id);
        String soilderIds =  "0,0,"+configFuben.getBingzhong()+",0,0";
        rst.setSoilderIds(soilderIds);
        
        ConfigBingzhong configBingzhong = ConfigDatasPool.getInstance().configBingzhongContainer.getConfigBy(configFuben.getBingzhong());
        
        rst.setPlayer_name(configFuben.getScene() +"-"+configBingzhong.getBingzhong());

        return rst;
    } 
    
    /**
     * 处理副本胜利
     */
    public void execute_win(int map_id, long player_id) {
        ResBattleEndMessage resp = new ResBattleEndMessage();
        resp.setCode(RankDataPool.BATTLE_WIN);
        
        Player player = PlayerManager.getInstance().get(player_id);
        player.setFuben_map_id(0); // 副本结束标志
        
        long now = System.currentTimeMillis();
        int id = map_id-1100;
        
        Fuben fuben = get(fuben_id(player_id, id));
        ConfigFuben configFuben = ConfigDatasPool.getInstance().configFubenContainer.getConfigBy(id);
        ConfigFubenlevel configFubenlevel = ConfigDatasPool.getInstance().configFubenlevelContainer.getConfigBy(fuben.getFuben_level());
        
        // 获取副本领取情况
        int fuben_achieve = player.getFuben_achieve();
        int i=id-1;
        boolean bit = ByteUtils.getBit(fuben_achieve, i);
        if(bit) {
            //已领取
        } else {
            // 未领取
            
            // 设置领取
            ByteUtils.setBitTo1(fuben_achieve, i);
            player.setFuben_achieve(fuben_achieve);
            
            // 奖励卡牌
            int pingzhi = configFuben.getPingzhi();
            if(pingzhi==3) {
                pingzhi = RollUtils.roll(2);
            }
            
            EventDispatcher.getInstance()
            .fireEvent(new EventKapaiNew(EventType.KAPAI_NEW, player_id, configFuben.getPingzhi(),
                    configFuben.getBingzhong(), 1, pingzhi,
                    1, configFuben.getStar()));
            
            
        }


        
        // 副本操作基准时间戳
        fuben.setUpdate_time(now);
        int fuben_win_num = fuben.getFuben_win_num();
        fuben_win_num++;
        fuben.setFuben_win_num(fuben_win_num);
        
        // 副本冷却倒计时
        int fuben_daojishi = configFuben.getWincold();
        fuben.setFuben_daojishi(fuben_daojishi);
        int fuben_level = fuben.getFuben_level();
        if(fuben_level<configFuben.getDengjishangxian()) fuben_level++;
        fuben.setFuben_level(fuben_level);
        fuben.setFocsUpdate();
        put(fuben.getFuben_id(), fuben);
        
        // 加成种类
        int type = configFuben.getJiachengzhonglei();
        if(type==3) {
            type = RollUtils.roll(2);
            
        }
        resp.setJiacheng_type(type);
        int jiacheng = configFubenlevel.getJiacheng();
        // 加成值
        resp.setJiachengzhi(jiacheng);
        MessagePusher.pushMessage(player_id, resp);
        
        DbService.getInstance().add2Queue(fuben);
        
        RankSoilderTeam rankSoilderTeam = RankSoilderTeamManager.getInstance().get(player_id);
        List<Kapai> teamKapais = TeamManager.getInstance().getTeamKapai(rankSoilderTeam);

        for(Kapai kapai : teamKapais) {
            int jiachengzhonglei = kapai.getJiachengzhonglei();
           
            
            if(jiachengzhonglei==type) {
                float jiachengbi = kapai.getJiachengbi();
                jiachengbi+=jiacheng;
                kapai.setJiachengbi(jiachengbi);
                DbService.getInstance().add2Queue(kapai);
            }
        }
        
        
    }
    
    /**
     * 处理副本失败
     */
    public void execute_lose(int map_id, long player_id) {
        ResBattleEndMessage resp = new ResBattleEndMessage();
        resp.setCode(RankDataPool.BATTLE_LOSE);
        MessagePusher.pushMessage(player_id, resp);
        
        Player player = PlayerManager.getInstance().get(player_id);
        player.setFuben_map_id(0); // 副本结束标志
        
        long now = System.currentTimeMillis();
        int id = map_id-1100;
        Fuben fuben = get(fuben_id(player_id, id));
        fuben.setUpdate_time(now);
        int fuben_lose_num = fuben.getFuben_lose_num();
        fuben_lose_num++;
        fuben.setFuben_lose_num(fuben_lose_num);
        int fuben_daojishi = ConfigDatasPool.getInstance().configFubenContainer.getConfigBy(id).getLosecold();
        fuben.setFuben_daojishi(fuben_daojishi);
        fuben.setFocsUpdate();
        put(fuben.getFuben_id(), fuben);
        DbService.getInstance().add2Queue(fuben);
        

    } 
}
