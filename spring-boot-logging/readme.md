## Logging in Spring Boot

Basic example for logging in Spring Boot.

See here for more informations: http://blog.netgloo.com/2014/12/11/logging-in-spring-boot/

### Usage

- Launch the application and go on http://localhost:8080/
- Take a look to console output: you should see some log from 
  `netgloo.controllers.HomeController`
- Optional: if you setted a log file in the `application.properties` open such
  file to see the log

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 7
- Maven 3

#### Using the terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.

