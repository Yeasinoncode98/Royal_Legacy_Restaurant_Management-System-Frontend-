@echo off
SET "MVNW_DIR=%~dp0.mvn\wrapper"
SET "MVN_JAR=%MVNW_DIR%\maven-wrapper.jar"
SET "DOWNLOAD_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
IF NOT EXIST "%MVN_JAR%" (
  powershell -Command "Invoke-WebRequest -Uri '%DOWNLOAD_URL%' -OutFile '%MVN_JAR%'"
)
java -classpath "%MVN_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
