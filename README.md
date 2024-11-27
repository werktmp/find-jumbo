# Find the closest jumbo stores
The find jumbo application is an application to find the 5 closest jumbo stores based on location

## Assessment
Create an application that shows the 5 closest Jumbo stores to a given position.
You can find a list of Jumbo stores in JSON format attached to this document.

There are just a few rules:
- It has to be Java programming language. What frameworks, libraries you use is up to you.
- We would like to see your skills to develop REST APIs
- Write your code as if it's production code as much as possible.
- Make sure the reviewer can easily run the application for evaluation purposes.

## How to setup for development
- Install java version *17*.

## How to run the application
- To start the application run this command: ``./mvnw spring-boot:run``

## Test
- In the folder *src/test* are endpoint tests for intellij http client.

## Project structure
This project is initialized with spring boot initializr. This is a project generator tool for more information see: [spring initializr](https://start.spring.io//)

## Notes
If it were a larger project and I had more time I would have incorporated additional elements such as:
- More security like jwt, rols,
- Piplines for building, testing and QA tasks
- More test coverage and test for all the classes
- Integration tests
- More error handling
- Correct error responses
- Clear and correct http responses
- Mapstruct
- Database for saving coordinates
- Docker setup for database
- Database migration framework
- Config for test/stg/pro
- More documentation on functions and classes (Swagger).
- Fix deprecate MockBean in tests


If you have any questions, feel free to ask.
