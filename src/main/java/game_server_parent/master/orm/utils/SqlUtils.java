package game_server_parent.master.orm.utils;

import game_server_parent.master.orm.OrmBridge;
import game_server_parent.master.orm.OrmProcessor;
import game_server_parent.master.orm.SqlFactory;
import game_server_parent.master.orm.cache.AbstractCacheable;

/**
 * <p>Filename:SqlUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月28日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class SqlUtils {
    public static String getInsertSql(AbstractCacheable entity) {
        OrmBridge bridge = OrmProcessor.INSTANCE.getOrmBridge(entity.getClass());
        return SqlFactory.createInsertSql(entity, bridge);
    }

    public static String getUpdateSql(AbstractCacheable entity) {
        OrmBridge bridge = OrmProcessor.INSTANCE.getOrmBridge(entity.getClass());
        return SqlFactory.createUpdateSql(entity, bridge);
    }

    public static String getDeleteSql(AbstractCacheable entity) {
        OrmBridge bridge = OrmProcessor.INSTANCE.getOrmBridge(entity.getClass());
        return SqlFactory.createDeleteSql(entity, bridge);
    }
    
    public static String getSaveSql(AbstractCacheable entity) {
        if (entity.isInsert()) {
            return getInsertSql(entity);
        }else if (entity.isUpdate()) {
            return getUpdateSql(entity);
        }else if (entity.isDelete()) {
            return getDeleteSql(entity);
        }
        return "";
    }
}
