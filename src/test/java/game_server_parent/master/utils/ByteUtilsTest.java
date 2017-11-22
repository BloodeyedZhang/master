package game_server_parent.master.utils;

/**
 * <p>Filename:ByteUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月15日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class ByteUtilsTest {

    public static void main(String[] args) {  
        int countBit = ByteUtils.countBit(5);
        System.out.println(countBit);
        
        //5:101
        boolean bit = ByteUtils.getBit(5, 2);
        System.out.println(bit);
        
        int setBitTo1 = ByteUtils.setBitTo1(0, 2);
        System.out.println(setBitTo1);
        
        int setBitTo0 = ByteUtils.setBitTo0(5, 0);
        System.out.println(setBitTo0);
        
        System.out.println( java.net.URLEncoder.encode("}"));
    }
}
