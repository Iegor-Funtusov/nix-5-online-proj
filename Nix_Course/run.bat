@REM This file run the program
call _settings.bat

ECHO Launching Java code...
IF EXIST errors.txt DEL errors.txt
IF NOT "%PACKAGE%"=="" SET PACKAGE=%PACKAGE%.
ECHO OFF

REM **********************************
REM запуск откомпилированной программы
REM **********************************

java -classpath %CLASSPATH% %PACKAGE%%PROJECT% 2>>errors.txt
timeout 10
ECHO.
FOR %%i IN (errors.txt) DO SET fsize=%%~zi
IF NOT %fsize%==0 GOTO error
IF EXIST errors.txt DEL errors.txt

GOTO end

:error

ECHO JVM found the following errors:
ECHO -------------------------------
ECHO.
TYPE errors.txt
ECHO.
PAUSE

:end