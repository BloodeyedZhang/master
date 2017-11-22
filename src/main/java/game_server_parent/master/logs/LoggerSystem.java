package game_server_parent.master.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Filename:LoggerSystem.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public enum LoggerSystem {
    /** 异常日志 */
    EXCEPTION,
//  /** 网关日志 */
    NET,
    /** job定时任务 */
    CRON_JOB,
//  NET,
    /** 数据库日志 */
    DB,
    /** AI日志 */
    AI
    ;
    
    public Logger getLogger() {
        return LoggerFactory.getLogger(this.name());
    }
}
