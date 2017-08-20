
****************************************************************************************************
The basics:

- Project was built using JDK 8.

- Can be run on Apache Tomcat 8.5.

- Used Maven 3.5.0 to create project and manage dependencies.

- Jersey as JAX-RS implementation for the REST services.

- Unit tests using JUnit 4, Mockito as a mocking framework when needed. Following Maven standards, 
	all unit test classes have the "Test" suffix. Run tests with Maven's "clean test" goals.

- Integration tests using the Jersey Test Framework. Following Maven standards, all integration test
	classes have the "IT" suffix. Run integration tests with Maven's "clean integration-test" goals.

****************************************************************************************************
API Information:

- "diff/{id}/left" and "diff/{id}/right" endpoints decode and store data informed on request body. 
	Return HTTP 200 when successful.

- "diff/{id}" endpoint calculates the diff and return diff status and offset of the first different
	byte (if both sides had same size but not equal data).
	
	
****************************************************************************************************
Suggestions for improvement:

- Add an actual database to store data. Currently being stored in memory, what limits number of 
	servers where service can be deployed, and causes data to be lost when server is restarted.
	I think that a NoSQL database would be perfect, as we would merely store a map of Keys and 
	values. 
	
- Add some sort of authentication. Currently multiple clients can use the same ID simultaneously,
	what could cause some concurrency problems (for example, a client can override left or right 
	data under an ID at the same time another client is trying to calc diff under that ID). With 
	authentication, we could bind IDs to clients. JWT, maybe?