FROM eclipse-temurin:17-jdk-alpine

LABEL org.opencontainers.image.title="ReaderPart"
LABEL org.opencontainers.image.authors="Your Name"
LABEL org.opencontainers.image.description="Spring Boot ReaderPart Application"

ENV VERSION="1.0-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:reader-part
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

EXPOSE 8080

COPY target/ReaderPart-1.0-SNAPSHOT.jar /app/reader-part.jar

CMD ["java", "-jar", "/app/reader-part.jar"]
