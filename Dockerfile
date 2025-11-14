FROM alpine/java:17
RUN apk add --no-cache freetype 
RUN apk add --no-cache msttcorefonts-installer fontconfig && update-ms-fonts
WORKDIR /app
COPY "/src/main/resources/reportes/grispapa.png" /app/
ADD target/papacotizacion-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

