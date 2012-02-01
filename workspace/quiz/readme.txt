Subject:
This is a readme file for sum-ware quiz project. The quiz implementation is started at 04/26 12:00 and ended 04/26 4:00. Explanation is provided below:

Name: Angelo Kaichen Huang (angelokh@gmail.com)

1.
In this project, except the project requirements, I used design pattern, log4j to make the future 
extension possible. Please follow the usage example(Usage: Main file.csv) to test the code.
 
com.sumware.quiz package:
ObjectFactory.java : Abstract(Base) class for factory pattern.
ObjectFactorySelector.java: A Abstract factory to pick up factory to create objects.
Option.java: A bean for option codes in  Vehicle class.
VehicleFactory.java: A factory implementation for Vehicle object.
VehicleParserImpl.java: Implements VehicleParser and provides the methods to parse cvs file and 
create a list of Vehicle objects.

A test class is also provided for testing the correctness of parser of vehicles, date format's handling and parser of options.

Test Package:
ParseVehicleTest.java

2.
SELECT c.Fname, c.Lname, c.Address1, c.Address2, c.City, c.State, c.Zip
FROM Customer AS c
WHERE c.Id NOT in (
SELECT c2.Id
FROM Customer AS c2, Vehicle AS v, Customer_Vehicle AS cv
WHERE c2.Id = cv.CustomerID AND v.Id = cv.VehicleID AND v.ModelYear = 2005 );