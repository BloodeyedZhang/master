package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
@Entity(readOnly=true)
public class ConfigPinzhi {

	/**
	 * 品质种类
	 */
	@Column
	private int pinzhi_zhonglei;
	/**
	 * 最低加成比
	 */
	@Column
	private float jiacheng_zuidi;
	/**
	 * 最高加成比
	 */
	@Column
	private float jiacheng_zuigao;
	
	public int getPinzhi_zhonglei() {
		return pinzhi_zhonglei;
	}
	public void setPinzhi_zhonglei(int pinzhi_zhonglei) {
		this.pinzhi_zhonglei = pinzhi_zhonglei;
	}
	public float getJiacheng_zuidi() {
		return jiacheng_zuidi;
	}
	public void setJiacheng_zuidi(float jiacheng_zuidi) {
		this.jiacheng_zuidi = jiacheng_zuidi;
	}
	public float getJiacheng_zuigao() {
		return jiacheng_zuigao;
	}
	public void setJiacheng_zuigao(float jiacheng_zuigao) {
		this.jiacheng_zuigao = jiacheng_zuigao;
	}
}
