package game_server_parent.master.cache;

/**
 * <p>Filename:Persistable.java</p>
 * <p>Description: 可持久化的 </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月30日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public interface Persistable<K, V> {

    /**
     * 能从数据库获取bean
     * @param k 查询主键
     * @return  持久化对象
     * @throws Exception
     */
    V load(K k) throws Exception;
    
//    /**
//     * 将对象序列号到数据库
//     * @param k
//     * @param v
//     * @throws PersistenceException
//     */
//    void save(K k, V v) throws Exception;
    
}
