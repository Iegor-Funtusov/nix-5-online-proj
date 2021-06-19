@echo off
echo -----BUILDING JAR------
call mvn clean install
echo -----RUNNING  JAR------
call java -jar target/calendar.jar