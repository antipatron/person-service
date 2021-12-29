@echo off
echo.
echo ##############################################################
echo Starting build with docker...
set/p mySqlPassDev=Enter de mysql password dev:
set/p s3UserOne=Enter de s3 user:
set/p s3AccessKeyOne=Enter de s3 access key:
set/p s3SecretKeyOne=Enter de s3 secret key:
cls
echo.
docker build --tag %IMAGE_NAME_PERSON% .
docker run --name %CONTAINER_NAME_PERSON% -d -e MYSQL_PASSWORD_DEV=%mySqlPassDev% -e S3_USER_ONE=%s3UserOne% -e S3_ACCESS_KEY_ONE=%s3AccessKeyOne% -e S3_SECRET_KEY_ONE=%s3SecretKeyOne% -p 7977:7977 %IMAGE_NAME_PERSON%:latest