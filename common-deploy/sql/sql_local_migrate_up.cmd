
chcp 65001
echo off
echo 请先确保dbconfig/*.conf都有正确配置MySQL连接！
echo.
dir dbconfig\*.conf /B
echo.
setlocal disableDelayedExpansion
set "files=."
for /r %%F in (dbconfig\*.conf) do (
  setlocal enableDelayedExpansion
  for /f "delims=" %%A in ("!files!") do (
    endlocal
	echo bin\sql-migrate up -config="%%F"
    bin\sql-migrate up -config="%%F"
  )
)
pause