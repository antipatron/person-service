@echo off
echo.
echo ##############################################################

IF NOT "%IMAGE_NAME_PERSON%"=="" goto removeImage

call set-env.bat

:removeImage
echo Removing image %IMAGE_NAME_PERSON%
docker rmi %IMAGE_NAME_PERSON%

