# stock

Assumptions:
1. Language is Java 8 SE
2. Project is of Maven type
3. Unit Tests use JUnit and EasyMock?s
4. Application is of a high frequency trading type. Therefore multi-threading is necessary
5. No frameworks like Spring or CDI can be used. DI have to be implemented manually. I'll be using Factory Pattern


Workflow:
The application is layered in 3 tiers:
1. Controller - Application interface
2. Service - this layer provides higher level (business meaningful) operations
3. Repository - low level / DAO layer. In the default repository implementation data will be held in memory. Caching will be used


1. First we define the model - the entities. 
2. Next we define the interfaces for our Repository layer and Service Layer.
3. Next we switch on to unit tests and employing Test Driven Development, starting to implement the layers step by step.

