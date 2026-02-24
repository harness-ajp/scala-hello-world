# Architecture Overview

## System Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                         User's Browser                          │
│                    http://localhost:3000                        │
└──────────────────────────┬──────────────────────────────────────┘
                           │
                           │ HTTP GET /
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│                     Frontend Service                            │
│                  (Scala + Cask Server)                          │
│                                                                  │
│  - Serves static HTML/CSS/JS                                    │
│  - Port: 3000 (configurable)                                    │
│  - Artifact: frontend-1.0.0-SNAPSHOT-all.jar                    │
│                                                                  │
└──────────────────────────┬──────────────────────────────────────┘
                           │
                           │ JavaScript Fetch
                           │ GET /api/hello?name=...
                           │ GET /api/status
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│                      Backend Service                            │
│                  (Scala + Cask Server)                          │
│                                                                  │
│  Endpoints:                                                     │
│  - GET  /              → "Backend is running!"                  │
│  - GET  /api/hello     → JSON greeting                          │
│  - GET  /api/status    → JSON status info                       │
│                                                                  │
│  Port: 8080 (configurable)                                      │
│  Artifact: backend-1.0.0-SNAPSHOT-all.jar                       │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

## Deployment Architecture

```
┌────────────────────────────────────────────────────────────────────┐
│                       Kubernetes Cluster                           │
│                                                                    │
│  ┌──────────────────────┐      ┌──────────────────────┐          │
│  │   Frontend Pod       │      │    Backend Pod       │          │
│  │                      │      │                      │          │
│  │  Container:          │      │  Container:          │          │
│  │  - Image: frontend   │      │  - Image: backend    │          │
│  │  - Port: 3000        │      │  - Port: 8080        │          │
│  │  - JAR: frontend-*.  │      │  - JAR: backend-*.   │          │
│  │         jar          │      │         jar          │          │
│  └──────────┬───────────┘      └──────────┬───────────┘          │
│             │                              │                      │
│  ┌──────────▼───────────┐      ┌──────────▼───────────┐          │
│  │  Frontend Service    │      │   Backend Service    │          │
│  │  (LoadBalancer)      │      │   (ClusterIP)        │          │
│  │  Port: 80 → 3000     │      │   Port: 8080         │          │
│  └──────────────────────┘      └──────────────────────┘          │
│                                                                    │
└────────────────────────────────────────────────────────────────────┘
```

## Build Artifacts

### Backend JAR
- **File**: `backend/build/libs/backend-1.0.0-SNAPSHOT-all.jar`
- **Type**: Fat JAR (includes all dependencies)
- **Size**: ~15-20 MB
- **Entry Point**: `com.example.backend.Main`

### Frontend JAR
- **File**: `frontend/build/libs/frontend-1.0.0-SNAPSHOT-all.jar`
- **Type**: Fat JAR (includes all dependencies + static resources)
- **Size**: ~15-20 MB
- **Entry Point**: `com.example.frontend.Server`

## Technology Stack

| Component | Technology | Purpose |
|-----------|-----------|---------|
| Language | Scala 3.3.1 | Primary programming language |
| Build Tool | Gradle 9.1.4 | Multi-project builds |
| JVM | Java 21 | Runtime environment |
| Backend Framework | Cask 0.9.2 | HTTP server and routing |
| JSON Library | uPickle 3.1.3 | JSON serialization |
| Logging | Logback 1.4.14 | Application logging |
| Frontend | HTML/CSS/JavaScript | User interface |
| Container | Eclipse Temurin 21 | Docker base image |

## Communication Flow

1. **User Request**: Browser → Frontend Service (port 3000)
2. **Page Load**: Frontend serves `index.html` with embedded JavaScript
3. **API Call**: JavaScript → Backend Service (port 8080) via fetch
4. **Response**: Backend → Frontend → User (JSON response displayed)

## Development Workflow

```
Developer → IntelliJ IDEA → Gradle Build → Fat JARs → Docker Images → Kubernetes
```

1. **Code**: Write Scala code in IntelliJ
2. **Build**: `gradle build` creates fat JARs
3. **Test Locally**: Run with `gradle run` or execute JARs
4. **Containerize**: Build Docker images
5. **Deploy**: Push to container registry and deploy to K8s

## Scaling Considerations

- **Frontend**: Stateless, can scale horizontally
- **Backend**: Stateless, can scale horizontally
- **Load Balancing**: Use K8s Service or external load balancer
- **Database**: Add database service when needed (not in skeleton)

## Security Notes

- **CORS**: Not configured in skeleton (add for production)
- **Authentication**: Not implemented (add JWT/OAuth as needed)
- **HTTPS**: Not configured (use ingress controller in K8s)
- **Input Validation**: Minimal (enhance for production)
