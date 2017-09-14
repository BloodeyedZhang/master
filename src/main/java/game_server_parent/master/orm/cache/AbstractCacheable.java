package game_server_parent.master.orm.cache;

import game_server_parent.master.orm.utils.DbUtils;
import game_server_parent.master.orm.utils.SqlUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

/**
 * <p>Filename:AbstractCacheable.java</p>
 * <p>Description: Cacheable骨架实现 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月28日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class AbstractCacheable extends Cacheable {
    private static Logger logger = LoggerFactory.getLogger(AbstractCacheable.class); 

    @Override
    public DbStatus getStatus() {
        return this.status;
    }

    @Override
    public final boolean isInsert() {
        return this.status == DbStatus.INSERT;
    }

    @Override
    public final boolean isUpdate() {
        return this.status == DbStatus.UPDATE;
    }

    @Override
    public final boolean isDelete() {
        return this.status == DbStatus.DELETE;
    }

    public void setInsert() {
        this.status = DbStatus.INSERT;
    }

    public final void setUpdate(){
        //只有该状态才可以变更为update
        if (this.status == DbStatus.NORMAL) {
            this.status = DbStatus.UPDATE;
        }
    }

    public final void setDelete(){
        if (this.status == DbStatus.INSERT) {
            this.status = DbStatus.NORMAL;
        } else{
            this.status = DbStatus.DELETE;
        }
    }
    
    public final void save() {
        String saveSql = SqlUtils.getSaveSql(this);
        if (StringUtils.isBlank(saveSql)) {
            return;
        }
        if (DbUtils.executeSql(saveSql)) {
            this.status = DbStatus.NORMAL;
        }
        if (logger.isDebugEnabled()) {
            System.err.println(saveSql);
            
        }
        if(logger.isInfoEnabled()) {
            logger.info("[save]"+saveSql);
        }
    }
}
