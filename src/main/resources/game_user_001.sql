/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : game_user_001

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-08-02 23:04:42
*/

SET FOREIGN_KEY_CHECKS=0;

drop table if exists sequence;   
create table sequence (       
seq_name        VARCHAR(50) NOT NULL, -- 序列名称       
current_val     INT         NOT NULL, -- 当前值       
increment_val   INT         NOT NULL    DEFAULT 1, -- 步长(跨度)       
PRIMARY KEY (seq_name)   
);

DROP FUNCTION if exists  currval;
create function currval(v_seq_name VARCHAR(50))   
returns integer 
begin     
    declare value integer;       
    set value = 0;       
    select current_val into value  from sequence where seq_name = v_seq_name; 
   return value; 
end;

INSERT INTO sequence VALUES ('seq_player_num', '1000000000', '1');
INSERT INTO sequence VALUES ('seq_kapai_num', '1000000', '1');
INSERT INTO sequence VALUES ('seq_soilderTeam_num', '100000', '1');
INSERT INTO sequence VALUES ('seq_treasury_num', '10000', '1');
INSERT INTO sequence VALUES ('seq_AttrChangeRecord_num', '0', '1');
INSERT INTO sequence VALUES ('seq_battle_num', '0', '1');

DROP FUNCTION if exists  nextval;
create function nextval (v_seq_name VARCHAR(50))
    returns integer
begin
    update sequence set current_val = current_val + increment_val  where seq_name = v_seq_name;
    return currval(v_seq_name);
end;

