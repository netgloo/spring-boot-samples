## Logging in Spring Boot

Basic example for logging in Spring Boot.

See here for more informations: http://blog.netgloo.com/2014/12/11/logging-in-spring-boot/


### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 8
- Maven > 3.0

#### Using the terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.

### Usage

- Launch the application and go on http://localhost:8080/
- Take a look to console output: you should see some log from 
  `netgloo.controllers.HomeController`
- Optional: if you setted a log file in the `application.properties` open such file to see the log

