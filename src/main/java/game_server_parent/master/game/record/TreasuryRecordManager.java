package game_server_parent.master.game.record;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.cache.CacheService;
import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.record.TreasuryRecord;
import game_server_parent.master.game.database.user.storage.Treasury;
import game_server_parent.master.game.treasury.TreasuryManager;
import game_server_parent.master.orm.utils.DbUtils;
import game_server_parent.master.utils.ArrayUtils;
import game_server_parent.master.utils.DateUtil;

/**
 * <p>Filename:TresuryRecondManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class TreasuryRecordManager extends CacheService<Long, TreasuryRecord>  {
    private Logger logger = LoggerFactory.getLogger(TreasuryRecordManager.class);

    private static TreasuryRecordManager instance = new TreasuryRecordManager();

    public static TreasuryRecordManager getInstance() {
        return instance;
    }
    
    @Override
    public TreasuryRecord load(Long teasury_id) throws Exception {
        String sql = "SELECT * FROM treasuryrecord where treasury_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(teasury_id));
        TreasuryRecord player = DbUtils.queryOne(DbUtils.DB_USER, sql, TreasuryRecord.class);
        return player;
    }
    
    public List<TreasuryRecord> queryMany(long treasury_id) {
        String sql = "SELECT * FROM treasuryrecord where treasury_id = {0}";
        sql = MessageFormat.format(sql, String.valueOf(treasury_id));
        List<TreasuryRecord> list = DbUtils.queryMany(DbUtils.DB_USER, sql, TreasuryRecord.class);
        return list;
    }
    
    /**
     * 创建一条宝库掉落记录
     * @param treasury_id
     * @param player_id
     * @param index
     * @param coins
     * @param diamonds
     * @param params
     */
    public void create(long treasury_id, long player_id, int index, int coins, int diamonds, List<int[]> params) {
        TreasuryRecord treasuryRecord = new TreasuryRecord();
        treasuryRecord.setTreasury_id(treasury_id);
        treasuryRecord.setPlayer_id(player_id);
        treasuryRecord.setIndex(index);
        
        
        int size = params.size();
        
        if(size==5) {
            String bingzhongs = ArrayUtils.array2String(params.get(0));
            String jiachengbis = ArrayUtils.array2String(params.get(1));
            String xingjis = ArrayUtils.array2String(params.get(2));
            String jiachengtypes = ArrayUtils.array2String(params.get(3));
            String pinzhis = ArrayUtils.array2String(params.get(4));
            
            treasuryRecord.setCoins(coins);
            treasuryRecord.setDiamonds(diamonds);
            treasuryRecord.setBingzhongs(bingzhongs);
            treasuryRecord.setJiachengbis(jiachengbis);
            treasuryRecord.setXingjis(xingjis);
            treasuryRecord.setJiachengtypes(jiachengtypes);
            treasuryRecord.setPinzhis(pinzhis);
            String currentTime = DateUtil.getCurrentTime();
            treasuryRecord.setCreatetime(currentTime);
            
            treasuryRecord.setInsert();
            DbService.getInstance().add2Queue(treasuryRecord);
        } else {
            logger.error("无法录入宝库记录，因为参数数组 应该是5，实际是 size="+size);
        }
        

    } 
}
