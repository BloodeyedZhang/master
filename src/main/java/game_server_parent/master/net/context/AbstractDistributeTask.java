package game_server_parent.master.net.context;

/**
 * <p>Filename:AbstractDistributeTask.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class AbstractDistributeTask implements IDistributeTask {

    /** 消息分发器的索引 */
    protected int distributeKey;
    
    /** 业务开始执行的毫秒数 */
    private long startMillis;
    
    /** 业务结束执行的毫秒数 */
    private long endMillis;
    
    @Override
    public int distributeKey() {
        // TODO Auto-generated method stub
        return distributeKey;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return this.getClass().getSimpleName();
    }
    public long getStartMillis() {
        return startMillis;
    }

    public void markStartMillis() {
        this.startMillis = System.currentTimeMillis();
    }

    public long getEndMillis() {
        return endMillis;
    }

    public void markEndMillis() {
        this.endMillis = System.currentTimeMillis();
    }

}
