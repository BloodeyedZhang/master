package game_server_parent.master.game.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.utils.ClassScanner;

/**
 * <p>Filename:HttpCommandManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月12日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class HttpCommandManager {
    
    private static volatile HttpCommandManager instance;
    
    private static Map<Integer, HttpCommandHandler> handlers = new HashMap<>();
    
    public static HttpCommandManager getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (HttpCommandManager.class) {
            if (instance == null) {
                instance = new HttpCommandManager();
                instance.initialize();
            }
            return instance;
        }
    }
    
    private void initialize() {
        Set<Class<?>> handleClazzs = ClassScanner.listClassesWithAnnotation("game_server_parent.master.game.http", CommandHandler.class);  

        for (Class<?> clazz: handleClazzs) {  
            try {  
                HttpCommandHandler handler = (HttpCommandHandler) clazz.newInstance(); 
                CommandHandler annotation = handler.getClass().getAnnotation(CommandHandler.class);
                handlers.put(annotation.cmd(), handler);
            }catch(Exception e) {  
                LoggerUtils.error("", e);
            }  
        }  
    }
    
    /**
     * 处理后台命令
     * @param httpParams
     * @return
     */
    public HttpCommandResponse handleCommand(HttpCommandParams httpParams) {
        HttpCommandHandler handler = handlers.get(httpParams.getCmd());
        if (handler != null) {
            return handler.action(httpParams);
        }
        return null;
    }
}
