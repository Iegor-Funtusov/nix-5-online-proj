@echo off
set M2_HOME=%~dp0apache-maven-3.8.1
set M2=%M2_HOME%\bin
set MAVEN_OPTS=-Xms256m -Xmx512m -Dfile.encoding=UTF-8
set PATH=%M2%;%PATH%
cd ../
set CLASSPATH=
@echo on
if exist "build" rmdir /s "build"
call mvn --version
call mvn package
cls
java -jar build/jar/Printer.jar
pause