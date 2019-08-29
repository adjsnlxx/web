## 用户系统
```
用户系统主要分为：账号体系和用户信息两大类

账号体系包括，登陆验证、注册、第三方授权、以及权限管理

用户信息包括，用户地理位置、用户属性、用户设备信息、还有用户日志信息
```

### 账号体系
```
登陆验证
在一般项目账号体系中，一般会要求支持手机、邮箱、账号、QQ、微信、微博实现登陆。
后面三种方式都是基于第三方授权后，完成的身份验证。手机、邮箱、账号则是相对传统的登录方式。

用户身份与登录的授权方式是独立开的，即用户uid和登录方式是一对多的关系。
举例来说，用户A在使用微博授权登陆后，服务端鉴别身份信息为uid=123。
用户A下次使用微信登陆，服务端鉴别身份同样为uid=123。不存在同一用户A拥有多个账号信息的现象。
登陆授权表设计如下：
```

#### 用户授权表
```sql
CREATE TABLE `user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `guid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `identity_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `identifier` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号 邮箱 用户名或第三方应用的唯一标识',
  `certificate` varchar(20) NOT NULL DEFAULT '' COMMENT '密码凭证(站内的保存密码，站外的不保存或保存token)',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '绑定时间',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新绑定时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `only` (`guid`,`identity_type`),
  KEY `idx_identity_type_identifier` (`identity_type`,`identifier`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户授权表'
```

### 用户信息
```
用户信息，为便于扩展，分成两类。
用户基础信息和用户拓展信息。

基本信息用来保存用户的基本属性，年龄、性别、生日、头像、手机号码等。
扩展信息，用来保存用户的设备信息或其他可扩展的内容。
另外还有位置信息，这个可独立出来，也可合并到扩展信息中，根据自己的使用场景来定。
```

#### 用户基础信息
```sql
CREATE TABLE `user_base` (
  `guid` bigint(20) NOT NULL COMMENT '用户ID',
  `user_role` tinyint(2) unsigned NOT NULL DEFAULT '2' COMMENT '2正常用户 3禁言用户 4虚拟用户 5运营',
  `register_source` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '注册来源：1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户账号，必须唯一',
  `nick_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `gender` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '用户性别 0-female 1-male',
  `birthday` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户生日',
  `signature` varchar(255) NOT NULL DEFAULT '' COMMENT '用户个人签名',
  `mobile` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号码(唯一)',
  `mobile_bind_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '手机号码绑定时间',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱(唯一)',
  `email_bind_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '邮箱绑定时间',
  `face` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `face200` varchar(255) NOT NULL DEFAULT '' COMMENT '头像 200x200x80',
  `srcface` varchar(255) NOT NULL DEFAULT '' COMMENT '原图头像',
  `create_time` int(11) unsigned NOT NULL COMMENT '创建时间',
  `update_time` int(11) unsigned NOT NULL COMMENT '修改时间',
  `push_token` varchar(50) NOT NULL COMMENT '用户设备push_token',
  PRIMARY KEY (`guid`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基础信息表'
```

#### 用户扩展信息
```sql
CREATE TABLE `user_extra` (
  `guid` bigint(20) NOT NULL COMMENT '用户 ID',
  `vendor` varchar(64) NOT NULL DEFAULT '' COMMENT '手机厂商：apple|htc|samsung，很少用',
  `client_name` varchar(50) NOT NULL DEFAULT '' COMMENT '客户端名称，如hjskang',
  `client_version` varchar(50) NOT NULL DEFAULT '' COMMENT '客户端版本号，如7.0.1',
  `os_name` varchar(16) NOT NULL DEFAULT '' COMMENT '设备号:android|ios',
  `os_version` varchar(16) NOT NULL DEFAULT '' COMMENT '系统版本号:2.2|2.3|4.0|5.1',
  `device_name` varchar(32) NOT NULL DEFAULT '' COMMENT '设备型号，如:iphone6s、u880、u8800',
  `device_id` varchar(128) NOT NULL DEFAULT '' COMMENT '设备ID',
  `idfa` varchar(50) NOT NULL DEFAULT '' COMMENT '苹果设备的IDFA',
  `idfv` varchar(50) NOT NULL DEFAULT '' COMMENT '苹果设备的IDFV',
  `market` varchar(20) NOT NULL DEFAULT '' COMMENT '来源',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `extend1` varchar(100) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `extend2` varchar(100) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `extend3` varchar(100) NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户额外信息表'
```

#### 用户位置信息
```sql
CREATE TABLE `user_location` (
  `guid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `curr_nation` varchar(10) NOT NULL DEFAULT '' COMMENT '所在地国',
  `curr_province` varchar(10) NOT NULL DEFAULT '' COMMENT '所在地省',
  `curr_city` varchar(10) NOT NULL DEFAULT '' COMMENT '所在地市',
  `curr_district` varchar(20) NOT NULL DEFAULT '' COMMENT '所在地地区',
  `location` varchar(255) NOT NULL DEFAULT '' COMMENT '具体地址',
  `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `update_time` int(11) unsigned DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户定位表'
```

#### 用户日志信息
```
日志信息，用来保存用户注册或者登陆行为的。另外会有一些修改密码或者修改重要信息的日志记录。
```

#### 用户登陆日志
```sql
CREATE TABLE `user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `guid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户uid',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '登录方式 第三方/邮箱/手机等',
  `command` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型 1登陆成功  2登出成功 3登录失败 4登出失败',
  `version` varchar(32) NOT NULL DEFAULT '1.0' COMMENT '客户端版本号',
  `client` varchar(20) NOT NULL DEFAULT 'dabaozha' COMMENT '客户端',
  `device_id` varchar(64) NOT NULL DEFAULT '' COMMENT '登录时设备号',
  `lastip` varchar(32) NOT NULL DEFAULT '' COMMENT '登录ip',
  `os` varchar(16) NOT NULL DEFAULT '' COMMENT '手机系统',
  `osver` varchar(32) NOT NULL DEFAULT '' COMMENT '系统版本',
  `text` varchar(200) NOT NULL DEFAULT '',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid_type_time` (`guid`,`type`,`create_time`) USING BTREE,
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登陆日志表'
```

#### 用户注册日志
```sql
CREATE TABLE `user_register_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `guid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `register_method` tinyint(2) unsigned NOT NULL COMMENT '注册方式1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `register_time` int(11) NOT NULL COMMENT '注册时间',
  `register_ip` varchar(16) NOT NULL DEFAULT '' COMMENT '注册IP',
  `register_client` varchar(16) NOT NULL DEFAULT '' COMMENT '注册客户端',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='用户注册日志表'
```

#### 修改信息日志
```sql
CREATE TABLE `user_info_update` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `guid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `attribute_name` varchar(30) NOT NULL COMMENT '属性名',
  `attribute_old_val` varchar(30) NOT NULL DEFAULT '' COMMENT '属性对应旧的值',
  `attribute_new_val` varchar(30) NOT NULL DEFAULT '' COMMENT '属性对应新的值',
  `update_time` int(11) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='用户注册日志表'
```

### 全局guid
```
建议不要使用表的主键作为用户ID，而是使用ID生成器(发号器)生成用户的唯一标示guid。当用户量急剧上升时，往往会采取分库分表的方法，然后通过将uid取余写到不同的表中。如果单纯的以某个表主键作为ID。会限制插入性能和增加业务复杂度,其次在分布式数据库中也无法保证ID唯一性。

全局ID生成，是有很多方案的。简单一点，可以采用redis自增属性，因为其具有原子性，在分布式坏境中，能保证ID的唯一性。另外还有其他的一些开源方案，可自行Google。
```

### Access Token
```
与传统的Session相比，Access Token比较适合做RESTful Api开发。传统Web应用中，用户登陆后会写用户信息到cookie中，服务端通过Session就能得到用户的身份。

Access Token的是OAuth2.0中用户经过授权后，返回调用API的凭证。对于自己的应用来讲，用户在登录后，即返回access_token。在token有效期内可凭借此凭证，调用其他接口。对于access_token的刷新有两种方案，第一种每次用户重启app时，重新refresh。第二种，在调用周期内服务端发现access token可能过期时，返回新的token给客户端。

至于Access Token的生成，这个并没有规定，只要保证其唯一性即可。简单点，对用户uid和当前时间哈希得到新的Access Token，并设置过期时间。另外也可以采用JWT实现。
```