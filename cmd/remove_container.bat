@echo off
echo.
echo ##############################################################

IF NOT "%CONTAINER_NAME_PERSON%"=="" goto removeContainer

call set-env.bat

:removeContainer
echo Removing container %CONTAINER_NAME_PERSON%
docker rm %CONTAINER_NAME_PERSON%

