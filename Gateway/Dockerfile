FROM eclipse-temurin:17-jdk-alpine

LABEL org.opencontainers.image.title="Gateway"
LABEL org.opencontainers.image.authors="Your Name"
LABEL org.opencontainers.image.description="Spring Boot Gateway Application"

# Zmienna środowiskowa dla wersji aplikacji
ENV VERSION="1.0-SNAPSHOT"

# Zmienna środowiskowa dla portu aplikacji
ENV SERVER_PORT=8082

# Eksponowanie portu aplikacji
EXPOSE 8082

# Skopiowanie pliku JAR aplikacji do obrazu Dockera
COPY target/gateway-1.0-SNAPSHOT.jar /app/gateway.jar

# Uruchamianie aplikacji
CMD ["java", "-jar", "/app/gateway.jar"]
