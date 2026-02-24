# Scala Hello World Application

A simple Scala application demonstrating frontend and backend as separate deployable artifacts.

## Project Structure

```
scala-hello-world/
├── backend/          # REST API backend
│   └── src/
│       └── main/
│           ├── scala/
│           │   └── com/example/backend/Main.scala
│           └── resources/
│               └── logback.xml
├── frontend/         # Web frontend
│   └── src/
│       └── main/
│           ├── scala/
│           │   └── com/example/frontend/Server.scala
│           └── resources/
│               └── index.html
├── build.gradle      # Root build configuration
└── settings.gradle   # Multi-project settings
```

## Prerequisites

- Java 21
- Gradle 9.1.4
- IntelliJ IDEA (recommended)

## Building the Project

### Build both projects
```bash
gradle build
```

This will create two fat JARs:
- `backend/build/libs/backend-1.0.0-SNAPSHOT-all.jar`
- `frontend/build/libs/frontend-1.0.0-SNAPSHOT-all.jar`

### Build individual projects
```bash
gradle :backend:build
gradle :frontend:build
```

## Running the Application

### Option 1: Using Gradle (Development)

**Terminal 1 - Start Backend:**
```bash
gradle :backend:run
```
Backend will start on `http://localhost:8080`

**Terminal 2 - Start Frontend:**
```bash
gradle :frontend:run
```
Frontend will start on `http://localhost:3000`

### Option 2: Using Fat JARs (Production)

**Terminal 1 - Start Backend:**
```bash
java -jar backend/build/libs/backend-1.0.0-SNAPSHOT-all.jar
```

**Terminal 2 - Start Frontend:**
```bash
java -jar frontend/build/libs/frontend-1.0.0-SNAPSHOT-all.jar
```

### Option 3: Custom Ports
```bash
PORT=9090 java -jar backend/build/libs/backend-1.0.0-SNAPSHOT-all.jar
PORT=4000 java -jar frontend/build/libs/frontend-1.0.0-SNAPSHOT-all.jar
```

## Testing the Application

1. Open your browser to `http://localhost:3000`
2. Enter your name in the input field
3. Click "Say Hello" to call the backend API
4. Click "Check Backend Status" to verify backend connectivity

## API Endpoints

### Backend (Port 8080)
- `GET /` - Health check
- `GET /api/hello?name=World` - Returns greeting message
- `GET /api/status` - Returns backend status

### Frontend (Port 3000)
- `GET /` - Serves the web application

## IntelliJ IDEA Setup

1. Open the project in IntelliJ IDEA
2. File → Project Structure → Project SDK → Select Java 21
3. IntelliJ should auto-detect the Gradle project
4. Wait for dependency download to complete
5. Right-click on `backend/src/main/scala/com/example/backend/Main.scala` → Run
6. Right-click on `frontend/src/main/scala/com/example/frontend/Server.scala` → Run

## Containerization

Both artifacts are self-contained fat JARs ready for containerization:

### Backend Dockerfile Example
```dockerfile
FROM eclipse-temurin:21-jre
COPY backend/build/libs/backend-1.0.0-SNAPSHOT-all.jar /app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]
```

### Frontend Dockerfile Example
```dockerfile
FROM eclipse-temurin:21-jre
COPY frontend/build/libs/frontend-1.0.0-SNAPSHOT-all.jar /app.jar
EXPOSE 3000
CMD ["java", "-jar", "/app.jar"]
```

## Technology Stack

- **Language**: Scala 3.3.1
- **Build Tool**: Gradle 9.1.4
- **JVM**: Java 21
- **Backend Framework**: Cask (lightweight HTTP server)
- **Frontend**: Static HTML/CSS/JavaScript served by Cask
- **JSON**: uPickle

## Next Steps

- Add database connectivity (e.g., Slick, Doobie)
- Implement proper error handling
- Add unit tests (ScalaTest)
- Add CORS configuration for production
- Implement authentication/authorization
- Add OpenAPI/Swagger documentation
- Set up CI/CD pipeline
