package game_server_parent.master.game.http.commands;

import game_server_parent.master.game.http.CommandHandler;
import game_server_parent.master.game.http.HttpCommandHandler;
import game_server_parent.master.game.http.HttpCommandParams;
import game_server_parent.master.game.http.HttpCommandResponse;
import game_server_parent.master.game.http.HttpCommands;
import game_server_parent.master.game.mall.events.EventBuyGoodSuc;
import game_server_parent.master.game.player.events.EventGmPlayerAttrChange;
import game_server_parent.master.listener.EventDispatcher;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.logs.LoggerSystem;

/**
 * <p>Filename:AddMoneyCommandHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月12日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@CommandHandler(cmd=HttpCommands.ADD_MONEY)
public class AddMoneyCommandHandler extends HttpCommandHandler {

    @Override
    public HttpCommandResponse action(HttpCommandParams httpParams) {
        // TODO Auto-generated method stub
        // http://192.168.1.140:8080/?cmd=5&params={pwd=winturn,name=gm1,playerId=10000001,money_coin=10,money_diamond=10,keynum=1}
        LoggerSystem.NET.getLogger().info("收到后台命令，改变玩家属性");
        String pwd = null;
        String name = null;
        try {
            pwd = httpParams.getString("pwd");
            name = httpParams.getString("name");
            if (pwd.equals("winturn")) {

                long player_id = httpParams.getLong("playerId");
                int money_coin = httpParams.getInt("money_coin");
                int money_diamond = httpParams.getInt("money_diamond");
                int keynum = httpParams.getInt("keynum");
                
                EventGmPlayerAttrChange event = new EventGmPlayerAttrChange(EventType.GM_ADD_MONEY, player_id);
                event.setMoney_coin(money_coin);
                event.setMoney_diamond(money_diamond);
                event.setKeynum(keynum);
                EventDispatcher.getInstance()
                        .fireEvent(event);
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
