package game_server_parent.master.listener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.logs.LoggerUtils;
import game_server_parent.master.utils.ClassFilter;
import game_server_parent.master.utils.ClassScanner;

/**
 * <p>Filename:ListenerManager.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public enum ListenerManager {
    INSTANCE;

    private Map<String, Method> map = new HashMap<String, Method>();

    private final String SCAN_PATH = "game_server_parent.master.game";
    
    public void initalize() {
        Set<Class<?>> listeners = ClassScanner.getClasses(SCAN_PATH, new ClassFilter() {
            @Override
            public boolean accept(Class<?> clazz) {
                return clazz.getAnnotation(Listener.class) != null;
            }
        });

        for (Class<?> listener: listeners) {
            try {
                Object handler = listener.newInstance();
                Method[] methods = listener.getDeclaredMethods();
                for (Method method:methods) {
                    EventHandler mapperAnnotation = method.getAnnotation(EventHandler.class);
                    if (mapperAnnotation != null) {
                        EventType[] eventTypes = mapperAnnotation.value();
                         for(EventType eventType: eventTypes) {
                             EventDispatcher.getInstance().registerEvent(eventType, handler);
                             map.put(getKey(handler, eventType), method);
                         }
                    }
                }
            }catch(Exception e) {
                LoggerUtils.error("", e);
            }
        }
    }

    /**
     * 分发给具体监听器执行
     * @param handler
     * @param event
     */
    public void fireEvent(Object handler,GameEvent event) {
        try {
            Method method = map.get(getKey(handler, event.getEventType()));
            method.invoke(handler, event);
        } catch (Exception e) {
            LoggerUtils.error("", e);
        }

    }

    private String getKey(Object handler, EventType eventType) {
        return handler.getClass().getName() + "-" + eventType.toString();
    }
}
