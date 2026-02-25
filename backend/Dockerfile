FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the fat JAR
COPY backend/build/libs/backend-1.0.0-SNAPSHOT.jar app.jar

# Expose the backend port
EXPOSE 8080

# Set environment variables
ENV PORT=8080
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
