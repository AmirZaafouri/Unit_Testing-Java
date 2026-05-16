# Unit_Testing-Java
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/AmirZaafouri/Unit_Testing-Java)

This repository contains a Spring Boot application for managing a ski station. The primary focus of this project is to demonstrate and implement unit testing for service-layer components in a Java environment using JUnit 5 and Mockito.

## Overview

The application provides a backend system for a ski resort, managing entities such as skiers, pistes (ski trails), courses, instructors, and subscriptions. It exposes a RESTful API for all management operations. The core business logic is encapsulated in service classes, which are thoroughly unit-tested.

## Features

*   **Skier Management**: Add, update, retrieve, and delete skiers. Assign skiers to subscriptions and pistes.
*   **Piste Management**: CRUD operations for ski pistes.
*   **Course Management**: Manage ski courses, including adding, updating, and retrieving course details.
*   **Instructor Management**: Handle instructor data and assign them to courses.
*   **Subscription Management**: Manage different types of subscriptions (Annual, Monthly, Semestrial).
*   **Registration Management**: Handle skier registrations for courses.
*   **API Documentation**: Integrated Swagger UI for easy API exploration and testing.

## Technology Stack

*   **Framework**: Spring Boot 2.6.9
*   **Language**: Java 8
*   **Build Tool**: Maven
*   **Database**: MySQL
*   **Data Persistence**: Spring Data JPA / Hibernate
*   **Testing**: JUnit 5, Mockito
*   **API Documentation**: Springdoc OpenAPI (Swagger)
*   **CI/CD**: Jenkins (see `jenkinsFile`)

## Unit Testing

This project places a strong emphasis on unit testing the service layer.

*   **Frameworks Used**: JUnit 5 for the test framework and Mockito for creating mock objects.
*   **Coverage**: The tests focus on the service implementation classes (`*ServicesImpl`). Examples include `CourseServicesImplTest.java` and `RegistrationServicesImplTest.java`.
*   **Methodology**: Mocks are created for repository dependencies (`@Mock`) to isolate the service layer during tests. The service under test is then injected with these mocks (`@InjectMocks`). Test cases cover various scenarios, including successful execution paths and exception handling.

## Getting Started

### Prerequisites

*   JDK 1.8 or later
*   Maven 3.6+
*   MySQL Server

### Configuration

1.  Clone the repository:
    ```bash
    git clone https://github.com/AmirZaafouri/Unit_Testing-Java.git
    cd Unit_Testing-Java
    ```
2.  Set up the database:
    *   Open the `src/main/resources/application.properties` file.
    *   Modify the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your local MySQL configuration.
    *   The application is configured to automatically create the database `stationSki` if it does not exist.

### Running the Application

You can run the application using the Maven Spring Boot plugin:

```bash
mvn spring-boot:run
```

The application will start on port `8088` with the context path `/api`.

### Running Tests

To run the unit tests for the project, execute the following Maven command:

```bash
mvn test
```

## API Documentation

Once the application is running, you can access the Swagger UI for API documentation and interaction at the following URL:

[http://localhost:8088/api/swagger-ui.html](http://localhost:8088/api/swagger-ui.html)
