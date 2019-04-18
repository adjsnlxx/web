-- MySQL dump 10.13  Distrib 5.6.35, for Win64 (x86_64)
--
-- Host: 192.168.8.128    Database: game_log_dev
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
-- Table structure for table `u_cash_diff_log`
--

DROP TABLE IF EXISTS `u_cash_diff_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_cash_diff_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `way` int(11) DEFAULT NULL COMMENT '途径',
  `userId` bigint(16) DEFAULT NULL COMMENT '用户的id',
  `cashDiff` bigint(20) DEFAULT NULL COMMENT '金豆的变化',
  `roomId` bigint(20) DEFAULT NULL COMMENT '房间id',
  `cashBefore` bigint(20) DEFAULT NULL COMMENT '开始金豆',
  `cashAfter` bigint(20) DEFAULT NULL COMMENT '变化之后的金豆',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `type` int(11) DEFAULT NULL COMMENT '1.金豆，2.元宝',
  `gameId` bigint(20) DEFAULT NULL COMMENT '游戏id',
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_onlinenumber_log`
--

DROP TABLE IF EXISTS `u_onlinenumber_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_onlinenumber_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `onlineNumber` bigint(20) DEFAULT NULL,
  `gameId` bigint(255) DEFAULT NULL COMMENT '游戏id，10007徐州，10008常州',
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33450 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_pay_apple_order_log`
--

