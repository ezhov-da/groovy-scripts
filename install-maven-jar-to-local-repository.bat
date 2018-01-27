@echo off
chcp 855

echo hello :^)
echo Input:
set /p path=Absolute path to jar:
set /p groupId=Group ID:
set /p artifactId=Artifact ID:
set /p version=Version:

%M2_HOME%\bin\mvn install:install-file -Dfile="%path%" -DgroupId=%groupId% -DartifactId=%artifactId% -Dversion=%version% -Dpackaging=jar -DgeneratePom=true