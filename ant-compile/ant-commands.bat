@echo off
set ANT_OPTS=-Xmx2G -Dfile.encoding=UTF-8
set ANT_HOME=%~dp0apache-ant-1.10.10
set PATH=%ANT_HOME%\bin;%PATH%
rem deleting CLASSPATH as a workaround for PLA-8702
set CLASSPATH=
cd ../
if exist "build" rmdir /s "build"
@echo on
call ant clean compile jar run
pause