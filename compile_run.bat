@echo off
cd src
echo Compiling...
javac */*.java *.java
if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b
)
echo Running...
java Main
pause
