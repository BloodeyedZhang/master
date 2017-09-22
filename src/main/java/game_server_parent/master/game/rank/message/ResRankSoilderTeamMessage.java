package game_server_parent.master.game.rank.message;

import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.rank.RankDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResRankSoilderTeam.java</p>
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
@MessageMeta(module=Modules.RANK, cmd=RankDataPool.RES_RANK_SOILDET_TEAM)
public class ResRankSoilderTeamMessage extends Message {

    @Protobuf(fieldType = FieldType.OBJECT,order=14)
    private List<RankSoilderTeam> rsts;
    
    @Protobuf(fieldType = FieldType.OBJECT,order=15)
    private List<Kapai> kapais;
    
    @Protobuf(fieldType = FieldType.OBJECT,order=16)
    private List<Kapai> kapais_enemy;


    public List<RankSoilderTeam> getRsts() {
        return rsts;
    }

    public void setRsts(List<RankSoilderTeam> rsts) {
        this.rsts = rsts;
    }
    
    public List<Kapai> getKapais() {
        return kapais;
    }

    public void setKapais(List<Kapai> kapais) {
        this.kapais = kapais;
    }

    public List<Kapai> getKapais_enemy() {
        return kapais_enemy;
    }

    public void setKapais_enemy(List<Kapai> kapais_enemy) {
        this.kapais_enemy = kapais_enemy;
    }

    @Override
    public String toString() {
        return "ResRankSoilderTeamMessage [rsts count=" + rsts.size() +  "]";
    }
}
