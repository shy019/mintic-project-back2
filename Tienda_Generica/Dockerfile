FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Heap y GC variables
ENV JAVA_XMS="512m"
ENV JAVA_XMX="512m"
ENV JAVA_GC_FLAGS="-XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:InitiatingHeapOccupancyPercent=30 -XX:G1PeriodicGCInterval=5000"

COPY target/*.jar app.jar
EXPOSE 8080

# ENTRYPOINT with env variables and AOT in execution time
ENTRYPOINT ["sh", "-c", \
  "java \
    -Xms${JAVA_XMS} \
    -Xmx${JAVA_XMX} \
    ${JAVA_GC_FLAGS} \
    -Dspring.aot.enabled=true \
    -jar /app/app.jar"]
