package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

@Entity(readOnly=true)
public class ConfigXingji {
	/**
	 * 星级
	 */
    @Column
	private int xingji;
    /**
     * 经验上限
     */
    @Column
	private int jingyan_shangxian;
	public int getXingji() {
		return xingji;
	}
	public void setXingji(int xingji) {
		this.xingji = xingji;
	}
	public int getJingyan_shangxian() {
		return jingyan_shangxian;
	}
	public void setJingyan_shangxian(int jingyan_shangxian) {
		this.jingyan_shangxian = jingyan_shangxian;
	}
}
