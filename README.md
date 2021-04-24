Пример работы с программой:

Input 2 numbers and +, -, / or *

2 4 +

6

Expression was solved

Input 2 numbers and +, -, / or *

10 20 *

200

Expression was solved

Не правильный ввод:

Input 2 numbers and +, -, / or *

10 20 k

Error



1) Console батник или: 


javac -sourcepath javaClasses -d classes -cp libs\lombok-1.18.20.jar:.\libs\commons-lang3-3.11.jar javaClasses\Warning.java javaClasses\Expre
ssion.java javaClasses\Program.java

java -cp classes;.\libs\lombok-1.18.20.jar;.\libs\commons-lang3-3.11.jar;. javaClasses.Program

2) Ant

ant clean

ant compile

ant jar

ant run

3) Maven

mvn exec: java
