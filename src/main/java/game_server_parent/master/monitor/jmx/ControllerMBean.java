package game_server_parent.master.monitor.jmx;

/**
 * <p>Filename:ControllerMBean.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月1日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public interface ControllerMBean {

    /** 
     * 统计在线玩家总数 
     * @return 
     */  
    int getOnlinePlayerSum();  
      
    /** 
     * 统计内存使用情况 
     * @return 
     */  
    String getMemoryInfo();  
    
    /**
     * 执行JavaScript代码
     * @param jsCode
     * @return
     */
    String execJavascript(String jsCode);
}
