package game_server_parent.master.orm.utils;

/**
 * <p>Filename:StringUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月28日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class StringUtils {

    public static boolean isEmpty(String word) {
        return word == null || word.length() <= 0;
    }
    
    /** 
     * 将单词的第一个字母大写
     * @param word
     * @return
     */
    public static String firstLetterToUpperCase(String word) {
        StringBuilder sb = new StringBuilder(word);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
    
    /** 
     * 将单词的第一个字母小写
     * @param word
     * @return
     */
    public static String firstLetterToLowerCase(String word) {
        StringBuilder sb = new StringBuilder(word);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

}
