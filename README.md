# 🎓 EduManage

EduManage is a university management system built with **Java and Spring Boot**.
The project provides role-based access for administrators, professors, and students, while supporting management of students, professors, courses, enrollments, and grades.

The application was built as a backend-focused project to explore Spring Boot architecture, REST APIs, authentication and authorization, testing, database persistence, and containerized deployment.

---

## 🚀 Live Demo

Try EduManage live:

```text
http://64.226.77.249:8080/login
```

### Demo Credentials

| Role        | Username      | Password         |
| ----------- | ------------- | ----------------- |
| `ADMIN`     | admin          | admin123           |
| `PROFESSOR` | professor      | professor123       |
| `STUDENT`   | 14024          | student123          |

> ⚠️ Note: This is a demo instance hosted on a personal VPS for portfolio purposes. Response times may vary, and data may be periodically reset.

Swagger UI:

```text
http://64.226.77.249:8080/swagger-ui/index.html
```
---

## ✨ Features

### Student Management

* Create, update, view, and delete students
* Unique student index numbers
* Input validation for student data
* Student accounts linked to student profiles

### Professor Management

* Manage professor records
* Assign professors to courses
* Many-to-many relationship between professors and courses

### Course Management

* Create, update, and delete courses
* Assign multiple professors to a course
* Prevent duplicate course names

### Enrollment & Grades

* Enroll students in courses
* Prevent duplicate enrollments
* Assign and update grades
* Grade validation
* View enrollments by student

### Authentication & Authorization

EduManage uses **Spring Security** with session-based authentication and role-based authorization.

Three roles are supported:

| Role        | Access                                                |
| ----------- | ----------------------------------------------------- |
| `ADMIN`     | Manage students, professors, courses, and enrollments |
| `PROFESSOR` | Access courses and enrollments                        |
| `STUDENT`   | Access personal student dashboard                     |

Unauthorized access is blocked through Spring Security rules.

### Student Dashboard

Students can sign in using their account and access a personal dashboard containing their profile information, enrolled courses, and grades.

### REST API

In addition to the Thymeleaf interface, EduManage exposes REST endpoints for the core domain entities.

```text
/api/v1/students
/api/v1/professors
/api/v1/courses
/api/v1/enrollments
```

The REST layer uses DTOs and mapping instead of exposing JPA entities directly.

### API Documentation

REST endpoints are documented using **OpenAPI / Swagger UI**.

After starting the application:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI specification:

```text
http://localhost:8080/v3/api-docs
```

---

## 🛠 Tech Stack

### Backend

* Java 21
* Spring Boot 3.2
* Spring MVC
* Spring Data JPA
* Spring Security
* Hibernate
* Jakarta Validation

### Database

* MySQL 8

### API

* REST
* DTO / Mapper pattern
* OpenAPI / Swagger

### Frontend

* Thymeleaf
* Bootstrap 5

### Testing

* JUnit 5
* Mockito
* MockMvc
* Spring Security Test

### Infrastructure

* Docker
* Docker Compose
* Maven

---

## 🏗 Architecture

The application follows a layered architecture:

```text
                    ┌─────────────────────┐
                    │      Thymeleaf      │
                    │        Views        │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │   MVC Controllers   │
                    └──────────┬──────────┘
                               │
                               │
┌───────────────┐    ┌─────────▼──────────┐
│   REST API    │───▶│      Services      │
│ Controllers   │    │  + Business Logic │
└───────┬───────┘    └─────────┬──────────┘
        │                       │
        │ DTOs / Mappers        │
        │             ┌─────────▼──────────┐
        └────────────▶│    Repositories    │
                      └─────────┬──────────┘
                                │
                      ┌─────────▼──────────┐
                      │       MySQL        │
                      └────────────────────┘
```

The service layer contains the core business logic, while repositories are responsible for database access.

---

## 🔐 Security

Security is implemented using Spring Security.

Example authorization rules:

```text
/students/**        ADMIN
/profesors/**       ADMIN
/courses/**         ADMIN, PROFESSOR
/upisi/**           ADMIN, PROFESSOR
/student/**         STUDENT
/api/**             Authenticated users
```

Passwords are stored using **BCrypt hashing**.

After successful authentication, users are redirected according to their role.

---

## 🧪 Testing

The project contains **44 automated tests** covering both business logic and security behavior.

### Unit tests

Service-layer tests use **JUnit 5 and Mockito** to verify:

* Student operations
* Enrollment creation
* Duplicate enrollment prevention
* Grade validation
* Entity retrieval and deletion
* Exception scenarios

### Security tests

**MockMvc and Spring Security Test** are used to verify role-based access.

Examples:

```text
ADMIN      → can access /students
PROFESSOR  → cannot access /students
PROFESSOR  → can access /courses
STUDENT    → can access /student/dashboard
STUDENT    → cannot access administration endpoints
```

Run all tests with:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

---

## 🚀 Running the Application

### Docker

The easiest way to start EduManage is with Docker Compose.

Clone the repository:

```bash
git clone https://github.com/Kico611/EduManage.git
cd EduManage
```

Build the application:

```bash
./mvnw clean package
```

On Windows:

```bash
mvnw.cmd clean package
```

Start the containers:

```bash
docker compose up --build
```

Docker Compose starts both:

```text
Spring Boot application
        +
MySQL database
```

The application will be available at:

```text
http://localhost:8080
```

---

## 💻 Running Without Docker

Requirements:

* Java 21
* MySQL 8
* Maven or included Maven Wrapper

Configure the database connection in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/edumanage
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

Then start the application:

```bash
./mvnw spring-boot:run
```

---

## 📁 Project Structure

```text
src/
├── main/
│   ├── java/com/kristijanbalic/edumanage/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── mapper/
│   │   ├── repository/
│   │   ├── security/
│   │   └── service/
│   │
│   └── resources/
│       ├── templates/
│       └── application.properties
│
└── test/
    └── java/com/kristijanbalic/edumanage/
        ├── security/
        └── service/
```

---

## 📚 What I Learned

EduManage was used to gain practical experience with:

* Designing layered Spring Boot applications
* Implementing service-layer business logic
* Working with JPA entity relationships
* Building REST APIs using DTOs
* Authentication and role-based authorization with Spring Security
* Exception handling and validation
* Unit testing with JUnit and Mockito
* Security testing with MockMvc
* API documentation with Swagger/OpenAPI
* Running Spring Boot and MySQL with Docker Compose

---

## 🔮 Possible Future Improvements

Potential future additions include:

* Dedicated professor dashboard
* Expanded student dashboard
* More fine-grained REST API permissions
* Pagination and filtering
* Database migrations
* CI/CD pipeline
* Additional integration tests

---

## 👤 Author

**Kristijan Balić**

GitHub: [@Kico611](https://github.com/Kico611)
