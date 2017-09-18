package game_server_parent.master.game.cronjob;

import java.util.Collection;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.player.DailyResetTask;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.logs.LoggerSystem;
import game_server_parent.master.net.context.TaskHandlerContext;

/**
 * <p>Filename:DailyResetJob.java</p>
 * <p>Description: 每日５点定时job </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月18日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@DisallowConcurrentExecution
public class DailyResetJob implements Job {

    private Logger logger = LoggerSystem.CRON_JOB.getLogger();
    
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        logger.info("每日５点定时任务开始");

        Collection<Player> onlines = PlayerManager.getInstance().getOnlinePlayers().values();

        for (Player player:onlines) {
            int distributeKey = player.distributeKey();
          //将事件封装成timer任务，丢回业务线程处理 
            TaskHandlerContext.INSTANCE.acceptTask(new DailyResetTask(distributeKey, player));
        }
    }

}
