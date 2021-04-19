# Summative Assessment Grading Rubric: Tasker Service Project

## Application Documentation - 5%

* Your solution must include a document that outlines the following:
  * The ports on which the various components are running.
  * The URL of the Git repository used to store configuration settings for the various components.

## Tasker

### Bugs - 10%

 * Bugs that were present in starter code must be fixed.

### REST API - 2%

* Must be fully documented in OpenAPI 3.x (Swagger).

### Controller - 10%

* Solution must contain all endpoints listed in the specification.
* Controller must contain code/annotations to validate all inputs.
* Solution must include a Controller Advice class that handles all Controller exceptions.

### Model - 3%

* TastViewModel must contain JSR 303 validation rules that match database limits.

### DAO Implementation - 10%

* All methods must be implemented properly.
* Solution must contain a RowMapper method.
* Solution must contain a full test suite for the DAO.

### Service Layer - 20%

* All methods must be properly implemented.
* Adserver Service must be integrated to supply ads for each Task.
* Code must find the Adserver via Eureka.
* Solution must contain a full test suite for the Service Layer. It must use Mockito.

### Configuration - 7%

* All configuration settings must come from a Spring Cloud Config Server.

## Adserver Service

### REST API - 2%

* The API must be documented in OpenAPI 3.x (Swagger).

### Configuration - 7%

* All configuration settings must come from a Spring Cloud Config Server.

## Spring Cloud Config Server - 9%

* The solution must contain a Spring Cloud Config Server

## Eureka Service Registry - 15%

* The solution must contain a Eureka Service Registry.

---

© 2019 Trilogy Education Services
