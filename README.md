# Basic-Thymeleaf Application

A professional, full-stack Spring Boot application demonstrating the integration of **Spring Boot 3.4.5**, **Thymeleaf**, and **PostgreSQL**. This project serves as a robust foundation for building data-driven web applications with modern Java patterns.

## 🚀 Overview

Basic-Thymeleaf is a CRUD (Create, Read, Update, Delete) application for managing **Users** and **Products**. It focuses on clean architecture, server-side validation, and dynamic UI rendering using Thymeleaf fragments.

### Key Features
- **User Management**: Complete CRUD operations for user profiles with role-based attributes.
- **Product Management**: Inventory management system with category tracking and stock control.
- **Server-Side Validation**: Robust data integrity using Jakarta Validation (e.g., email format, price range, required fields).
- **Automated DB Initialization**: Uses `schema.sql` to automatically set up tables and indexes on application startup.
- **Dynamic UI Components**: Reusable navigation bar and layout fragments for a consistent user experience.
- **Modern Tech Stack**: Built with Java 21 and Spring Boot 3.4 for maximum performance and security.

## 🛠️ Tech Stack

- **Backend**: 
  - [Spring Boot 3.4.5](https://spring.io/projects/spring-boot)
  - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
  - [Lombok](https://projectlombok.org/)
- **Frontend**:
  - [Thymeleaf](https://www.thymeleaf.org/)
  - Semantic HTML5 & CSS3
- **Database**:
  - [PostgreSQL](https://www.postgresql.org/)
- **Build Tool**:
  - Maven

## 📋 Prerequisites

Before running the application, ensure you have the following installed:
- **JDK 21** or higher
- **PostgreSQL** (running locally or accessible via network)
- **Maven** (optional, wrapper included)

## ⚙️ Setup & Installation

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd Basic-Thymeleaf
   ```

2. **Configure Database**:
   - Create a new PostgreSQL database named `basic_thymeleaf_db`.
   - Open `src/main/resources/application.properties` and update the credentials:
     ```properties
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Run the Application**:
   Using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   Or via your IDE (Run `BasicThymeleafApplication.java`).

4. **Access the App**:
   Open your browser and navigate to: `http://localhost:8080`

## 📂 Project Structure

- `src/main/java/com/example/basicthymeleaf/controller`: Request handlers for web pages.
- `src/main/java/com/example/basicthymeleaf/model`: JPA entities (User, Product).
- `src/main/java/com/example/basicthymeleaf/repository`: Spring Data JPA repositories.
- `src/main/resources/templates`: Thymeleaf HTML templates.
- `src/main/resources/static`: Static assets (CSS, JS, Images).
- `src/main/resources/schema.sql`: Database schema definition.

## 🛡️ Security & Performance

- **CVE Mitigation**: The `pom.xml` includes explicit version overrides for transitive dependencies (Tomcat, Jackson, Thymeleaf) to ensure protection against known vulnerabilities.
- **Database Indexing**: Optimized query performance using database indexes on frequently filtered columns (category, email, role).

---
*Built with ❤️ for modern Java development.*
