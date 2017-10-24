package game_server_parent.master.redis;

/**
 * <p>Filename:IRedisSerializer.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月10日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public interface IRedisSerializer {
    
    public byte[] serialize(Object o);

    public <T> T deserialize(byte[] src, Class<T> cls);
}
