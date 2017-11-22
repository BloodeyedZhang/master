package game_server_parent.master.db;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.Service;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicBoolean;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.utils.BlockingUniqueQueue;
import game_server_parent.master.utils.NameableThreadFactory;

/**
 * <p>Filename:DbService.java</p>
 * <p>Description: 用户数据异步持久化的服务 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class DbService extends AbstractIdleService  {
    
    private Logger logger = LoggerFactory.getLogger(DbService.class);
    
    public static final int BatchNumber = 20;

    public static final long Save_Interval_Time = 5 * 60 * 1000l / BatchNumber;
    
    private ScheduledFuture<?> scheduledFuture;
    
    private static volatile DbService instance;
    
    public static DbService getInstance() {
        if (instance ==  null) {
            synchronized (DbService.class) {
                if (instance ==  null) {
                    instance = new DbService();
                }
            }
        }
        return instance;
    }
    
    
    /**
     * 启动消费者线程
     */
    
    public void init() {
        new NameableThreadFactory("db-save-service").newThread(new Worker()).start();
    }
    
    
    @SuppressWarnings("rawtypes")
    private BlockingQueue<BaseEntity> queue = new BlockingUniqueQueue<BaseEntity>();
    //private BlockingQueue<BaseEntity> queue_secondary = new BlockingUniqueQueue<BaseEntity>();
    
    private final AtomicBoolean run = new AtomicBoolean(true);
    
    public void add2Queue(BaseEntity<?> entity) {
        this.queue.add(entity);
    }
    
    
    private class Worker implements Runnable {
        @Override
        public void run() {
            while(run.get()) {
                try {
                    if(logger.isDebugEnabled()) {
                        logger.debug("执行DBService数据持久化");
                    }
                    BaseEntity<?> entity = queue.take();
                    saveToDb(entity);
                } catch (InterruptedException e) {
                    LoggerUtils.error("", e);
                }
            }
        }
    }
    
    /**
     * 数据真正持久化
     * @param entity
     */
    private void saveToDb(BaseEntity<?> entity) {
        entity.save();
    }


    @Override
    protected void startUp() throws Exception {
        scheduledFuture = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(logger.isDebugEnabled()) {
                    logger.debug("执行DBService数据持久化");
                }
                while(run.get()) {
                    try {
                        BaseEntity<?> entity = queue.take();
                        saveToDb(entity);
                    } catch (InterruptedException e) {
                        LoggerUtils.error("DBService exception", e);
                    }
                }
            }

        }, Save_Interval_Time, Save_Interval_Time, TimeUnit.MILLISECONDS);
    }


    @Override
    protected void shutDown() throws Exception {
        // TODO Auto-generated method stub
        scheduledFuture.cancel(false);
        scheduledFuture.get();
    }

}
