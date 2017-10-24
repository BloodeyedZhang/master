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
public class RollUtilsTest {
public static void main(String[] args) {
    double i=2, j=2.1, k=2.5, m=2.9; 
    System.out.println("舍掉小数取整:Math.floor(2)=" + (int)Math.floor(i)); 
    System.out.println("舍掉小数取整:Math.floor(2.1)=" + (int)Math.floor(j)); 
    System.out.println("舍掉小数取整:Math.floor(2.5)=" + (int)Math.floor(k)); 
    System.out.println("舍掉小数取整:Math.floor(2.9)=" + (int)Math.floor(m)); 
                                         
    /* 这段被注释的代码不能正确的实现四舍五入取整 
    System.out.println("四舍五入取整:Math.rint(2)=" + (int)Math.rint(i)); 
    System.out.println("四舍五入取整:Math.rint(2.1)=" + (int)Math.rint(j)); 
    System.out.println("四舍五入取整:Math.rint(2.5)=" + (int)Math.rint(k)); 
    System.out.println("四舍五入取整:Math.rint(2.9)=" + (int)Math.rint(m)); 
    
    System.out.println("四舍五入取整:(2)=" + new DecimalFormat("0").format(i)); 
    System.out.println("四舍五入取整:(2.1)=" + new DecimalFormat("0").format(i)); 
    System.out.println("四舍五入取整:(2.5)=" + new DecimalFormat("0").format(i)); 
    System.out.println("四舍五入取整:(2.9)=" + new DecimalFormat("0").format(i)); 
    */ 
    
    System.out.println("四舍五入取整:(2)=" + new BigDecimal("2").setScale(0, BigDecimal.ROUND_HALF_UP)); 
    System.out.println("四舍五入取整:(2.1)=" + new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP)); 
    System.out.println("四舍五入取整:(2.5)=" + new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP)); 
    System.out.println("四舍五入取整:(2.9)=" + new BigDecimal("2.9").setScale(0, BigDecimal.ROUND_HALF_UP));

    System.out.println("凑整:Math.ceil(2)=" + (int)Math.ceil(i)); 
    System.out.println("凑整:Math.ceil(2.1)=" + (int)Math.ceil(j)); 
    System.out.println("凑整:Math.ceil(2.5)=" + (int)Math.ceil(k)); 
    System.out.println("凑整:Math.ceil(2.9)=" + (int)Math.ceil(m));

    System.out.println("舍掉小数取整:Math.floor(-2)=" + (int)Math.floor(-i)); 
    System.out.println("舍掉小数取整:Math.floor(-2.1)=" + (int)Math.floor(-j)); 
    System.out.println("舍掉小数取整:Math.floor(-2.5)=" + (int)Math.floor(-k)); 
    System.out.println("舍掉小数取整:Math.floor(-2.9)=" + (int)Math.floor(-m)); 
    
    System.out.println("四舍五入取整:(-2)=" + new BigDecimal("-2").setScale(0, BigDecimal.ROUND_HALF_UP)); 
    System.out.println("四舍五入取整:(-2.1)=" + new BigDecimal("-2.1").setScale(0, BigDecimal.ROUND_HALF_UP)); 
    System.out.println("四舍五入取整:(-2.5)=" + new BigDecimal("-2.5").setScale(0, BigDecimal.ROUND_HALF_UP)); 
    System.out.println("四舍五入取整:(-2.9)=" + new BigDecimal("-2.9").setScale(0, BigDecimal.ROUND_HALF_UP));

    System.out.println("凑整:Math.ceil(-2)=" + (int)Math.ceil(-i)); 
    System.out.println("凑整:Math.ceil(-2.1)=" + (int)Math.ceil(-j)); 
    System.out.println("凑整:Math.ceil(-2.5)=" + (int)Math.ceil(-k)); 
    System.out.println("凑整:Math.ceil(-2.9)=" + (int)Math.ceil(-m)); 
    } 
}
