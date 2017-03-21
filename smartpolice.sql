/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : smartpolice

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2016-08-12 15:49:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_alias`
-- ----------------------------
DROP TABLE IF EXISTS `app_alias`;
CREATE TABLE `app_alias` (
  `aliasid` int(11) NOT NULL AUTO_INCREMENT,
  `masterid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `alias` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`aliasid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of app_alias
-- ----------------------------
INSERT INTO `app_alias` VALUES ('1', '1', '1', 'lc');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `type` int(11) NOT NULL COMMENT '类别：0 建议，1 问题，2 投诉， 3 表扬',
  `date` date NOT NULL,
  `title` varchar(30) CHARACTER SET utf8 NOT NULL,
  `content` text CHARACTER SET utf8,
  `handle` char(2) CHARACTER SET utf8 DEFAULT NULL,
  `replyid` int(11) DEFAULT NULL,
  `replydate` date DEFAULT NULL,
  `reply` text CHARACTER SET utf8,
  PRIMARY KEY (`commentid`),
  KEY `FK_comUser_comm` (`userid`),
  KEY `FK_manaInf_comm` (`replyid`),
  CONSTRAINT `FK_comUser_comm` FOREIGN KEY (`userid`) REFERENCES `company_user` (`userid`),
  CONSTRAINT `FK_manaInf_comm` FOREIGN KEY (`replyid`) REFERENCES `manager_inf` (`managerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `company_inf`
