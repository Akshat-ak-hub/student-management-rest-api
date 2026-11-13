# Student Management REST API

A Spring Boot REST API for managing student records, built with Spring Data JPA and MySQL.

## Tech stack
- Java 17, Spring Boot 3.3
- Spring Web, Spring Data JPA, Bean Validation
- MySQL 8

## Setup
1. Install JDK 17+, Maven, and MySQL.
2. Edit `src/main/resources/application.properties` and set your MySQL `username` and `password`.
   The database `student_db` is created automatically on first run, and the `students` table is created by Hibernate.
3. Run:
   ```bash
   mvn spring-boot:run
   ```
   The API starts at `http://localhost:8080`.

## Endpoints

| Method | Endpoint | Description | Success |
|---|---|---|---|
| POST | `/api/students` | Add a new student | 201 |
| GET | `/api/students` | Get all students | 200 |
| GET | `/api/students/{id}` | Get student by ID | 200 |
| PUT | `/api/students/{id}` | Update a student | 200 |
| DELETE | `/api/students/{id}` | Delete a student | 200 |
| GET | `/api/students/course/{course}` | Get students by course | 200 |

### Error responses
| Situation | Status |
|---|---|
| Student ID not found | 404 |
| Invalid input (blank name, bad email, age out of range) | 400 |
| Email already used by another student | 409 |

### Sample request body
```json
{
  "name": "Aarav Sharma",
  "email": "aarav@example.com",
  "course": "Computer Science",
  "age": 20
}
```

## Testing with Postman
Import `postman/Student_Management_API.postman_collection.json` and run the requests in order.
Screenshots are in the `screenshots/` folder.

## Project structure
```
src/main/java/com/example/student/
├── StudentManagementApplication.java
├── controller/StudentController.java
├── service/StudentService.java
├── repository/StudentRepository.java
├── entity/Student.java
└── exception/ (StudentNotFoundException, DuplicateEmailException, ErrorResponse, GlobalExceptionHandler)
```
