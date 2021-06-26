@echo off
call cd unit_8/app
echo -----BUILDING JAR------
call mvn clean install
echo -----RUNNING  JAR------
call java -jar target/app.jar