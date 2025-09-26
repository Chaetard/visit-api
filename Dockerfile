# ============================
# Etapa de compilación
# ============================
FROM gradle:8.5-jdk21 AS build
WORKDIR /home/gradle/src

# Copiamos solo lo necesario primero (para aprovechar cache de Docker)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copiamos el resto del proyecto
COPY . .

# 🔹 Dar permisos al wrapper de gradle
RUN chmod +x ./gradlew

# 🔹 Compilar proyecto con bootJar (Spring Boot), sin tests
RUN ./gradlew clean bootJar -x test --no-daemon

# ============================
# Etapa final: imagen ligera
# ============================
FROM openjdk:21-jdk-slim
WORKDIR /app

# 🔹 Copiar el .jar generado y renombrarlo como app.jar
COPY --from=build /home/gradle/src/build/libs/visit-counter-api-0.0.1-SNAPSHOT.jar app.jar

# Render asigna dinámicamente un puerto → Spring Boot debe respetar PORT
EXPOSE 8080

# 🔹 Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]