DROP TABLE IF EXISTS `u_pay_apple_order_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_pay_apple_order_log` (
  `orderId` varchar(36) NOT NULL COMMENT '订单号',
  `userId` bigint(16) DEFAULT NULL COMMENT '玩家Id',
  `storeId` int(11) DEFAULT NULL COMMENT '商品Id',
  `bodyString` varchar(10000) DEFAULT NULL COMMENT '苹果支付后的验证body',
  `createDate` datetime DEFAULT NULL COMMENT '创建日期',
  `status` int(1) DEFAULT NULL COMMENT '状态：0 未验证      1：已发货   41：异常',
  `uploadOrderIdIsEmpty` int(1) DEFAULT NULL COMMENT '上传的订单id 是否为空',
  `md5BodyString` varchar(100) NOT NULL DEFAULT '' COMMENT 'bodyString的md5签名',
  `productId` int(11) NOT NULL DEFAULT '0' COMMENT '产品id',
  PRIMARY KEY (`orderId`) USING BTREE,
  UNIQUE KEY `ind_payAppleMD5` (`md5BodyString`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_pay_gods_order_log`
--

DROP TABLE IF EXISTS `u_pay_gods_order_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_pay_gods_order_log` (
  `orderId` varchar(36) NOT NULL COMMENT '订单号',
  `productId` varchar(20) DEFAULT NULL COMMENT 'appId',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户的id',
  `payWay` int(10) DEFAULT NULL COMMENT '支付点1.快充，2.商城充值,3.破产时弹出的充值页面,4游戏中充值',
  `paymentPlatform` varchar(20) DEFAULT NULL COMMENT '支付平台(1:支付宝 2:微信 3；银联支付 4：苹果 )',
  `status` int(10) DEFAULT NULL COMMENT '支付状态(1：预订单 2：已支付  3：已发货 4：结束)',
  `price` float(10,2) DEFAULT '0.00' COMMENT '订单价格',
  `countPrice` float(20,2) DEFAULT NULL COMMENT '价格',
  `godsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `tradeItem` varchar(128) DEFAULT NULL COMMENT '交易物品(可以以字符串配置方式灵活变更)',
  `godsNum` int(10) DEFAULT NULL,
  `godsIntegral` varchar(255) DEFAULT NULL COMMENT '商品积分',
  `storeId` bigint(20) DEFAULT NULL COMMENT '商品的id',
  `appStoreId` int(11) DEFAULT NULL COMMENT 'app对应的商品Id',
  `orderCreateTime` datetime DEFAULT NULL COMMENT '下单时间',
  `payTime` datetime DEFAULT NULL COMMENT '支付时间',
  `deliverTime` datetime DEFAULT NULL COMMENT '发货时间',
  `finishTime` datetime DEFAULT NULL COMMENT '订单最终完成时间',
  PRIMARY KEY (`orderId`) USING BTREE,
  UNIQUE KEY `order_id` (`orderId`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_props_log`
--

DROP TABLE IF EXISTS `u_props_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_props_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL COMMENT '玩家id',
  `taskId` bigint(20) DEFAULT NULL COMMENT '任务id',
  `nickName` varchar(255) DEFAULT NULL COMMENT '玩家昵称',
  `way` int(11) DEFAULT NULL,
  `roomId` bigint(20) DEFAULT NULL COMMENT '房间id',
  `gameId` bigint(20) DEFAULT NULL COMMENT '游戏id',
  `gameTypeId` int(11) DEFAULT NULL COMMENT '玩法ID',
  `propsBefore` bigint(20) DEFAULT NULL COMMENT '变化之前',
  `propsAfter` bigint(20) DEFAULT NULL COMMENT '变化后',
  `propsNum` bigint(20) DEFAULT NULL COMMENT '变化的值',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `propsId` int(255) DEFAULT NULL COMMENT '10007.红包10003.充值卡话费10005.抽奖卡10004.比赛参赛卡10006.开房卡',
  `param1` varchar(32) DEFAULT NULL,
  `param2` varchar(32) DEFAULT NULL,
  `productId` int(11) NOT NULL DEFAULT '0' COMMENT '产品id',
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=913 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_pyj_record_log`
--

DROP TABLE IF EXISTS `u_pyj_record_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_pyj_record_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `gameId` int(11) DEFAULT NULL COMMENT '玩法ID',
  `clubId` bigint(20) DEFAULT NULL COMMENT '俱乐部',
  `passwod` int(20) DEFAULT NULL,
  `roundCount` int(4) NOT NULL DEFAULT '0',
  `playerCount` int(4) DEFAULT '0' COMMENT '玩家人数',
  `gameStartTime` datetime DEFAULT '1979-01-01 00:00:00',
  `isRead` tinyint(1) DEFAULT '0',
  `ownerId` bigint(20) DEFAULT NULL,
  `ownerName` varchar(128) DEFAULT NULL COMMENT '房主姓名',
  `winnerId` bigint(20) DEFAULT NULL COMMENT '大赢家',
  `winnerName` varchar(128) DEFAULT NULL COMMENT '大赢家姓名',
  `payType` int(11) DEFAULT NULL COMMENT '付费类型',
  `gameCard` int(11) DEFAULT NULL COMMENT '实际消耗房卡',
  `clubCard` int(11) DEFAULT '0' COMMENT 'club专属房卡',
  `winnerNum` int(1) DEFAULT '1' COMMENT '大赢家人数',
  `gameStopTime` datetime DEFAULT NULL,
  `userId1` bigint(20) DEFAULT NULL,
  `userName1` varchar(255) DEFAULT NULL,
  `userCashDiff1` bigint(20) DEFAULT NULL,
  `userId2` bigint(20) DEFAULT NULL,
  `userName2` varchar(255) DEFAULT NULL,
  `userCashDiff2` bigint(20) DEFAULT NULL,
  `userId3` bigint(20) DEFAULT NULL,
  `userName3` varchar(255) DEFAULT NULL,
  `userCashDiff3` bigint(20) DEFAULT NULL,
  `userId4` bigint(20) DEFAULT NULL,
  `userName4` varchar(255) DEFAULT NULL,
  `userCashDiff4` bigint(20) DEFAULT NULL,
  `gameNum` int(11) DEFAULT NULL,
  `playBackNames` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `roomId` (`roomId`) USING BTREE,
  KEY `IX_gameStopTime_clubId` (`gameStopTime`,`clubId`) USING BTREE,
  KEY `IX_winnerId` (`winnerId`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=352 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_pyj_user_record`
--

DROP TABLE IF EXISTS `u_pyj_user_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_pyj_user_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `productId` int(11) DEFAULT NULL,
  `userID` bigint(20) NOT NULL DEFAULT '0' COMMENT '玩家ID',
  `nickName` varchar(128) NOT NULL DEFAULT '' COMMENT '玩家昵称',
  `gameID` int(11) NOT NULL DEFAULT '0' COMMENT '游戏ID',
  `date` datetime DEFAULT '1979-01-01 00:00:00' COMMENT '日期',
  `roomID` int(11) DEFAULT NULL COMMENT '房号',
  `playerCount` int(4) NOT NULL DEFAULT '0' COMMENT '大赢家人数',
  `gameCard` int(11) DEFAULT '0' COMMENT '实际消耗房卡',
  `clubCard` int(11) DEFAULT '0' COMMENT 'club专属房卡',
  `clubId` bigint(20) DEFAULT NULL COMMENT '俱乐部',
  `gameStartTime` datetime DEFAULT '1979-01-01 00:00:00' COMMENT '开局时间',
  `playCardCount` int(11) NOT NULL DEFAULT '0' COMMENT '每局出牌总数',
  `playBackFileName` varchar(500) DEFAULT NULL COMMENT '回放文件名称',
  `userInfo1` varchar(512) DEFAULT NULL,
  `userInfo2` varchar(512) DEFAULT NULL,
  `userInfo3` varchar(512) DEFAULT NULL,
  `userInfo4` varchar(512) DEFAULT NULL,
  `recordId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_game` (`userID`,`gameID`) USING BTREE,
  KEY `IX_roomId_userId` (`roomID`,`userID`) USING BTREE,
  KEY `IX_gameStartTime` (`gameStartTime`) USING BTREE,
  KEY `IX_recordId` (`recordId`) USING BTREE,
  KEY `IX_recordId_userId` (`userID`,`recordId`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1032 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='玩家战绩记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_room_action_log`
--

DROP TABLE IF EXISTS `u_room_action_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_room_action_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roomId` int(11) NOT NULL DEFAULT '0',
  `userId` bigint(20) NOT NULL DEFAULT '0',
  `nickName` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `actionType` int(3) NOT NULL DEFAULT '0',
  `actionName` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `productId` int(11) NOT NULL DEFAULT '0' COMMENT '产品id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `roomId` (`roomId`) USING BTREE,
  KEY `actionType` (`actionType`) USING BTREE,
  KEY `roomId_datetime` (`roomId`,`datetime`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2424 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_room_rank_log`
--

DROP TABLE IF EXISTS `u_room_rank_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_room_rank_log` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `dictActivityId` int(11) DEFAULT NULL COMMENT '对应活动id',
  `type` int(11) DEFAULT NULL COMMENT '统计的类型，暂时未用',
  `gameId` int(11) DEFAULT NULL,
  `userId` bigint(16) DEFAULT NULL COMMENT '房主的userId',
  `roomNum` int(11) DEFAULT NULL COMMENT '完成的房间数量',
  `updateTime` datetime DEFAULT NULL COMMENT '更新数量的时刻',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `room` (`roomNum`,`userId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='房主争霸赛排行榜';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_share_log`
--

DROP TABLE IF EXISTS `u_share_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_share_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分享统计表',
  `userId` bigint(20) DEFAULT NULL,
  `way` int(11) DEFAULT NULL COMMENT '分享类型 (1 大厅分享（无奖励）朋友圈；2 大厅分享（有奖励）朋友圈；3 领取钻石-朋友圈分享；4 俱乐部-朋友圈分享；5 房间等待界面-邀请好友（区分玩法）；6 大厅分享给好友（无奖励）；7 领取钻石-分享给好友；8 俱乐部分享给好友)',
  `param` varchar(32) DEFAULT NULL COMMENT '分享参数，比如房间分享的gameId',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `productId` int(11) NOT NULL DEFAULT '0' COMMENT '产品id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_user_info_log`
--

DROP TABLE IF EXISTS `u_user_info_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_info_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `loginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `logoutTime` datetime DEFAULT NULL COMMENT '下线时间',
  `loginIP` varchar(255) DEFAULT NULL COMMENT '登录IP',
  `loginWay` int(2) DEFAULT NULL COMMENT '登录方式，0.wifi，1.移动网路',
  `loginType` int(4) DEFAULT '0' COMMENT '玩家登陆类型（1游客、2手机号、0、3微信、100手机号注册）',
  `identificationCode` varchar(20) NOT NULL DEFAULT '' COMMENT '识别码',
  `isChosenArea` int(1) NOT NULL DEFAULT '0' COMMENT '是否已选择地区',
  `registerTime` datetime DEFAULT NULL COMMENT '用户注册时间',
  `productId` int(11) NOT NULL DEFAULT '0' COMMENT '产品id',
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `productId` (`productId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1240 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `u_user_play_log`
--

DROP TABLE IF EXISTS `u_user_play_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `u_user_play_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gameId` bigint(20) DEFAULT NULL COMMENT '游戏id',
  `roomId` bigint(20) DEFAULT NULL COMMENT '房间id',
  `bank` bigint(20) DEFAULT NULL COMMENT '庄家id',
  `cashBefore` bigint(20) DEFAULT NULL COMMENT '开始金豆',
  `cashDiff` bigint(20) DEFAULT NULL COMMENT '输赢金豆(已经扣除台费)',
  `fan` int(11) DEFAULT NULL COMMENT '番数',
  `createTime` datetime DEFAULT NULL COMMENT '创建日期',
  `recordUUId` varchar(128) DEFAULT NULL COMMENT '对局记录文件UUID',
  `userId` bigint(20) DEFAULT NULL COMMENT '玩家ID',
  `tableMoney` bigint(20) unsigned zerofill DEFAULT NULL COMMENT '房间台费',
  `idBankruptcy` int(10) unsigned zerofill DEFAULT NULL COMMENT '是否破产，1.破产',
  `tableId` varchar(64) DEFAULT NULL COMMENT '桌号',
  `deskRoundId` varchar(64) DEFAULT NULL COMMENT '对局ID',
  `cashAfter` bigint(20) DEFAULT NULL COMMENT '输赢后的金豆值',
  UNIQUE KEY `id` (`id`) USING BTREE
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
