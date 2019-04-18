-- MySQL dump 10.13  Distrib 5.6.35, for Win64 (x86_64)
--
-- Host: 192.168.8.128    Database: game_dev
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
-- Table structure for table `oldusermap`
--

DROP TABLE IF EXISTS `oldusermap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oldusermap` (
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `userId` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `productId` bigint(16) NOT NULL DEFAULT '0' COMMENT '产品id',
  `accountName` varchar(64) DEFAULT NULL COMMENT '账号，由微信appid+微信openid组成',
  `nickName` varchar(128) DEFAULT '用户' COMMENT '昵称',
  `imgId` varchar(255) DEFAULT NULL COMMENT '头像',
  `imgMD5` varchar(32) DEFAULT NULL,
  `privateRoomDiamond` bigint(16) DEFAULT '0' COMMENT '钻石数',
  `paper` bigint(16) DEFAULT '0' COMMENT '兑换券数量',
  `preferredCity` int(11) DEFAULT '0' COMMENT '选择地区（废弃）',
  `wxUnionID` varchar(255) DEFAULT NULL COMMENT '微信unionID',
  `UDID` varchar(128) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '账号创建时间',
  `repeatedNum` int(8) DEFAULT NULL COMMENT '昵称头像相同的数量',
  `repeatedUnionIdNum` int(8) DEFAULT NULL COMMENT '微信unionID相同的数量',
  `isCharged` tinyint(4) DEFAULT NULL COMMENT '是否已充值',
  `clubId` bigint(16) DEFAULT NULL COMMENT '俱乐部id',
  `newUserId` bigint(16) DEFAULT NULL COMMENT '新服的userId',
  `newAccountName` varchar(64) DEFAULT NULL COMMENT '新服的账号',
  `oldUserId` bigint(16) DEFAULT NULL COMMENT '预分配的新服的userId',
  PRIMARY KEY (`userId`,`productId`) USING BTREE,
  KEY `uid` (`wxUnionID`) USING BTREE,
  KEY `account` (`accountName`) USING BTREE,
  KEY `head` (`nickName`,`imgMD5`) USING BTREE,
  KEY `udid` (`UDID`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `s_activity`
--

DROP TABLE IF EXISTS `s_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityId` int(11) NOT NULL DEFAULT '0' COMMENT 'dict_activity表的id',
  `status` int(2) NOT NULL DEFAULT '2' COMMENT '活动状态（1：开放 2：关闭）',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='具体游戏服要运行的公共库dict_activity表的活动列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `s_exp_level`
--

DROP TABLE IF EXISTS `s_exp_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_exp_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(11) NOT NULL DEFAULT '0',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `exp` int(11) DEFAULT NULL COMMENT '升到该级所需经验',
  `title` varchar(12) DEFAULT NULL COMMENT '称号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `s_mails`
--

DROP TABLE IF EXISTS `s_mails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_mails` (
  `sysMailId` bigint(11) NOT NULL AUTO_INCREMENT,
  `gameId` int(11) DEFAULT NULL COMMENT '游戏ID，-1 为不指定',
  `title` varchar(32) DEFAULT NULL,
  `content` varchar(4096) DEFAULT NULL,
  `reward` varchar(256) DEFAULT NULL COMMENT '奖励字符串',
  `filterValue` varchar(256) DEFAULT NULL COMMENT '过滤类型，比如：{lv:[2,5], num:0, userId:[1103204,22], city:[1,2]}或者{userId:1103204}，可一个或多个，有数组结构的可以只写一个数字',
  `startTime` datetime DEFAULT NULL COMMENT '上线时间',
  `endTime` datetime DEFAULT NULL COMMENT '结束时间',
  `functionId` int(11) DEFAULT NULL COMMENT 'native功能跳转ID，目前预留',
  `startRegisterTime` datetime DEFAULT NULL COMMENT '开始注册时间',
  `endRegisterTime` datetime DEFAULT NULL COMMENT '结束注册时间',
  PRIMARY KEY (`sysMailId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `s_privateroom_info`
--

DROP TABLE IF EXISTS `s_privateroom_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_privateroom_info` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `roomId` int(36) DEFAULT NULL COMMENT '房间Id',
  `gameId` int(36) DEFAULT NULL COMMENT '游戏Id',
  `password` int(36) DEFAULT NULL COMMENT '房间邀请码',
  `initScore` int(255) DEFAULT NULL COMMENT '初始积分',
  `playerSum` int(36) DEFAULT NULL COMMENT '游戏人数',
  `roomFeeType` int(36) DEFAULT NULL COMMENT '房费类型',
  `roundSum` int(11) DEFAULT NULL COMMENT '局数',
  `roomFeeSum` int(36) DEFAULT NULL COMMENT '开房费用数量',
  `difen` bigint(255) DEFAULT NULL COMMENT '底分',
  `fengding` bigint(255) DEFAULT NULL COMMENT '封顶',
  `wanfa` varchar(2408) DEFAULT NULL COMMENT '玩法',
  `wanfahuchi` varchar(1024) DEFAULT NULL COMMENT '玩法互斥',
  `shareTitle` varchar(1024) DEFAULT NULL COMMENT '分享标题',
  `shareDesc` varchar(1024) DEFAULT NULL COMMENT '分享描述',
  `shareLink` varchar(1024) DEFAULT NULL COMMENT '分享链接',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `playerInfo` varchar(1024) DEFAULT NULL COMMENT '玩家信息',
  `nowRound` int(36) DEFAULT NULL COMMENT '当前的局数',
  `expiredTime` int(36) DEFAULT NULL COMMENT '房间关闭时长',
  `ownerId` bigint(16) DEFAULT NULL COMMENT '房主id',
  `jiadi` bigint(255) DEFAULT NULL COMMENT '加底',
  `isVaild` varchar(16) DEFAULT NULL,
  `isNeedRoomFee` varchar(16) DEFAULT NULL,
  `fanma` int(10) DEFAULT NULL,
  `hhs` tinyint(3) DEFAULT NULL,
  `lhs` tinyint(3) DEFAULT NULL,
  `roomJieSuanType` int(11) DEFAULT '1' COMMENT '房间结算类型 （1:房主付费，2：大赢家付费 ， 3：AA付费）',
  `extendColumn1` varchar(512) DEFAULT NULL,
  `extendColumn2` varchar(128) DEFAULT NULL,
  `extendColumn3` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=592 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `s_quest`
--

DROP TABLE IF EXISTS `s_quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_quest` (
  `Id` int(11) NOT NULL COMMENT '任务ID',
  `type` int(4) DEFAULT NULL COMMENT '任务接取类型（1-不循环  2 每日循环 3次数循环 4每日次数循环5单日内链式）',
  `questName` varchar(32) DEFAULT NULL COMMENT '任务名称',
  `questType` smallint(4) DEFAULT NULL COMMENT '任务类型（16 类参加代码详细）',
  `preQuestId` int(11) DEFAULT NULL COMMENT '前置任务id（非必要 = 0）',
  `repeatSum` int(11) DEFAULT NULL COMMENT '循环数量（针对循环任务有效）',
  `condition1` int(11) DEFAULT NULL,
  `condition2` int(11) DEFAULT NULL,
  `condition3` int(11) DEFAULT NULL,
  `awardAutoSend` tinyint(1) DEFAULT NULL COMMENT '是否实时发放',
  `awardType` tinyint(2) DEFAULT NULL COMMENT 'type1=全部发放，2=随机一件，3=自选一件',
  `awardList` varchar(128) DEFAULT NULL COMMENT '任务奖励（1：sum|2：sum 的格式）10008=砖石，10009=元宝。空字符串，没有奖励。',
  `desc` varchar(32) DEFAULT NULL COMMENT '任务描述',
  `iconImg` bigint(16) DEFAULT NULL COMMENT '图标id',
  `recommend` tinyint(2) DEFAULT NULL COMMENT '推荐类型（1 圈红闪烁  2 新手福利  3成长福利）',
  `showQustList` tinyint(1) DEFAULT NULL COMMENT '是否显示任务链',
  `distCode` varchar(128) DEFAULT NULL COMMENT '推广码',
  `acceptType` int(4) DEFAULT NULL COMMENT '任务接取类型(1-注册接取  2 等级  3 链式接取 7 日常链式)',
  `acceptValue` int(11) DEFAULT NULL COMMENT '任务接取条件值',
  `shareTag` tinyint(1) DEFAULT NULL COMMENT '分享标签，1-允许分享，0-不允许分享',
  `shareAward` varchar(64) DEFAULT NULL COMMENT '分享奖励（1：sum|2：sum 的格式）',
  `shareTitle` varchar(16) DEFAULT NULL COMMENT '分享标题',
  `shareDesc` varchar(32) DEFAULT NULL COMMENT '分享描述',
  `shareLink` varchar(128) DEFAULT NULL COMMENT '分享链接',
  `order` int(10) DEFAULT NULL COMMENT '显示排序',
  `citycode` int(11) DEFAULT NULL COMMENT '针对城市显示的任务，填城市编码，全部显示不填',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `s_room_info`
--

DROP TABLE IF EXISTS `s_room_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_room_info` (
  `Id` int(11) NOT NULL DEFAULT '0',
  `gameId` int(11) NOT NULL DEFAULT '0' COMMENT '游戏id',
  `name` varchar(16) DEFAULT NULL COMMENT '房间名',
  `roomTag` int(4) DEFAULT NULL COMMENT '房间标签（1 正式 2测试）',
  `awardType` int(4) DEFAULT NULL COMMENT '奖励类型',
  `awardValue` int(10) DEFAULT NULL COMMENT '奖励数',
  `thresholdMax` int(11) DEFAULT NULL COMMENT '最高门槛',
  `thresholdMin` int(11) DEFAULT NULL COMMENT '最低门槛',
  `playerSumMin` int(11) DEFAULT NULL COMMENT '最小玩家人数',
  `playerSum` int(11) DEFAULT '0' COMMENT '最大玩家人数',
  `antes` int(11) DEFAULT NULL COMMENT '注底',
  `type` int(11) DEFAULT NULL COMMENT '游戏玩法',
  `subType` smallint(4) DEFAULT NULL COMMENT '类型（具体定义参考具体游戏细节）',
  `playTimeOut` int(11) DEFAULT NULL COMMENT '操作超时',
  `actTimeOut` varchar(64) DEFAULT NULL COMMENT '操作超时设置（| 分隔）',
  `roomFeeType` int(4) DEFAULT '0' COMMENT '房费类型（填入物品ID，可以是金豆和元宝，开房卡）',
  `roomFee` int(11) DEFAULT NULL COMMENT '房费数量',
  `drawWater` int(11) DEFAULT NULL COMMENT '台费(开局扣）',
  `onlineSum` int(11) DEFAULT '100' COMMENT '在线人数 （模拟）',
  `ruleDesc` bigint(16) DEFAULT '0' COMMENT '规则描述（字符串ID）',
  `matchWaitTime` tinyint(2) DEFAULT NULL COMMENT '匹配时长（超时匹配机器人）',
  `breakDesk` tinyint(2) DEFAULT NULL COMMENT '是否每局强制拆桌（0 不拆 1 拆）',
  `startTime` datetime DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '结束时间',
  `propEnable` tinyint(2) DEFAULT '0' COMMENT '是否用道具（0 不能，1能）',
  `taskEnable` tinyint(2) DEFAULT NULL COMMENT '是否桌面任务（0不开始，1开启）',
  `ebEnable` tinyint(2) DEFAULT '0' COMMENT '是否开启公告（0无 ，1 有）',
  `emojiEnable` tinyint(2) DEFAULT NULL COMMENT '表情是否开启(0 不能，1能）',
  `tag` tinyint(2) DEFAULT NULL COMMENT '优先补助开关（1、开放 0、不开放）',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_activity_log`
--

DROP TABLE IF EXISTS `u_activity_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_activity_log` (
  `gameId` int(11) DEFAULT NULL COMMENT '游戏id',
  `userId` bigint(16) NOT NULL DEFAULT '0' COMMENT '用户id',
  `taskId` int(11) NOT NULL DEFAULT '0' COMMENT '活动任务id',
  `eventId` int(11) DEFAULT NULL COMMENT '事件id，同活动任务id对应的事件',
  `times` smallint(6) DEFAULT NULL COMMENT '已完成次数',
  `status` int(11) DEFAULT NULL COMMENT '完成状态记录',
  `data` varchar(45) DEFAULT NULL COMMENT '预留字段，比如完成进度记录',
  `isRewarded` tinyint(2) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`,`taskId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='活动系统，记录用户完成的活动事件的具体进度，包括总次数，单次的内部或者参数状态等。会不断查询修改，不算只用于记录统计的日志系统';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_combat_cains`
--

DROP TABLE IF EXISTS `u_combat_cains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_combat_cains` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL DEFAULT '0',
  `gameId` int(20) NOT NULL DEFAULT '0',
  `onDay` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  `grade` bigint(20) NOT NULL DEFAULT '0' COMMENT '战绩',
  `rank` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_complaint`
--

DROP TABLE IF EXISTS `u_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_complaint` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型标记',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `content` varchar(256) DEFAULT NULL COMMENT '内容',
  `submitDate` datetime DEFAULT NULL COMMENT '提交日期',
  `dealDate` datetime DEFAULT NULL COMMENT '处理日期',
  `result` smallint(5) DEFAULT NULL COMMENT '处理结果',
  `userId` bigint(16) DEFAULT NULL COMMENT '玩家Id',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_day_grade`
--

DROP TABLE IF EXISTS `u_day_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_day_grade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `gameId` int(11) DEFAULT NULL,
  `onDay` date DEFAULT NULL COMMENT '时间',
  `grade` bigint(20) DEFAULT NULL COMMENT '战绩',
  `rank` int(11) DEFAULT NULL COMMENT '名次',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_inviters`
--

DROP TABLE IF EXISTS `u_inviters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_inviters` (
  `userId` bigint(16) NOT NULL COMMENT '用户id',
  `inviterId` bigint(16) NOT NULL DEFAULT '0' COMMENT 'userId用户设置inviterId用户为邀请人',
  `gameId` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '一共有num人设置了userId为邀请人，等于userId在列inviterId中的总数',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='邀请好友系统设置邀请人';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_mails`
--

DROP TABLE IF EXISTS `u_mails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_mails` (
  `umailId` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '邮件id',
  `userId` bigint(11) NOT NULL DEFAULT '0' COMMENT '玩家id',
  `title` varchar(32) DEFAULT NULL,
  `content` varchar(4096) DEFAULT NULL,
  `operateStatus` tinyint(1) DEFAULT '-1' COMMENT '功能性邮件操作状态（-1 非功能性邮件,0 未操作，1 同意，2 不同意）',
  `readStatus` tinyint(1) DEFAULT '0' COMMENT '是否已读（0 未读，1 已读）',
  `pullStatus` tinyint(1) DEFAULT NULL COMMENT '是否领取附件',
  `sendTime` datetime DEFAULT NULL COMMENT '发送时间',
  `endTime` datetime DEFAULT NULL COMMENT '失效时间',
  `reward` varchar(256) DEFAULT NULL COMMENT '奖励字符串',
  `params` varchar(256) DEFAULT NULL COMMENT '业务扩展参数',
  PRIMARY KEY (`umailId`) USING BTREE,
  KEY `userId` (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_privateroom_template`
--

DROP TABLE IF EXISTS `u_privateroom_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_privateroom_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clubId` bigint(20) DEFAULT NULL COMMENT '俱乐部Id',
  `ownerId` bigint(16) DEFAULT NULL COMMENT '房主id',
  `gameId` int(36) DEFAULT NULL COMMENT '游戏Id',
  `playerSum` int(36) DEFAULT NULL COMMENT '游戏人数',
  `roundSum` int(11) DEFAULT NULL COMMENT '局数',
  `roomFeeSum` int(36) DEFAULT NULL COMMENT '开房费用数量',
  `fengding` bigint(255) DEFAULT NULL COMMENT '封顶',
  `wanfa` varchar(2408) DEFAULT NULL COMMENT '玩法',
  `expiredTime` int(36) DEFAULT NULL COMMENT '房间关闭时长',
  `jiadi` bigint(255) DEFAULT NULL COMMENT '加底',
  `fanma` int(10) DEFAULT NULL,
  `hhs` tinyint(3) DEFAULT NULL,
  `lhs` tinyint(3) DEFAULT NULL,
  `roomJieSuanType` int(11) DEFAULT '1' COMMENT '房间结算类型 （1:房主付费，2：大赢家付费 ， 3：AA付费）',
  `enabled` varchar(16) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `clubId` (`clubId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_quest`
--

DROP TABLE IF EXISTS `u_quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_quest` (
  `userId` bigint(16) NOT NULL,
  `questId` int(11) NOT NULL DEFAULT '0',
  `status` int(4) DEFAULT NULL COMMENT '任务状态（0 未完成 1 完成 2 已领取奖励）',
  `achive1` int(11) DEFAULT NULL,
  `achive2` int(11) DEFAULT NULL,
  `achive3` int(11) DEFAULT NULL,
  `repeateTimes` int(2) DEFAULT NULL COMMENT '已经循环接取次数',
  `endTime` date DEFAULT NULL,
  `finishTime` datetime DEFAULT NULL COMMENT '完成时间',
  `deadline` int(2) DEFAULT NULL COMMENT '兑换码期限',
  `phoneNo` varchar(20) DEFAULT NULL COMMENT '任务绑定的手机号码',
  `backup` varchar(20) DEFAULT NULL COMMENT '备用字段',
  `placeID` int(12) DEFAULT '0' COMMENT '这个新手红包是dict_welfare_cfg表中哪个placeID',
  PRIMARY KEY (`userId`,`questId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_smail`
--

DROP TABLE IF EXISTS `u_smail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_smail` (
  `userId` bigint(16) NOT NULL COMMENT '用户id',
  `smailId` bigint(11) NOT NULL DEFAULT '0' COMMENT '系统邮件id',
  `status` tinyint(11) DEFAULT NULL COMMENT '读取状态（1：已读，0：已删除）',
  `updateTime` datetime DEFAULT NULL COMMENT '最近操作时间',
  PRIMARY KEY (`userId`,`smailId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的系统邮件状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_user_ext`
--

DROP TABLE IF EXISTS `u_user_ext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_ext` (
  `userId` bigint(16) NOT NULL DEFAULT '0' COMMENT '玩家id',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `lastLogoutTime` datetime DEFAULT NULL COMMENT '上次登出时间',
  `winTimes` int(11) DEFAULT NULL COMMENT '获胜场次',
  `totalTimes` int(11) DEFAULT NULL COMMENT '总场次数',
  `winStreak` int(11) DEFAULT NULL COMMENT '连胜数',
  `maxWinningStreak` int(11) DEFAULT NULL COMMENT '最大连胜',
  `forbidden` tinyint(2) NOT NULL DEFAULT '-1' COMMENT '号禁(-1  未禁号，0~5 禁号原因)',
  `forbiddenTime` datetime DEFAULT NULL COMMENT '解禁日期',
  `lastAllowanceTime` datetime DEFAULT NULL COMMENT '一次上领取工资时间',
  `allowanceTimes` smallint(2) DEFAULT '0' COMMENT '每日领取过破产补助的次数',
  `lastMonthSignInRecord` int(12) DEFAULT '0' COMMENT '上月签到记录',
  `thisMonthSignInRecord` int(12) DEFAULT '0' COMMENT '本月签到记录',
  `lastSignInGift` int(12) DEFAULT NULL COMMENT '上月礼包',
  `thisSignInGift` int(12) DEFAULT NULL COMMENT '本月礼包',
  `fillCheckTimes` int(11) DEFAULT '0' COMMENT '当日补签次数',
  `activeness` int(11) DEFAULT NULL COMMENT '活跃度',
  `preferredCity` int(11) DEFAULT '0' COMMENT '首选gameid',
  `updateAreaTime` datetime DEFAULT NULL COMMENT '更新地区时间',
  `identificationCode` varchar(20) NOT NULL DEFAULT '' COMMENT '识别码',
  `activeAmount` int(11) DEFAULT '0' COMMENT '活跃天数',
  `consecutiveDaysAmout` int(11) DEFAULT '0' COMMENT '连续登录天数',
  `openedClubList` varchar(250) DEFAULT NULL COMMENT '最近使用的俱乐部排序',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_user_free`
--

DROP TABLE IF EXISTS `u_user_free`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_free` (
  `userId` bigint(16) NOT NULL,
  `json` varchar(2048) DEFAULT NULL COMMENT '没有固定用途，按需要设置内容，一般用于少部分用户的需要',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='没有固定用途，按需要设置内容，一般用于少部分用户的需要';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_user_info`
--

DROP TABLE IF EXISTS `u_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_info` (
  `userId` bigint(16) NOT NULL AUTO_INCREMENT,
  `guid` bigint(18) DEFAULT '0' COMMENT '账号系统绑定ID',
  `passwd` varchar(255) DEFAULT NULL,
  `bagPasswd` varchar(255) DEFAULT NULL,
  `accountName` varchar(64) DEFAULT NULL,
  `type` int(11) DEFAULT '1' COMMENT '类型（1=玩家,2=robot，3=系统人员）',
  `nickName` varchar(128) NOT NULL DEFAULT '用户',
  `imgId` varchar(256) DEFAULT NULL,
  `imgLegal` tinyint(2) DEFAULT '0' COMMENT '否是通过审核',
  `imgMD5` varchar(32) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL COMMENT '手机型号',
  `phoneNum` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮件地址',
  `weixin` varchar(64) DEFAULT NULL COMMENT '微信号',
  `compellation` varchar(32) DEFAULT NULL COMMENT '姓名',
  `IDcard` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `IDCardStatus` int(2) NOT NULL DEFAULT '0',
  `createTime` datetime DEFAULT NULL COMMENT '账号创建时间',
  `activeIP` varchar(255) DEFAULT NULL COMMENT '跃活ip',
  `IMEI` varchar(255) NOT NULL DEFAULT '' COMMENT '手机串号',
  `MACAdress` varchar(255) NOT NULL DEFAULT '' COMMENT '硬件地址',
  `spId` int(11) DEFAULT NULL COMMENT 'spId-- 测试：10000， 应用宝:10001, ios：10002',
  `spgId` int(11) DEFAULT NULL COMMENT 'spgId',
  `pub` varchar(128) DEFAULT NULL COMMENT '标识',
  `sex` tinyint(2) DEFAULT '0' COMMENT '性别',
  `status` tinyint(2) DEFAULT '0' COMMENT '用户状态 0 未激活 1 普通激活2 手机激活',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `levelMax` int(11) DEFAULT '1' COMMENT '级等',
  `VIP` smallint(2) DEFAULT '0' COMMENT 'VIP 等级',
  `VIPoverTime` datetime DEFAULT NULL COMMENT 'VIP 到期时间',
  `province` smallint(4) DEFAULT '0' COMMENT '省编号',
  `city` smallint(4) DEFAULT '0' COMMENT '城市编号',
  `ext1` varchar(128) DEFAULT NULL COMMENT '用户注册的方式，1wifi，2，移动网络',
  `ext2` varchar(128) DEFAULT NULL COMMENT '附加',
  `loginName` varchar(64) DEFAULT NULL,
  `os` int(2) DEFAULT NULL COMMENT '操作系统（1 = android 2=ios 3=mac 4= windows）',
  `everydayGrade` bigint(20) DEFAULT '0' COMMENT '每日赢/输钱数',
  `WXProvince` varchar(255) DEFAULT NULL,
  `WXCity` varchar(255) DEFAULT NULL,
  `WXCountry` varchar(255) DEFAULT NULL,
  `gameId` bigint(20) DEFAULT NULL COMMENT '所属游戏的id',
  `wxUnionID` varchar(255) DEFAULT NULL COMMENT '微信unionid',
  `oldUserBinding` tinyint(2) DEFAULT '0' COMMENT '性别',
  `UDID` varchar(128) DEFAULT NULL,
  `miID` varchar(128) DEFAULT NULL COMMENT '小米推送服务唯一id',
  `identificationCode` varchar(20) NOT NULL DEFAULT '' COMMENT '识别码',
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE KEY `accountName_UNIQUE` (`accountName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4299978033 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_user_orders`
--

DROP TABLE IF EXISTS `u_user_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_orders` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `city` varchar(11) NOT NULL COMMENT '城市的区号',
  `userId` bigint(16) NOT NULL COMMENT '用户id',
  `orderId` bigint(18) NOT NULL COMMENT '订单编号',
  `phoneNo` varchar(11) NOT NULL COMMENT '电话号码',
  `amount` int(4) NOT NULL COMMENT '红包金额，单位RMB',
  `exchangeCode` varchar(20) NOT NULL COMMENT '兑换码',
  `validity` int(4) NOT NULL COMMENT '兑换码有效期，单位天',
  `createdTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(4) DEFAULT '0' COMMENT '状态0已生成1已过期2已发送3已退回4已领取',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_phone_no` (`phoneNo`) USING BTREE,
  KEY `idx_orderId` (`orderId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_user_point`
--

DROP TABLE IF EXISTS `u_user_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_point` (
  `userId` bigint(16) NOT NULL COMMENT '玩家id',
  `cash` bigint(16) NOT NULL DEFAULT '0' COMMENT '背包账户金币',
  `takenCash` bigint(16) DEFAULT '0' COMMENT '带入金币',
  `lockCash` bigint(16) DEFAULT '0' COMMENT '锁定金币',
  `freeCash` bigint(16) DEFAULT '0' COMMENT '赠送金币(只做统计)',
  `rechargeTimes` int(11) DEFAULT NULL COMMENT '充值次数',
  `rechargeTotal` bigint(16) DEFAULT NULL COMMENT '值充总金额',
  `point` bigint(16) DEFAULT '0' COMMENT '元宝',
  `freePoint` bigint(16) DEFAULT '0' COMMENT '赠送元宝(仅作统计)',
  `prepaidCard` bigint(16) DEFAULT '0' COMMENT '充值卡话费（元）',
  `gachaCard` bigint(16) DEFAULT '0' COMMENT '抽奖卡（张）',
  `matchEnterCard` bigint(16) DEFAULT '0' COMMENT '比赛参赛卡（张）',
  `createTime` datetime DEFAULT NULL COMMENT ' 创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `firstChargeTime` datetime DEFAULT NULL COMMENT '第一次充值的时间',
  `inviteRoomCard` bigint(16) DEFAULT NULL COMMENT '开房卡（张）',
  `cashGiftCard` bigint(16) DEFAULT '0' COMMENT '现金卡（红包，单位元）',
  `privateRoomDiamond` bigint(16) DEFAULT '0',
  `privateRoomGameId` bigint(16) DEFAULT '0',
  `paper` bigint(16) DEFAULT '0' COMMENT '兑换券，商城系统用',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_user_record`
--

DROP TABLE IF EXISTS `u_user_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_record` (
  `userId` bigint(12) NOT NULL,
  `gameId` int(11) NOT NULL DEFAULT '0' COMMENT '游戏id',
  `totalTimes` int(11) DEFAULT NULL,
  `winTimes` int(11) DEFAULT NULL,
  `exp` bigint(16) DEFAULT NULL COMMENT '经验值',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `brokeOtherTimes` int(11) DEFAULT NULL COMMENT '使对手破产次数',
  `beBrokenTimes` int(11) DEFAULT NULL COMMENT '被对手破产次数',
  `winStreak` int(11) DEFAULT NULL COMMENT '连胜数',
  `maxWinStreak` int(11) DEFAULT NULL COMMENT '最大连胜数',
  `maxWinCash` int(11) DEFAULT NULL COMMENT '最大赢钱数',
  `maxDouble` int(11) DEFAULT NULL COMMENT '最大番数（倍数）',
  `maxPattern` varchar(256) DEFAULT NULL COMMENT '最大胡牌牌型',
  `maxDataTimer` datetime DEFAULT NULL COMMENT '最大牌型发生的时间',
  `lasterGameTimer` datetime DEFAULT NULL COMMENT '最后玩游戏时间',
  `roomAmount` int(11) DEFAULT '0' COMMENT '开房次数',
  `playAmount` int(11) DEFAULT '0' COMMENT '游戏局数',
  `activeAmount` int(11) DEFAULT '0' COMMENT '活跃天数',
  `consecutiveDaysAmout` int(11) DEFAULT '0' COMMENT '连续登录天数',
  `lasterLoginDateTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`userId`,`gameId`) USING BTREE
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

-- Dump completed on 2019-03-30 11:37:57
