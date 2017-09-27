package game_server_parent.master.game.http;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>Filename:HttpCommandParams.java</p>
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
public class HttpCommandParams {
    /**  命令类型 {@link HttpCommands} */
    private int cmd;
    
    private Map<String, Object> params;
    
    public static HttpCommandParams valueOf(int cmd, Map<String, Object> params) {
        HttpCommandParams one = new HttpCommandParams();
        one.cmd    = cmd;
        one.params = params;
        return one;
    }
    
    public int getCmd() {
        return cmd;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    
    public String getString(String key) {
        return (String)params.get(key);
    }

    public int getInt(String key) {
        if (params.containsKey(key)) {
            Double d = getDouble(key);
            return d.intValue();
        }
        return 0;
    }
    
    public long getLong(String key) {
        if (params.containsKey(key)) {
            Double d = getDouble(key);
            return d.longValue();
        }
        return 0;
    }
    
    public Double getDouble(String key) {
        if (params.containsKey(key)) {
            Double d = (Double) params.get(key);
            return d;
        }
        return 0.0;
    }
    
    public float getFloat(String key) {
        if (params.containsKey(key)) {
            Double d = getDouble(key);
            return d.floatValue();
        }
        return 0.0F;
    }

    @Override
    public String toString() {
        return "HttpCommandParams [cmd=" + cmd + ", params=" + params
                        + "]";
    }
}
