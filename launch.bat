javac -d build/classes src/*.java
if not exist "build/jar" mkdir "build/jar"
cd build/classes
jar cfe ../jar/Solver.jar Solver Solver.class
cls
java -jar ../jar/Solver.jar