call mvn clean
call mvn install
call mvn exec:java -D exec.mainClass="ua.com.alevel.app.AppMain"
call cd..