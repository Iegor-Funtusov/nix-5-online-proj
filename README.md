# nix-5-online-proj
`Console commands`  
1. javac -sourcepath src/ -d build/classes -cp libs/lombok-1.18.18.jar;libs/commons-lang3-3.11.jar;libs/guava-23.0.jar src/com/nixsolutions/courses/service/Todo.java src/com/nixsolutions/courses/service/TodoDAO.java src/com/nixsolutions/courses/service/TodoUtils.java src/com/nixsolutions/courses/Hello.java

2. java -cp ./build/classes;./libs/lombok-1.18.18.jar;./libs/guava-23.0.jar;./libs/commons-lang3-3.11.jar;. com.nixsolutions.courses.Hello 

`Ant`
1. cd ant
2. setantenv.bat (Linux - setantenv.sh)
3. ant clean
4. ant compile
5. ant jar
6. ant run  

`Maven`

1. Reload maven from Maven folder in Intellij IDEA
2. Run Hello class from maven module

   
   or (if maven installed)




1. mvn clean install
2. mvn exec:java -D exec:mainClass=com.nixsolutions.courses.Hello
