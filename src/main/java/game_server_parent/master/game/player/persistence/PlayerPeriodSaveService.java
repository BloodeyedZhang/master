package game_server_parent.master.game.player.persistence;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AbstractIdleService;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerDataPool;
import game_server_parent.master.game.player.PlayerListener;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.PlayerNameManager;

/**
 * <p>Filename:PlayerPeriodSaveService.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月10日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class PlayerPeriodSaveService extends AbstractIdleService {
    private Logger logger = LoggerFactory.getLogger(PlayerPeriodSaveService.class);
    public static final int BatchNumber = 20;

    public static final long Save_Interval_Time = 5 * 60 * 1000l / BatchNumber;

    public static final long IMSave_Interval_Time = 5 * 1000l;

    public static final long Insert_Interval_Time = 1000l;
    
    private long saveIntervalTime = Save_Interval_Time;

    private long imSaveIntervalTime = IMSave_Interval_Time;

    private long insertIntervalTime = Insert_Interval_Time;
    
    private long lastUpdateTime = 0l;

    private long lastIMUpdateTime = 0l;
    
    private ScheduledFuture<?> scheduledFuture;
    
    private static PlayerPeriodSaveService instance = new PlayerPeriodSaveService();
    
    private PlayerPeriodSaveService() {
    }
    
    public static PlayerPeriodSaveService getInstance() {
        return instance;
    }
    
    @Override
    protected void startUp() throws Exception {
        scheduledFuture = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(logger.isDebugEnabled()) {
                    logger.debug("执行PlayerSaveService数据持久化");
                }
                try {
                    //doImmediatelyInsert();
    
                    // immediateSave(period real)
                    long now = System.currentTimeMillis();
/*                    if (now - lastIMUpdateTime >= imSaveIntervalTime) {
                        lastIMUpdateTime = now;
                        doSave(SaveMode.PeriodBatchSave);
                    }*/
                    
                    if (now - lastIMUpdateTime >= imSaveIntervalTime) {
                        lastIMUpdateTime = now;
                        PlayerNameManager.getInstance().save();
                    }
    
                    // periodSave
                    if (now - lastUpdateTime < saveIntervalTime) {
                        return;
                    }
    
                    ConcurrentMap<Long,Player> onlinePlayers = PlayerManager.getInstance().getOnlinePlayers();
                    if (onlinePlayers.size() <= 0) {
                        return;
                    }
                    lastUpdateTime = now;
                    createSaveJob(onlinePlayers);
                } catch (Throwable e) {
                    logger.error("PlayerPeriodSaveService Exception:" + e.toString());
                }
            }

        }, saveIntervalTime, saveIntervalTime, TimeUnit.MILLISECONDS);
    }
    
    private void doImmediatelyInsert() {
       // ConcurrentMap<Long,Player> onlinePlayers = PlayerManager.getInstance().getOnlinePlayers();
        
    }
    
    private void createSaveJob(ConcurrentMap<Long, Player> onlinePlayers) {
        // TODO Auto-generated method stub
        for(Player player : onlinePlayers.values()) {
            if(!player.isInsert()) {
               player.setFocsUpdate();
            }
            if(player.getIs_ai() == PlayerDataPool.NOT_IS_AI) {
                DbService.getInstance().add2Queue(player);
            }
        }
    }

    @Override
    protected void shutDown() throws Exception {
        // TODO Auto-generated method stub
        scheduledFuture.cancel((false));
        try {
            if (logger.isInfoEnabled()) {
                logger.info("waiting for period save future stop");
            }

            scheduledFuture.get();
        } catch (Exception e) {
        }
    }
}
