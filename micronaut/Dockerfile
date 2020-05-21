FROM openjdk:14-alpine
COPY build/libs/websocket-*-all.jar websocket.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "websocket.jar"]