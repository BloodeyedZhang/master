package game_server_parent.master.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p>Filename:SchedulerManager.java</p>
 * <p>Description: 调度任务工具 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月12日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public enum SchedulerManager {

    INSTANCE;
    
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new NameableThreadFactory("Scheduler-service"));

    /** 名字不能重复的tasks */
    private ConcurrentMap<String, ScheduledFuture> uniqueTasks = new ConcurrentHashMap<>();

    /** 相同名字归为一组的tasks */
    private ConcurrentMap<String, List<ScheduledFuture>> groupTasks = new ConcurrentHashMap<>();

    /**
     * 注册互斥的timeout任务
     * @param taskName
     * @param task
     * @param delay
     */
    public void registerUniqueTimeoutTask(String taskName, Runnable task, long delay) {
        if (uniqueTasks.containsKey(taskName)) {
            ScheduledFuture oldTask = uniqueTasks.get(taskName);
            oldTask.cancel(false);
        }
        ScheduledFuture taskFuture = executor.schedule(task, delay, TimeUnit.MILLISECONDS);
        uniqueTasks.put(taskName, taskFuture);
    }
    
    /**
     * 注册timeout任务
     * @param taskName
     * @param task
     * @param delay
     */
    public void registerTimeoutTask(String taskName, Runnable task, long delay) {
        groupTasks.putIfAbsent(taskName, new ArrayList<ScheduledFuture>());
        List<ScheduledFuture> tasks = groupTasks.get(taskName);
        ScheduledFuture taskFuture = executor.schedule(task, delay, TimeUnit.MILLISECONDS);
        tasks.add(taskFuture);
    }
    
    /**
     * 注册定时任务
     * @param taskName
     * @param task
     * @param delay
     * @param period
     */
    public void scheduleAtFixedRate(String taskName, Runnable task, long delay, long period) {
        groupTasks.putIfAbsent(taskName, new ArrayList<ScheduledFuture>());
        List<ScheduledFuture> tasks = groupTasks.get(taskName);
        ScheduledFuture taskFuture = executor.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
        tasks.add(taskFuture);
    }
    
    /**
     * 注销任务
     * @param taskName
     */
    public void cancleTask(String taskName) {
        ScheduledFuture oldTask = uniqueTasks.get(taskName);
        if (oldTask != null) {
            oldTask.cancel(false);
        }
        List<ScheduledFuture> tasks = groupTasks.get(taskName);
        if (tasks != null && tasks.size() > 0) {
            for (ScheduledFuture task:tasks) {
                task.cancel(false);
            }
        }
    }
}
