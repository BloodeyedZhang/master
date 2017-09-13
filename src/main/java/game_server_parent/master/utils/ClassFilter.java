package game_server_parent.master.utils;

/**
 * <p>Filename:ClassFilter.java</p>
 * <p>Description: 类扫描过滤器 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public interface ClassFilter {
    /** 
     * 是否满足条件
     * @param clazz 
     * @return 
     */  
    boolean accept(Class<?> clazz);  
}
