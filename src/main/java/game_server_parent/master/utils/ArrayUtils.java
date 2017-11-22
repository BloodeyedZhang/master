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
    
    private static int[] arr = new int[40];
    
    public static String array2String(int[] array) {
        return StringUtils.join(array, separtor);
    } 
    
    public static void initFeibonaqie() {
        arr[0] = arr[1] = 100;  
        for (int i = 2; i < arr.length; i++) {  
            arr[i] = arr[i - 1] + arr[i - 2];  
        } 
    }
    
    public static int getFeibonaqie(int index) {
        int count = arr.length;
        if(index>=count) {
            return arr[count-1];
        } else if(index<0){
            return arr[0];
        } else {
            return arr[index];
        }
    }
}
