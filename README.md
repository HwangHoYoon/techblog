# Tech Blog Platform - Backend MVP

## Tech Stack
- Spring Boot 3.2.4
- Java 17
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- Lombok
- commonmark-java

## How to Run
1. Create a PostgreSQL database named 	echblog.
2. Update src/main/resources/application.yml with your database credentials.
3. Run ./gradlew bootRun.

## API Endpoints
- /api/auth/register (POST)
- /api/auth/login (POST)
- /api/auth/refresh (POST)
- /api/users/{username} (GET)
- /api/users/me (PUT)
- /api/posts (GET, POST)
- /api/posts/{postId} (PUT, DELETE)
- /api/posts/user/{username} (GET)
- /api/posts/{username}/{slug} (GET)
- /api/posts/{postId}/comments (GET, POST)
- /api/comments/{commentId} (PUT, DELETE)

