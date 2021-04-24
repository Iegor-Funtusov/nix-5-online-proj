# unit_1_compile
> Command line
1. javac -sourcepath src/ -d built/classes/ -cp lib/commons-lang3-3.9.jar;lib/commons-collections-3.2.2.jar src/Main.java
2. java -cp built/classes/;lib/commons-lang3-3.9.jar;lib/commons-collections-3.2.2.jar; Main
>Ant
1. cd ant
2. setantenv.bat (setantenv.sh for Linux)
3. ant clean ant compile ant jar ant run
>Maven
1. 