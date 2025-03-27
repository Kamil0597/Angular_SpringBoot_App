FROM eclipse-temurin:17-jdk-alpine

LABEL org.opencontainers.image.title="BookCategoryPart"
LABEL org.opencontainers.image.authors="Your Name"
LABEL org.opencontainers.image.description="Spring Boot BookCategoryPart Application"

ENV VERSION="1.0-SNAPSHOT"

ENV SERVER_PORT=8081

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:book-category-part
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

EXPOSE 8081

COPY target/BookCategoryPart-1.0-SNAPSHOT.jar /app/book-category-part.jar

CMD ["java", "-jar", "/app/book-category-part.jar"]
