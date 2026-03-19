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
2. Documentation check (threat model + assignment files) and evidence artifact upload
3. SAST with Semgrep (`p/java` and `p/owasp-top-ten`)
4. Dependency scan with OWASP Dependency-Check
5. Optional DAST with OWASP ZAP baseline scan (exports HTML + JSON artifacts)

The pipeline runs on every push, pull request, and manual trigger.

Optional ZAP run options:

- Manual trigger: run workflow with `run_zap=true` and set `zap_target_url`
- Auto-enable via repo variables:
  - `ZAP_ENABLED=true`
  - `ZAP_TARGET_URL=http://localhost:8080` (or your deployed URL)

## Project Structure

- `src/` - Spring Boot application source
- `docs/assignment/` - assignment brief and module reference material
- `docs/threat-model/` - formal STRIDE threat model files
- `docs/evidence/screenshots/` - implementation and scan screenshots
- `docs/evidence/dast/zap/session/` - OWASP ZAP session artifacts

## Security Notes

- Passwords are stored as BCrypt hashes.
- Role-based access control is enforced with `USER` and `LIBRARIAN`.
- Method-level authorization uses `@PreAuthorize`.

Before publishing publicly, remove or externalize any development-only seeded credentials.