DROP TABLE IF EXISTS `AttrChangeRecord`;
CREATE TABLE `AttrChangeRecord` (
  `id` bigint(20) DEFAULT 0 ,
  `record_id` bigint(20) DEFAULT 0 ,
  `player_id` bigint(20) DEFAULT 0 ,
  `sourceEvtType` varchar(100) DEFAULT '',
  `targetEvtType` varchar(100) DEFAULT '',
  `attrChange` int(10) DEFAULT 0,
  `extra_param` varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `TreasuryRecord`;
CREATE TABLE `TreasuryRecord` (
  `id` bigint(20) DEFAULT 0 ,
  `treasury_id` bigint(20) DEFAULT 0 ,
  `player_id` bigint(20) DEFAULT 0 ,
  `index` int(10) DEFAULT 0,
  `coins` int(10) DEFAULT 0,
  `diamonds` int(10) DEFAULT 0,
  `bingzhongs` varchar(255) DEFAULT '',
  `jiachengbis` varchar(255) DEFAULT '',
  `xingjis` varchar(255) DEFAULT '',
  `jiachengtypes` varchar(255) DEFAULT '',
  `pinzhis` varchar(255),
  `createtime` varchar(255) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for systemrecord
-- ----------------------------
DROP TABLE IF EXISTS `systemrecord`;
CREATE TABLE `systemrecord` (
  `key` varchar(255) NOT NULL,
  `value` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemrecord
-- ----------------------------
INSERT INTO `systemrecord` VALUES ('dailyResetTimestamp', '1505491785589');

DROP TABLE IF EXISTS `Player`;
CREATE TABLE `Player` (
  `id` bigint(20) DEFAULT 0 ,
  `player_id` bigint(20) DEFAULT 0 ,
  `level` int(11) DEFAULT 1,
   `name` varchar(128)  COMMENT '昵称',
   `job` tinyint DEFAULT 0 COMMENT '职业',
   `exp` bigint(20) DEFAULT  0 COMMENT '经验',
   `lastDailyReset` bigint(13) DEFAULT 0 COMMENT '每日重置时间戳',
   `money1` int(10) DEFAULT 0 COMMENT '货币1',
   `money2` int(10) DEFAULT 0 COMMENT '货币2',
   `bonus_points` int(10) DEFAULT 0 COMMENT '排行积分',
   `rank_battle_id` int(10) DEFAULT 0 COMMENT '排行战斗ID',
   `treasuryLevel` int(10) DEFAULT 0 COMMENT '宝库等级',
   `maxTreasuryLevel` int(10) DEFAULT 0 COMMENT '宝库等级上限',
   `treasuryLevelProgress` int(10) DEFAULT 0 COMMENT '等级进度',
   `keyNum` int(3) DEFAULT 0 COMMENT '持有钥匙数量',
   `maxKeyNum` int(3) DEFAULT 0 COMMENT '持有钥匙上限'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `SoilderTeam`;
CREATE TABLE `SoilderTeam`(
  `id` bigint(20) DEFAULT NULL,
  `team_id` int(20) NOT  NULL ,
  `player_id` bigint(20) DEFAULT NULL,
  `soilderIds` varchar(255) DEFAULT '',
  `shengmingzhi` int(11) DEFAULT 0,
  `gongjizhi` int(11) DEFAULT 0,
  PRIMARY KEY(`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `RankSoilderTeam`;
CREATE TABLE `RankSoilderTeam`(
  `id` bigint(20) DEFAULT NULL,
  `team_id` int(20) NOT  NULL ,
  `player_id` bigint(20) DEFAULT NULL,
  `soilderIds` varchar(255) DEFAULT '',
  `shengmingzhi` int(11) DEFAULT 0,
  `gongjizhi` int(11) DEFAULT 0,
  PRIMARY KEY(`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `Kapai`;
CREATE TABLE `Kapai`(
  `id` bigint(20) DEFAULT NULL,
  `kapai_id` int(20) NOT  NULL ,
  `player_id` bigint(20) DEFAULT NULL,
  `dalei` int(3) DEFAULT 1,
  `bingzhong` int(3) DEFAULT 1011,
  `pinzhi` int(3) DEFAULT 1,
  `xingji` int(3) DEFAULT 1,
  `jiachengbi` float DEFAULT NULL,
  `s_dengji` int(3) DEFAULT 1,
  `jingyan` int(11) DEFAULT 0,
  `shengmingzhi` int(11) DEFAULT 0,
  `gongjizhi` int(11) DEFAULT 0,
  `zhiliaozhi` int(11) DEFAULT 0,
  `jingyan_shangxian` int(11) DEFAULT 0,
  `speed` float DEFAULT NULL,
  `jingzun` float DEFAULT NULL,
  `fanwei` float DEFAULT NULL,
  `jiachengzhonglei` int(3) DEFAULT 0,
  PRIMARY KEY(`kapai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Treasury`;
CREATE TABLE `Treasury` (
  `id` bigint(20) DEFAULT NULL,
  `treasury_id` bigint(20) NOT NULL,
  `player_id` bigint(20) DEFAULT NULL,
  `treasuryLevel` int(11) NOT NULL,
  `baseHP` int(11) DEFAULT NULL,
  `level1` int(11) DEFAULT NULL,
  `level2` int(11) DEFAULT NULL,
  `level3` int(11) DEFAULT NULL,
  `level4` int(11) DEFAULT NULL,
  `level5` int(11) DEFAULT NULL,
  `level1HP` int(11) DEFAULT NULL,
  `level2HP` int(11) DEFAULT NULL,
  `level3HP` int(11) DEFAULT NULL,
  `level4HP` int(11) DEFAULT NULL,
  `level5HP` int(11) DEFAULT NULL,
  `diamond1` int(11) DEFAULT NULL,
  `coin1` int(11) DEFAULT NULL,
  `card1` int(11) DEFAULT NULL,
  `diamond2` int(11) DEFAULT NULL,
  `coin2` int(11) DEFAULT NULL,
  `card2` int(11) DEFAULT NULL,
  `diamond3` int(11) DEFAULT NULL,
  `coin3` int(11) DEFAULT NULL,
  `card3` int(11) DEFAULT NULL,
  `diamond4` int(11) DEFAULT NULL,
  `coin4` int(11) DEFAULT NULL,
  `card4` int(11) DEFAULT NULL,
  `diamond5` int(11) DEFAULT NULL,
  `coin5` int(11) DEFAULT NULL,
  `card5` int(11) DEFAULT NULL,
  `card1_pinzhi` varchar(255) DEFAULT NULL,
  `card2_pinzhi` varchar(255) DEFAULT NULL,
  `card3_pinzhi` varchar(255) DEFAULT NULL,
  `card4_pinzhi` varchar(255) DEFAULT NULL,
  `card5_pinzhi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

