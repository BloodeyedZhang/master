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
	 * 加成比
	 */
	@Column
	private float jiacheng_base;
	
	public int getPinzhi_zhonglei() {
		return pinzhi_zhonglei;
	}
	public void setPinzhi_zhonglei(int pinzhi_zhonglei) {
		this.pinzhi_zhonglei = pinzhi_zhonglei;
	}
    public float getJiacheng_base() {
        return jiacheng_base;
    }
    public void setJiacheng_base(float jiacheng_base) {
        this.jiacheng_base = jiacheng_base;
    }
}
