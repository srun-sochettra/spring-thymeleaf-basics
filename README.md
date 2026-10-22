# Spring Boot Thymeleaf CRUD

**A server-rendered product and user management exercise with validated forms and PostgreSQL persistence.**

Built with Java 21, Spring Boot 3.4.5, Thymeleaf and Spring Data JPA. Shared page fragments, Bootstrap styling and transactional services connect browser forms to relational records. This is an educational demonstration, not a secured administration system or a verified production deployment.

## Read before starting

**Startup is destructive to the configured database.** application.properties enables SQL initialisation on every startup, and schema.sql drops products and users with CASCADE before recreating them. DataInitializer then inserts sample records when the tables are empty. Changes do not survive a normal restart under this configuration.

Use a disposable database only. Do not point the application at valuable data. This documentation does not change the reset behaviour. Database credentials are committed in properties; replace and rotate them if used anywhere real. Their values are not repeated here.

## Features

- Product list, detail, create/edit forms, deletion and active-status toggles.
- User list, create/edit forms, deletion and active-status toggles.
- Product keyword search and category selection; user name/email search.
- Jakarta validation with form re-rendering and field errors.
- Flash messages after redirects, shared navigation/header/footer fragments and a home dashboard.
- BigDecimal product prices, stock quantities and database-backed email uniqueness.

User roles and active flags are record attributes, not access-control permissions. No login, password model, Spring Security configuration or authorisation enforcement is supplied. This is not an e-commerce checkout or stock-reservation system.

## Local development

Use JDK 21 and an isolated PostgreSQL database named basic_thymeleaf_db, matching the supplied properties. Review the destructive schema first and configure private database connection values. No Docker configuration or environment template is supplied. A root .env file has no supplied loader for direct Maven execution.

Suggested commands from the existing repository root, after those prerequisites:

```powershell
.\mvnw.cmd spring-boot:run
```

POSIX equivalent: `sh mvnw spring-boot:run`. The wrapper selects Maven 3.9.15; initial use may download Maven and dependencies. These commands were not executed during this refresh.

The configured server port is 8080. The application supplies home routes at / and /home, plus /about. No separate frontend build or JavaScript package installation is defined.

## Browser routes

| Method | Path | Purpose |
| --- | --- | --- |
| GET | /products | List; optional keyword or category |
| GET | /products/{id} | Product details |
| GET | /products/new | Create form |
| POST | /products | Create |
| GET | /products/{id}/edit | Edit form |
| POST | /products/{id}/update | Update |
| POST | /products/{id}/delete | Delete |
| POST | /products/{id}/toggle | Toggle active state |
| GET | /users | List; optional keyword |
| GET | /users/new | Create form |
| POST | /users | Create |
| GET | /users/{id}/edit | Edit form |
| POST | /users/{id}/update | Update |
| POST | /users/{id}/delete | Delete |
| POST | /users/{id}/toggle | Toggle active state |

These controllers render HTML and redirects, not a JSON REST API. Missing detail/edit records redirect to the list with an error message.

### Search semantics

Product keyword search matches name/description case-insensitively and returns active products only. Category selection matches case-insensitively but does not restrict active state. The unfiltered list includes all products. A non-blank keyword takes precedence over category; the two filters are not combined.

User search matches first name, last name or email case-insensitively, including inactive records. Search is submitted through GET forms, not a live-search API. No pagination is supplied. The user list's active count is global rather than limited to the current search results.

## Validation and data

Products require a 2–100 character name, positive price with at most two fractional digits, non-negative stock and a non-blank category. Description is limited to 500 characters. Users require 2–50 character first/last names, email syntax, a non-blank role and a phone matching optional plus followed by 10–15 digits.

The role dropdown offers four values, but the model only requires a non-blank string; no server-side role allowlist is supplied. Column lengths are not equivalent to complete form validation. Image URLs are stored as text and loaded by the browser; there is no image upload service.

## Architecture

Controllers -> transactional services -> Spring Data JPA repositories -> PostgreSQL.

- [ProductController.java](src/main/java/com/example/basicthymeleaf/controller/ProductController.java): browser workflow and BindingResult handling.
- [UserServiceImpl.java](src/main/java/com/example/basicthymeleaf/service/UserServiceImpl.java): updates and duplicate-email checks.
- [ProductRepository.java](src/main/java/com/example/basicthymeleaf/repository/ProductRepository.java): JPQL search and category queries.
- [schema.sql](src/main/resources/schema.sql): destructive reset, tables and indexes.
- [DataInitializer.java](src/main/java/com/example/basicthymeleaf/config/DataInitializer.java): sample records.

The POM declares explicit dependency overrides. Comments describing them as safe/latest are not a verified vulnerability assessment. No dependency audit or compatibility build was performed here. Templates still contain older Java/Spring version copy and permission descriptions that are not enforced; this overlay does not edit them.

Bootstrap CSS/JS, icons, fonts and sample images use external hosts. No fully offline, accessibility-conformance or performance guarantee is made. CSS includes ongoing animation without a supplied reduced-motion override.

## Checks

Suggested commands:

```powershell
.\mvnw.cmd test
.\mvnw.cmd package
```

POSIX equivalents use `sh mvnw test` and `sh mvnw package`. The supplied contextLoads test disables SQL initialisation, but still uses the configured datasource and does not disable DataInitializer. Do not run it against valuable data or assume it is an isolated unit test. It has no CRUD/browser assertions.

No dependency resolution, compilation, Maven tests, database access or browser execution was performed during this refresh. Read [CONTRIBUTING.md](CONTRIBUTING.md), [SECURITY.md](SECURITY.md) and [refresh notes](docs/repository-refresh.md).

## Licence

No root project licence file is supplied. Maven wrapper notices do not establish an application licence. This refresh assigns none.
