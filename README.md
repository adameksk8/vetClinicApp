# vetClinicApp
 
## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6.3](https://maven.apache.org)
- [MySQL Server](https://dev.mysql.com)

## Running the application locally

First of all, you have to run your local MySQL Server and update application.properties file (vetClinicApp\vetClinicApp\src\main\resources\) to access your local database.

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `it.nowak.adam.vetClinicApp.vetClinicAppApplication` class from your IDE.

## Testing the application

In order to fill database with example data, you can use vetclinicdb.sql script in `db scripts` directory .

There are some example API calls prepared in `vetClinicApp.postman_collection.json` file at `postman` directory