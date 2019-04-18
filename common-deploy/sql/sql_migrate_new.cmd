echo off
echo.
dir dbconfig\*.conf /B
echo.
set /p config=Which config? 
set /p desc=File desc? 
echo bin\sql-migrate new -config=dbconfig\%config% %desc%
bin\sql-migrate new -config=dbconfig\%config% %desc%
pause