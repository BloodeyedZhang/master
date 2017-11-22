package game_server_parent.master.game.http.commands;

import game_server_parent.master.game.http.CommandHandler;
import game_server_parent.master.game.http.HttpCommandHandler;
import game_server_parent.master.game.http.HttpCommandParams;
import game_server_parent.master.game.http.HttpCommandResponse;
import game_server_parent.master.game.http.HttpCommands;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.events.EventNewPlayer;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.logs.LoggerSystem;
import game_server_parent.master.utils.IdGenerator;
import game_server_parent.master.utils.SchedulerManager;

/**
 * <p>Filename:CloseServerCommandHandler.java</p>
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
@CommandHandler(cmd=HttpCommands.CREATE_NEW_PLAYER)
public class CreatePlayerCommandHandler extends HttpCommandHandler {

    @Override
    public HttpCommandResponse action(HttpCommandParams httpParams) {
        // http://192.168.1.140:8080/?cmd=3&params={pwd=winturn,name=winturn}
        LoggerSystem.NET.getLogger().info("收到后台命令，准备创建新角色");

        String pwd = httpParams.getString("pwd");
        String name = httpParams.getString("winturn");
        if(pwd.equals("winturn")) {
            int nextId = PlayerManager.getInstance().getNextId();
            EventDispatcher.getInstance().fireEvent(new EventNewPlayer(EventType.PLAYER_CREATE, nextId));
            return HttpCommandResponse.valueOfSucc();
        }
        else {
            HttpCommandResponse resp = HttpCommandResponse.valueOfFailed();
            resp.setMessage(name+"没有权限:创建新角色");
            return resp;
        }
        /*
        SchedulerManager.INSTANCE.registerUniqueTimeoutTask("http_close_server", () -> {
            //发出关闭信号，交由ServerStartup的关闭钩子处理
            Runtime.getRuntime().exit(0);
        },  5*1000);
        */

        
    }

}
