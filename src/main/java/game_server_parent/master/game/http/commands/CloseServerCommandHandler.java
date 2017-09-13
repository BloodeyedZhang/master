package game_server_parent.master.game.http.commands;

import game_server_parent.master.game.http.CommandHandler;
import game_server_parent.master.game.http.HttpCommandHandler;
import game_server_parent.master.game.http.HttpCommandParams;
import game_server_parent.master.game.http.HttpCommandResponse;
import game_server_parent.master.game.http.HttpCommands;
import game_server_parent.master.logs.LoggerSystem;
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
@CommandHandler(cmd=HttpCommands.CLOSE_SERVER)
public class CloseServerCommandHandler extends HttpCommandHandler {

    @Override
    public HttpCommandResponse action(HttpCommandParams httpParams) {
        LoggerSystem.HTTP_COMMAND.getLogger().info("收到后台命令，准备停服");
        
        SchedulerManager.INSTANCE.registerUniqueTimeoutTask("http_close_server", () -> {
            //发出关闭信号，交由ServerStartup的关闭钩子处理
            Runtime.getRuntime().exit(0);
        },  5*1000);

        return HttpCommandResponse.valueOfSucc();
    }

}
