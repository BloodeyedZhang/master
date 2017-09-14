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

DROP TABLE IF EXISTS `Player`;
CREATE TABLE `Player` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` int(11) DEFAULT 1,
   `name` varchar(128)  COMMENT '昵称',
   `job` tinyint DEFAULT 0 COMMENT '职业',
   `exp` bigint(20) DEFAULT  0 COMMENT '经验',
   PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000001;

-- ----------------------------
-- Records of Player
-- ----------------------------
insert Player values(10000,99,'kingston',1,12345);

DROP TABLE IF EXISTS `Kapai`;
CREATE TABLE `Kapai`(
  `id` bigint(20) DEFAULT NULL,
  `kapai_id` int(20) NOT  NULL AUTO_INCREMENT,
  `player_id` bigint(20) DEFAULT NULL,
  `dalei` int(3) DEFAULT 1,
  `bingzhong` int(3) DEFAULT 1011,
  `pinzhi` int(3) DEFAULT 1,
  `xingji` int(3) DEFAULT 1,
  `jiachengbi` int(3) DEFAULT 0,
  `s_dengji` int(3) DEFAULT 1,
  `jingyan` int(11) DEFAULT 0,
  PRIMARY KEY(`kapai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000001;

-- ----------------------------
-- Records of Kapai
-- ----------------------------
insert Kapai values(0,10000,10000,1,1011,1,1,0,1,0);
insert Kapai values(0,10001,10000,2,1012,1,1,0,1,0);
insert Kapai values(0,10002,10000,3,1013,1,1,0,1,0);
insert Kapai values(0,10003,10000,4,1014,1,1,0,1,0);
insert Kapai values(0,10004,10000,5,1015,1,1,0,1,0);
