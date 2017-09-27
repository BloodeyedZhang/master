package game_server_parent.master.game.http.commands;

import game_server_parent.master.game.http.CommandHandler;
import game_server_parent.master.game.http.HttpCommandHandler;
import game_server_parent.master.game.http.HttpCommandParams;
import game_server_parent.master.game.http.HttpCommandResponse;
import game_server_parent.master.game.http.HttpCommands;
import game_server_parent.master.game.kapai.events.EventKapaiNew;
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
@CommandHandler(cmd=HttpCommands.CREATE_NEW_CARD)
public class CreateCardCommandHandler extends HttpCommandHandler {

    @Override
    public HttpCommandResponse action(HttpCommandParams httpParams) {
        
        // http://192.168.1.140:8080/?cmd=4&params={pwd=winturn,name=gm1,card_playerId=10000001,card_bingzhong=1014,card_dengji=1,card_jiachengzhonglei,card_jiachengbi=0.1}
        
        LoggerSystem.HTTP_COMMAND.getLogger().info("收到后台命令，准备创建新卡牌");

        String pwd = null;
        String name = null;
        try {
            pwd = httpParams.getString("pwd");
            name = httpParams.getString("name");
            if (pwd.equals("winturn")) {

                long card_playerId = httpParams.getLong("card_playerId");
                int card_bingzhong = httpParams.getInt("card_bingzhong");
                int card_dengji = httpParams.getInt("card_dengji");
                Float card_jiachengbi = httpParams.getFloat("card_jiachengbi");
                int card_jiachengzhonglei = httpParams.getInt("card_jiachengzhonglei");
                EventDispatcher.getInstance()
                        .fireEvent(new EventKapaiNew(EventType.KAPAI_NEW, card_playerId, 0,
                                card_bingzhong, card_dengji, card_jiachengzhonglei,
                                card_jiachengbi));
                return HttpCommandResponse.valueOfSucc();
            } else {
                HttpCommandResponse resp = HttpCommandResponse.valueOfFailed();
                resp.setMessage(name + "没有权限:创建新卡牌");
                return resp;
            }
        } catch (Exception e) {
            HttpCommandResponse resp = HttpCommandResponse.valueOfFailed();
            resp.setMessage(name + "输入参数有错." + e.getLocalizedMessage());
            return resp;
        }
        /*
         * SchedulerManager.INSTANCE.registerUniqueTimeoutTask(
         * "http_close_server", () -> { //发出关闭信号，交由ServerStartup的关闭钩子处理
         * Runtime.getRuntime().exit(0); }, 5*1000);
         */

        
    }

}
