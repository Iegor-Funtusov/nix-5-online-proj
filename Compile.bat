@REM This File Compile and Run The Program
call _settings.bat

SET FOLDER=
IF NOT "%PACKAGE%"=="" SET FOLDER=%PACKAGE:.=\%
IF NOT "%PACKAGE%"=="" SET PACKAGE=%PACKAGE%.
IF NOT "%FOLDER%"=="" SET FOLDER=%FOLDER%\
IF NOT EXIST .\bin md .\bin
IF EXIST .\bin\%FOLDER%*.class DEL .\bin\%FOLDER%*.class
IF EXIST errors.txt DEL errors.txt

ECHO Compiling project %PACKAGE%%PROJECT%...

javac -classpath %CLASSPATH% -d bin -sourcepath src DZ_1\package\%FOLDER%%PROJECT%.java %XLINT% 2>>errors.txt

ECHO.
:wait
IF NOT EXIST errors.txt GOTO wait

FOR %%i IN (errors.txt) DO SET fsize=%%~zi
IF NOT %fsize%==0 GOTO error

ECHO Compiling succesful. Launching Compiled Java Program

java -classpath %CLASSPATH% %PACKAGE%%PROJECT% 2>>errors.txt
timeout 10
FOR %%i IN (errors.txt) DO SET fsize=%%~zi
IF NOT %fsize%==0 GOTO error
IF EXIST errors.txt DEL errors.txt

GOTO end

:error

ECHO The compiler found the following errors:
ECHO ----------------------------------------
ECHO.
TYPE errors.txt
ECHO.
PAUSE

:end