package game_server_parent.master.game.mall.message;

import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import game_server_parent.master.game.Modules;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.mall.MallDataPool;
import game_server_parent.master.net.Message;
import game_server_parent.master.net.annotation.MessageMeta;

/**
 * <p>Filename:ResBuyMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年10月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@MessageMeta(module=Modules.MALL, cmd=MallDataPool.RES_Mall_BUY)
public class ResBuyMessage extends Message {

    @Protobuf(order=1)
    private int diamond;
    @Protobuf(order=2)
    private int keyNum;
    @Protobuf(fieldType=FieldType.OBJECT, order=3)
    private List<Kapai> kapais = new ArrayList<Kapai>();
    @Protobuf(order=4)
    private int type;
    @Protobuf(order=5)
    private int code;
    @Protobuf(order=6)
    private String tips;
    @Protobuf(order=7)
    private int deduct_type;
    @Protobuf(order=8)
    private int deduct_num;

    public ResBuyMessage() {
    }
    
    @Override
    public String toString() {
        return "ResBuyMessage [ diamond=" + diamond + ", keyNum=" + keyNum + ", kapais.count=" + kapais.size() 
                + ", type=" + type +", code="+code + ", tips=" + tips + ", deduct_type=" + deduct_type + ", deduct_num=" + deduct_num
                +"]";
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(int keyNum) {
        this.keyNum = keyNum;
    }

    public List<Kapai> getKapais() {
        return kapais;
    }

    public void setKapais(List<Kapai> kapais) {
        this.kapais = kapais;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public int getDeduct_type() {
        return deduct_type;
    }

    public void setDeduct_type(int deduct_type) {
        this.deduct_type = deduct_type;
    }

    public int getDeduct_num() {
        return deduct_num;
    }

    public void setDeduct_num(int deduct_num) {
        this.deduct_num = deduct_num;
    }
}
