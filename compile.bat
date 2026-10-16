@echo off
echo Compiling EduTrack...
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
del sources.txt
echo Compilation successful! Output stored in out\
