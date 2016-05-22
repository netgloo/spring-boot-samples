## Spring Boot: using Joda Time on JPA entities with Hibernate

See here for more informations: http://blog.netgloo.com/2015/04/06/spring-boot-using-joda-time-on-jpa-entity-with-hibernate/

### Build and run

#### Configurations

- Open the `application.properties` file and set your own configurations (mainly database's connection parameters).
- Create the MySQL database configured inside the `application.properties`.

#### Prerequisites

- Java 8
- Maven > 3.0

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


### Usage

- Run the application
- Take a look to your database schema: Hibernate creates the table `users`
  with date columns having the expected types.
- Go on *http://localhost:8080/*, insert a date in the form and click *save*:
  will be created a new entry in the database's table with the given date.

