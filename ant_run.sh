call ant\setantenv.bat

echo ***********
echo ANT COMPILE
echo ***********

call ant clean
call ant compile
call ant jar
call ant run
pause