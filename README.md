MicroService Template
=================================================

This project contains a Spring Boot REST API micro-service to be used as a template for building MicroServices.
You can copy the contents of this repo into your new API repo and then change all references to "changeme" in this
template to reference to applicable configuration on your API

Prerequisites
-------------

This project uses [Lombok](https://projectlombok.org/) for automatic code
generation.  You will need to install the Lombok plugin for IntelliJ using
File > Settings... > Plugins > Lombok. 

Running
-------

mvn spring-boot:run

Running in Intellij, add to JVM:  -Dspring.profiles.active=dev -Dap.env=dev

Development credentials are user/password.
