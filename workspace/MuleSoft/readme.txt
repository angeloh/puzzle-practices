PROBLEM TWO: MARS ROVERS

Description: I implemented this program with two kinds of design patterns. Command pattern for rover to execute a series of commands and factory pattern to create rover. A proxy object "RoverCommand" is used to store a rover with its commands. There is a control center to store a list of RoverCommand objects and provides methods to execute rovers. Two remote web services REST & SOAP are also provided in the solution. There is one demo html page which has a http form to send the GET request to REST.

Please follow the steps to execute this program.
1) Execute using input file
#1.Using Ant to compile all java files to jar.
cd MuleSoft
ant

#2. Command to execute the main class with an input file.
***Unix (shell script provided)***
./rover in

***Windows***
java -cp . -jar Rover.jar in


2) Running UnitTest with sample hard-coded input
ant junit

3)
OPTIONAL EXTENSION EXERCISE
#1. Two remote service provided(REST & SOAP) and one html page provided to access REST

#2. To test REST, first start REST class using jdk simple server.
cd build/classes
java -cp . com.mars.service.RoverREST

#3. Open this html page "rover.html" in any browser to send the request to http://localhost:8080/RoverREST

#4. To test SOAP, first start SOAP class using jdk simple server.
java -cp . com.mars.service.RoverSOAP

#5. Open any browser and type the following link to read WSDL.
http://localhost:8080/RoverSOAP?wsdl