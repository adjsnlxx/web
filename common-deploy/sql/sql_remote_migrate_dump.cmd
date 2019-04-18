echo off
for /F "tokens=1,2 delims= " %%i in ('bin\sql-config.exe -config=./dbconfig/dsqp_dict.conf.remote') do (
	REM echo %%i, %%j
	if "%%i"=="IP" set mysql_ip="%%j"
	if "%%i"=="Port" set mysql_port="%%j"
	if "%%i"=="User" set mysql_user="%%j"
	if "%%i"=="Passwd" set mysql_password="%%j"
	if "%%i"=="DBName" set mysql_dbname="%%j"
)
echo on

bin\sql-migrate up -config=./dbconfig/dsqp_dict.conf.remote

REM bin\mysql -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password% -e "source dict_delete.sql" -D %mysql_dbname%

bin\mysqldump --no-data --ignore-table=%mysql_dbname%.gorp_migrations %mysql_dbname% -r full/dsqp_dict.sql -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password%
bin\mysqldump %mysql_dbname% gorp_migrations -r full/dsqp_dict_gorp_migrations.data -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password%
bin\mysqldump %mysql_dbname% --no-create-info --ignore-table=%mysql_dbname%.gorp_migrations -r initialize_data/dict_initialize_data.sql -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password%

echo off
for /F "tokens=1,2 delims= " %%i in ('bin\sql-config.exe -config=./dbconfig/game_dev.conf.remote') do (
	REM echo %%i, %%j
	if "%%i"=="IP" set mysql_ip="%%j"
	if "%%i"=="Port" set mysql_port="%%j"
	if "%%i"=="User" set mysql_user="%%j"
	if "%%i"=="Passwd" set mysql_password="%%j"
	if "%%i"=="DBName" set mysql_dbname="%%j"
)
echo on

bin\sql-migrate up -config=./dbconfig/game_dev.conf.remote
bin\mysqldump --no-data --ignore-table=%mysql_dbname%.gorp_migrations game_dev -r full/game_dev.sql -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password%
bin\mysqldump %mysql_dbname% gorp_migrations -r full/game_dev_gorp_migrations.data -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password%

echo off
for /F "tokens=1,2 delims= " %%i in ('bin\sql-config.exe -config=./dbconfig/game_log_dev.conf.remote') do (
	REM echo %%i, %%j
	if "%%i"=="IP" set mysql_ip="%%j"
	if "%%i"=="Port" set mysql_port="%%j"
	if "%%i"=="User" set mysql_user="%%j"
	if "%%i"=="Passwd" set mysql_password="%%j"
	if "%%i"=="DBName" set mysql_dbname="%%j"
)
echo on

bin\sql-migrate up -config=./dbconfig/game_log_dev.conf.remote
bin\mysqldump --no-data --ignore-table=%mysql_dbname%.gorp_migrations %mysql_dbname% -r full/game_log_dev.sql -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password%
bin\mysqldump %mysql_dbname% gorp_migrations -r full/game_log_dev_gorp_migrations.data -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password%
pause