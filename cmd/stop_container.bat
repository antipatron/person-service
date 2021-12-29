@echo off
echo.
echo ##############################################################

IF NOT "%CONTAINER_NAME_PERSON%"=="" goto stopContainer

call set-env.bat

:stopContainer
echo Stopping container %CONTAINER_NAME_PERSON%
docker stop %CONTAINER_NAME_PERSON%



