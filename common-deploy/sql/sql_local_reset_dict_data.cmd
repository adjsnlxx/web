echo off
for /F "tokens=1,2 delims= " %%i in ('bin\sql-config.exe -config=./dbconfig/dsqp_dict.conf') do (
	REM echo %%i, %%j
	if "%%i"=="IP" set mysql_ip="%%j"
	if "%%i"=="Port" set mysql_port="%%j"
	if "%%i"=="User" set mysql_user="%%j"
	if "%%i"=="Passwd" set mysql_password="%%j"
	if "%%i"=="DBName" set mysql_dbname="%%j"
)
echo on

bin\mysql -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password% -e "source full/dsqp_dict.sql" -D %mysql_dbname% --default-character-set=utf8
bin\mysql -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password% -e "source full/dsqp_dict_gorp_migrations.data" -D %mysql_dbname% --default-character-set=utf8
bin\sql-migrate up -config=./dbconfig/dsqp_dict.conf
bin\mysql -h%mysql_ip% -P%mysql_port% -u%mysql_user% -p%mysql_password% -e "source initialize_data/dict_initialize_data.sql" -D %mysql_dbname%
pause