#!/bin/sh
MAVEN_PROJECTBASEDIR=${MAVEN_BASEDIR:-"$(cd "`dirname "$0"`"; pwd)"}
WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain
if [ -r "$WRAPPER_JAR" ]; then
  exec java -jar "$WRAPPER_JAR" "$@"
fi
# try download wrapper jar
PROPERTIES="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"
DISTRIBUTION_URL=$(grep -E "^distributionUrl=" "$PROPERTIES" | cut -d= -f2-)
WRAPPER_URL=$(grep -E "^wrapperUrl=" "$PROPERTIES" | cut -d= -f2-)
mkdir -p "$MAVEN_PROJECTBASEDIR/.mvn/wrapper"
jar="$WRAPPER_JAR"
echo "Downloading Maven Wrapper jar from $WRAPPER_URL"
curl -s -L -o "$jar" "$WRAPPER_URL" || wget -q -O "$jar" "$WRAPPER_URL"
exec java -jar "$WRAPPER_JAR" "$@"
