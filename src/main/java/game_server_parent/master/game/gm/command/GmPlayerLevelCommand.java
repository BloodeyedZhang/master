package game_server_parent.master.game.gm.command;

import java.util.List;

import game_server_parent.master.db.DbService;
import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.bean.ConfigPlayerLevel;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.gm.message.ResGmResultMessage;

/**
 * <p>Filename:GmPlayerLevelCommand.java</p>
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
public class GmPlayerLevelCommand extends AbstractGmCommand {

    @Override
    public String getPattern() {
        return "^playerLv\\s+(\\d+)";
    }

    @Override
    public String help() {
        return "修改玩家等级(playerLv [level])";
    }

    @Override
    public ResGmResultMessage execute(Player player, List<String> params) {
        int newLevel = Integer.parseInt(params.get(0));
        ConfigPlayerLevel configLevel = ConfigDatasPool.getInstance()
                                .configPlayerLevelContainer.getConfigBy(newLevel);
        if (configLevel == null) {
            return ResGmResultMessage.buildFailResult("目标等级参数无效");
        }
        player.setLevel(newLevel);
        player.setUpdate();
        
        DbService.getInstance().add2Queue(player);
        return ResGmResultMessage.buildSuccResult("修改玩家等级成功");
    }
}
