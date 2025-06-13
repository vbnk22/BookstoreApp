# 🛒 Bookstore – Web Application

Bookstore is a sample web application built using **Spring Boot**. It provides functionality for placing orders, and managing the store through an admin panel. The project includes user authentication and a simple shopping cart system.

---

## ✨ Features

✅ Product listing
✅ Shopping cart functionality  
✅ Order placement  
✅ User registration and login (with Spring Security)  
✅ Admin panel to manage products and orders  
✅ Role-based access control (user/admin)  

---

## 🛠 Technologies Used

- Java 17+
- Spring Boot 3.2.5
- Spring MVC
- Spring Security 6
- Spring Data JPA (with Hibernate)
- PostgreSQL
- Thymeleaf
- Maven
- H2 (for development/testing)
- Lombok

---

## 🧩 Requirements

### Environment

- **Java Development Kit (JDK)** 17 or higher
- **Maven** (version 3.6+ recommended)
- **PostgreSQL** (or H2 for local testing)

### Frameworks and Libraries

- **Spring Boot Starters**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-security`
  - `spring-boot-starter-thymeleaf`
  - `spring-boot-starter-test` (test scope)
- **Thymeleaf Extras** for Spring Security 6
- **Hibernate Validator** (8.0.1.Final)
- **PostgreSQL JDBC Driver** (42.7.3)
- **Lombok** (1.18.32)
- **H2 Database** (for in-memory testing)

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Configuration

1. Clone the repository:

```bash
git clone https://github.com/vbnk22/BookstoreApp
```

2. Configure your PostgreSQL database credentials in application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. Build and run the application:

```bash
mvn spring-boot:run
```

4. Access the app:

http://localhost:8080
