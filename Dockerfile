# Koristi openjdk 17 kao osnovnu sliku
FROM openjdk:17

# Kopiraj JAR datoteku unutar kontejnera
COPY target/CRUD-0.0.1-SNAPSHOT.jar app.jar

# Postavi varijablu okruženja
ENV JAVA_OPTS=""

# Pokreni aplikaciju
ENTRYPOINT ["java", "-jar", "/app.jar"]
