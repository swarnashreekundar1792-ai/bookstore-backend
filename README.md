# Online Bookstore Application — Backend

A RESTful backend API for an Online Bookstore application built with Java and Spring Boot.

## Tech Stack
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA / Hibernate
- MySQL 8.0.45
- Maven

## Project Structure
```
src/main/java/com/bookstore/api/
├── entity/         → Book.java, Category.java
├── repository/     → BookRepository.java, CategoryRepository.java
├── service/        → BookService.java, CategoryService.java
└── controller/     → BookController.java, CategoryController.java

```

## Database
- Database: MySQL 8.0.45
- Tables: books, categories
- Relationship: One Category has many Books (One-to-Many)

## API Endpoints

### Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/books | Get all books |
| GET | /api/books/{id} | Get book by ID |
| POST | /api/books | Create new book |
| PUT | /api/books/{id} | Update book |
| DELETE | /api/books/{id} | Delete book |
| GET | /api/books/search/{keyword} | Search books by keyword |
| GET | /api/books/category/{categoryId} | Get books by category |

### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/categories | Get all categories |
| GET | /api/categories/{id} | Get category by ID |
| POST | /api/categories | Create category |
| PUT | /api/categories/{id} | Update category |
| DELETE | /api/categories/{id} | Delete category |


## How to Run Locally

1. Install Java 21, MySQL 8, Maven
2. Create MySQL database: bookstore\_db
3. Clone this repository
4. Open in Eclipse IDE
5. Update src/main/resources/application.properties with your MySQL password
6. Run BookstoreBackendApplication.java
7. Backend starts at http://localhost:8080

## Frontend Repository

https://github.com/swarnashreekundar1792-ai/bookstore-frontend

```