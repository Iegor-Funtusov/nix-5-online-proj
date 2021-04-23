@echo off
title Compiling Crud
cd ../
del /f /s /q build 1>nul
javac -d build/classes -cp libs/lombok-1.18.20.jar;libs/commons-lang3-3.12.0.jar -sourcepath src src/com/k4rnaj1k/Crud.java src/com/k4rnaj1k/AnSecondary.java
cd build/classes
cls
cd ../
if not exist jar mkdir jar
cd classes
@echo on
jar cfm ../jar/Crud.jar ../../cmd-compile/Manifest.txt com/k4rnaj1k/*.class
java -jar ../jar/Crud.jar
pause