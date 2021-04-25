echo *************
echo MAVEN COMPILE
echo *************

call mvn clean
call mvn compile
call mvn install
call mvn exec:java -D exec.mainClass="ua.example.Main"
pause