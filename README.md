## This is a working example for PostgreSQL database accompanying the blog post "Testing Pessimistic Locking Handling with Spring Boot and JPA"

More examples here:
- [Example for Oracle](https://github.com/andistoev/testing-pessimistic-locking-handling-spring-boot-jpa-oracle)
- [Example for MySQL](https://github.com/andistoev/testing-pessimistic-locking-handling-spring-boot-jpa-mysql)
- [Example for PostgreSQL (this one)](https://github.com/andistoev/testing-pessimistic-locking-handling-spring-boot-jpa-postgresql)

### Intro
This SpringBoot maven project is part of my [blog post](https://blog.mimacom.com/testing-pessimistic-locking-handling-spring-boot-jpa/) where you could find not only explanation for it, but also theoretical background for:
- What does pessimistic locking handling mean
- How you could implement it within a Spring Boot Application and JPA on the Oralce, MySQL and PostgreSQL databases
- How you could write integration tests for handling pessimistic locking.

### Prerequisites:
- Docker
- JDK8+.

### Setup
- Start PostgreSQL database container from docker/db-up.sh
- Check with "docker ps" if the container is up&running.

### Run pessimistic locking integration test withing in-memory database Apache Derby
🔔 *Ideal for your DevOps pipeline*
- Switch profile in InventoryServicePessimisticLockingTest.java to "test" (the default one)
- Run "./mvnw clean verify".

### Run pessimistic locking integration test withing Oracle DB 
🔔 *Ideal for quick local test to try the real database when changing the pessimistic locking handling code*
- Switch profile in InventoryServicePessimisticLockingTest.java to "test-oracle"
- Run "./mvnw clean verify".

Have fun and do not hesitate to contact me if you have any questions or suggestions!

### About me
My name is [Andrey Zahariev Stoev](https://www.linkedin.com/in/andistoev). 
I am working as Senior Software Architect in Switzerland.
I love software craftsmanship and systems thinking.
I am passionate about travel, languages and cultural diversity exploration.

