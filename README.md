# unit_1_compile
> Command line
1. javac -sourcepath src/ -d built/classes/ -cp lib/commons-lang3-3.9.jar;lib/commons-collections-3.2.2.jar src/main/java/Main.java src/main/java/Fir
   st.java src/main/java/Second.java src/main/java/Third.java
2. java -cp built/classes/;lib/commons-lang3-3.9.jar;lib/commons-collections-3.2.2.jar; Main
>Ant
1. cd ant
2. setantenv.bat (setantenv.sh for Linux)
3. ant clean 
4. ant compile 
5. ant jar
6. ant run
>Maven
1. mvn clean install
2. mvn exe:java -D exec.mainClass=Main