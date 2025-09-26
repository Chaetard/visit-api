# Etapa de compilación con Gradle y JDK 21
FROM gradle:8.5-jdk21 AS build
WORKDIR /home/gradle/src

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY . .

# 🔹 Aquí damos permisos al gradlew
RUN chmod +x ./gradlew

# Compilar (sin tests)
RUN ./gradlew clean build -x test --no-daemon