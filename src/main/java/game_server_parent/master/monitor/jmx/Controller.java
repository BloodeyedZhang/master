package game_server_parent.master.monitor.jmx;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import game_server_parent.master.game.player.PlayerManager;

/**
 * <p>Filename:Controller.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月1日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class Controller implements ControllerMBean {

    @Override
    public int getOnlinePlayerSum() {
        return PlayerManager.getInstance().getOnlinePlayers().size();
    }

    @Override
    public String getMemoryInfo() {
        StringBuilder sb = new StringBuilder();  
        sb.append("free:")  
        .append(Runtime.getRuntime().freeMemory())  
        .append(",\n")  
        .append("total:")  
        .append(Runtime.getRuntime().totalMemory());  

        return sb.toString();  
    }

    @Override
    public String execJavascript(String jsCode) {
        String msg = "执行成功";
        try {
            ScriptEngineManager engineManager= new ScriptEngineManager();
            ScriptEngine scriptEngine = engineManager.getEngineByName("JavaScript");
            System.err.println("成功执行脚本:"+jsCode);  
            return scriptEngine.eval(jsCode).toString();
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }
    
    @Override
    public String getPlayerInfo(long player_id) {
        return PlayerManager.getInstance().get(player_id).toString();
    }

}
