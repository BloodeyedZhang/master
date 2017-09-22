package game_server_parent.master.game.rank;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.team.TeamManager;
import game_server_parent.master.orm.utils.DbUtils;

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
        String sql = "SELECT * FROM RankSoilderTeam where player_id = {0}";
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
                TeamManager.getInstance().updateSoilderTeam(rankSoilderTeam, split);
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
    
    public RankSoilderTeam queryOnnRandonm(long id) {
        String sql = "SELECT * FROM RankSoilderTeam where player_id != {0}";
        sql = MessageFormat.format(sql, String.valueOf(id));
        RankSoilderTeam soilderTeam = DbUtils.queryOne(DbUtils.DB_USER, sql, RankSoilderTeam.class);
        if(soilderTeam == null) soilderTeam = new RankSoilderTeam();
        return soilderTeam;
    }
    
    /** 匹配一只队伍 **/
    public RankSoilderTeam queryOneEnemy(long player_id) {
        
        return null;
    }
    
    /** 待定 匹配多只队伍 **/
    public List<RankSoilderTeam> queryManyEnemy(){
        
        return null;
    }
}
