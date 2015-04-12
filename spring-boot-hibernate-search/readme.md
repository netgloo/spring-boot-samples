## Spring Boot and Hibernate Search integration

See here for more informations: http://blog.netgloo.com/2014/11/23/spring-boot-and-hibernate-search-integration/

### Usage

- Run the application
- Type the url `http://localhost:8080/search?q=some text to search`: a search
  will be performed for the query text "*some text to search*".

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations:

- Database connection parameters (this example use MySQL5 as DBMS)
- Hibernate Search's index directory

#### Prerequisites

- Java 7
- Maven 3

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.