-- ----------------------------
DROP TABLE IF EXISTS `company_inf`;
CREATE TABLE `company_inf` (
  `companyid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `demo` text CHARACTER SET utf8,
  `logo` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of company_inf
-- ----------------------------
INSERT INTO `company_inf` VALUES ('1', '小米', '北京', '电器', '手机厂商', 'MI');
INSERT INTO `company_inf` VALUES ('2', '魅族', '北京', '电器', '手机厂商', 'MZ');
INSERT INTO `company_inf` VALUES ('3', '雅濒欢少有限公司5', '智能监控系统呵呵', '智能监控系统', '智能监控', 'ZN');
INSERT INTO `company_inf` VALUES ('4', '智能监控2', '智能监控2地址', '智能监控系统', '智能监控', 'ZN');
INSERT INTO `company_inf` VALUES ('5', '智能监控3', '智能监控3地址', '智能监控系统', '智能监控', 'ZN');
INSERT INTO `company_inf` VALUES ('6', '打算发分公司', '成都', '家具', '家具厂商', 'DK');

-- ----------------------------
-- Table structure for `company_user`
-- ----------------------------
DROP TABLE IF EXISTS `company_user`;
CREATE TABLE `company_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `number` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `position` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `state` char(2) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `FK553DAE4DC4160A1D` (`companyid`),
  CONSTRAINT `FK553DAE4DC4160A1D` FOREIGN KEY (`companyid`) REFERENCES `company_inf` (`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of company_user
-- ----------------------------
INSERT INTO `company_user` VALUES ('1', '1', '1', '刘超', '刘超', 'liuchao@163.com', '111111222222', '管理员', '0');
INSERT INTO `company_user` VALUES ('2', '2', '2', '胡志强', '胡志强', 'huzhiqiang@qq.com', '222222111111', '工人', '1');
INSERT INTO `company_user` VALUES ('3', '3', '3', '智能监控3', '易发胜', 'huzhiqiang@qq.com', '111111222222', '管理员', '0');
INSERT INTO `company_user` VALUES ('4', '4', '4', '智能监控4', '易发胜', 'huzhiqiang@qq.com', '15688549658', '管理员', '1');
INSERT INTO `company_user` VALUES ('5', '5', '5', '智能监控5', '易发胜', 'huzhiqiang@qq.com', '15688549658', '管理员', '0');

-- ----------------------------
-- Table structure for `contact_inf`
-- ----------------------------
DROP TABLE IF EXISTS `contact_inf`;
CREATE TABLE `contact_inf` (
  `contactid` int(11) NOT NULL AUTO_INCREMENT,
  `masterid` int(11) NOT NULL,
  `contactedid` int(11) NOT NULL,
  `group` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `aliasid` int(11) DEFAULT NULL,
  `settime` date DEFAULT NULL,
  PRIMARY KEY (`contactid`),
  KEY `FK_userInf_conInf_masterId` (`masterid`),
  KEY `FK_userInf_conInf_contactedId` (`contactedid`),
  KEY `FK_aliaApp_conInf` (`aliasid`),
  CONSTRAINT `FK_aliaApp_conInf` FOREIGN KEY (`aliasid`) REFERENCES `app_alias` (`aliasid`),
  CONSTRAINT `FK_userInf_conInf_contactedId` FOREIGN KEY (`contactedid`) REFERENCES `user_inf` (`userid`),
  CONSTRAINT `FK_userInf_conInf_masterId` FOREIGN KEY (`masterid`) REFERENCES `user_inf` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of contact_inf
-- ----------------------------

-- ----------------------------
-- Table structure for `contact_wait`
-- ----------------------------
DROP TABLE IF EXISTS `contact_wait`;
CREATE TABLE `contact_wait` (
  `cwid` int(11) NOT NULL AUTO_INCREMENT,
  `masterid` int(11) NOT NULL,
  `contactedid` int(11) NOT NULL,
  `group` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `applytime` date NOT NULL,
  `message` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`cwid`),
  KEY `FK_userInf_conWait_master` (`masterid`),
  KEY `FK_userInf_conWait_contacted` (`contactedid`),
  CONSTRAINT `FK_userInf_conWait_contacted` FOREIGN KEY (`contactedid`) REFERENCES `user_inf` (`userid`),
  CONSTRAINT `FK_userInf_conWait_master` FOREIGN KEY (`masterid`) REFERENCES `user_inf` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of contact_wait
-- ----------------------------

-- ----------------------------
-- Table structure for `device_attach`
-- ----------------------------
DROP TABLE IF EXISTS `device_attach`;
CREATE TABLE `device_attach` (
  `subid` int(11) NOT NULL AUTO_INCREMENT,
  `serial` varchar(20) CHARACTER SET utf8 NOT NULL,
  `iftype` char(2) CHARACTER SET utf8 NOT NULL,
  `deviceid` int(11) NOT NULL,
  `type` char(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`subid`),
  KEY `FK_devInf_devAtt` (`deviceid`),
  CONSTRAINT `FK_devInf_devAtt` FOREIGN KEY (`deviceid`) REFERENCES `device_inf` (`deviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of device_attach
-- ----------------------------

-- ----------------------------
-- Table structure for `device_audit`
-- ----------------------------
DROP TABLE IF EXISTS `device_audit`;
CREATE TABLE `device_audit` (
  `auditid` int(11) NOT NULL AUTO_INCREMENT,
  `devicename` varchar(20) CHARACTER SET utf8 NOT NULL,
  `code` varchar(16) CHARACTER SET utf8 NOT NULL,
  `demo` text CHARACTER SET utf8 NOT NULL,
  `companyid` int(11) NOT NULL,
  `date` date NOT NULL,
  `checkid` int(11) DEFAULT NULL,
  `checkdate` date DEFAULT NULL,
  `type` char(2) CHARACTER SET utf8 NOT NULL,
  `state` char(2) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`auditid`),
  KEY `FKDC1E84B2C4160A1D` (`companyid`),
  KEY `FKDC1E84B2652F7718` (`checkid`),
  CONSTRAINT `FKDC1E84B2652F7718` FOREIGN KEY (`checkid`) REFERENCES `manager_inf` (`managerid`),
  CONSTRAINT `FKDC1E84B2C4160A1D` FOREIGN KEY (`companyid`) REFERENCES `company_inf` (`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of device_audit
-- ----------------------------
INSERT INTO `device_audit` VALUES ('1', '摄像头1', '1111', '摄像头1', '3', '2016-04-24', '1', '2016-04-24', '1', '0');
INSERT INTO `device_audit` VALUES ('6', '摄像头2', '2222', '摄像头2', '2', '2016-04-25', '1', '2016-04-25', '0', '1');

-- ----------------------------
-- Table structure for `device_inf`
-- ----------------------------
DROP TABLE IF EXISTS `device_inf`;
CREATE TABLE `device_inf` (
  `deviceid` int(11) NOT NULL AUTO_INCREMENT,
  `serial` varchar(30) CHARACTER SET utf8 NOT NULL,
  `code` varchar(16) CHARACTER SET utf8 NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 NOT NULL,
  `type` char(2) CHARACTER SET utf8 NOT NULL,
  `state` char(2) CHARACTER SET utf8 NOT NULL,
  `date` date DEFAULT NULL,
  `longitude` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `latitude` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `maxconlimit` int(2) NOT NULL DEFAULT '10',
  `mphone` varchar(11) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`deviceid`),
  KEY `serial` (`serial`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of device_inf
-- ----------------------------
INSERT INTO `device_inf` VALUES ('1', '123', '123', 'user', '12345678', '1', '1', '2016-06-29', '23', '23', '10', '');
INSERT INTO `device_inf` VALUES ('17', '123456789', '694994466313', '13032864544', '123456', '0', '1', '2016-07-19', '116.441454', '39.947892', '10', '13032864523');
INSERT INTO `device_inf` VALUES ('19', '12345', '1234', '13882758888', '123456', '1', '1', '2016-07-19', '12.2222', '23.3333', '10', '13882758888');

-- ----------------------------
-- Table structure for `device_log`
-- ----------------------------
DROP TABLE IF EXISTS `device_log`;
CREATE TABLE `device_log` (
  `logid` int(11) NOT NULL AUTO_INCREMENT,
  `deviceid` int(11) NOT NULL,
  `date` date NOT NULL,
  `ipaddr` varchar(15) NOT NULL,
  `port` int(2) NOT NULL,
  `operate` int(1) NOT NULL,
  PRIMARY KEY (`logid`),
  KEY `FK_devInf_devLog` (`deviceid`),
  CONSTRAINT `FK_devInf_devLog` FOREIGN KEY (`deviceid`) REFERENCES `device_inf` (`deviceid`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of device_log
-- ----------------------------
INSERT INTO `device_log` VALUES ('17', '1', '2016-07-14', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('18', '1', '2016-07-14', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('19', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('20', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('21', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('22', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('23', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('24', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('25', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('26', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('27', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('28', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('29', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('30', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('31', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('32', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('33', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('34', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('35', '1', '2016-07-17', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('36', '1', '2016-07-17', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('37', '1', '2016-07-17', '192.168.1.108', '5678', '1');
INSERT INTO `device_log` VALUES ('38', '1', '2016-07-19', '192.168.1.106', '5678', '1');
INSERT INTO `device_log` VALUES ('39', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('40', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('41', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('42', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('43', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('44', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('45', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('46', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('47', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('48', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('49', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('50', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('51', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('52', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('53', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('54', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('55', '1', '2016-07-25', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('56', '1', '2016-07-29', '192.168.1.110', '5678', '1');
INSERT INTO `device_log` VALUES ('57', '1', '2016-08-05', '192.168.1.117', '5678', '1');
INSERT INTO `device_log` VALUES ('58', '1', '2016-08-05', '192.168.1.117', '5678', '1');
INSERT INTO `device_log` VALUES ('59', '1', '2016-08-05', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('60', '1', '2016-08-05', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('61', '1', '2016-08-05', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('62', '1', '2016-08-05', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('63', '1', '2016-08-05', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('64', '1', '2016-08-05', '192.168.1.109', '5678', '1');
INSERT INTO `device_log` VALUES ('65', '1', '2016-08-05', '192.168.1.109', '5678', '1');

-- ----------------------------
-- Table structure for `dev_alias`
-- ----------------------------
DROP TABLE IF EXISTS `dev_alias`;
CREATE TABLE `dev_alias` (
  `aliasid` int(11) NOT NULL AUTO_INCREMENT,
  `masterid` int(11) NOT NULL,
  `deviceid` int(11) NOT NULL,
  `alias` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`aliasid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of dev_alias
-- ----------------------------

-- ----------------------------
-- Table structure for `manager_inf`
-- ----------------------------
DROP TABLE IF EXISTS `manager_inf`;
CREATE TABLE `manager_inf` (
  `managerid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 NOT NULL,
  `number` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `state` char(2) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`managerid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of manager_inf
-- ----------------------------
INSERT INTO `manager_inf` VALUES ('1', 'lc', 'admin', 'admin', '15680755752', '1129147938@qq.com', '1');

-- ----------------------------
-- Table structure for `msg_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `msg_alarm`;
CREATE TABLE `msg_alarm` (
  `alarmid` int(11) NOT NULL AUTO_INCREMENT,
  `time` date NOT NULL,
  `deviceid` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `url` text CHARACTER SET utf8,
  `size` int(11) DEFAULT NULL,
  `md5` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`alarmid`),
  KEY `FK_devInf_msgAlarm` (`deviceid`),
  CONSTRAINT `FK_devInf_msgAlarm` FOREIGN KEY (`deviceid`) REFERENCES `device_inf` (`deviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of msg_alarm
-- ----------------------------

-- ----------------------------
-- Table structure for `msg_chat`
-- ----------------------------
DROP TABLE IF EXISTS `msg_chat`;
CREATE TABLE `msg_chat` (
  `chatid` int(11) NOT NULL,
  `sendid` int(11) NOT NULL,
  `recvid` int(11) NOT NULL,
  `sendtime` date NOT NULL,
  `content` text CHARACTER SET utf8 NOT NULL,
  `APPRECVID` int(11) DEFAULT NULL,
  `RECVTYPE` int(11) DEFAULT NULL,
  `DEVERCVID` int(11) DEFAULT NULL,
  PRIMARY KEY (`chatid`),
  KEY `FK_SuserInf_msgChat` (`sendid`),
  KEY `FK_RuserInf_msgChat` (`recvid`),
  CONSTRAINT `FK_RuserInf_msgChat` FOREIGN KEY (`recvid`) REFERENCES `user_inf` (`userid`),
  CONSTRAINT `FK_SuserInf_msgChat` FOREIGN KEY (`sendid`) REFERENCES `user_inf` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of msg_chat
-- ----------------------------

-- ----------------------------
-- Table structure for `msg_con`
-- ----------------------------
DROP TABLE IF EXISTS `msg_con`;
CREATE TABLE `msg_con` (
  `chatid` int(11) NOT NULL,
  `sendid` int(11) NOT NULL,
  `recvid` int(11) NOT NULL,
  `sendtime` date NOT NULL,
  `content` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`chatid`),
  KEY `FK_userInf_msgCon` (`sendid`),
  KEY `FK_devInf_msgCon` (`recvid`),
  CONSTRAINT `FK_devInf_msgCon` FOREIGN KEY (`recvid`) REFERENCES `device_inf` (`deviceid`),
  CONSTRAINT `FK_userInf_msgCon` FOREIGN KEY (`sendid`) REFERENCES `user_inf` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of msg_con
-- ----------------------------

-- ----------------------------
-- Table structure for `msg_notice`
-- ----------------------------
DROP TABLE IF EXISTS `msg_notice`;
CREATE TABLE `msg_notice` (
  `noticeid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 NOT NULL,
  `content` text CHARACTER SET utf8 NOT NULL,
  `sendid` int(11) NOT NULL,
  `sendtime` date NOT NULL,
  PRIMARY KEY (`noticeid`),
  KEY `FK_manaInf_msgNotice` (`sendid`),
  CONSTRAINT `FK_manaInf_msgNotice` FOREIGN KEY (`sendid`) REFERENCES `manager_inf` (`managerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of msg_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `msg_publish`
-- ----------------------------
DROP TABLE IF EXISTS `msg_publish`;
CREATE TABLE `msg_publish` (
  `mpid` int(11) NOT NULL AUTO_INCREMENT,
  `noticeid` int(11) NOT NULL,
  `apprecvid` int(11) DEFAULT NULL,
  `comprecvid` int(11) DEFAULT NULL,
  `devrecvid` int(11) DEFAULT NULL,
  `manrecvid` int(11) DEFAULT NULL,
  PRIMARY KEY (`mpid`),
  KEY `FK_msgNotice_msgPublish` (`noticeid`),
  KEY `FK_userInf_msgPublish` (`apprecvid`),
  KEY `FK_comUser_msgPublish` (`comprecvid`),
  KEY `FK_manaInf_msgPublish` (`manrecvid`),
  KEY `FK_devInf_msgPublish` (`devrecvid`),
  CONSTRAINT `FK_comUser_msgPublish` FOREIGN KEY (`comprecvid`) REFERENCES `company_user` (`userid`),
  CONSTRAINT `FK_devInf_msgPublish` FOREIGN KEY (`devrecvid`) REFERENCES `device_inf` (`deviceid`),
  CONSTRAINT `FK_manaInf_msgPublish` FOREIGN KEY (`manrecvid`) REFERENCES `manager_inf` (`managerid`),
  CONSTRAINT `FK_msgNotice_msgPublish` FOREIGN KEY (`noticeid`) REFERENCES `msg_notice` (`noticeid`),
  CONSTRAINT `FK_userInf_msgPublish` FOREIGN KEY (`apprecvid`) REFERENCES `user_inf` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of msg_publish
-- ----------------------------

-- ----------------------------
-- Table structure for `msg_recv`
-- ----------------------------
DROP TABLE IF EXISTS `msg_recv`;
CREATE TABLE `msg_recv` (
  `recvid` int(11) NOT NULL AUTO_INCREMENT,
  `messageid` int(11) NOT NULL,
  `senduserid` int(11) NOT NULL,
  `recvuserid` int(11) NOT NULL,
  `msgtype` char(10) CHARACTER SET utf8 NOT NULL,
  `state` int(2) NOT NULL,
  `recvtime` date NOT NULL,
  PRIMARY KEY (`recvid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of msg_recv
-- ----------------------------

-- ----------------------------
-- Table structure for `relate_inf`
-- ----------------------------
DROP TABLE IF EXISTS `relate_inf`;
CREATE TABLE `relate_inf` (
  `relateid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `deviceid` int(11) NOT NULL,
  `settime` date DEFAULT NULL,
  `authority` char(10) CHARACTER SET utf8 NOT NULL,
  `aliasid` int(11) DEFAULT NULL,
  PRIMARY KEY (`relateid`),
  KEY `FK_userInf_relaInf` (`userid`),
  KEY `FK_devInf_relaInf` (`deviceid`),
  KEY `FK_aliaApp_relaInf` (`aliasid`),
  CONSTRAINT `FK_aliaApp_relaInf` FOREIGN KEY (`aliasid`) REFERENCES `app_alias` (`aliasid`),
  CONSTRAINT `FK_devInf_relaInf` FOREIGN KEY (`deviceid`) REFERENCES `device_inf` (`deviceid`),
  CONSTRAINT `FK_userInf_relaInf` FOREIGN KEY (`userid`) REFERENCES `user_inf` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of relate_inf
-- ----------------------------
INSERT INTO `relate_inf` VALUES ('1', '1', '1', '2016-08-02', '1', '1');
INSERT INTO `relate_inf` VALUES ('3', '2', '1', '2016-08-02', '1', '1');
INSERT INTO `relate_inf` VALUES ('4', '3', '1', '2016-08-02', '1', '1');

-- ----------------------------
-- Table structure for `relate_wait`
-- ----------------------------
DROP TABLE IF EXISTS `relate_wait`;
CREATE TABLE `relate_wait` (
  `rwid` int(11) NOT NULL AUTO_INCREMENT,
  `applyid` int(11) NOT NULL,
  `deviceid` int(11) NOT NULL,
  `askid` int(11) NOT NULL,
  `applytype` bit(1) NOT NULL,
  `applyright` char(10) CHARACTER SET utf8 NOT NULL,
  `applytime` date NOT NULL,
  `acktime` date DEFAULT NULL,
  `message` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`rwid`),
  KEY `FK_userInf_relaWait_apply` (`applyid`),
  KEY `FK_devInf_relaWait` (`deviceid`),
  KEY `FK_userInf_relaWait_ask` (`askid`),
  CONSTRAINT `FK_devInf_relaWait` FOREIGN KEY (`deviceid`) REFERENCES `device_inf` (`deviceid`),
  CONSTRAINT `FK_userInf_relaWait_apply` FOREIGN KEY (`applyid`) REFERENCES `user_inf` (`userid`),
  CONSTRAINT `FK_userInf_relaWait_ask` FOREIGN KEY (`askid`) REFERENCES `user_inf` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of relate_wait
-- ----------------------------

-- ----------------------------
-- Table structure for `server_inf`
-- ----------------------------
DROP TABLE IF EXISTS `server_inf`;
CREATE TABLE `server_inf` (
  `serverid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(3) NOT NULL,
  `demo` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `memory` int(11) DEFAULT NULL,
  `CPU` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `ip` tinyint(4) NOT NULL COMMENT '用4个字段来分开存储ip地址，可读性稍差（分别为192， 168， 120， 65），存储空间占用少',
  `port` int(2) NOT NULL,
  `longitude` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `latitude` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  PRIMARY KEY (`serverid`),
  KEY `FK_manaInf_servInf` (`manager`),
  CONSTRAINT `FK_manaInf_servInf` FOREIGN KEY (`manager`) REFERENCES `manager_inf` (`managerid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of server_inf
-- ----------------------------
INSERT INTO `server_inf` VALUES ('1', '1', '服务器1', '1024', 'i7 5960', '127', '5678', '30', '30', '1');

-- ----------------------------
-- Table structure for `soft_download`
-- ----------------------------
DROP TABLE IF EXISTS `soft_download`;
CREATE TABLE `soft_download` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial` varchar(30) CHARACTER SET utf8 NOT NULL,
  `deviceid` int(11) DEFAULT NULL,
  `appuserid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of soft_download
-- ----------------------------

-- ----------------------------
-- Table structure for `soft_inf`
-- ----------------------------
DROP TABLE IF EXISTS `soft_inf`;
CREATE TABLE `soft_inf` (
  `softid` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(3) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `version` varchar(10) CHARACTER SET utf8 NOT NULL,
  `serial` varchar(30) CHARACTER SET utf8 NOT NULL,
  `date` date DEFAULT NULL,
  `uploadid` int(11) DEFAULT NULL,
  `md5` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `url` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`softid`),
  KEY `FK_comUser_softInf` (`uploadid`),
  KEY `serial` (`serial`),
  CONSTRAINT `FK_comUser_softInf` FOREIGN KEY (`uploadid`) REFERENCES `company_user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of soft_inf
-- ----------------------------

-- ----------------------------
-- Table structure for `soft_publish`
-- ----------------------------
DROP TABLE IF EXISTS `soft_publish`;
CREATE TABLE `soft_publish` (
  `id` int(11) NOT NULL,
  `serial` varchar(30) CHARACTER SET utf8 NOT NULL,
  `begindate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `state` char(2) CHARACTER SET utf8 DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of soft_publish
-- ----------------------------

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `typeid` int(11) NOT NULL AUTO_INCREMENT,
  `typeno` char(1) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of type
-- ----------------------------

-- ----------------------------
-- Table structure for `user_inf`
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 NOT NULL,
  `set` char(10) CHARACTER SET utf8 NOT NULL,
  `regdate` date NOT NULL,
  `authority` char(10) CHARACTER SET utf8 NOT NULL,
  `birth` date DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `sex` bit(1) DEFAULT b'1',
  `type` char(2) CHARACTER SET utf8 DEFAULT NULL,
  `state` char(2) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `mail` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `mphone` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES ('1', 'user', '12345678', '1', '2016-06-07', '1', '2016-06-01', 'lc', '', '1', '1', null, null);
INSERT INTO `user_inf` VALUES ('2', 'liuchao', '123456', '1', '2016-08-02', '1', '2016-08-02', 'lc', '', '1', '1', null, null);
INSERT INTO `user_inf` VALUES ('3', 'hzq', '1234567', '1', '2016-08-02', '1', '2016-08-02', 'hzq', '', '1', '1', null, null);

-- ----------------------------
-- Table structure for `user_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log` (
  `logid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `ipaddr` varchar(15) NOT NULL,
  `port` int(2) NOT NULL,
  `operate` int(1) NOT NULL,
  PRIMARY KEY (`logid`),
  KEY `FK_userInf_userLog` (`userid`),
  CONSTRAINT `FK_userInf_userLog` FOREIGN KEY (`userid`) REFERENCES `user_inf` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('1', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `user_log` VALUES ('2', '1', '2016-07-15', '192.168.1.108', '5678', '1');
INSERT INTO `user_log` VALUES ('3', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('4', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('5', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('6', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('7', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('8', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('9', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('10', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('11', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('12', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('13', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('14', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('15', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('16', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('17', '1', '2016-07-25', '192.168.1.110', '5678', '1');
INSERT INTO `user_log` VALUES ('18', '1', '2016-07-25', '192.168.1.110', '5678', '1');
