package game_server_parent.master.game.http.commands;

import game_server_parent.master.game.http.CommandHandler;
import game_server_parent.master.game.http.HttpCommandHandler;
import game_server_parent.master.game.http.HttpCommandParams;
import game_server_parent.master.game.http.HttpCommandResponse;
import game_server_parent.master.game.http.HttpCommands;

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
        // http://192.168.1.140:8080/?cmd=5&params={pwd=winturn,name=gm1,playerId=10000001,money_type=1,money_val=100}
        
        return null;
    }

}
