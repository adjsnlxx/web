-- MySQL dump 10.13  Distrib 5.6.35, for Win64 (x86_64)
--
-- Host: 192.168.8.128    Database: dsqp_dict
-- ------------------------------------------------------
-- Server version	5.6.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dict_activity`
--

DROP TABLE IF EXISTS `dict_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(63) NOT NULL DEFAULT '' COMMENT '活动大众预留字段',
  `gameId` int(11) NOT NULL DEFAULT '0' COMMENT '游戏名称对应的id',
  `title` varchar(63) NOT NULL DEFAULT '' COMMENT '活动标题',
  `ui` int(11) NOT NULL DEFAULT '0' COMMENT '界面排序',
  `contentType` int(11) NOT NULL DEFAULT '1' COMMENT '内容类型(1文字内容，2图片内容)',
  `content` varchar(2048) NOT NULL DEFAULT '' COMMENT '活动内容(内容类型为1时保存文本，为2保存图片url)',
  `url` varchar(63) NOT NULL DEFAULT '' COMMENT '图片链接',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '系统类型(100活动,200邀请好友)',
  `share` varchar(512) DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '活动状态(1开放0关闭)',
  `startTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动开始时间',
  `endTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动结束时间',
  `vanishTime` datetime DEFAULT NULL,
  `flag` int(11) DEFAULT '0' COMMENT '角标(0： 没有  ；1:新   2：热)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8 COMMENT='活动表，type按大系统分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_app_config`
--

DROP TABLE IF EXISTS `dict_app_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_app_config` (
  `firstID` int(11) NOT NULL,
  `appID` varchar(128) NOT NULL,
  `upVersion` varchar(10) DEFAULT NULL COMMENT '客户端更新version',
  `version` varchar(10) DEFAULT NULL,
  `reviewVersion` varchar(10) DEFAULT NULL,
  `mikey` varchar(256) DEFAULT NULL COMMENT '小米推送key',
  `status` int(1) DEFAULT '0' COMMENT '1省包,0子包',
  PRIMARY KEY (`firstID`,`appID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_config`
--

DROP TABLE IF EXISTS `dict_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_config` (
  `productId` int(11) NOT NULL,
  `sKey` varchar(64) NOT NULL,
  `sValue` varchar(512) DEFAULT NULL,
  `sDesc` varchar(256) DEFAULT NULL,
  `isValid` int(1) DEFAULT '1',
  PRIMARY KEY (`productId`,`sKey`),
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_event`
--

DROP TABLE IF EXISTS `dict_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '事件名',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '事件大类型',
  `type2` int(11) NOT NULL DEFAULT '0' COMMENT '级别',
  `remark` varchar(32) NOT NULL DEFAULT '' COMMENT '备注扩展字段',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='活动事件表，定义要完成的一个具体行为';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_game_config`
--

