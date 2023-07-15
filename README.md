# Read Me - Order API - SIBS Test

## How to run this project:

* Have PostgreSQL installed and create a database:
`CREATE DATABASE order_db;`

* In application.properties file, fill the values for the properties below, according to your setup:
`spring.datasource.username=postgres 
 spring.datasource.password=yourpassword
 spring.mail.username=preferably@gmail.com
 spring.mail.password=yourpwd
`

* Other `spring.mail` setup may need adjustments if using other mail clients.

* `spring.jpa.hibernate.ddl-auto` is configured as UPDATE, so data will be preserved between executions.

* Run OrderApplication (from IDE or using Maven):
`mvnw spring-boot:run`

## API Endpoints

#### Base Url: `http://localhost:8080/api`

A Postman collection inside the project is provided with some call examples:

`sibs-test-api.postman_collection.json`

### /items, /users : 
Default CRUD functionality provided by Spring Data Rest.

### /orders 
All CRUD common methods.
POST/PUT methods will add an Order to the system, automatically completed if there is stock of that item (`quantity` will be returned updated).

### /stocks
All CRUD common functionalities.
POST/PUT methods will add an Stock to the system, automatically completing an Order if the stock is enough (`quantity` of the stock will be returned decreased when an Order is found).

## Logging

One directory above the project, will be generated a `sibs-test-logs` directory with the log file.
