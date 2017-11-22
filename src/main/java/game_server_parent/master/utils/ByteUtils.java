package game_server_parent.master.utils;

/**
 * <p>
 * Filename:ByteUtils.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.
 * </p>
 * <p>
 * Company: WinTurn Network Technology
 * </p>
 * <p>
 * Summary:
 * </p>
 * <p>
 * Created: 2017年11月15日
 * </p>
 *
 * @author zjj
 * @version
 * 
 */
public class ByteUtils {

    /**
     *  求解正数的二进制表示法中的 1 的位数
     * @param num
     * @return
     */
    public static int countBit(int num) {
        int count = 0;
        for (; num > 0; count++) {
            num &= (num - 1);
        }
        return count;
    }
    
    /**
     * 获取 整数 num 的第 i 位的值 （判断某个数的第 i 位是0 还是 1？）
     * @param num
     * @param i
     * @return
     */
    public static boolean getBit(int num, int i)
    {
        return ((num & (1 << i)) != 0);//true 表示第i位为1,否则为0
    }
    
    /**
     * 将 整数 num 的第 i 位的值 置为 1
     * @param num
     * @param i
     * @return
     */
    public static int setBitTo1(int num, int i)
    {
        return (num | (1 << i));
    }
    
    /**
     * 将 整数 num 的第 i 位的值 置为 0
     * @param num
     * @param i
     * @return
     */
    public static int setBitTo0(int num, int i)
    {
       int mask = ~(1 << i);//000100
       return (num & (mask));//111011
    }
}
