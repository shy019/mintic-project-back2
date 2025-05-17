# Usa la imagen oficial de OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el jar construido a la imagen
COPY target/*.jar app.jar

# Expone el puerto que usa Spring Boot (por defecto 8080)
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
