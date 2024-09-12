# Alapértelmezett Java kép
FROM openjdk:21-jdk

# Munkakönyvtár létrehozása
WORKDIR /app

# JAR fájl másolása a target könyvtárból a konténerbe
COPY target/demo-0.0.1-SNAPSHOT.jar /app/football-project-app.jar

# Port kitettség
EXPOSE 8080

# Futtatási parancs
CMD ["java", "-jar", "football-project-app.jar"]

