package game_server_parent.master.db;

import java.util.concurrent.BlockingQueue;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicBoolean;
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
public class DbService {

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
    
    private final AtomicBoolean run = new AtomicBoolean(true);
    
    public void add2Queue(BaseEntity<?> entity) {
        this.queue.add(entity);
    }
    
    
    private class Worker implements Runnable {
        @Override
        public void run() {
            while(run.get()) {
                try {
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

}
