package game_server_parent.master.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.record.RecordListener;

/**
 * <p>Filename:DateUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.winturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2015-01-16</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class DateUtil {
    
    public static String getDate() {
        return DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis()));
    }
    
    /**
     * 获取当前时间，并格式化
     * 格式： yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }
    
    /**
     * 时间戳（精度为秒）格式化时间 yyyy-MM-dd HH:mm:ss
     * @param time
     * @return
     */
    public static String formatData(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time*1000L));
    }
    
    public static Date getDate(long time) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Date(time);
    }
    
    public static long getTime(String time){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date;
        try {
            date = format.parse(time);
            return date.getTime()/1000;
        } catch (ParseException e) {
            //LogUtil.businessError("DateUtil", "getTime exception.", null, e);
            e.printStackTrace();
        }
        return 0;
    }
    
    public static long getDefaultTime(String time) {
        time = (time.replace("T", " "));
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Date date;
        try {
            date = format.parse(time);
            return date.getTime()/1000;
        } catch (ParseException e) {
            //LogUtil.businessError("DateUtil", "getTime exception.", null, e);
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return 返回当前日期的0点 毫秒数
     */
    public static long getTimeForZero(String time) {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  
        Date date;
        try {
            date = format.parse(time);
            return date.getTime()/1000;
        } catch (ParseException e) {
            //LogUtil.businessError("DateUtil", "getTimeForZero exception.", null, e);
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * @param lastLogoutTime
     * @return
     */
    public static String formatDate(String date) {
        if (date == null || date.equals("")) {
            date = "empty";
        } else {
            date = DateUtil.formatData(Long.parseLong(date));
        }
        return date;
    }
    
    /**
     * 时间戳 (精度为秒)
     * @param time
     * @return
     */
    public static long currentTime() {
        return System.currentTimeMillis()/1000;
    }
/*    public static void main(String[] args) {
        System.out.println(getDate());
    }
*/
    
  //获得当天0点时间 
    public static int getTimesmorning(){ 
    Calendar cal = Calendar.getInstance(); 
    cal.set(Calendar.HOUR_OF_DAY, 0); 
    cal.set(Calendar.SECOND, 0); 
    cal.set(Calendar.MINUTE, 0); 
    cal.set(Calendar.MILLISECOND, 0); 
    return (int) (cal.getTimeInMillis()/1000); 
    } 
    //获得当天24点时间 
    public static int getTimesnight(){ 
    Calendar cal = Calendar.getInstance(); 
    cal.set(Calendar.HOUR_OF_DAY, 24); 
    cal.set(Calendar.SECOND, 0); 
    cal.set(Calendar.MINUTE, 0); 
    cal.set(Calendar.MILLISECOND, 0); 
    return (int) (cal.getTimeInMillis()/1000); 
    } 
    //获得本周一0点时间 
    public static int getTimesWeekmorning(){ 
    Calendar cal = Calendar.getInstance(); 
    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
    return (int) (cal.getTimeInMillis()/1000); 
    } 
    //获得本周日24点时间 
    public static int getTimesWeeknight(){ 
    Calendar cal = Calendar.getInstance(); 
    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
    return (int) ((cal.getTime().getTime()+ (7 * 24 * 60 * 60 * 1000))/1000); 
    } 
    //获得本月第一天0点时间 
    public static int getTimesMonthmorning(){ 
    Calendar cal = Calendar.getInstance(); 
    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
    cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH)); 
    return (int) (cal.getTimeInMillis()/1000); 
    } 
    //获得本月最后一天24点时间 
    public static int getTimesMonthnight(){ 
    Calendar cal = Calendar.getInstance(); 
    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0); 
    cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
    cal.set(Calendar.HOUR_OF_DAY, 24); 
    return (int) (cal.getTimeInMillis()/1000); 
    }
    
    
    
}
