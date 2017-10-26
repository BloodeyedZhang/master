package game_server_parent.master.game.crossrank;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.ServerConfig;

/**
 * <p>Filename:AbstractCrossRank.java</p>
 * <p>Description:
 * provides a skeletal implementation of the <tt>CrossRank</tt>
 *  </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月10日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public abstract class AbstractCrossRank implements CrossRank {

    @Protobuf
    private int serverId;
    /**  record creating timestamp */
    @Protobuf
    private long createTime;
    @Protobuf
    private long playerId;
    /**  one level rank score*/
    @Protobuf
    private int score;
    /**  second level rank score */
    @Protobuf
    private int aid;
    @Protobuf
    private String name;
    @Protobuf
    private long result;

    /** 32位时间戳 */
    protected  long TIME_MAX_VALUE = 0xFFFFFFFFL; 
    
    public AbstractCrossRank(long playerId, int score, int aid, String name) {
        this.playerId = playerId;
        this.score = score;
        this.aid  = aid;
        this.serverId = ServerConfig.getInstance().getServerId();
        this.createTime = System.currentTimeMillis();
        this.name = name;
    }
    
    public AbstractCrossRank(long playerId, int score, int aid, String name, int result) {
        this.playerId = playerId;
        this.score = score;
        this.aid  = aid;
        this.serverId = ServerConfig.getInstance().getServerId();
        this.createTime = System.currentTimeMillis();
        this.name = name;
        this.result = result;
    }

    public AbstractCrossRank(long playerId, int score) {
        this(playerId, score, 0, "");
    }
    
    public AbstractCrossRank() {
        
    }
    
    @Override
    public int getRankType() {
        return CrossRankKinds.FIGHTING;
    }

    @Override
    public int getServerId() {
        return serverId;
    }

    @Override
    public long getCreateTime() {
        return createTime;
    }

    @Override
    public long getPlayerId() {
        return this.playerId;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getAid() {
        return aid;
    }
    
    @Override
    public String getName() {
        return name;
    } 

    @Override
    public String buildRankKey() {
        return "CrossRank_" + getRankType();
    }

    @Override
    public String buildResultKey() {
        return getClass().getSimpleName();
    }

    /* 
     * 为了实现多级排行，我们需要将多维因素映射到一维因素。
     * 在52位精度，我们可以把低32位表示记录创建时间，高20位表示等级值。
     * 20位最大值为100多万，如果超过这个值，那么就要重新考虑位数的划分或者排行因素了。
     * 为了易于拓展，生成一维分数的方法必须允许子类修改。
     */
    @Override
    public double buildRankScore() {
        //default rank score 
        // score      |     createtime
        //  20bits            32bits  
        long timePart = (TIME_MAX_VALUE - getCreateTime()/1000) & TIME_MAX_VALUE;
        //long timePart = TIME_MAX_VALUE- getCreateTime()/1000;
        long result  = (long)score << 32 | timePart;
        /*
        System.err.println(Long.toBinaryString((long)score << 32));
        System.err.println(Long.toBinaryString(timePart));
        System.err.println(Long.toBinaryString(result));
        System.err.println(( (long)score << 32)+"|"+timePart+"|"+result);
        */
        return  result;
    }
    
    public void setResult(long result) {
        this.result = result;
    }

    @Override
    public long getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "AbstractCrossRank [serverId=" + serverId
                        + ", createTime=" + createTime
                        + ", playerId=" + playerId
                        + ", score=" + score + ", aid="
                        + aid + ", name=" + name + ", result=" + result + "]";
    }

}
