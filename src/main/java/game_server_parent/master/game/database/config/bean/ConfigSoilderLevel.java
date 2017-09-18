package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;

@Entity(readOnly=true)
public class ConfigSoilderLevel {
	/**
	 * 唯一编号
	 */
	@Column
	private int id;
	/**
	 * 等级
	 */
	@Column
	private int dengji;
	/**
	 * 兵种
	 */
	@Column
	private String bingzhong;
	/**
	 * 等级生命
	 */
	@Column
	private int shengming_dengji;
	/**
	 * 品质生命
	 */
	@Column
	private double shengming_pinzhi;
	/**
	 * 等级攻击
	 */
	@Column
	private int gongjili_dengji;
	/**
	 * 品质攻击
	 */
	@Column
	private double gongjili_pinzhi;
	/**
	 * 动画速率
	 */
	@Column
	private double speed;
	/**
	 * 等级治疗
	 */
	@Column
	private int zhiliao_dengji;
	/**
	 * 品质治疗
	 */
	@Column
	private int zhiliao_pinzhi;
	/**
	 * 射击精准
	 */
	@Column
	private int jingzun;
	/**
	 * 伤害范围 
	 */
	@Column
	private int fanwei;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDengji() {
		return dengji;
	}
	public void setDengji(int dengji) {
		this.dengji = dengji;
	}
	public String getBingzhong() {
		return bingzhong;
	}
	public void setBingzhong(String bingzhong) {
		this.bingzhong = bingzhong;
	}
	public int getShengming_dengji() {
		return shengming_dengji;
	}
	public void setShengming_dengji(int shengming_dengji) {
		this.shengming_dengji = shengming_dengji;
	}
	public double getShengming_pinzhi() {
		return shengming_pinzhi;
	}
	public void setShengming_pinzhi(double shengming_pinzhi) {
		this.shengming_pinzhi = shengming_pinzhi;
	}
	public int getGongjili_dengji() {
		return gongjili_dengji;
	}
	public void setGongjili_dengji(int gongjili_dengji) {
		this.gongjili_dengji = gongjili_dengji;
	}
	public double getGongjili_pinzhi() {
		return gongjili_pinzhi;
	}
	public void setGongjili_pinzhi(double gongjili_pinzhi) {
		this.gongjili_pinzhi = gongjili_pinzhi;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getZhiliao_dengji() {
		return zhiliao_dengji;
	}
	public void setZhiliao_dengji(int zhiliao_dengji) {
		this.zhiliao_dengji = zhiliao_dengji;
	}
	public int getZhiliao_pinzhi() {
		return zhiliao_pinzhi;
	}
	public void setZhiliao_pinzhi(int zhiliao_pinzhi) {
		this.zhiliao_pinzhi = zhiliao_pinzhi;
	}
	public int getJingzun() {
		return jingzun;
	}
	public void setJingzun(int jingzun) {
		this.jingzun = jingzun;
	}
	public int getFanwei() {
		return fanwei;
	}
	public void setFanwei(int fanwei) {
		this.fanwei = fanwei;
	}
}
