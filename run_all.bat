@echo off
call cd console_compile
call console_run.bat
call cd ..\ant_compile
call ant_run.bat
call cd ..\maven_compile
call maven_compile.bat

