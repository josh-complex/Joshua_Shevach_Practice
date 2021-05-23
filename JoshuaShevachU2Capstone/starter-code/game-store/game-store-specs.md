# Game Store Project

This project involves creating a simple database backed REST inventory management web service for a Video Game Store using Agile development techniques in a solo setting. You are responsible for designing and documenting the REST API and implementing the controller, service, layer, DAO, Java data objects, and unit tests for the application based on an existing database structure.

## Structure
Your solution must have the following structural elements:

* Your solution must be in an IntelliJ project called ```FirstNameLastNameU1Capstone``` where FirstName and LastName are your first and last names respectively.
* Your project must be built using Spring Boot and Spring MVC. Initialize your project using ```start.spring.io```
* Your solution must include a DAO the utilized JdbcTemplates and Prepared Statements
* Your REST API must be documented with Swagger
* Your REST API must accept and return data in JSON format where appropriate
* You must implement ControllerAdvice to handle exceptions and return propery HTTP status codes and data when exception occur

## Methodology
* You must manage your work in Pivotal Tracker
* You must create stories and epics
* You must estimate your work using story points
* You must use a Test Driven Development approach (inluding Red/Green/Refactor) for your code
* You must use JUnit for unit and integration tests
* Your design may include a Service Layer and view model if you deem it necessary
* Your unit test suite should utilize mock objects where appropriate
* Yous should utilize JSR303 for input validation

## Requirements/Features

This system must manage the inventory of video games, game consoles, and t-shirts.

* Your REST API must allow the end user to:
    1. Perform standard CRUD operations for Games
    1. Search for Games by Studio
    1. Search for Games by ESRB Rating
    1. Search for Games by Title
    1. Perform standard CRUD operations for Consoles
    1. Search for Consoles by Manufacturer
    1. Perform standard CRUD operations for T-Shirts
    1. Search for T-Shirts by Color
    1. Search for T-Shirts by Size

You must use the following database structure:

```sql
create schema if not exists game_store;
use game_store;

create table if not exists game (
	game_id int(11) not null auto_increment primary key,
    title varchar(50) not null,
    ersb_rating varchar(50) not null,
    description varchar(255) not null,
    price decimal(5, 2) not null,
    studio varchar(50) not null,
    quantity int(11)
);

create table if not exists console (
	console_id int(11) not null auto_increment primary key,
    model varchar(50) not null,
    manufacturer varchar(50) not null,
    memory_amount varchar(20),
    processor varchar(20),
    price decimal(5, 2) not null,
    quantity int(11) not null
);

create table if not exists t_shirt (
	t_shirt_id int(11) not null auto_increment primary key,
    size varchar(20) not null,
    color varchar(20) not null,
    description varchar(255) not null,
    price decimal(5,2) not null,
    quantity int(11) not null
);
```

---
© 2019 Trilogy Education Services





