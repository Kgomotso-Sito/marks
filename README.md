# Marks
App designed for centralising and abstracting learners marks in a school.

Micro-services architecture was used to create this applications, there are 4
microservices in implementing the holistic system. Namely;
* Authentication 
* User management
* Subject management
* Marks Management

###Additional projects;
* Micro-service registry
* API-Gateway
* Marks gui

Each project is on it's own branch.

####Micro-service registry
* This is a eureka server for managing the the microservices
* must be started first before the other micro-services.

####API-Gateway
* is a single entry to all the available API, this is for security reason.
* this is a both eureka server and client
* Must be started after all the other micro-services

####Marks-Gui
* an angular project for the user interface

