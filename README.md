# microservices-template
An experiment at creating a template for scala akka microservices

This repo aims to try out the following:
* A multi-project sbt build, with latest play, akka, scala dependencies
* Actor Messages in a separate project
* Play! Framework as an API gateway
* Akka as intra microservice comms
* Akka remoting
* Actor per request model for handling incoming HTTP requests in API gateway
* Compile time dependency injection with actors using Cake pattern
