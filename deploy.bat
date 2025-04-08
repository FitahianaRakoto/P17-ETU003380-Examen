@echo off
set APP_NAME=ETU003380
set SRC_DIR="src\main\java"
set WEB_DIR="src\main\webapp"
set BUILD_DIR="build"
set LIB_DIR="lib"
set TOMCAT_WEBAPPS="D:\Tom_Cat\webapps"
set SERVLET_API_JAR="lib/servlet-api.jar"
set MYSQL_API_JAR="lib/mysql-connector-j-9.1.0.jar"
set CLASSPATH=%LIB_DIR%\servlet-api.jar;%LIB_DIR%\mysql-connector-j-9.1.0.jar;%CLASSPATH%

REM Suppression et recréation du dossier temporaire
if exist %BUILD_DIR% rmdir /s /q %BUILD_DIR%
mkdir %BUILD_DIR%\WEB-INF\classes
mkdir %BUILD_DIR%\WEB-INF\lib


xcopy "lib" "%BUILD_DIR%\WEB-INF\lib" /E /I /Y

REM Compilation des fichiers Java avec le JAR des Servlets
dir /s /b %SRC_DIR%\*.java > sources.txt
for /f "delims=" %%f in (sources.txt) do (
    copy "%%f" "%BUILD_DIR%\WEB-INF\classes"
)
del sources.txt

cd "%BUILD_DIR%\WEB-INF\classes"
javac -cp "../../../%LIB_DIR%/*" -d . *java
cd ../../../


dir /s /b "%BUILD_DIR%\WEB-INF\classes"\*.java > sources.txt
for /f "delims=" %%f in (sources.txt) do (
    del "%%f"
)

REM Supprimer le fichier sources.txt après la compilation
del sources.txt

REM Copier les fichiers web
xcopy %WEB_DIR% %BUILD_DIR% /E /I /Y

REM Création du fichier .war dans le dossier build
cd %BUILD_DIR%
jar -cvf %APP_NAME%.war *
cd ..

REM Déploiement vers Tomcat
copy %BUILD_DIR%\%APP_NAME%.war %TOMCAT_WEBAPPS%

echo Déploiement terminé. Redémarrez Tomcat si nécessaire.
