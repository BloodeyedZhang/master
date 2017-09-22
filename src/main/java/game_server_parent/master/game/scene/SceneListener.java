package game_server_parent.master.game.scene;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.kapai.message.ResSelectPlayerKapaiMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.message.ResPlayerMessage;
import game_server_parent.master.game.rank.RankListener;
import game_server_parent.master.game.rank.RankSoilderTeamManager;
import game_server_parent.master.game.rank.message.ResRankSoilderTeamMessage;
import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.game.scene.message.ResPlayerEnterSceneMessage;
import game_server_parent.master.game.scene.message.ResPlayerPreEnterSceneMessage;
import game_server_parent.master.game.team.TeamManager;
import game_server_parent.master.game.team.message.ResPlayerTeamMessage;
import game_server_parent.master.listener.EventType;
import game_server_parent.master.listener.annotation.EventHandler;
import game_server_parent.master.listener.annotation.Listener;
import game_server_parent.master.net.MessagePusher;
import game_server_parent.master.net.SessionManager;
import game_server_parent.master.net.combine.CombineMessage;

/**
 * <p>Filename:SceneListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: 2015 www.zjwinturn.com Co.Ltd. All rights reserved.</p>
 * <p>Company: WinTurn Network Technology</p>
 * <p>Summary: </p>
 * <p>Created: 2017年9月20日</p>
 *
 * @author  zjj
 * @version 
 * 
 */
@Listener
public class SceneListener {
    
    private Logger logger = LoggerFactory.getLogger(SceneListener.class);

    @EventHandler(value=EventType.ENTER_DATING)
    public void onEnterDating(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        // 下发组合包
        CombineMessage combineMessage = new CombineMessage();
        ResPlayerEnterSceneMessage resPlayerEnterSceneMessage = new ResPlayerEnterSceneMessage();
        resPlayerEnterSceneMessage.setMapId(MapEnum.Dating.value());
        // 推送场景ID消息
        combineMessage.addMessage(resPlayerEnterSceneMessage);  
       // combineMessage.addMessage(ResGmResultMessage.buildSuccResult("执行gm成功"));
        // 推送卡牌消息
        List<Kapai> kapais = KapaiManager.getInstance().getPlayerKapaiList(player_id);
        combineMessage.addMessage(new ResSelectPlayerKapaiMessage(kapais));
        // 推送队伍消息
        List<SoilderTeam> playerTeamList = TeamManager.getInstance().getPlayerTeamList(player_id);
        combineMessage.addMessage(new ResPlayerTeamMessage(playerTeamList));
        // 推送角色消息
        Player player = PlayerManager.getInstance().get(player_id);
        ResPlayerMessage resPlayerMessage = new ResPlayerMessage();
        resPlayerMessage.setPlayer(player);
        combineMessage.addMessage(resPlayerMessage);
        
        IoSession session = SessionManager.INSTANCE.getSessionBy(player_id);
        MessagePusher.pushMessage(session, combineMessage);
    }
    
    @EventHandler(value=EventType.PRE_ENTER_ZHANDOU)
    public void onPreEnterZhandou(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        int mapId = event.getMapId();
        RankSoilderTeam rankSoilderTeam = RankSoilderTeamManager.getInstance().get(player_id);
        ResPlayerPreEnterSceneMessage resp = null;
        if(rankSoilderTeam.getTeam_id() == 0) {
            resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_FAIL);
        } else {
            resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_SUCC);
        }
        MessagePusher.pushMessage(player_id, resp);
    }
    
    @EventHandler(value=EventType.ENTER_ZHANDOU)
    public void onEnterZhandou(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        // 准备组合包 发送给客户端
        CombineMessage combineMessage = new CombineMessage();
        // 添加场景ID消息
        ResPlayerEnterSceneMessage resPlayerEnterSceneMessage = new ResPlayerEnterSceneMessage();
        resPlayerEnterSceneMessage.setMapId(MapEnum.Dating.value());
        combineMessage.addMessage(resPlayerEnterSceneMessage);  
        // 添加角色消息
      //  Player player = PlayerManager.getInstance().get(player_id);
       // ResPlayerMessage resPlayerMessage = new ResPlayerMessage();
       // resPlayerMessage.setPlayer(player);
       // combineMessage.addMessage(resPlayerMessage);
        // 添加己方排行队伍消息
        ArrayList<RankSoilderTeam> rsts = new ArrayList<RankSoilderTeam>();
        RankSoilderTeam rankSoilderTeam = RankSoilderTeamManager.getInstance().get(player_id);
        List<Kapai> teamKapais = TeamManager.getInstance().getTeamKapai(rankSoilderTeam);
        ResRankSoilderTeamMessage resRankSoilderTeamMessage = new ResRankSoilderTeamMessage();
        rsts.add(rankSoilderTeam);
        resRankSoilderTeamMessage.setKapais(teamKapais);
        // 添加敌方排行队伍列表消息
        RankSoilderTeam rst_enemy = RankSoilderTeamManager.getInstance().queryOneEnemy(player_id);
       // RankSoilderTeam rst_enemy = rankSoilderTeam;
        rsts.add(rst_enemy);
        List<Kapai> teamKapais_enemy = TeamManager.getInstance().getTeamKapai(rst_enemy);
        resRankSoilderTeamMessage.setKapais_enemy(teamKapais_enemy);
        resRankSoilderTeamMessage.setRsts(rsts);
        
        combineMessage.addMessage(resRankSoilderTeamMessage);
        
        MessagePusher.pushMessage(player_id, combineMessage);
    }
    
    @EventHandler(value=EventType.ENTER_JINKU)
    public void onEnterJinku(EventEnterScene event) {
        
    }
}
