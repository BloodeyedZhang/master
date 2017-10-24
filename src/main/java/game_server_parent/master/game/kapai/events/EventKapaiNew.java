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

    private int pinzhi;
    private int bingzhong;
    private int dengji;
    private int jiachengzhonglei;
    private float jiachengbi;
    private int xingji;
    
    /**
     * @param evtType
     * @param playerId
     * @param xingji 
     */
    public EventKapaiNew(EventType evtType, long playerId, int pinzhi, int bingzhong, int dengji, int jiachengzhonglei, float jiachengbi, int xingji) {
        super(evtType, playerId);
        this.pinzhi = pinzhi;
        this.bingzhong = bingzhong;
        this.dengji = dengji;
        this.jiachengzhonglei = jiachengzhonglei;
        this.jiachengbi = jiachengbi;
        this.xingji = xingji;
    }

    public int getPinzhi() {
        return pinzhi;
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
    public void setPinzhi(int pinzhi) {
        this.pinzhi = pinzhi;
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

    public int getXingji() {
        return xingji;
    }

    public void setXingji(int xingji) {
        this.xingji = xingji;
    }

    @Override
    public String toString() {
        return "EventKapaiNew [playerId=" + getPlayerId() + ",eventType="+ getEventType()+", pinzhi=" + pinzhi + ", bingzhong=" + bingzhong 
                + ", dengji=" + dengji + ", jiachengzhonglei=" + jiachengzhonglei + ", jiachengbi=" + jiachengbi 
                + ", xingji=" + xingji + "]";
    }

}
