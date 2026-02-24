# Quick Start Guide

## Step 1: Import into IntelliJ IDEA

1. Open IntelliJ IDEA
2. File → Open → Select the `scala-hello-world` directory
3. IntelliJ will detect it as a Gradle project
4. Wait for the project to sync and download dependencies (this may take a few minutes)

## Step 2: Verify Project Setup

After the project loads:
1. Check that Project SDK is set to Java 21:
   - File → Project Structure → Project → SDK
2. Verify Gradle settings:
   - File → Settings → Build, Execution, Deployment → Build Tools → Gradle
   - Should use Gradle 9.1.4

## Step 3: Build the Project

Open the Terminal in IntelliJ (View → Tool Windows → Terminal) and run:

```bash
gradle clean build
```

This will:
- Compile both backend and frontend
- Run any tests
- Create fat JARs in `build/libs/` for each project

Expected output:
```
BUILD SUCCESSFUL in 30s
```

## Step 4: Run the Backend

**Option A: From IntelliJ**
1. Navigate to `backend/src/main/scala/com/example/backend/Main.scala`
2. Right-click on `Main` object
3. Select "Run 'Main'"
4. Backend will start on `http://localhost:8080`

**Option B: From Terminal**
```bash
gradle :backend:run
```

**Option C: Using the JAR**
```bash
java -jar backend/build/libs/backend-1.0.0-SNAPSHOT-all.jar
```

You should see:
```
Cask server started at http://0.0.0.0:8080
```

## Step 5: Test the Backend

Open a new terminal and test the endpoints:

```bash
# Test root endpoint
curl http://localhost:8080/

# Test hello endpoint
curl http://localhost:8080/api/hello?name=Scala

# Test status endpoint
curl http://localhost:8080/api/status
```

Expected responses:
- Root: `"Backend is running!"`
- Hello: `{"message":"Hello, Scala!","timestamp":1234567890}`
- Status: `{"status":"ok","service":"backend","version":"1.0.0"}`

## Step 6: Run the Frontend

Open a **new terminal** (keep backend running) and:

**Option A: From IntelliJ**
1. Navigate to `frontend/src/main/scala/com/example/frontend/Server.scala`
2. Right-click on `Server` object
3. Select "Run 'Server'"
4. Frontend will start on `http://localhost:3000`

**Option B: From Terminal**
```bash
gradle :frontend:run
```

**Option C: Using the JAR**
```bash
java -jar frontend/build/libs/frontend-1.0.0-SNAPSHOT-all.jar
```

You should see:
```
Cask server started at http://0.0.0.0:3000
```

## Step 7: Test the Application

1. Open your browser to: `http://localhost:3000`
2. You should see the "Scala Hello World Application" page
3. Enter your name and click "Say Hello"
4. The frontend will call the backend API and display the response

## Troubleshooting

### Port Already in Use
If you get "Address already in use" error:

**Backend (port 8080):**
```bash
PORT=9090 gradle :backend:run
```

**Frontend (port 3000):**
```bash
PORT=4000 gradle :frontend:run
```

Remember to update the `BACKEND_URL` in `frontend/src/main/resources/index.html` if you change the backend port.

### Dependencies Not Downloading
1. Check your internet connection
2. Try: `gradle clean build --refresh-dependencies`
3. Check if Maven Central is accessible

### IntelliJ Not Recognizing Scala
1. Install the Scala plugin: File → Settings → Plugins → Search "Scala" → Install
2. Restart IntelliJ
3. File → Invalidate Caches → Invalidate and Restart

### "Cannot resolve symbol" errors
1. File → Invalidate Caches → Invalidate and Restart
2. Right-click on project → Gradle → Refresh Gradle Project

## Docker Deployment (Optional)

After building the project:

```bash
# Build images
docker-compose build

# Run both services
docker-compose up

# Or run in detached mode
docker-compose up -d

# Stop services
docker-compose down
```

Access:
- Frontend: `http://localhost:3000`
- Backend: `http://localhost:8080`

## What You've Built

✅ **Backend Service**: REST API with 3 endpoints
✅ **Frontend Service**: Web server serving static HTML
✅ **Two Separate Deployables**: Independent fat JARs
✅ **Containerization Ready**: Dockerfiles and docker-compose
✅ **Production Ready**: Self-contained artifacts

## Next Steps

Now that you have a working skeleton:

1. **Add More Endpoints**: Extend the backend API
2. **Database Integration**: Add Slick or Doobie
3. **Testing**: Add ScalaTest for unit tests
4. **Configuration**: Use Typesafe Config or HOCON
5. **Logging**: Enhance logging with structured logs
6. **Security**: Add authentication/authorization
7. **API Documentation**: Add Swagger/OpenAPI

Enjoy building with Scala! 🎉
