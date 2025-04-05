FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/artifact/Loans.jar /app/Loans.jar

EXPOSE 8080

CMD ["java", "-jar", "Loans.jar"]