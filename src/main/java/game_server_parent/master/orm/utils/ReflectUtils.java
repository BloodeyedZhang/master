package game_server_parent.master.orm.utils;

import java.lang.reflect.Method;

/**
 * <p>Filename:ReflectUtils.java</p>
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
public class ReflectUtils {
    public static Object getMethodValue(Object obj, String property)
            throws Exception {
            String methodName = "get" + StringUtils.firstLetterToUpperCase(property);
            Method method = obj.getClass().getMethod(methodName);
            return method.invoke(obj);
        }
}
