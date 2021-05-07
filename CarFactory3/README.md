# Car Factory - app
Car Factory app is a little demonstration of implementation of Strategy pattern and Inversion of Control that produces a car according to input.
* New beans are injected through appropriate API endpoint on a running server. 
### Technologies

* Java 11
* Spring Boot version 2.4.5.

### Garage features

* Car type: Cabrio, Hatchback, Sedan 

### Example
Inputs:

~~~
cabrio
~~~

Outputs:
~~~
Cabrio Car has produced.
~~~
### Launch
* Run the project without Mercedes class (or by commenting the Mercedes class)
* Write a Mercedes class by implementing Car interface (or uncomment the Mercedes class)
* Compile newly created Mercedes class
* Send post request to `http://localhost:8080/regbean` with
~~~
{
    "beanName" : "mercedes",
    "beanClassName" : "Mercedes"
}
~~~
* Test if the bean is created
~~~
{
    "carType" : "mercedes"
}
~~~
### Note
* Postman collections can be found in resources folder