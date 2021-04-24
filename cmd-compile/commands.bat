@echo off
title Compiling Printer
cd ../
if exist "build" rmdir /s "build"
javac -d build/classes -cp libs/lombok-1.18.20.jar;libs/commons-lang3-3.12.0.jar -sourcepath src src/com/k4rnaj1k/Printer.java src/com/k4rnaj1k/AnSecondary.java
cd build/classes
cls
cd ../
if not exist jar mkdir jar
cd classes
jar cfm ../jar/Printer.jar ../../cmd-compile/Manifest.txt com/k4rnaj1k/*.class
@echo on
java -jar ../jar/Printer.jar
pause