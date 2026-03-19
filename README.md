# Secure Mini Library Management Web Application

Spring Boot web application for a Secure Software Development module project.

## Tech Stack

- Spring Boot 3.x
- Spring Web
- Spring Security
- Spring Data JPA
- Thymeleaf
- H2 Database
- Maven

## Run Locally

```bash
mvn spring-boot:run
```

App default URL: `http://localhost:8080`

## CI Security Pipeline

GitHub Actions workflow is defined in:

- `.github/workflows/ci-security.yml`

Stages:

1. Build + tests (`mvn clean verify`)
2. SAST with Semgrep (`p/java` and `p/owasp-top-ten`)
3. Dependency scan with OWASP Dependency-Check

The pipeline runs on every push, pull request, and manual trigger.

## Security Notes

- Passwords are stored as BCrypt hashes.
- Role-based access control is enforced with `USER` and `LIBRARIAN`.
- Method-level authorization uses `@PreAuthorize`.

Before publishing publicly, remove or externalize any development-only seeded credentials.
