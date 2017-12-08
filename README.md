# Coding exercise

The 'domain' module contains the bussiness objects and logic, has a small set of dependencies and a reference "in-memory" implementation.

The 'web' module contains the transport stack (http), persistence (spring-data) and integration tests.

To get started execute the following command in the project root:

    $mvn clean install

This will run the tests and will install the 'domain' module in your local maven repository and make it available for the 'web' module, which depends on it.

To run the application execute:

    $mvn spring-boot:run

while in the 'web' directory. This will boot the application with and embedded servlet container (tomcat) running on port 8080.