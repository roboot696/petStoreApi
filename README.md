# Swagger Petstore Regression Tests

This repository contains regression tests for the Swagger Petstore service implemented in Java using Maven, Cucumber, and Gherkin. The tests cover both service regression and endpoint testing of the Swagger Petstore API, which is available at [https://github.com/swagger-api/swagger-petstore](https://github.com/swagger-api/swagger-petstore).

## Prerequisites

Ensure the following prerequisites are met before running the tests:

- Java version 21
- Maven 
- Deploy and have the Swagger Petstore service active locally according to the instructions in [this repository](https://github.com/swagger-api/swagger-petstore).

## Libraries Used

The project utilizes the following libraries:

- Logback Classic: `1.2.3`
- JUnit: `4.13.2`
- AssertJ Core: `3.19.0`
- Serenity Core: `4.1.3`
- Serenity Screenplay: `4.1.3`
- Serenity Cucumber: `4.1.3`
- Serenity REST Assured: `4.1.3`
- Cucumber Java: `7.15.0`
- Cucumber Core: `7.15.0`
- Cucumber JUnit: `7.15.0`
- REST Assured: `5.4.0`

## Maven Configuration

The project's `pom.xml` file includes Maven dependencies and plugins. Below are key configurations:

- Maven Surefire Plugin: `3.2.5`
- Maven Failsafe Plugin: `3.2.5`
- Maven Compiler Plugin: `3.8.0`
- Serenity Maven Plugin: `4.1.3`

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/swagger-petstore-regression.git
   cd swagger-petstore-regression

2. **Install dependencies using Maven**

    ```bash
    mvn clean verify
    
    
## Performance Tests

Performance tests are available in [this repository](https://github.com/roboot696/performanceApiPetStore).

