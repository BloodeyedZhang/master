package game_server_parent.master.net.codec;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * <p>Filename:CodecContext.java</p>
 * <p>Description: 解码上下文  </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年8月25日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class CodecContext {
    public static final int READ_CAPACITY = 1024;
    
    public static final int WRITE_CAPACITY = 256;
    /** 上一次解码未处理完的数据包（断包） */
    private IoBuffer buffer;
    
    public CodecContext() {
        buffer = IoBuffer.allocate(READ_CAPACITY).setAutoExpand(true);
    }
    
    public IoBuffer append(IoBuffer in) {
        this.buffer.put(in);
        return this.buffer;
    }
    
    public IoBuffer getBuffer() {
        return this.buffer;
    }
}
