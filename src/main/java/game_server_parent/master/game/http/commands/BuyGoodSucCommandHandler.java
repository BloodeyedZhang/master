package game_server_parent.master.game.http.commands;

import game_server_parent.master.game.http.CommandHandler;
import game_server_parent.master.game.http.HttpCommandHandler;
import game_server_parent.master.game.http.HttpCommandParams;
import game_server_parent.master.game.http.HttpCommandResponse;
import game_server_parent.master.game.http.HttpCommands;
import game_server_parent.master.game.kapai.KapaiDataPool;
import game_server_parent.master.game.kapai.events.EventKapaiNew;
import game_server_parent.master.game.mall.events.EventBuyGoodSuc;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.logs.LoggerSystem;

/**
 * <p>Filename:BuyGoodSucCommandHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年11月17日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@CommandHandler(cmd=HttpCommands.BUY_GOOD_SUC)
public class BuyGoodSucCommandHandler extends HttpCommandHandler {

    @Override
    public HttpCommandResponse action(HttpCommandParams httpParams) {
        // http://192.168.1.140:8080/?cmd=6&params={pwd=winturn,name=winturn,player_id=1000001,good_id=10004}
        LoggerSystem.NET.getLogger().info("收到后台命令，发放商品");
        String pwd = null;
        String name = null;
        try {
            pwd = httpParams.getString("pwd");
            name = httpParams.getString("name");
            if (pwd.equals("winturn")) {

                long player_id = httpParams.getLong("player_id");
                int good_id = httpParams.getInt("good_id");
                EventDispatcher.getInstance()
                        .fireEvent(new EventBuyGoodSuc(EventType.MALL_BUY_SUC, player_id, good_id));
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
    }

}
