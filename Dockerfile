# ===== Fase 1: Build Maven =====
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Ottimizzazione: copia prima solo pom.xml per caching dipendenze
COPY pom.xml .
# Download dipendenze (layer cachabile se pom.xml non cambia)
RUN mvn dependency:go-offline -B

# Copia codice sorgente
COPY src ./src

# Compila e crea il WAR (skip tests per velocizzare build)
RUN mvn clean package -DskipTests -B

# ===== Fase 2: Runtime Java =====
FROM eclipse-temurin:17-jre
WORKDIR /app

# Installa curl per health checks
RUN apt-get update && \
    apt-get install -y curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copia il WAR generato nella fase di build
COPY --from=build /app/target/vaporant-0.0.1-SNAPSHOT.war app.war

# Espone la porta dell'applicazione
EXPOSE 8080

# Health check per verificare che l'app sia attiva
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# Variabili d'ambiente con valori di default (override da docker-compose)
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/storage \
    SPRING_DATASOURCE_USERNAME=root \
    SPRING_DATASOURCE_PASSWORD=password

# Comando di avvio dell'applicazione
ENTRYPOINT ["java", "-jar", "app.war"]
