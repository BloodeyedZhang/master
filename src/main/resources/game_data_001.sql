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
	`shengming_pinzhi`	float	DEFAULT NULL,
	`gongjili_dengji`	int	DEFAULT NULL,
	`gongjili_pinzhi`	float	DEFAULT NULL,
	`speed`		float	DEFAULT NULL,
	`zhiliao_dengji`	int	DEFAULT NULL,
	`zhiliao_pinzhi`	float	DEFAULT NULL,
	`jingzun`	float	DEFAULT NULL,
	`fanwei`	float	DEFAULT NULL
	
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ConfigBingzhong`;
CREATE TABLE `ConfigBingzhong` (
	`id` int	DEFAULT NULL,
	`bingzhong` varchar(64)	DEFAULT "",
	`shengming_base` int	DEFAULT NULL,
	`gongji_base` int	DEFAULT NULL,
	`zhiliao_base` int	DEFAULT NULL,
	`dalei` int		DEFAULT NULL,
	`yulantu` varchar(255)	DEFAULT NULL,
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
	`jiacheng_base`	float	DEFAULT NULL
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ConfigXingji`;
CREATE TABLE `ConfigXingji` (
	`xingji`	int	DEFAULT NULL,
	`jingyan_shangxian`	int	DEFAULT NULL
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `configjiacheng`;
CREATE TABLE `configjiacheng` (
    `jiacheng_zhonglei`   int(3) DEFAULT NULL,
    `des`    float   DEFAULT NULL
    
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `configtreasuryreward`;
CREATE TABLE `configtreasuryreward` (
  `treasuryLevel` int(11) DEFAULT NULL,
  `levelupDiamonds` int(11) DEFAULT NULL,
  `levelupCoins` int(11) DEFAULT NULL,
  `gotArmy` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `configtreasury`;
CREATE TABLE `configtreasury` (
  `treasuryLevel` int(11) NOT NULL,
  `baseHP` int(11) DEFAULT NULL,
  `level1HP` int(11) DEFAULT NULL,
  `level2HP` int(11) DEFAULT NULL,
  `level3HP` int(11) DEFAULT NULL,
  `level4HP` int(11) DEFAULT NULL,
  `diamondMiu1` float DEFAULT NULL,
  `diamondSigma1` float DEFAULT NULL,
  `coinMiu1` float DEFAULT NULL,
  `coinSigma1` float DEFAULT NULL,
  `cardMiu1` float DEFAULT NULL,
  `cardSigma1` float DEFAULT NULL,
  `diamondMiu2` float DEFAULT NULL,
  `diamondSigma2` float DEFAULT NULL,
  `coinMiu2` float DEFAULT NULL,
  `coinSigma2` float DEFAULT NULL,
  `cardMiu2` float DEFAULT NULL,
  `cardSigma2` float DEFAULT NULL,
  `diamondMiu3` float DEFAULT NULL,
  `diamondSigma3` float DEFAULT NULL,
  `coinMiu3` float DEFAULT NULL,
  `coinSigma3` float DEFAULT NULL,
  `cardMiu3` float DEFAULT NULL,
  `cardSigma3` float DEFAULT NULL,
  `diamondMiu4` float DEFAULT NULL,
  `diamondSigma4` float DEFAULT NULL,
  `coinMiu4` float DEFAULT NULL,
  `coinSigma4` float DEFAULT NULL,
  `cardMiu4` float DEFAULT NULL,
  `cardSigma4` float DEFAULT NULL,
  PRIMARY KEY (`treasuryLevel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `configpinzhipr`
-- ----------------------------
DROP TABLE IF EXISTS `configpinzhipr`;
CREATE TABLE `configpinzhipr` (
  `type` varchar(255) DEFAULT NULL,
  `meihua` float DEFAULT NULL,
  `fangpian` float DEFAULT NULL,
  `hongtao` float DEFAULT NULL,
  `heitao` float DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of configpinzhipr
-- ----------------------------
INSERT INTO `configpinzhipr` VALUES ('s_kabao', '0.6574', '0.3123', '0.0262', '0.0041', '');
INSERT INTO `configpinzhipr` VALUES ('l_kabao', '0.4957', '0.4328', '0.0517', '0.0198', '');
INSERT INTO `configpinzhipr` VALUES ('sl_kabao', '0', '0.7785', '0.1671', '0.0544', '保证1张黑桃，只随机另外15张卡');
INSERT INTO `configpinzhipr` VALUES ('box1', '0.6963', '0.2978', '0.0056', '0.0002', '');
INSERT INTO `configpinzhipr` VALUES ('box2', '0.6057', '0.3714', '0.0205', '0.0024', '');
INSERT INTO `configpinzhipr` VALUES ('box3', '0.5066', '0.4362', '0.0509', '0.0063', '');
INSERT INTO `configpinzhipr` VALUES ('box4', '0.3901', '0.4744', '0.1028', '0.0337', '');

-- ----------------------------
-- Table structure for `configbingzhongvt`
-- ----------------------------
DROP TABLE IF EXISTS `configbingzhongvt`;
CREATE TABLE `configbingzhongvt` (
  `id` int(11) DEFAULT NULL,
  `bingzhong` varchar(255) DEFAULT NULL,
  `vt` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of configbingzhongvt
-- ----------------------------
INSERT INTO `configbingzhongvt` VALUES ('1011', 's1', '40');
INSERT INTO `configbingzhongvt` VALUES ('1012', 's2', '40');
INSERT INTO `configbingzhongvt` VALUES ('1013', 's3', '40');
INSERT INTO `configbingzhongvt` VALUES ('1014', 's4', '40');
INSERT INTO `configbingzhongvt` VALUES ('1015', 's5', '40');
INSERT INTO `configbingzhongvt` VALUES ('1016', 's6', '80');
INSERT INTO `configbingzhongvt` VALUES ('1017', 's7', '80');
INSERT INTO `configbingzhongvt` VALUES ('1018', 's8', '80');
INSERT INTO `configbingzhongvt` VALUES ('1019', 's9', '50');
INSERT INTO `configbingzhongvt` VALUES ('1020', 's10', '40');
INSERT INTO `configbingzhongvt` VALUES ('1021', 's11', '40');
INSERT INTO `configbingzhongvt` VALUES ('1022', 's12', '60');
INSERT INTO `configbingzhongvt` VALUES ('1023', 's13', '60');
INSERT INTO `configbingzhongvt` VALUES ('1024', 's14', '55');
INSERT INTO `configbingzhongvt` VALUES ('1025', 's15', '25');
INSERT INTO `configbingzhongvt` VALUES ('1026', 's16', '40');
INSERT INTO `configbingzhongvt` VALUES ('1027', 's17', '60');
INSERT INTO `configbingzhongvt` VALUES ('1028', 's18', '25');
INSERT INTO `configbingzhongvt` VALUES ('1029', 's19', '15');
INSERT INTO `configbingzhongvt` VALUES ('1030', 's20', '15');
INSERT INTO `configbingzhongvt` VALUES ('1031', 's21', '35');
INSERT INTO `configbingzhongvt` VALUES ('1032', 's22', '40');
INSERT INTO `configbingzhongvt` VALUES ('1033', 's23', '30');

-- ----------------------------
-- Table structure for `configtreasuryvt`
-- ----------------------------
DROP TABLE IF EXISTS `configtreasuryvt`;
CREATE TABLE `configtreasuryvt` (
  `level` int(10) DEFAULT NULL,
  `vt` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of configtreasuryvt
-- ----------------------------
INSERT INTO `configtreasuryvt` VALUES ('1', '825');
INSERT INTO `configtreasuryvt` VALUES ('2', '825');
INSERT INTO `configtreasuryvt` VALUES ('3', '865');
INSERT INTO `configtreasuryvt` VALUES ('4', '865');
INSERT INTO `configtreasuryvt` VALUES ('5', '865');
INSERT INTO `configtreasuryvt` VALUES ('6', '865');
INSERT INTO `configtreasuryvt` VALUES ('7', '915');
INSERT INTO `configtreasuryvt` VALUES ('8', '915');
INSERT INTO `configtreasuryvt` VALUES ('9', '915');
INSERT INTO `configtreasuryvt` VALUES ('10', '940');
INSERT INTO `configtreasuryvt` VALUES ('11', '940');
INSERT INTO `configtreasuryvt` VALUES ('12', '940');
INSERT INTO `configtreasuryvt` VALUES ('13', '940');
INSERT INTO `configtreasuryvt` VALUES ('14', '965');
INSERT INTO `configtreasuryvt` VALUES ('15', '965');
INSERT INTO `configtreasuryvt` VALUES ('16', '965');
INSERT INTO `configtreasuryvt` VALUES ('17', '965');
INSERT INTO `configtreasuryvt` VALUES ('18', '1000');
INSERT INTO `configtreasuryvt` VALUES ('19', '1000');
INSERT INTO `configtreasuryvt` VALUES ('20', '1000');
INSERT INTO `configtreasuryvt` VALUES ('21', '1015');
INSERT INTO `configtreasuryvt` VALUES ('22', '1015');
INSERT INTO `configtreasuryvt` VALUES ('23', '1015');
INSERT INTO `configtreasuryvt` VALUES ('24', '1015');
INSERT INTO `configtreasuryvt` VALUES ('25', '1030');

-- ----------------------------
-- Table structure for `configtreasurybingzhong`
-- ----------------------------
DROP TABLE IF EXISTS `configtreasurybingzhong`;
CREATE TABLE `configtreasurybingzhong` (
  `level` int(11) DEFAULT NULL,
  `s1` float DEFAULT NULL,
  `s2` float DEFAULT NULL,
  `s3` float DEFAULT NULL,
  `s4` float DEFAULT NULL,
  `s5` float DEFAULT NULL,
  `s6` float DEFAULT NULL,
  `s7` float DEFAULT NULL,
  `s8` float DEFAULT NULL,
  `s9` float DEFAULT NULL,
  `s10` float DEFAULT NULL,
  `s11` float DEFAULT NULL,
  `s12` float DEFAULT NULL,
  `s13` float DEFAULT NULL,
  `s14` float DEFAULT NULL,
  `s15` float DEFAULT NULL,
  `s16` float DEFAULT NULL,
  `s17` float DEFAULT NULL,
  `s18` float DEFAULT NULL,
  `s19` float DEFAULT NULL,
  `s20` float DEFAULT NULL,
  `s21` float DEFAULT NULL,
  `s22` float DEFAULT NULL,
  `s23` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of configtreasurybingzhong
-- ----------------------------
INSERT INTO `configtreasurybingzhong` VALUES ('1', '4.85', '4.85', '4.85', '4.85', '4.85', '9.7', '9.7', '9.7', null, '4.85', null, '7.27', '7.27', '6.67', null, '4.85', '7.27', null, null, null, null, '4.85', '3.64');
INSERT INTO `configtreasurybingzhong` VALUES ('2', '4.85', '4.85', '4.85', '4.85', '4.85', '9.7', '9.7', '9.7', null, '4.85', null, '7.27', '7.27', '6.67', null, '4.85', '7.27', null, null, null, null, '4.85', '3.64');
INSERT INTO `configtreasurybingzhong` VALUES ('3', '4.62', '4.62', '4.62', '4.62', '4.62', '9.25', '9.25', '9.25', null, '4.62', '4.62', '6.94', '6.94', '6.36', null, '4.62', '6.94', null, null, null, null, '4.62', '3.47');
INSERT INTO `configtreasurybingzhong` VALUES ('4', '4.62', '4.62', '4.62', '4.62', '4.62', '9.25', '9.25', '9.25', null, '4.62', '4.62', '6.94', '6.94', '6.36', null, '4.62', '6.94', null, null, null, null, '4.62', '3.47');
INSERT INTO `configtreasurybingzhong` VALUES ('5', '4.62', '4.62', '4.62', '4.62', '4.62', '9.25', '9.25', '9.25', null, '4.62', '4.62', '6.94', '6.94', '6.36', null, '4.62', '6.94', null, null, null, null, '4.62', '3.47');
INSERT INTO `configtreasurybingzhong` VALUES ('6', '4.62', '4.62', '4.62', '4.62', '4.62', '9.25', '9.25', '9.25', null, '4.62', '4.62', '6.94', '6.94', '6.36', null, '4.62', '6.94', null, null, null, null, '4.62', '3.47');
INSERT INTO `configtreasurybingzhong` VALUES ('7', '4.37', '4.37', '4.37', '4.37', '4.37', '8.74', '8.74', '8.74', '5.46', '4.37', '4.37', '6.56', '6.56', '6.01', null, '4.37', '6.56', null, null, null, null, '4.37', '3.28');
INSERT INTO `configtreasurybingzhong` VALUES ('8', '4.37', '4.37', '4.37', '4.37', '4.37', '8.74', '8.74', '8.74', '5.46', '4.37', '4.37', '6.56', '6.56', '6.01', null, '4.37', '6.56', null, null, null, null, '4.37', '3.28');
INSERT INTO `configtreasurybingzhong` VALUES ('9', '4.37', '4.37', '4.37', '4.37', '4.37', '8.74', '8.74', '8.74', '5.46', '4.37', '4.37', '6.56', '6.56', '6.01', null, '4.37', '6.56', null, null, null, null, '4.37', '3.28');
INSERT INTO `configtreasurybingzhong` VALUES ('10', '4.26', '4.26', '4.26', '4.26', '4.26', '8.51', '8.51', '8.51', '5.32', '4.26', '4.26', '6.38', '6.38', '5.85', '2.66', '4.26', '6.38', null, null, null, null, '4.26', '3.19');
INSERT INTO `configtreasurybingzhong` VALUES ('11', '4.26', '4.26', '4.26', '4.26', '4.26', '8.51', '8.51', '8.51', '5.32', '4.26', '4.26', '6.38', '6.38', '5.85', '2.66', '4.26', '6.38', null, null, null, null, '4.26', '3.19');
INSERT INTO `configtreasurybingzhong` VALUES ('12', '4.26', '4.26', '4.26', '4.26', '4.26', '8.51', '8.51', '8.51', '5.32', '4.26', '4.26', '6.38', '6.38', '5.85', '2.66', '4.26', '6.38', null, null, null, null, '4.26', '3.19');
INSERT INTO `configtreasurybingzhong` VALUES ('13', '4.26', '4.26', '4.26', '4.26', '4.26', '8.51', '8.51', '8.51', '5.32', '4.26', '4.26', '6.38', '6.38', '5.85', '2.66', '4.26', '6.38', null, null, null, null, '4.26', '3.19');
INSERT INTO `configtreasurybingzhong` VALUES ('14', '4.15', '4.15', '4.15', '4.15', '4.15', '8.29', '8.29', '8.29', '5.18', '4.15', '4.15', '6.22', '6.22', '5.7', '2.59', '4.15', '6.22', '2.59', null, null, null, '4.15', '3.11');
INSERT INTO `configtreasurybingzhong` VALUES ('15', '4.15', '4.15', '4.15', '4.15', '4.15', '8.29', '8.29', '8.29', '5.18', '4.15', '4.15', '6.22', '6.22', '5.7', '2.59', '4.15', '6.22', '2.59', null, null, null, '4.15', '3.11');
INSERT INTO `configtreasurybingzhong` VALUES ('16', '4.15', '4.15', '4.15', '4.15', '4.15', '8.29', '8.29', '8.29', '5.18', '4.15', '4.15', '6.22', '6.22', '5.7', '2.59', '4.15', '6.22', '2.59', null, null, null, '4.15', '3.11');
INSERT INTO `configtreasurybingzhong` VALUES ('17', '4.15', '4.15', '4.15', '4.15', '4.15', '8.29', '8.29', '8.29', '5.18', '4.15', '4.15', '6.22', '6.22', '5.7', '2.59', '4.15', '6.22', '2.59', null, null, null, '4.15', '3.11');
INSERT INTO `configtreasurybingzhong` VALUES ('18', '4', '4', '4', '4', '4', '8', '8', '8', '5', '4', '4', '6', '6', '5.5', '2.5', '4', '6', '2.5', null, null, '3.5', '4', '3');
INSERT INTO `configtreasurybingzhong` VALUES ('19', '4', '4', '4', '4', '4', '8', '8', '8', '5', '4', '4', '6', '6', '5.5', '2.5', '4', '6', '2.5', null, null, '3.5', '4', '3');
INSERT INTO `configtreasurybingzhong` VALUES ('20', '4', '4', '4', '4', '4', '8', '8', '8', '5', '4', '4', '6', '6', '5.5', '2.5', '4', '6', '2.5', null, null, '3.5', '4', '3');
INSERT INTO `configtreasurybingzhong` VALUES ('21', '3.94', '3.94', '3.94', '3.94', '3.94', '7.88', '7.88', '7.88', '4.93', '3.94', '3.94', '5.91', '5.91', '5.42', '2.46', '3.94', '5.91', '2.46', null, '1.48', '3.45', '3.94', '2.96');
INSERT INTO `configtreasurybingzhong` VALUES ('22', '3.94', '3.94', '3.94', '3.94', '3.94', '7.88', '7.88', '7.88', '4.93', '3.94', '3.94', '5.91', '5.91', '5.42', '2.46', '3.94', '5.91', '2.46', null, '1.48', '3.45', '3.94', '2.96');
INSERT INTO `configtreasurybingzhong` VALUES ('23', '3.94', '3.94', '3.94', '3.94', '3.94', '7.88', '7.88', '7.88', '4.93', '3.94', '3.94', '5.91', '5.91', '5.42', '2.46', '3.94', '5.91', '2.46', null, '1.48', '3.45', '3.94', '2.96');
INSERT INTO `configtreasurybingzhong` VALUES ('24', '3.94', '3.94', '3.94', '3.94', '3.94', '7.88', '7.88', '7.88', '4.93', '3.94', '3.94', '5.91', '5.91', '5.42', '2.46', '3.94', '5.91', '2.46', null, '1.48', '3.45', '3.94', '2.96');
INSERT INTO `configtreasurybingzhong` VALUES ('25', '3.88', '3.88', '3.88', '3.88', '3.88', '7.77', '7.77', '7.77', '4.85', '3.88', '3.88', '5.83', '5.83', '5.34', '2.43', '3.88', '5.83', '2.43', '1.46', '1.46', '3.4', '3.88', '2.91');

-- ----------------------------
-- Table structure for `configbingzhongfight`
-- ----------------------------
DROP TABLE IF EXISTS `configbingzhongfight`;
CREATE TABLE `configbingzhongfight` (
  `id` int(10) DEFAULT NULL,
  `x` int(10) DEFAULT NULL,
  `y` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `configbingzhongfight` VALUES ('1011','7','1');
