package game_server_parent.master.utils;

import java.awt.datatransfer.FlavorTable;

import org.codehaus.groovy.transform.sc.StaticCompilationVisitor;

/**
 * <p>Filename:tizhlv.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月8日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class tizhlv {
    
    public static void BMI(float tizhong, float shenggao) {
        float bmi = tizhong/(shenggao*shenggao);
        System.out.println("BMI="+bmi);
    } 
    
    public static void  main(String[] args) {
        float bmi=22F;
        float age = 29F;
        float sex = 1F; //boy=1,girl=0
        float tizhilv = (float) (1.2*bmi+0.23*age-5.4-10.8*sex);
        System.out.println("BMI="+bmi+", 体脂率="+tizhilv);
    }
}
