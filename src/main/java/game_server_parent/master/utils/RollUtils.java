package game_server_parent.master.utils;

import java.math.BigDecimal;

/**
 * <p>Filename:RollUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月16日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class RollUtils {

    public static final int DEFAULT_MIN_ROLL_NUMBER = 1;

    /**
     * Roll点，范围[0.0,1.0）
     * @return
     */
    public static double rollDouble() {
        return Math.random();
    } 
    
    /**
     * ROLL点, 范围[1, 100]
     * 
     * @return [1, 100]
     */
    public static int roll() {
        return roll(100);
    }
    
    /**
     * ROLL点, 范围[1, max]
     * 
     * @return [1, max]
     */
    public static int roll(int max) {
        max = max < DEFAULT_MIN_ROLL_NUMBER ? DEFAULT_MIN_ROLL_NUMBER : max;
        return (int) (Math.random() * max + 1);
    }
    
    /**
     * r.nextGaussian()服从N(0,1)
     *  ans服从N(a,b)
     * @param a
     * @param b
     * @return
     */
    public static double ans(double a, double b) {
        java.util.Random r = new java.util.Random();
        double ans = r.nextGaussian()*Math.sqrt(b)+a;
        return ans;
    } 
    
    /**
     * 四舍五入取整
     * @param ans
     * @return
     */
    public static int round(double ans) {
        BigDecimal setScale = new BigDecimal(ans).setScale(0, BigDecimal.ROUND_HALF_UP);
        return setScale.intValue();
    }
}
