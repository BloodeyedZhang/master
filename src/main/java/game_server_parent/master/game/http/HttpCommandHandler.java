package game_server_parent.master.game.http;

/**
 * <p>Filename:HttpCommandHandler.java</p>
 * <p>Description: 抽象后台命令处理者 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月12日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class HttpCommandHandler {
    /**
     * 处理后台命令
     * @param httpParams
     * @return
     */
    public abstract HttpCommandResponse action(HttpCommandParams httpParams);
}
