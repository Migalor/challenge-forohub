\
    @ECHO OFF
    setlocal
    set MAVEN_PROJECTBASEDIR=%~dp0
    set WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar
    if exist "%WRAPPER_JAR%" goto run
    if not exist "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper" mkdir "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper"
    set PROPERTIES=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties
    for /f "tokens=1,* delims==" %%a in ('findstr /B /C:"wrapperUrl=" "%PROPERTIES%"') do set WRAPPER_URL=%%b
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%WRAPPER_URL%', '%WRAPPER_JAR%')"
    :run
    java -jar "%WRAPPER_JAR%" %*
    endlocal
