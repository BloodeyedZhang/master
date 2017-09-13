package game_server_parent.master.game.gm.command;

import java.lang.reflect.Field;
import java.util.List;

import game_server_parent.master.game.database.config.ConfigDatasPool;
import game_server_parent.master.game.database.config.Reloadable;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.gm.message.ResGmResultMessage;

/**
 * <p>Filename:GmReloadConfigCommand.java</p>
 * <p>Description: 修改配置表的gm </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class GmReloadConfigCommand extends AbstractGmCommand {

    @Override
    public String getPattern() {
        return "^reloadConfig\\s+([a-zA-Z_]+)";
    }

    @Override
    public String help() {
        return "修改配置表(^reloadConfig [tableName])";
    }

    @Override
    public ResGmResultMessage execute(Player player, List<String> params) {
        String tableName = params.get(0);
        String containerName = tableName + "Container";
        try {
            Field field = ConfigDatasPool.class.getDeclaredField(containerName);
            Class<?> type = field.getType();
            Reloadable newContainer = (Reloadable) type.newInstance();
            newContainer.reload();
            field.set(ConfigDatasPool.getInstance(), newContainer);
            
            return ResGmResultMessage.buildSuccResult("重载["+tableName+"]表成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResGmResultMessage.buildFailResult("找不到目标配置表");
    }
    
}
