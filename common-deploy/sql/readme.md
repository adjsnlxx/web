The file is encoding by UTF-8. 

## 简介

我们使用 sql-migrate 管理 sql 文件的版本。sql-migrate 的网站： https://github.com/rubenv/sql-migrate

## dbconfig 目录

dbconfig 目录保存了各个 db 的 dbconfig.conf 文件的模板。

* dbconfig/chargecenter_dev.conf.template
* dbconfig/club_log.conf.template
* dbconfig/club.conf.template
* dbconfig/dev.conf.template
* dbconfig/club_dict.conf.template 

另外还有用于同步数据的公共标准库配置，当有数据变化的时候（非结构变化），不需要手动更新git库里的sql，先把公共标准库的数据修改到最新，再执行cmd命令dump标准库到git库里（不会自动提交到远程git，便于确认）

* dbconfig/dsqp_dict.conf.remote
* dbconfig/game_dev.conf.remote
* dbconfig/game_log_dev.conf.remote

## 脚本文件

实际工作流程可以直接用几个cmd文件进行

### sql_migrate_new.cmd 

用于数据库结构变化的时候，新增SQL语句

### sql_local_migrate_up.cmd 

在本地配置的MySQL（dbconfig里的*.conf文件）里执行migration脚本

### sql_local_reset_dict_data.cmd 

在本地配置的MySQL（dbconfig里的*.conf文件）里重新初始化公共库结构及其初始化数据

### sql_remote_init.cmd.bak

在公共标准库（dbconfig里的*.remote文件）里初始化所有数据库，会先清空已有表，所以需要先去掉bak再执行

### sql_remote_migrate_dump.cmd

在公共标准库（dbconfig里的*.remote文件）里同步最新migration脚本，并dump出最新的表结构和公共库数据到git库（不会自动提交到远程git，便于确认）

### bin/*.exe 

提供操作MySQL的基本功能，其中的bin/sql-config.exe是通过sql-config文件夹下的go文件编译，用于解析dbconfig里的MySQL配置文件。

### bin/*.sh 

文件是指定了 dbconfig 文件的快捷脚本，它们兼容 mac 和 centos

* chargecenter_dev.sh	
* club_dict.sh		
* dev.sh
* club.sh			
* club_log.sh

## sql-migrate 命令参数示例

sql-migrate new -config=./dbconfig/dsqp_dict.conf dict_task


## playbooks 目录

playbooks 目录是 ansible 脚本，用于生成 *.sh 和 dbconfig 目录里 template 的文件。

## full 目录

full 目录是各个 db 的完整数据结构

## initialize_data 目录

initialize_data 目录是公共库的初始数据

## migration 目录 

migration 目录保存 sql-migrate 生成的 sql 文件

## sql-config 目录

用于生成bin/sql-config.exe的go代码，用于解析dbconfig里的MySQL配置文件