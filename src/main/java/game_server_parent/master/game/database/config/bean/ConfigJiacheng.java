package game_server_parent.master.game.database.config.bean;

import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
@Entity(readOnly=true)
public class ConfigJiacheng {

	/**
	 * 加成种类
	 */
	@Column
	private int jiacheng_zhonglei;
	/**
	 * 种类描述
	 */
	@Column
	private String des;
    public int getJiacheng_zhonglei() {
        return jiacheng_zhonglei;
    }
    public void setJiacheng_zhonglei(int jiacheng_zhonglei) {
        this.jiacheng_zhonglei = jiacheng_zhonglei;
    }
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
}
