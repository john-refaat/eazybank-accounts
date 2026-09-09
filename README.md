# Accounts Microservice

## Overview
    
The Accounts microservice is a Spring Boot-based REST API that manages customer accounts in a banking system. This
service provides CRUD operations for customer accounts and their associated information.

## Features

- Create, read, update, and delete customer accounts
- Manage customer information (name, email, phone number)
- Track account details (account number, account type, branch address)
- RESTful API endpoints with comprehensive validation
- Integrated H2 in-memory database for development and testing
- OpenAPI/Swagger documentation for API exploration
- Spring Boot Actuator for monitoring and health checks

## Technologies Used

- **Java 21**
- **Spring Boot 4.1.0**
- **Spring Data JPA** - Database operations
- **Spring Validation** - Request validation
- **H2 Database** - In-memory database
- **Lombok** - Reduce boilerplate code
- **SpringDoc OpenAPI 3.1.0** - API documentation
- **Maven** - Build and dependency management

## Prerequisites

- Java 21 or higher
- Maven 3.6+ (or use the included Maven wrapper)

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd accounts
   ```

2. **Build the project**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

## Running the Application

The application will start on port **8080** by default.

### Access Points

- **Application URL**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
    - JDBC URL: `jdbc:h2:mem:loansdb`
    - Username: `sa`
    - Password: (leave empty)

- **API Documentation (Swagger UI)**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **Actuator Endpoints**: http://localhost:8080/actuator

## Database Schema

The application uses two main tables:

### Customer Table

- `customer_id` (Primary Key)
- `name`
- `email`
- `phone_number`
- Audit fields: `created_at`, `created_by`, `updated_at`, `updated_by`

### Accounts Table

- `account_number` (Primary Key)
- `customer_id` (Foreign Key)
- `account_type`
- `branch_address`
- Audit fields: `created_at`, `created_by`, `updated_at`, `updated_by`

## API Documentation

The API is fully documented using OpenAPI 3.0 specification. Once the application is running, you can explore and test
the API endpoints using the Swagger UI interface.

**API Details:**

- Title: Accounts API
- Version: 1.0
- Description: Documentation Accounts API Microservice v1.0

## Development

This project uses:

- Spring Boot DevTools for hot reload during development
- Lombok to reduce boilerplate code
- Hibernate for ORM with automatic schema updates
- Spring MVC Problem Details for standardized error responses

## Contact

**Developer**: John Estefanos  
**Email**: johnrstefanos@gmail.com

## License

This project is part of the EazyBytes microservices tutorial series.
