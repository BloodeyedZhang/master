package game_server_parent.master.net.context;

/**
 * <p>Filename:IDistributeTask.java</p>
 * <p>Description: 可分发的任务接口 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public interface IDistributeTask {
    /**
     * 分发的工作线程索引
     * @return
     */
    int distributeKey();
    
    /**
     * 获取名字
     * @return
     */
    String getName();
    
    /**
     * 执行业务
     */
    void action();
}
