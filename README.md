# ct-statement-validator

# Introduction 
Application will verify the statement against the duplicate reference numbers and wrong end balance. 
If End balance is negative or the reference is duplicate. The application will return them as a array list which has two properties. They referenceNumber and description


# Prerequisites
Java 8 installed 
maven latest installed.

# Clone 
To clone the repository `git clone https://github.com/Arjun-1r/ct-statement-validator.git`

# Run 

Navigate to the cloned repository in command prompt or terminal window and type the following command.
`mvn spring-boot:run` 

# Post man to test the file upload
Since it is only backend application we can test the project using post man.

Url: http://localhost:8080/file (post request) (file should be added in body form data)

We can test application using the files available in the repository. The path is src/test/java 

# Run Junit test Cases

To execute the test cases use the following command

`mvn test`

# To generate war package.
To deploy the application we need to execute the following command to get project .war format. 
This .war package can deployed to web server like tomcat. 
Command: `mvn package`

