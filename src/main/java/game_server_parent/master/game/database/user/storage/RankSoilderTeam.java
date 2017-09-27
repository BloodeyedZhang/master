package game_server_parent.master.game.database.user.storage;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.db.BaseEntity;
import game_server_parent.master.orm.annotation.Column;
import game_server_parent.master.orm.annotation.Entity;
import game_server_parent.master.orm.annotation.Id;
import game_server_parent.master.utils.IdGenerator;

/**
 * <p>Filename:RankSoilderTeam.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月21日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Entity
public class RankSoilderTeam extends Team implements Cloneable {
    
    @Id
    @Column
    @Protobuf(order=3)
    private long player_id;
    
    @Column
    @Protobuf(order=1)
    private int team_id;
    
    @Column
    private long id;
    
    @Column
    @Protobuf(order=2)
    private String soilderIds; // 格式要求： "0,0,0,0,0" 


    @Column
    @Protobuf(order=4)
    private int shengmingzhi;
    
    @Column
    @Protobuf(order=5)
    private int gongjizhi;
    
    @Protobuf(order=6)
    private String player_name;
    
    public RankSoilderTeam() {
        this.id = IdGenerator.getNextId();
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(long player_id) {
        this.player_id = player_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getSoilderIds() {
        return soilderIds;
    }

    public void setSoilderIds(String soilderIds) {
        this.soilderIds = soilderIds;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getShengmingzhi() {
        return shengmingzhi;
    }

    public void setShengmingzhi(int shengmingzhi) {
        this.shengmingzhi = shengmingzhi;
    }

    public int getGongjizhi() {
        return gongjizhi;
    }

    public void setGongjizhi(int gongjizhi) {
        this.gongjizhi = gongjizhi;
    }
    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    @Override  
    public Object clone() {  
        RankSoilderTeam stu = null;  
        try{  
            stu = (RankSoilderTeam)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return stu;  
    }  
    
    @Override
    public String toString() {
        return "RankSoilderTeam [id=" + id +", team_id=" + team_id+ ", player_id=" + player_id + ", soilderIds=" + soilderIds 
                + ", shengmingzhi=" + shengmingzhi + ", gongjilizhi=" + gongjizhi + ", player_name=" + player_name + "]";
    }
}