DROP TABLE IF EXISTS `dict_game_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_game_config` (
  `gameId` int(11) NOT NULL DEFAULT '0' COMMENT '游戏id',
  `gameName` varchar(32) DEFAULT NULL COMMENT '前端显示名',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述，用于注释',
  `type` int(11) DEFAULT NULL COMMENT '玩法类型',
  `playType` int(11) DEFAULT NULL COMMENT '玩法',
  `version` varchar(10) DEFAULT NULL COMMENT '作废',
  `reviewVersion` varchar(10) DEFAULT NULL COMMENT '作废',
  `initScore` int(11) DEFAULT '0' COMMENT '初始分数',
  `roomFeeType` int(11) DEFAULT '0',
  `expiredTime` int(11) DEFAULT '0' COMMENT '房间过期时间',
  `playerSum` smallint(6) DEFAULT '0' COMMENT '人数',
  `roundSum` varchar(15) DEFAULT NULL COMMENT '局数',
  `isNeedRoomFee` varchar(1) DEFAULT NULL,
  `roomFeeSum` varchar(2048) DEFAULT NULL COMMENT '旧版房费',
  `roomFee` varchar(256) DEFAULT NULL COMMENT '新版json房费',
  `difen` tinyint(4) DEFAULT '0',
  `wanfa` varchar(1024) DEFAULT NULL COMMENT '玩法规则字符串',
  `fengding` varchar(15) DEFAULT NULL,
  `jiadi` varchar(64) DEFAULT NULL,
  `wanfahuchi` varchar(64) DEFAULT NULL,
  `fanma` varchar(32) DEFAULT NULL,
  `roomShare` varchar(1024) DEFAULT NULL COMMENT '房间分享内容',
  `huahuasheng` varchar(128) DEFAULT NULL,
  `lunhuasheng` varchar(128) DEFAULT NULL,
  `voteWaitTime` int(11) DEFAULT '60' COMMENT '解散倒计时',
  `voteCdSecond` int(11) DEFAULT '60' COMMENT '解散冷却时间',
  `extendColumn1` varchar(512) DEFAULT NULL COMMENT '备用字段',
  `extendColumn2` varchar(128) DEFAULT NULL COMMENT '备用字段',
  `extendColumn3` varchar(128) DEFAULT NULL COMMENT '备用字段',
  `className` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_game_database`
--

DROP TABLE IF EXISTS `dict_game_database`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_game_database` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(11) NOT NULL,
  `platformId` int(11) NOT NULL,
  `databaseName` varchar(64) NOT NULL,
  `gamePointSyncUrl` varchar(128) NOT NULL,
  `gameConfigUrl` varchar(128) NOT NULL,
  `gameName` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IX_gameId` (`gameId`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_mobile`
--

DROP TABLE IF EXISTS `dict_mobile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_mobile` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` char(9) NOT NULL DEFAULT '' COMMENT '手机号码段',
  `province` char(10) NOT NULL DEFAULT '' COMMENT '省份',
  `provinceCode` varchar(10) DEFAULT NULL COMMENT '省份编码',
  `city` char(10) NOT NULL DEFAULT '' COMMENT '市',
  `service_provider` char(10) DEFAULT '' COMMENT '运营商',
  `city_code` char(9) NOT NULL DEFAULT '' COMMENT '区号',
  `postcode` char(9) NOT NULL DEFAULT '' COMMENT '邮编',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  KEY `city_code` (`city_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全国手机号码段归属地';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_place`
--

DROP TABLE IF EXISTS `dict_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_place` (
  `placeID` int(11) NOT NULL COMMENT '地方ID',
  `firstID` int(11) DEFAULT NULL COMMENT '用于标识唯一游戏的ID',
  `placeName` varchar(255) DEFAULT NULL COMMENT '地方名',
  `placeLevel` int(11) DEFAULT NULL COMMENT '地方级别',
  `parentPlaceID` int(11) DEFAULT NULL COMMENT '父地方ID',
  `ui` int(11) DEFAULT NULL COMMENT '0为不显示，非0为显示',
  PRIMARY KEY (`placeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_place_config`
--

DROP TABLE IF EXISTS `dict_place_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_place_config` (
  `placeID` int(11) NOT NULL,
  `pyjRechargeTips` varchar(512) DEFAULT NULL COMMENT '充值提示',
  `pyjAdvertisementURLs` varchar(1024) DEFAULT NULL COMMENT '广告',
  `recruitSetting` varchar(512) DEFAULT NULL COMMENT '招募配置',
  `popupAdURLs` varchar(512) NOT NULL COMMENT '弹出广告',
  `popupAdEnable` int(1) NOT NULL DEFAULT '0' COMMENT '弹出广告开关',
  `marqueenTipMap` varchar(512) DEFAULT NULL COMMENT '跑马灯',
  `dailyShare` varchar(1024) DEFAULT NULL COMMENT '每日分享',
  `inviteShare` varchar(1024) DEFAULT NULL COMMENT '邀请好友分享',
  `clubShare` varchar(1024) DEFAULT NULL COMMENT '俱乐部分享',
  `awardList` varchar(128) DEFAULT '' COMMENT '任务奖励（1：sum|2：sum 的格式）',
  PRIMARY KEY (`placeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_place_convert`
--

DROP TABLE IF EXISTS `dict_place_convert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_place_convert` (
  `firstID` int(11) NOT NULL,
  `oldPlaceID` int(11) NOT NULL DEFAULT '0',
  `newPlaceID` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_place_game`
--

DROP TABLE IF EXISTS `dict_place_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_place_game` (
  `placeID` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(11) NOT NULL,
  `gameName` varchar(32) DEFAULT NULL COMMENT '前端显示名',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述，用于注释',
  `type` int(11) DEFAULT NULL COMMENT '玩法类型',
  `playType` int(11) DEFAULT NULL COMMENT '玩法',
  `initScore` int(11) DEFAULT '0',
  `roomFeeType` int(11) DEFAULT '0',
  `expiredTime` int(11) DEFAULT '0',
  `playerSum` smallint(6) DEFAULT '0',
  `roundSum` varchar(15) DEFAULT NULL,
  `isNeedRoomFee` varchar(1) DEFAULT NULL,
  `roomFeeSum` varchar(128) DEFAULT NULL,
  `roomFee` varchar(1024) DEFAULT NULL,
  `difen` tinyint(4) DEFAULT '0',
  `wanfa` varchar(1024) DEFAULT NULL,
  `fengding` varchar(15) DEFAULT NULL,
  `jiadi` varchar(64) DEFAULT NULL,
  `wanfahuchi` varchar(64) DEFAULT NULL,
  `fanma` varchar(32) DEFAULT NULL,
  `roomShare` varchar(1024) DEFAULT NULL,
  `huahuasheng` varchar(128) DEFAULT NULL,
  `lunhuasheng` varchar(128) DEFAULT NULL,
  `voteWaitTime` int(11) DEFAULT '60',
  `voteCdSecond` int(11) DEFAULT '60',
  `extendColumn1` varchar(512) DEFAULT NULL,
  `extendColumn2` varchar(128) DEFAULT NULL,
  `extendColumn3` varchar(128) DEFAULT NULL,
  `isClose` tinyint(1) DEFAULT '1' COMMENT '玩法开关',
  PRIMARY KEY (`placeID`,`gameId`)
) ENGINE=InnoDB AUTO_INCREMENT=88880131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_place_version`
--

DROP TABLE IF EXISTS `dict_place_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_place_version` (
  `firstID` int(11) NOT NULL,
  `version` varchar(10) DEFAULT NULL,
  `reviewVersion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`firstID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_task`
--

DROP TABLE IF EXISTS `dict_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityId` int(11) NOT NULL DEFAULT '0' COMMENT '活动id',
  `eventId` int(11) NOT NULL DEFAULT '0' COMMENT '事件id',
  `eventNum` int(11) NOT NULL DEFAULT '0' COMMENT '数量',
  `reward` varchar(63) NOT NULL DEFAULT '' COMMENT '奖励，格式为：{类型:数量,类型:数量}，比如：{1,100}，表示类型1的数量100',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '奖励状态(1.启用，2.禁用)',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '名称',
  `ui` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=325 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_welfare_cfg`
--

DROP TABLE IF EXISTS `dict_welfare_cfg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_welfare_cfg` (
  `placeId` int(11) NOT NULL DEFAULT '0' COMMENT '城市ID，对应dict_place表',
  `productId` int(11) NOT NULL DEFAULT '0' COMMENT '产品Id',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '活动状态，0关闭1开启',
  `startTime` datetime DEFAULT NULL COMMENT '开始时间，为空时表示无限制',
  `endTime` datetime DEFAULT NULL COMMENT '结束时间，为空时表示无限制',
  `target` varchar(255) NOT NULL DEFAULT 'all' COMMENT '目标玩法,all全部,或者：10016_10018_xx',
  `totalSet` int(11) DEFAULT '0' COMMENT '目标总局数',
  `cityName` varchar(11) DEFAULT NULL COMMENT '领取地区名称',
  `cityCode` varchar(11) DEFAULT NULL COMMENT '领取地区,0时表示全部，否则则时对应城市的区号，或者对应省的编号',
  `title` varchar(128) NOT NULL COMMENT '标题',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `exchange` varchar(32) NOT NULL COMMENT '兑换公众号',
  `amount` int(4) NOT NULL COMMENT '红包金额，单位RMB',
  `validity` int(4) NOT NULL COMMENT '兑换码有效期，单位天',
  PRIMARY KEY (`placeId`),
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-30 11:37:55
