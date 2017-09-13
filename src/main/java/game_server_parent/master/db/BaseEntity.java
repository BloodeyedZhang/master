package game_server_parent.master.db;

import game_server_parent.master.orm.cache.AbstractCacheable;

import java.io.Serializable;

/**
 * <p>Filename:BaseEntity.java</p>
 * <p>Description: db实体基类 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月28日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class BaseEntity<Id extends Comparable<Id>> extends AbstractCacheable 
implements Serializable {

    private static final long serialVersionUID = 5416347850924361417L;

    public abstract Id getId() ;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId()==null)?0:getId().hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (getId() != other.getId())
            return false;
        return true;
    }
    
}
