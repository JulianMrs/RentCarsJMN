# RentCars

Car rental API made with Spring Boot using an in-memory database (h2-database) with a data.sql file containing 3 cars and 2 customers for data loading at runtime.

This API exposes two operations:

1) Rental of one or more cars by the same customer.

In this operation, when a rental is made, it is saved in the database and the car(s) become rented (Boolean control). In addition, the rental price is calculated according to the type of car.

2) Return of a car and calculation of an extra price for returning the car late.

In this operation, the car is returned, so it is once again free (Boolean control). Also, if the car is returned late, the rental price is updated.

The database is made up of three tables:

-Car: Represents the rental car and saves the id, name, type, status (rented or not), price.

-Client: Represents the client who rents the car and saves the id, name and loyalty points.

-Rental: Represents the rental of a car made by a customer and saves the id, car id, customer id, start date, end date and rental price.

In summary, a customer is allowed to rent one or more vehicles, so a rental will be generated for each of them, which will have a customer and an associated car.

To test this application, you will simply have to download the code and import it as a maven project in STS to then launch the Embedded Tomcat Server to be able to launch the relevant requests to test
the exposed operations mentioned above.
