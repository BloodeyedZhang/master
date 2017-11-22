package game_server_parent.master.game.scene;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game_server_parent.master.game.achievement.AchievementManager;
import game_server_parent.master.game.achievement.message.ResAchieveListMessage;
import game_server_parent.master.game.crossrank.CrossRankKinds;
import game_server_parent.master.game.crossrank.CrossRankService;
import game_server_parent.master.game.crossrank.impl.CrossBonusPointsRank;
import game_server_parent.master.game.database.user.player.Player;
import game_server_parent.master.game.database.user.storage.Fuben;
import game_server_parent.master.game.database.user.storage.Kapai;
import game_server_parent.master.game.database.user.storage.KapaiAchievement;
import game_server_parent.master.game.database.user.storage.RankSoilderTeam;
import game_server_parent.master.game.database.user.storage.SoilderTeam;
import game_server_parent.master.game.database.user.storage.Treasury;
import game_server_parent.master.game.fuben.FubenManager;
import game_server_parent.master.game.fuben.message.ResFubenListMessage;
import game_server_parent.master.game.fuben.message.ResFubenSoilderTeamMessage;
import game_server_parent.master.game.kapai.KapaiManager;
import game_server_parent.master.game.kapai.message.ResSelectPlayerKapaiMessage;
import game_server_parent.master.game.player.PlayerManager;
import game_server_parent.master.game.player.message.ResPlayerMessage;
import game_server_parent.master.game.rank.RankSoilderTeamManager;
import game_server_parent.master.game.rank.message.ResRankSoilderTeamMessage;
import game_server_parent.master.game.scene.events.EventEnterScene;
import game_server_parent.master.game.scene.message.ResPlayerEnterSceneMessage;
import game_server_parent.master.game.scene.message.ResPlayerPreEnterSceneMessage;
import game_server_parent.master.game.team.TeamManager;
import game_server_parent.master.game.team.message.ResPlayerTeamMessage;
import game_server_parent.master.game.treasury.TreasuryManager;
import game_server_parent.master.game.treasury.message.ResTreasuryTeamMessage;
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
    
    public final static int twoH = 7200;

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
        // 角色排行信息
        CrossBonusPointsRank cbpr = new CrossBonusPointsRank(player_id);
        Long revrank = CrossRankService.getInstance().queryRevrank(CrossRankKinds.BONUS_POINTS, cbpr);
        Player player = PlayerManager.getInstance().get(player_id);
        if(revrank==null) revrank=-2L;
        player.setRank((int)(revrank+1));

        updateNow(player);
        ResPlayerMessage resPlayerMessage = new ResPlayerMessage();
        resPlayerMessage.setPlayer(player);
        combineMessage.addMessage(resPlayerMessage);
        
        // 角色成就信息
        List<KapaiAchievement> achieveList = AchievementManager.getInstance().getList(player_id);
        ResAchieveListMessage resAchieveListMessage = new ResAchieveListMessage();
        resAchieveListMessage.setAchieveList(achieveList);
        combineMessage.addMessage(resAchieveListMessage);

        
        IoSession session = SessionManager.INSTANCE.getSessionBy(player_id);
        MessagePusher.pushMessage(session, combineMessage);
        
        AchievementManager.getInstance().upList(player_id);
    }
    
    public static void updateNow(Player player) {
        int keyNum = player.getKeyNum();
        int maxKeyNum = player.getMaxKeyNum();
        if(keyNum==maxKeyNum) {
            player.setNow(0);
            long now = System.currentTimeMillis();
            player.setLastDailyReset(now);
        } else {
         // 服务器当前时间
            long now = System.currentTimeMillis();
            long lastDailyReset = player.getLastDailyReset();
            int h = (int)(now - lastDailyReset) / twoH;
            //int time = (int)(now/1000 - lastDailyReset/1000) % twoH;
            int mix = (int)(now/1000 - lastDailyReset/1000) % twoH;
            int time = twoH-mix;
            player.setNow(time);
            if(h>0) {
                keyNum+=h;
                if(keyNum>maxKeyNum) {
                    keyNum = maxKeyNum;
                }
                player.setLastDailyReset(now);
            }
        }
    }
    
    @EventHandler(value=EventType.PRE_ENTER_DATING)
    public void onPreEnterDating(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        //long player_id = event.getPlayerId();
        //int mapId = event.getMapId();
        //ResPlayerPreEnterSceneMessage resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_SUCC);
        //MessagePusher.pushMessage(player_id, resp);
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
        Player player = PlayerManager.getInstance().get(player_id);
        rankSoilderTeam.setPlayer_name(player.getName());
        rsts.add(rankSoilderTeam);
        resRankSoilderTeamMessage.setKapais(teamKapais);
        // 添加敌方排行队伍列表消息
        RankSoilderTeam rst_enemy = RankSoilderTeamManager.getInstance().queryOneEnemy(player_id);

        if(rst_enemy.getTeam_id()==0 || player_id == rst_enemy.getPlayer_id()) {
            // 如果没有取到 就取自身 暂时
            rst_enemy = (RankSoilderTeam) rankSoilderTeam.clone();
            rst_enemy.setPlayer_name("心魔");
            rst_enemy.setPlayer_id(0);
            player.setFight_enemy(player.getFight());
            player.setBp_enemy(player.getBonus_points());
            player.setIs_enemy_ai(player.getIs_ai());
        } else {
            Player enemy = PlayerManager.getInstance().get(rst_enemy.getPlayer_id());
            rst_enemy.setPlayer_name(enemy.getName());
            player.setFight_enemy(enemy.getFight());
            player.setBp_enemy(enemy.getBonus_points());
            int is_ai = enemy.getIs_ai();
            if(is_ai==1) { // 是AI
                player.setIs_enemy_ai((int)enemy.getPlayer_id());
            } else {
                player.setIs_enemy_ai(enemy.getIs_ai());
            }
            
        }

        rsts.add(rst_enemy);
        List<Kapai> teamKapais_enemy = TeamManager.getInstance().getTeamKapai(rst_enemy);
        resRankSoilderTeamMessage.setKapais_enemy(teamKapais_enemy);
        resRankSoilderTeamMessage.setRsts(rsts);
        
        combineMessage.addMessage(resRankSoilderTeamMessage);
        
        MessagePusher.pushMessage(player_id, combineMessage);
    }
    
    @EventHandler(value=EventType.PRE_ENTER_JINKU)
    public void onPreEnterJinku(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        int mapId = event.getMapId();
        RankSoilderTeam rankSoilderTeam = RankSoilderTeamManager.getInstance().get(player_id);
        ResPlayerPreEnterSceneMessage resp = null;
        if(rankSoilderTeam.getTeam_id() == 0) {
            resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_FAIL, "NO_TEAMS");
        }
        
        Player player = PlayerManager.getInstance().get(player_id);
        int keyNum = player.getKeyNum();
        
        if((keyNum -= 1)>=0) {
            player.setKeyNum(keyNum);
            //player.setFocsUpdate();
            //DbService.getInstance().add2Queue(player);
            resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_SUCC);
        } else {
            resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_FAIL, "NO_KEYS");
        }
        resp = new ResPlayerPreEnterSceneMessage(mapId, SceneDataPool.ENTER_SUCC);
        
        updateNow(player);
        ResPlayerMessage resPlayerMessage = new ResPlayerMessage();
        resPlayerMessage.setPlayer(player);
        
        // 准备组合包 发送给客户端
        CombineMessage combineMessage = new CombineMessage();
        combineMessage.addMessage(resp);
        combineMessage.addMessage(resPlayerMessage);
        
        MessagePusher.pushMessage(player_id, combineMessage);
    }
    
    @EventHandler(value=EventType.ENTER_JINKU)
    public void onEnterJinku(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        // 准备组合包 发送给客户端
        CombineMessage combineMessage = new CombineMessage();
     // 添加场景ID消息
        ResPlayerEnterSceneMessage resPlayerEnterSceneMessage = new ResPlayerEnterSceneMessage();
        resPlayerEnterSceneMessage.setMapId(MapEnum.Treasury.value());
        combineMessage.addMessage(resPlayerEnterSceneMessage);
        
        Player player = PlayerManager.getInstance().get(player_id);
        
        // 添加己方宝箱队伍消息
        RankSoilderTeam rankSoilderTeam = RankSoilderTeamManager.getInstance().get(player_id);
        rankSoilderTeam.setPlayer_name(player.getName());
        List<Kapai> teamKapais = TeamManager.getInstance().getTeamKapai(rankSoilderTeam);
        
        // 添加宝库怪物数据
        Treasury treasury = TreasuryManager.getInstance().resetTreasury(player_id);
        //Treasury treasury = TreasuryManager.getInstance().get(player_id);
        
        ResTreasuryTeamMessage resTreasuryTeamMessage = new ResTreasuryTeamMessage();
        resTreasuryTeamMessage.setRst(rankSoilderTeam);
        resTreasuryTeamMessage.setKapais(teamKapais);
        resTreasuryTeamMessage.setTreasury(treasury);
        
        combineMessage.addMessage(resTreasuryTeamMessage);
        
        MessagePusher.pushMessage(player_id, combineMessage);
    }
    
    @EventHandler(value=EventType.ENTER_FUBEN_LEVEL)
    public void onEnterFubenLevel(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        //Player player = PlayerManager.getInstance().get(player_id);
        // 准备组合包 发送给客户端
        CombineMessage combineMessage = new CombineMessage();
        
        // 添加场景ID消息
        ResPlayerEnterSceneMessage resPlayerEnterSceneMessage = new ResPlayerEnterSceneMessage();
        resPlayerEnterSceneMessage.setMapId(MapEnum.Treasury.value());
        combineMessage.addMessage(resPlayerEnterSceneMessage);
        
        // 添加副本列表信息
        ResFubenListMessage resFubenListMessage = new ResFubenListMessage();
        List<Fuben> fubens = FubenManager.getInstance().getFubenList(player_id);
        resFubenListMessage.setFubens(fubens);
        combineMessage.addMessage(resFubenListMessage);
        
        MessagePusher.pushMessage(player_id, combineMessage);
    }
    
    
    @EventHandler(value=EventType.ENTER_FUBEN_ZHANDOU)
    public void onEnterFubenZhandou(EventEnterScene event) {
        logger.info(getClass().getSimpleName()+"捕捉到事件"+event);
        
        long player_id = event.getPlayerId();
        int map_id = event.getMapId();
        // 准备组合包 发送给客户端
        CombineMessage combineMessage = new CombineMessage();
        
        // 添加场景ID消息
        ResPlayerEnterSceneMessage resPlayerEnterSceneMessage = new ResPlayerEnterSceneMessage();
        resPlayerEnterSceneMessage.setMapId(map_id);
        combineMessage.addMessage(resPlayerEnterSceneMessage);
        
        // 添加己方排行队伍消息
        ArrayList<RankSoilderTeam> rsts = new ArrayList<RankSoilderTeam>();
        RankSoilderTeam rankSoilderTeam = RankSoilderTeamManager.getInstance().get(player_id);
        List<Kapai> teamKapais = TeamManager.getInstance().getTeamKapai(rankSoilderTeam);
        ResFubenSoilderTeamMessage resRankSoilderTeamMessage = new ResFubenSoilderTeamMessage();
        Player player = PlayerManager.getInstance().get(player_id);
        rankSoilderTeam.setPlayer_name(player.getName());
        rsts.add(rankSoilderTeam);
        resRankSoilderTeamMessage.setKapais(teamKapais);
     // 添加敌方排行队伍列表消息
        RankSoilderTeam rst_enemy = FubenManager.getInstance().getTeam(player_id, map_id);
        Kapai fuben_boss = FubenManager.getInstance().queryOneEnemy(player_id, map_id);
        rst_enemy.setShengmingzhi(fuben_boss.getShengmingzhi());
        rst_enemy.setGongjizhi(fuben_boss.getGongjizhi());
        Kapai kapai1 = new Kapai();
        Kapai kapai2 = new Kapai();
        Kapai kapai4 = new Kapai();
        Kapai kapai5 = new Kapai();
        rsts.add(rst_enemy);
        List<Kapai> teamKapais_enemy = new ArrayList<>();
        teamKapais_enemy.add(kapai1);
        teamKapais_enemy.add(kapai2);
        teamKapais_enemy.add(fuben_boss);
        teamKapais_enemy.add(kapai4);
        teamKapais_enemy.add(kapai5);
        resRankSoilderTeamMessage.setKapais_enemy(teamKapais_enemy);
        resRankSoilderTeamMessage.setRsts(rsts);
        combineMessage.addMessage(resRankSoilderTeamMessage);
        
        MessagePusher.pushMessage(player_id, combineMessage);
    }
}
