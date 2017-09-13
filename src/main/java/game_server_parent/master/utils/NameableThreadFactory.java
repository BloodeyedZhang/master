package game_server_parent.master.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Filename:NameableThreadFactory.java</p>
 * <p>Description: 可命名线程工厂 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class NameableThreadFactory implements ThreadFactory {
    private ThreadGroup threadGroup;
    
    private String groupName;
    
    private AtomicInteger idGenerator = new AtomicInteger(1);
    
    public NameableThreadFactory(String group) {
        this.groupName = group;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(threadGroup, r, getNextThreadName());
    }
    
    public Thread newDaemonThread(Runnable r) {
        Thread t =  new Thread(threadGroup, r, getNextThreadName());
        t.setDaemon(true);
        return t;
    }
    
    private String getNextThreadName() {
        return this.groupName + "-thread-" + this.idGenerator.getAndIncrement();
    }
}
