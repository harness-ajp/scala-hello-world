FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the fat JAR (built by sbt-assembly)
COPY frontend/target/scala-3.3.1/frontend-1.0.0-SNAPSHOT.jar app.jar

# Expose the frontend port
EXPOSE 3000

# Set environment variables
ENV PORT=3000
ENV JAVA_OPTS="-Xmx256m -Xms128m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
