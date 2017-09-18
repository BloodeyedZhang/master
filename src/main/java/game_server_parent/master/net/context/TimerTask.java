package game_server_parent.master.net.context;

/**
 * <p>Filename:TimerTask.java</p>
 * <p>Description: timer任务  </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class TimerTask extends AbstractDistributeTask {
    private int currLoop;
    /** 小于0表示无限任务 */
    private int maxLoop;
    
    public TimerTask(int distributeKey) {
        this(distributeKey, 1);
    }

    public TimerTask(int distributeKey, int maxLoop) {
        this.distributeKey = distributeKey;
        this.maxLoop = maxLoop;
    }
    
    public void updateLoopTimes() {
        this.currLoop += 1;
    }
    
    public boolean canRunAgain() {
        if (this.maxLoop <= 0) {
            return true;
        }
        return this.currLoop < this.maxLoop;
    }
}
