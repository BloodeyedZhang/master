package game_server_parent.master.game.kapai.events;

import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.PlayerEvent;

/**
 * <p>Filename:EventKapaiNew.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月14日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
public class EventKapaiNew extends PlayerEvent {

    private int dalei;
    private int bingzhong;
    private int dengji;
    private int jiachengzhonglei;
    private float jiachengbi;
    
    /**
     * @param evtType
     * @param playerId
     */
    public EventKapaiNew(EventType evtType, long playerId, int dalei, int bingzhong, int dengji, int jiachengzhonglei, float jiachengbi) {
        super(evtType, playerId);
        this.dalei = dalei;
        this.bingzhong = bingzhong;
        this.dengji = dengji;
        this.jiachengzhonglei = jiachengzhonglei;
        this.jiachengbi = jiachengbi;
    }
    
    public int getDalei() {
        return dalei;
    }

    public int getBingzhong() {
        return bingzhong;
    }

    public int getDengji() {
        return dengji;
    }

    public void setDengji(int dengji) {
        this.dengji = dengji;
    }

    public float getJiachengbi() {
        return jiachengbi;
    }

    public void setJiachengbi(float jiachengbi) {
        this.jiachengbi = jiachengbi;
    }

    public void setDalei(int dalei) {
        this.dalei = dalei;
    }

    public void setBingzhong(int bingzhong) {
        this.bingzhong = bingzhong;
    }

    public int getJiachengzhonglei() {
        return jiachengzhonglei;
    }

    public void setJiachengzhonglei(int jiachengzhonglei) {
        this.jiachengzhonglei = jiachengzhonglei;
    }

    @Override
    public String toString() {
        return "EventKapaiNew [playerId=" + getPlayerId() + ",eventType="+ getEventType()+", dalei=" + dalei + ", bingzhong=" + bingzhong 
                + ", dengji=" + dengji + ", jiachengzhonglei=" + jiachengzhonglei + ", jiachengbi=" + jiachengbi + "]";
    }

}
