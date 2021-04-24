javac -d build/classes src/*.java
if not exist "build/jar" mkdir build/jar
cd build/classes
jar cfe ../jar/Solve.jar Solve Solve.class
cls
java -jar ../jar/Solve.jar