package game_server_parent.master.game.team;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.KapaiId;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.database.user.storage.Team;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.orm.utils.DbUtils;

/**
 * <p>Filename:TeamManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class TeamManager extends CacheService<Long, SoilderTeam> {
    
    private Logger logger = LoggerFactory.getLogger(TeamManager.class);
    
    private static TeamManager instance = new TeamManager();

    public static TeamManager getInstance() {
        return instance;
    }

    @Override
    public SoilderTeam load(Long id) throws Exception {
        String sql = "SELECT * FROM SoilderTeam where team_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(id));
        SoilderTeam soilderTeam = DbUtils.queryOne(DbUtils.DB_USER, sql, SoilderTeam.class);
        return soilderTeam;
    }
    public SoilderTeam createNewKapai(long player_id) {
        SoilderTeam kapai = new SoilderTeam();
        kapai.setPlayer_id(player_id);
        int nextId = this.getNextId();
        kapai.setTeam_id(nextId);
        kapai.setSoilderIds("0,0,0,0,0");
        //设为插入状态
        kapai.setInsert();

        return kapai;
    }
    
    public List<SoilderTeam> getPlayerTeamList(long player_id){
        String sql = "SELECT * FROM SoilderTeam where player_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(player_id));
        List<SoilderTeam> kapai_list = DbUtils.queryMany(DbUtils.DB_USER, sql, SoilderTeam.class);
        return kapai_list;
    }
    
    public int getNextId() {
        String sql = "select nextval('seq_soilderTeam_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
    
    public int getCurId() {
        String sql = "select currval('seq_soilderTeam_num') as nextId;";
        KapaiId kapaiId = DbUtils.queryOne(DbUtils.DB_USER, sql, KapaiId.class);
        return kapaiId.getNextId();
    }
    
    /**
     * 更新队伍数据：队伍成员、队伍总生命值、队伍总战斗力
     * @param st
     */
    public void updateTeam(SoilderTeam st) {
        // TODO
        String soilderIds = st.getSoilderIds();
        String[] split = soilderIds.split(",");

        if(split.length == 5) {
            updateSoilderTeam(st, split);
            st.setFocsUpdate();
            DbService.getInstance().add2Queue(st);
        } else {
            logger.error("队伍{}，成员少于5人 {}",st.getTeam_id(),soilderIds);
        }
    }
    
    /** 更新堆中对象信息 */
    public void updateSoilderTeam(Team st, String[] split) {
        int sum_shengming = 0;
        int sum_gongji = 0;
        for (String str : split) {
            if(str.isEmpty() || str.equals("0")) {
                continue;
            } else {
                try {
                    int id = Integer.parseInt(str);
                    Kapai kapai = KapaiManager.getInstance().get((long)id);
                    sum_shengming += kapai.getShengmingzhi();
                    sum_gongji += kapai.getGongjizhi();
                } catch (NumberFormatException e) {
                    logger.error("获取成员生命攻击数值出错 卡牌实例ID：{}",str, e);
                }
            }
        }
        st.setShengmingzhi(sum_shengming);
        st.setGongjizhi(sum_gongji);
    }
    
    /** 获取队伍中的成员列表 */
    public List<Kapai> getTeamKapai(Team st){
        List<Kapai> kapais = new ArrayList<Kapai>(5);
        String soilderIds = st.getSoilderIds();
        String[] split = soilderIds.split(",");
        for (String str : split) {
            if(str.isEmpty() || str.equals("0")) {
                kapais.add(new Kapai());
                continue;
            } else {
                try {
                    int id = Integer.parseInt(str);
                    Kapai kapai = KapaiManager.getInstance().get((long)id);
                    kapais.add(kapai);
                } catch (NumberFormatException e) {
                    logger.error("获取成员生命攻击数值出错 卡牌实例ID：{}",str, e);
                    kapais.add(new Kapai());
                }
            }
        }
        return kapais;
    }
}