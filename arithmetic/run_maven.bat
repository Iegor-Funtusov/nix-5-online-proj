echo *************
echo MAVEN COMPILE
echo *************

call mvn clean
call mvn install
call mvn exec:java -D exec.mainClass="ua.com.array.Main"
pause