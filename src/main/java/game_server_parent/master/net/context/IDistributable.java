package game_server_parent.master.net.context;

/**
 * <p>Filename:IDistributable.java</p>
 * <p>Description: 线程分发 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public interface IDistributable {
    /**
     * 分发的工作线程索引
     * @return
     */
    int distributeKey();
}
