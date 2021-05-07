# Garage - app
This is a Garage app with automated ticketing system that allows customers to use a garage without human intervention and unique ticket is issued to a driver.
### Technologies

* Java 11
* Spring Boot version 2.4.5.

### Garage features

* Car holds 1 slot
* Jeep holds 2 slots
* Truck holds 4 slots
* 1 slot is being left to next one after each vehicle

### Garage order inputs

* Park: Order for parking consists of `order plate_number vehicle_color vehicle_type`. For example, `park 34-SO-1988 Black Car` and returns appropriate message, for instance `Allocated 1 slot.`, that consists information about allocated space by respective vehicle. 
* Leave: Order for removing a vehicle from a garage includes `order car_order_number`. For example,`leave 3`, 3rd vehicle is leaving the garage.
* Status: Order for viewing the current status of a garage is `status`, which returns all vehicles in order with possessed slots.

### Example
Inputs:

~~~
park 34-SO-1988 Black Car
park 34-BO-1987 Red Truck
park 34-VO-2018 Blue Jeep
park 34-HBO-2020 Black Truck
leave 3
park 34-LO-2000 White Car
status
~~~

Outputs:
~~~
Allocated 1 slot.
Allocated 4 slots.
Allocated 2 slots.
Garage is full.
Allocated 1 slot.
Status:
34-SO-1998 Black [1]
34-BO-1987 Red [3,4,5,6]
34-LO-2000 White [8]
~~~
### Note
* Postman collections can be found in resources folder