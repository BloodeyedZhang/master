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
	private float shengming_pinzhi;
	/**
	 * 等级攻击
	 */
	@Column
	private int gongjili_dengji;
	/**
	 * 品质攻击
	 */
	@Column
	private float gongjili_pinzhi;
	/**
	 * 动画速率
	 */
	@Column
	private float speed;
	/**
	 * 等级治疗
	 */
	@Column
	private int zhiliao_dengji;
	/**
	 * 品质治疗
	 */
	@Column
	private float zhiliao_pinzhi;
	/**
	 * 射击精准
	 */
	@Column
	private float jingzun;
	/**
	 * 伤害范围 
	 */
	@Column
	private float fanwei;
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
	public float getShengming_pinzhi() {
		return shengming_pinzhi;
	}
	public void setShengming_pinzhi(float shengming_pinzhi) {
		this.shengming_pinzhi = shengming_pinzhi;
	}
	public int getGongjili_dengji() {
		return gongjili_dengji;
	}
	public void setGongjili_dengji(int gongjili_dengji) {
		this.gongjili_dengji = gongjili_dengji;
	}
	public float getGongjili_pinzhi() {
		return gongjili_pinzhi;
	}
	public void setGongjili_pinzhi(float gongjili_pinzhi) {
		this.gongjili_pinzhi = gongjili_pinzhi;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getZhiliao_dengji() {
		return zhiliao_dengji;
	}
	public void setZhiliao_dengji(int zhiliao_dengji) {
		this.zhiliao_dengji = zhiliao_dengji;
	}
	public float getZhiliao_pinzhi() {
		return zhiliao_pinzhi;
	}
	public void setZhiliao_pinzhi(float zhiliao_pinzhi) {
		this.zhiliao_pinzhi = zhiliao_pinzhi;
	}
	public float getJingzun() {
		return jingzun;
	}
	public void setJingzun(float jingzun) {
		this.jingzun = jingzun;
	}
	public float getFanwei() {
		return fanwei;
	}
	public void setFanwei(float fanwei) {
		this.fanwei = fanwei;
	}
	public String getKey() {
	    return this.bingzhong+""+this.dengji;
	}
}
