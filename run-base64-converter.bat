@echo off
setlocal enabledelayedexpansion
chcp 855

:again

echo What use?
echo 0 - decode
echo 1 - encode
echo or exit

set /p USE=What use:

if "%USE%"=="0" (
    set FLAG=-d
) else if "%USE%"=="1" (
    set FLAG=-f
) else if "%USE%"=="exit" (
    echo Selected [exit] ... goodbye :^)
    exit
) else (
    echo Input 1 or 0 or exit ...
    echo.
    goto again
)

echo Selected flag: %FLAG%

set /p FROM=Enter [from] file:
set /p TO=Enter [to] file:

echo Start execute
groovy %~dp0\src\main\groovy\ru\ezhov\base64Converter.groovy %FLAG% %FROM% %TO%

pause