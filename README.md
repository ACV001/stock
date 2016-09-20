# stock

<H2>Assumptions:</h2>

1. Language is Java 8 SE
2. Project is of Maven type
3. Unit Tests use JUnit
4. Application is of a high frequency trading type. Therefore multi-threading is necessary
5. No frameworks like Spring or CDI can be used. DI have to be implemented manually. I'll be using Factory Pattern


<H2>Structure</H2>
The application is layered in 3 tiers:
1. Controller - Application interface
2. Service - this layer provides higher level (business meaningful) operations
3. Repository - low level / DAO layer. In the default repository implementation data will be held in memory.
This is done to obtain better decoupling. Alternative implementations could be substituted any time. 


<H2>Workflow</H2>
1. First we define the model - the entities. 
2. Next we define the interfaces for our Repository layer and Service Layer.
3. Next we switch on to unit tests and employing Test Driven Development, starting to implement the layers step by step.

<h2>Some details</h2>

1. It is considered that the requests (now coming from SimpleStockApplication), will be coming in parallel from multiple clients. That's why we need to provide some concurrency enhancements. For async, non-blocking calculations, we'll be using Future holding the results of the calculations. The clients will get their results when they are ready without blocking other clients. The main entry point is SimpleStockApplication. 
2. The storage is a ConcurrentHashMap.
3. Some setup actions need to be done so additional service methods were added (addStock, findStock, saveTrade, ...).
4. All entity classes are immutable.

<h2>Not Implemented</h2>

1. Some operations (marked correspondingly) - which weren't actually required in the problem statement :) YAGNE broken
2. Caching mechanism
3. Security
4. Logging
5. Audit
6. Not all applicable java SE 8 features were used here
7. Incomplete JUnit test coverage (not all layers were covered).
8. ...


<h2>To run</h2>

1. Clone the repository (https://github.com/ACV001/stock.git)
2. Import in Eclipse as maven project (Alternatively mvn clean test)
3. Run the SimpleStockApplication or all unit tests






<H2>Tools Used</H2>
1. Java SE
2. Maven
3. Eclipse
4. JUnit


