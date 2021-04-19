# Summative Assessment: Tasker Service Project

The purpose of this assessment is to demonstrate your ability to work with existing code, find and fix bugs, finish features, modify an existing service to use a Spring Cloud Config Server, and to integrate another service using the Eureka Service Registry.

## Tasker

Tasker is a simple task-tracking web service. The team that began development on this project was disbanded when some of the developers were pulled into another project and two key members left the company. This project had limited documentation (included below) and was left in an unknown state. 

Your task is to pick this project back up and complete the code. The existing code has an unknown number of bugs; you must fix all of these issues as part of your implementation.

## Documentation

### REST API

```javasc
Create a new task:
------------------
URI: /tasks
HTTP Method: POST
RequestBody: JSON TaskViewModel information (minus advertisement)
ResponseBody: JSON TaskViewModel information (including ID and advertisement)

Update a task:
--------------
URI: /tasks
HTTP Method: PUT
RequestBody: JSON TaskViewModel information (including ID and advertisement)
ResponseBody: None

Find task by ID:
----------------
URI: /tasks/{id}
HTTP Method: GET
RequestBody: None
ResponseBody: JSON TaskViewModel information (including ID and advertisement)

Find all tasks:
---------------
URI: /tasks
HTTP Method: GET
RequestBody: None
ResponseBody: JSON List of TaskViewModels (including ID and advertisement)

Find tasks by category:
-----------------------
URI: /tasks/category/{category}
HTTP Method: GET
RequestBody: None
ResponseBody: JSON List of TaskViewModels (inclding ID and advertisement)

Delete task:
------------
URI: /tasks/{id}
HTTP Method: DELETE
RequestBody: None
Responsebody: None
```

### Advertisement Feature

The revenue department has requested a feature to incorporate an ad server that serves up an advertisement with every task that is returned by Tasker. This work was started, but most of the implementation remains to be done. The design notes from the previous team are included here:


* The DAO must remain unchanged, so the Adserver must be integrated in the Service Layer.
* The methods of the Service Layer will translate between TaskViewModel objects and Task objects and will fetch an ad from the Adserver for each TaskViewModel that is returned to the Controller.
* We have to return an advertisement with each task, but the advertisement is not part of our data model, so we'll use a view model to accomodate this requirement.
* There is no documentation for the Adserver Service, so we'll have to look at the code to figure out what the REST API looks like; maybe we can use Postman to see what comes back from the endpoints.



## Architectural/Implementation Changes

* Implement a new Spring Cloud Config Server for this project.
* Implement a new Eureka Service Registry for this project.
* Modify the Tasker Service to use the Spring Cloud Config Server for all of its configuration settings.
* Modify the Tasker Service to include Controller Advice for handling all Controller exceptions. Your implementation should use `CustomErrorResponse` with `ResponseEntity`.
* Modify the Adserver Service to use the Spring Cloud Config Server for all of its configuration settings.
* Modify the Adserver Service to register with the Eureka Service Registry.
* Make sure the Tasker Service uses the Eureka Service Registry to find the Adserver.
* Make sure the Tasker Service uses a Feign client to interact with the Adserver.
* Make sure that all integrations between components are done via Dependency Injection.

## Additional Requirements

* The finished project must have a complete integration test suite for the DAO.
* The finished project must have a complete unit test suite for the Service Layer that uses mocks.
* The final Task API must be documented with Open API 3.x (Swagger).
* You must add JSR 303 validation and an exception handler for the controller.

---

© 2019 Trilogy Education Services
