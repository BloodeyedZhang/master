package game_server_parent.master.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Filename:ArrayUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ArrayUtils {

    private static final char separtor = ',';
    
    public static String array2String(int[] array) {
        return StringUtils.join(array, separtor);
    } 
}
