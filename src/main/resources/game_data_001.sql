/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : game_data_001

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-08-02 23:04:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ConfigPlayerLevel
-- ----------------------------
DROP TABLE IF EXISTS `ConfigPlayerLevel`;
CREATE TABLE `ConfigPlayerLevel` (
  `level` int(11) DEFAULT NULL,
  `needExp` bigint(20) DEFAULT NULL,
  `vitality` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of ConfigPlayerLevel
-- ----------------------------
INSERT INTO `ConfigPlayerLevel` VALUES ('1', '2345', '100');
INSERT INTO `ConfigPlayerLevel` VALUES ('2', '23450', '105');

DROP TABLE IF EXISTS `ConfigSkill`;
CREATE TABLE `ConfigSkill` (
  `id` int(11) DEFAULT 1,
  `name`  varchar(64) DEFAULT "",
  `effect` varchar(128) DEFAULT ""
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `ConfigSkill` VALUES ('1', '万剑诀', '剑芒如雨直落，攻击敌方全体');
INSERT INTO `ConfigSkill` VALUES ('2', '天剑', '人剑合一，身化利剑，攻击敌方全体');

-- ----------------------------
-- Records of ConfigSoilderLevel
-- ----------------------------
DROP TABLE IF EXISTS `ConfigSoilderLevel`;
CREATE TABLE `ConfigSoilderLevel` (
	`id`	int	DEFAULT NULL,
	`dengji` 	int	DEFAULT NULL,
	`bingzhong`		varchar(64)	DEFAULT "",
	`shengming_dengji`	int	DEFAULT NULL,
	`shengming_pinzhi`	double	DEFAULT NULL,
	`gongjili_dengji`	int	DEFAULT NULL,
	`gongjili_pinzhi`	double	DEFAULT NULL,
	`speed`		double	DEFAULT NULL,
	`zhiliao_dengji`	int	DEFAULT NULL,
	`zhiliao_pinzhi`	int	DEFAULT NULL,
	`jingzun`	int	DEFAULT NULL,
	`fanwei`	int	DEFAULT NULL
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ConfigBingzhong`;
CREATE TABLE `ConfigBingzhong` (
	`id` int	DEFAULT NULL,
	`bingzhong` varchar(64)	DEFAULT "",
	`shengming_base` int	DEFAULT NULL,
	`gongji_base` int	DEFAULT NULL,
	`zhiliao_base` int	DEFAULT NULL,
	`dalei` int		DEFAULT NULL,
	`yulantu` int	DEFAULT NULL,
	`shangxian` int		DEFAULT NULL,
	`a_fanweishanghai` float	DEFAULT NULL,
	`a_gongjifudong` float	DEFAULT NULL,
	`a_gongjijiange` float	DEFAULT NULL,
	`a_gongjijiange_huifuzhi` float	DEFAULT NULL,
	`a_gongjiqianyao` 	float	DEFAULT NULL,
	`a_gongjiqianyao_huifuzhi` float	DEFAULT NULL,
	`a_guanchuangesu` int	DEFAULT NULL,
	`a_jiansu` float	DEFAULT NULL,
	`a_jiansu_shijian` float	DEFAULT NULL,
	`a_jingzhundu` float	DEFAULT NULL,
	`a_jitui` float	DEFAULT NULL,
	`a_jitui_zhiliang` int	DEFAULT NULL,
	`a_leixing` int	DEFAULT NULL,
	`a_shecheng` float	DEFAULT NULL,
	`a_shoujipandingmoxing`	varchar(64)	DEFAULT "",
	`a_xuanyun` float	DEFAULT NULL,
	`a_zhonglipaoshe` float	DEFAULT NULL,
	`a_zidanhudubilv` float	DEFAULT NULL,
	`a_zidanmoxing` varchar(64)	DEFAULT "",
	`a_zidansudu` float	DEFAULT NULL,
	`a_zidao` boolean 	DEFAULT NULL,
	`a_zilaoshuzi`	float	DEFAULT NULL,
	`a_ziliao` boolean	DEFAULT NULL,
	`c_jineng` varchar(64)	DEFAULT "",
	`f_zidan_baozha` varchar(64)	DEFAULT "",
	`f_zidan_fashe` varchar(64)	DEFAULT "",
	`n_mingzi_usen` varchar(64)	DEFAULT "",
	`n_mingzi_zhcn` varchar(64)	DEFAULT "",
	`s_shengyin` varchar(64)	DEFAULT "",
	`s_zidan_yinxiao` varchar(64)	DEFAULT "",
	`u_baohutao_daxiao` float	DEFAULT NULL,
	`u_baohutao_shengming` float	DEFAULT NULL,
	`u_donghuasulv` float	DEFAULT NULL,
	`u_donghuasulv_huifuzhi` float	DEFAULT NULL,
	`u_jiangli` float	DEFAULT NULL,
	`u_jiaosetiji_h` float	DEFAULT NULL,
	`u_jiaosetiji_r` float	DEFAULT NULL,
	`u_jiaosetiji_y` float	DEFAULT NULL,
	`u_jihuo` boolean	DEFAULT NULL,
	`u_moxing` varchar(64)	DEFAULT "",
	`u_yidongsudu` float	DEFAULT NULL,
	`u_yidongsudu_huifuzhi` float	DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ConfigPinzhi`;
CREATE TABLE `ConfigPinzhi` (
	`pinzhi_zhonglei`	int	DEFAULT NULL,
	`jiacheng_zuidi`	float	DEFAULT NULL,
	`jiacheng_zuigao`	float	DEFAULT NULL
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ConfigXingji`;
CREATE TABLE `ConfigXingji` (
	`xingji`	int	DEFAULT NULL,
	`jingyan_shangxian`	int	DEFAULT NULL
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;











