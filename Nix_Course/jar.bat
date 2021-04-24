@REM This file generate project's jar 

call _settings.bat

IF EXIST %PROJECT%.zip DEL %PROJECT%.zip
IF EXIST %PROJECT%.jar DEL %PROJECT%.jar

ECHO Creating JAR archive: %PROJECT%.jar

@REM ��������� ������:

jar.exe -cmf manifest.mf %PROJECT%.jar -C bin .

@REM �������� ������:

java.exe -jar %PROJECT%.jar