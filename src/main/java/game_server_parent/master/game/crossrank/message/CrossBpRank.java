package game_server_parent.master.game.crossrank.message;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.crossrank.CrossRank;

/**
 * <p>Filename:CrossBpRank.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月19日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class CrossBpRank {

    @Protobuf(order=1)
    private int serverId;
    /**  record creating timestamp */
    @Protobuf(order=2)
    private long createTime;
    @Protobuf(order=3)
    private long playerId;
    @Protobuf(order=4)
    private long rank;
    /**  one level rank score*/
    @Protobuf(order=5)
    private int score;
    /**  second level rank score */
    @Protobuf(order=6)
    private int aid;
    @Protobuf(order=7)
    private String name;
    
    public CrossBpRank() {}
    /**
     * @param i
     * @param crossRank
     */
    public CrossBpRank(long i, CrossRank crossRank) {
        super();
        this.rank = i;
        this.serverId = crossRank.getServerId();
        this.createTime = crossRank.getCreateTime();
        this.playerId = crossRank.getPlayerId();
        this.score = crossRank.getScore();
        this.aid = crossRank.getAid();
        this.name = crossRank.getName();
    }
    public int getServerId() {
        return serverId;
    }
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public long getPlayerId() {
        return playerId;
    }
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
    public long getRank() {
        return rank;
    }
    public void setRank(long rank) {
        this.rank = rank;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getAid() {
        return aid;
    }
    public void setAid(int aid) {
        this.aid = aid;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "CrossBpRank[playerId="+playerId+", rank="+rank+"]";
    }
}
