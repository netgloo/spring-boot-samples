## Spring Boot file uploads with Ajax

How to uploading a file using Ajax with a Spring Boot web application server side.

See here for more informations:
http://blog.netgloo.com/2015/02/08/spring-boot-file-upload-with-ajax/

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
- Click the *Browse...* button and choose a file to upload (of size less 
  than 3MB)
- Go in the directory you have set in the `application.properties` file: the
  uploaded file will be copied here.

