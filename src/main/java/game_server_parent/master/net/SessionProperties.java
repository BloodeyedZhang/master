package game_server_parent.master.net;

import org.apache.mina.core.session.AttributeKey;

/**
 * <p>Filename:SessionProperties.java</p>
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
public interface SessionProperties {
    /** 编码上下文 */
    AttributeKey CODEC_CONTEXT = new AttributeKey(SessionProperties.class, "CONTEXT_KEY");
    /** 线程池分发器的索引 */
    AttributeKey DISTRIBUTE_KEY = new AttributeKey(SessionProperties.class, "DISTRIBUTE_KEY");
    /** 玩家id */
    AttributeKey PLAYER_ID = new AttributeKey(SessionProperties.class, "PLAYER_ID");
}
