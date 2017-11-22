package game_server_parent.master.utils;

/**
 * <p>Filename:Demo3.java</p>
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
public class Demo3 {  
    // 定义数组方法  
    public static void main(String[] args) {  
        int arr[] = new int[40];  
        arr[0] = arr[1] = 100;  
        for (int i = 2; i < arr.length; i++) {  
            arr[i] = arr[i - 1] + arr[i - 2];  
        }  
        System.out.println("斐波那契数列的前20项如下所示：");  
        for (int i = 0; i < arr.length; i++) {  
            if (i % 5 == 0)  
                System.out.println();  
            System.out.print(arr[i] + "\t");  
        }  
    }  
  
}  