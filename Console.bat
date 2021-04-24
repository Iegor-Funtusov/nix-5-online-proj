javac -sourcepath javaClasses -d classes -cp libs\lombok-1.18.20.jar:.\libs\commons-lang3-3.11.jar javaClasses\Warning.java javaClasses\Expression.java javaClasses\Program.java

java -cp classes;.\libs\lombok-1.18.20.jar;.\libs\commons-lang3-3.11.jar;. javaClasses.Program