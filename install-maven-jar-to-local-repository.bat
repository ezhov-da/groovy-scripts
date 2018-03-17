@echo off
chcp 855


echo hello :^)
echo Input:
set /p maven_home=Path to M2_HOME if not exist or empty:
set /p path=Absolute path to jar:
set /p groupId=Group ID:
set /p artifactId=Artifact ID:
set /p version=Version:

if "%maven_home%" NEQ "" (
    set %M2_HOME%=%maven_home%
)

%M2_HOME%\bin\mvn install:install-file -Dfile="%path%" -DgroupId=%groupId% -DartifactId=%artifactId% -Dversion=%version% -Dpackaging=jar -DgeneratePom=true
