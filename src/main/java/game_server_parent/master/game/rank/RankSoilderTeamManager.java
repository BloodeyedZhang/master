package game_server_parent.master.game.rank;

import java.text.MessageFormat;
import java.util.List;

import org.logicalcobwebs.cglib.core.DebuggingClassWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigBingzhong;
import game_server_parent.master.game.database.config.bean.ConfigFuben;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.scene.MapEnum;
import game_server_parent.master.game.team.TeamManager;
import game_server_parent.master.orm.utils.DbUtils;
import game_server_parent.master.utils.RollUtils;

/**
 * <p>Filename:RankSoilderTeamManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月21日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class RankSoilderTeamManager extends CacheService<Long, RankSoilderTeam> {

    private Logger logger = LoggerFactory.getLogger(RankSoilderTeamManager.class);
    
    private static RankSoilderTeamManager instance = new RankSoilderTeamManager();

    public static RankSoilderTeamManager getInstance() {
        return instance;
    }

    @Override
    public RankSoilderTeam load(Long id) throws Exception {
        String sql = "SELECT * FROM ranksoilderteam where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(id));
        RankSoilderTeam soilderTeam = DbUtils.queryOne(DbUtils.DB_USER, sql, RankSoilderTeam.class);
        if(soilderTeam == null) soilderTeam = new RankSoilderTeam();
        return soilderTeam;
    }
    
    public void updateSoilderTeam(SoilderTeam st, long player_id) {
        
        String soilderIds = st.getSoilderIds();

        
        if(soilderIds.equals("0,0,0,0,0")) {
            // 不需要更新队伍信息
        } else {
            String[] split = soilderIds.split(",");
            if(split.length==5) {
                
                RankSoilderTeam rankSoilderTeam = RankSoilderTeamManager.getInstance().get(player_id);
                rankSoilderTeam.setSoilderIds(soilderIds);
                int sum_fight = TeamManager.getInstance().updateSoilderTeam(rankSoilderTeam, split);
                Player player = PlayerManager.getInstance().get(player_id);
                player.setFight(sum_fight); // 更新玩家战力值
                
                if(rankSoilderTeam.getTeam_id() == 0) {
                    // 数据库中没有数据需要插入
                    rankSoilderTeam.setTeam_id(st.getTeam_id());
                    rankSoilderTeam.setPlayer_id(player_id);
                    rankSoilderTeam.setInsert();
                } else {
                    // need update
                    rankSoilderTeam.setTeam_id(st.getTeam_id());
                    rankSoilderTeam.setFocsUpdate();
                }
                DbService.getInstance().add2Queue(rankSoilderTeam);
            } else {
                logger.error("队伍{}，成员少于5人 {}",st.getTeam_id(),soilderIds);
            }
        }

    }
    
    /** 获取随机一条数据*/
    public RankSoilderTeam queryOnnRandonm(long id) {
       // String sql = "SELECT * FROM RankSoilderTeam where player_id != {0}";
        String sql= "SELECT * FROM ranksoilderteam  AS t1  JOIN (SELECT ROUND(RAND() * ((SELECT MAX(player_id) FROM ranksoilderteam)-(SELECT MIN(player_id) FROM ranksoilderteam))"
                + "+(SELECT MIN(player_id) FROM ranksoilderteam)) AS player_id) AS t2 WHERE t1.player_id >= t2.player_id and t1.player_id != {0} ORDER BY t1.player_id LIMIT 1";
        sql = MessageFormat.format(sql, String.valueOf(id));
        RankSoilderTeam soilderTeam = null;
        try {
            soilderTeam = DbUtils.queryOne(DbUtils.DB_USER, sql, RankSoilderTeam.class);
        } catch (Exception e) {
            logger.error("获取随机一条数据 处理异常 e:{}",e);
        }
        if(soilderTeam == null) {
            logger.error("获取随机一条数据 没有获得需要的数据 player_id={}",id);
            soilderTeam = new RankSoilderTeam();
        } 
        return soilderTeam;
    }
    
    public RankSoilderTeam queryOneTeam(long player_id) {
        String sql= "SELECT * FROM ranksoilderteam where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(player_id));
        RankSoilderTeam soilderTeam = null;
        try {
            soilderTeam = DbUtils.queryOne(DbUtils.DB_USER, sql, RankSoilderTeam.class);
        } catch (Exception e) {
            logger.error("获取随机一条数据 处理异常 e:{}",e);
        }
        if(soilderTeam == null) {
            logger.error("获取随机一条数据 没有获得需要的数据 player_id={}",player_id);
            soilderTeam = new RankSoilderTeam();
        } 
        return soilderTeam;
    }
    
    // 随机一条
    //SELECT * FROM users  AS t1  JOIN (SELECT ROUND(RAND() * ((SELECT MAX(userId) FROM `users`)-(SELECT MIN(userId) FROM users))+(SELECT MIN(userId) FROM users)) AS userId) AS t2 WHERE t1.userId >= t2.userId ORDER BY t1.userId LIMIT 1
    // 随机多条
    //SELECT * FROM users WHERE userId >= ((SELECT MAX(userId) FROM users)-(SELECT MIN(userId) FROM users)) * RAND() + (SELECT MIN(userId) FROM users)  LIMIT 1
    
    /** 匹配一只队伍 **/
    public RankSoilderTeam queryOneEnemy(long player_id) {
        /*
         * 匹配规则——
         * 1、期望战斗力=玩家队伍战力+150*当前战绩
         * 2、期望战斗力正负100，先玩家后AI，积分与玩家的差距由近到远排序，得到一个序列
         * 3、从序列上第一个开始，每一个取一个1-10的随机数，1-3则成功匹配作为对手，4-10则跳过取序列上下一个
         * 4、如果序列结束仍然未找到匹配对手，则回到步骤3重复选取
         */
        
        Player player = PlayerManager.getInstance().get(player_id);
        int expect_fight = player.getFight()+150*player.getRank_score();
        Player enemy = rankqueryOneByFight(expect_fight);
        
        if(enemy==null) {
            return new RankSoilderTeam();
        }
        //RankSoilderTeam queryOnnRandonm = queryOnnRandonm(player_id);
        RankSoilderTeam queryOnnRandonm = queryOneTeam(enemy.getPlayer_id());
        if(queryOnnRandonm.getTeam_id() == 0) {
            // 没有匹配到队伍, 匹配预设的队伍
            // queryOnnRandonm = queryOnePreEnemy(player_id);
        }
        return queryOnnRandonm;
    }
    
    private Player rankqueryOneByFight(int expect_fight) {
        String sql= "SELECT * FROM player where fight <= {0} and fight >= {1}";
        int tmp_fight = 250; // 期望战斗力误差范围值
        int min = expect_fight-tmp_fight;
        if(min<1) min=1; // 排除未配置队伍的玩家
        sql = MessageFormat.format(sql, String.valueOf(expect_fight+tmp_fight), String.valueOf(min));
        List<Player> players = null;
        try {
            players = DbUtils.queryMany(DbUtils.DB_USER, sql, Player.class);
        } catch (NullPointerException e) {
            return null;
        }
        Player duishou = null;
        duishou = queryOneEnemy(players);
        if(duishou==null) {
            //没有匹配到
            if(players.size()==0) {
                return null;
            }
            int roll = RollUtils.roll(players.size());
            duishou = players.get(roll-1);
        }
        return duishou;
    }
    
    private Player queryOneEnemy(List<Player> players) {
        for(Player player : players) {
            if(choose()) {
                return player;
            }
        }
        return  null;
    }
    
    private boolean choose() {
        int roll = RollUtils.roll(10);
        return roll <=3;
    }
    
    /** 待定 匹配多只队伍 **/
    public List<RankSoilderTeam> queryManyEnemy(){
        
        return null;
    }
}
