

call _settings.bat

IF EXIST %PROJECT%.zip DEL %PROJECT%.zip
IF EXIST %PROJECT%.jar DEL %PROJECT%.jar

ECHO Creating JAR archive: %PROJECT%.jar
jar.exe -cmf manifest.mf %PROJECT%.jar -C bin .
java.exe -jar %PROJECT%.jar
PAUSE