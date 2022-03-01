# Spring Boot "Product-Service Microservice"

This is a sample Java / Maven / Spring Boot (version 2.6.3) application that can be used as a starter for creating a microservice complete with built-in health check, metrics and much more. I hope it helps you.

## How to Run 

This application is packaged as a war which has Tomcat 10 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 11 and Maven 4.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/spring-boot-rest-example-0.5.0.war
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8005 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```

## About the Service

The service is just a simple Online Shopping portal  review REST service. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints.

More interestingly, you can start calling some of the operational endpoints (see full list below) like ```/metrics``` and ```/health``` (these are available on **port 8005**)

You can use this sample service to understand the conventions and configurations that allow you to create a DB-backed RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.
 
Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Demonstrates how to set up healthcheck, metrics, info, environment, etc. endpoints automatically on a configured port. Inject your own health / metrics info with a few lines of code.
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations. 
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger2 using annotations 

Here are some endpoints you can call:

### Get information about system health, configurations, etc.

```
http://localhost:8005/products  //Get All Record from the database
http://localhost:8005/productById/1 //Get partical id from the database
http://localhost:8005/product/Adish  //Get records by name 
```

### Create a product resource

```
POST http://localhost:8005/addProduct  //Created only single product
Accept: application/json
Content-Type: application/json

{
   "name" : "Adish",
   "price" : "10",
   "quantity" : "150"
}

POST http://localhost:8005/addProducts  //Created Multiple products
Accept: application/json
Content-Type: application/json

[
    {
   "name" : "Rohit",
   "price" : "10",
   "quantity" : "150"
},
{
   "name" : "Kumar",
   "price" : "12",
   "quantity" : "134"
}

]

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8090/example/v1/hotels/1
```

### Retrieve a paginated list of product

```
http://localhost:8005/

Response: HTTP 200
Content: paginated list 
```

### Update a product resource

```
PUT http://localhost:8005/update
Accept: application/json
Content-Type: application/json

{
    "id" : "1",
   "name" : "Alisher",
   "price" : "12",
   "quantity" : "134"
}

RESPONSE: HTTP 204 (No Content)
```
### To view postman 2 API docs

Run the server and browse to localhost:8005/postman-ui.html

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

**/metrics** Shows “metrics” information for the current application.

**/health** Shows application health information.

**/info** Displays arbitrary application info.

**/DiscoveryClient** Displays a collated list of all @EnableDiscoveryClient.

**/mappings** Displays a collated list of all @RequestMapping paths.

**/beans** Displays a complete list of all the Spring Beans in your application.

**/env** Exposes properties from Spring’s ConfigurableEnvironment.

**/trace** Displays trace information (by default the last few HTTP requests).

### To view your H2 in-memory datbase

The 'test' profile runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8005/h2-console. Default username is 'root' with a password is 'password' Make sure you disable this in your production profiles

# Running the project with MySQL

This project uses an in-memory database so that you don't have to install a database in order to run it. However, converting it to run with another relational database such as MySQL or PostgreSQL is very easy. Since the project uses Spring Data and the Repository pattern, it's even fairly easy to back the same service with MongoDB. 

Here is what you would do to back the services with MySQL, for example: 

### In pom.xml add: 

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

### Append this to the end of application.yml: 

spring.application.name=product-service
server.port=8005
eureka.client.serviceUrl.defaultZone=http://localhost:9090/eureka/

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/springbootcrud
spring.datasource.username = root
spring.datasource.password = password
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

### Then run is using the 'mysql' profile:

```
        java -jar -Dspring.profiles.active=mysql target/spring-boot-rest-example-0.5.0.war
or
        mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=mysql"
```

# Attaching to the app remotely from your IDE

Run the service with these command line options:

```
mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
or
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Dspring.profiles.active=test -Ddebug -jar target/spring-boot-rest-example-0.5.0.war
```
and then you can connect to it remotely using your IDE. For example, from IntelliJ You have to add remote debug configuration: Edit configuration -> Remote.

# Questions and Comments: adishansari786@gmail.com


