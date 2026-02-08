# 🎓 Online-Learning-Backend

<div align="center">

[![GitHub stars](https://img.shields.io/github/stars/Rahul-tech12/Online-Learning-Backend?style=for-the-badge)](https://github.com/Rahul-tech12/Online-Learning-Backend/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/Rahul-tech12/Online-Learning-Backend?style=for-the-badge)](https://github.com/Rahul-tech12/Online-Learning-Backend/network)
[![GitHub issues](https://img.shields.io/github/issues/Rahul-tech12/Online-Learning-Backend?style=for-the-badge)](https://github.com/Rahul-tech12/Online-Learning-Backend/issues)
[![GitHub license](https://img.shields.io/github/license/Rahul-tech12/Online-Learning-Backend?style=for-the-badge)](LICENSE)

**Robust and scalable backend services for an online learning platform.**

</div>

## 📖 Overview

This repository hosts the backend service for an online learning platform, built with Java and the Spring Boot framework. It provides a comprehensive set of RESTful APIs to manage courses, users, enrollments.

## ✨ Features

-   🎯 **RESTful API Design**: Well-structured and intuitive API endpoints for all core functionalities.
-   🔐 **JWT Authentication & Authorization**: Secure user access and role-based permissions using JSON Web Tokens and Spring Security.
-   🗄️ **Data Persistence**: Efficient data storage and retrieval using PostgreSQL with Spring Data JPA.
-   ✅ **Input Validation**: Robust validation mechanisms to ensure data integrity and prevent common vulnerabilities.
-   📝 **API Documentation**: Automated, interactive API documentation provided by OpenAPI (Swagger UI) for easy API exploration and integration.
-   🔄 **Database Migrations**: Handles database schema evolution using Hibernate's DDL capabilities.

## 🛠️ Tech Stack

**Backend:**
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-security)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-data-jpa)
[![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)](https://jwt.io/)
[![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)

**Database:**
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)

## 📁 Project Structure

```
Online-Learning-Backend/
├── .mvn/                  # Maven Wrapper files
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── onlinelearning/  # Main application source code
│   │   │           ├── OnlineLearningBackendApplication.java  # Main Spring Boot application class
│   │   │           ├── config/      # Spring Security, JWT, CORS configurations
│   │   │           ├── controller/  # REST API endpoints
│   │   │           ├── model/       # JPA Entities / Database Models
│   │   │           ├── repository/  # Spring Data JPA Repositories
│   │   │           └── service/     # Business logic and services
│   │   └── resources/     # Application resources (e.g., application.properties)
│   └── test/
│       └── java/          # Test source code
├── Docker                 # Dockerfile for containerization
├── LICENSE                # Project license file
├── mvnw                   # Maven Wrapper script (Linux/macOS)
├── mvnw.cmd               # Maven Wrapper script (Windows)
└── pom.xml                # Maven project configuration
```

## ⚙️ Configuration

### Environment Variables & Application Properties
Key application settings are managed through `application.properties` (or `application.yml`) in `src/main/resources`.

| Property                  | Description                                    | Example Value                                  | Required |
| :------------------------ | :--------------------------------------------- | :--------------------------------------------- | :------- |
| `server.port`             | Port for the API server                        | `8080`                                         | Yes      |
| `spring.datasource.url`   | JDBC URL for the MySQL database                | `jdbc:mysql://localhost:3306/online_learning_db` | Yes      |
| `spring.datasource.username` | Username for database access                 | `online_user`                                  | Yes      |
| `spring.datasource.password` | Password for database access                 | `your_password`                                | Yes      |
| `jwt.secret`              | Secret key for signing and verifying JWTs      | `YourSuperSecretKey...`                        | Yes      |
| `jwt.expiration`          | Expiration time for JWTs in milliseconds       | `86400000` (24 hours)                          | Yes      |
| `spring.jpa.hibernate.ddl-auto` | Hibernate DDL generation strategy          | `update`                                       | Yes      |

### Configuration Files
-   `pom.xml`: Defines project dependencies, build plugins, and project metadata for Maven.
-   `src/main/resources/application.properties`: Primary configuration file for Spring Boot.

## 🔧 Development

### Building
To compile and package the application into an executable JAR file:
```bash
./mvnw clean package
```
The executable JAR will be located in the `target/` directory.

### Running Tests
To execute all unit and integration tests:
```bash
./mvnw test
```

## 📚 API Reference

The API endpoints are fully documented using OpenAPI (Swagger UI). Once the application is running, you can access the interactive documentation at:

`http://localhost:8080/swagger-ui.html`

### Authentication
This API uses **JWT (JSON Web Token)** for authentication.
1.  Users typically register or log in to receive a JWT.
2.  This token must be included in the `Authorization` header of subsequent requests as a Bearer token:
    `Authorization: Bearer <your_jwt_token>`

### Example Endpoints
(Please refer to the Swagger UI for a complete and up-to-date list of endpoints, schemas, and example requests.)

-   **User Management:**
    -   `POST /api/auth/register`: Register a new user.
    -   `POST /api/auth/login`: Authenticate and receive a JWT.
    -   `GET /api/users/{id}`: Retrieve user details (requires authentication).

-   **Course Management:**
    -   `GET /api/courses`: Retrieve a list of all courses.
    -   `POST /api/courses`: Create a new course (requires admin/instructor role).
    -   `GET /api/courses/{id}`: Retrieve details of a specific course.

-   **Enrollment:**
    -   `POST /api/enrollments`: Enroll a user in a course (requires authentication).
    -   `GET /api/users/{userId}/enrollments`: View a user's enrolled courses.

<div align="center">

**⭐ Star this repo if you find it helpful!**

Made with ❤️ by Rahul-tech12

</div>
