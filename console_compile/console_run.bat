echo ***************
echo CONSOLE COMPILE
echo ***************

@echo on
call rmdir /S /Q build
call mkdir build\classes
call javac -sourcepath \src -d build\classes -cp libs\commons-lang3-3.12.0.jar;.\libs\lombok-1.18.20.jar;. src\ua\example\test_package\SecondClass.java src\ua\example\test_package\TestClass.java src\ua\example\Main.java
call java -cp build\classes;.\libs\commons-lang3-3.12.0.jar;.\libs\lombok-1.18.20.jar ua.example.Main
@echo off
pause