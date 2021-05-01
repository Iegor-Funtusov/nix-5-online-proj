@echo off
echo -----BUILDING PROJECT------
echo cleaning build directory...
if exist build\ (rmdir build /q /s) else (echo No build/ directory)
call javac -sourcepath src/ -d build/classes src/com/nixsolutions/courses/utils/ArrayUtils.java src/com/nixsolutions/courses/utils/ArithmeticUtils.java src/com/nixsolutions/courses/service/Service.java
call java -cp build/classes com.nixsolutions.courses.service.Service
