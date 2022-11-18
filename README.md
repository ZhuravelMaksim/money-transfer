Money transfer service
==

---

https://github.com/ZhuravelMaksim/money-transfer
---

Local Environment
====

* Java 17
        $ java --version
        openjdk 17.0.5 2022-10-18 LTS
        OpenJDK Runtime Environment Zulu17.38+21-CA (build 17.0.5+8-LTS)
        OpenJDK 64-Bit Server VM Zulu17.38+21-CA (build 17.0.5+8-LTS, mixed mode, sharing)

* Maven 3.6.3 (or use included wrapper by `./mvnw`).
        $ mvn -v
        Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)

* Postgres 11+ with pre-defined `money-transfer-service` database and `public` scheme
    * POSTGRES_USER: root
    * POSTGRES_PASSWORD: ethernet
    
* Docker
  https://docs.docker.com/desktop/

* Redis
  https://hub.docker.com/_/redis

Run steps in Intellij Idea
====

1. Execute `$ ./mvnw clean package` to build auto-generated apt sources
    * Skip tests during build:
        * `-DskipITs` to build module without integration tests execution
        * `-DskipTests` to build module without all tests execution.

2. Install (if not already exists) [EnvFile](https://plugins.jetbrains.com/plugin/7861-envfile/) plugin for Intellij
   Idea

3. Add `.env.local` file to Spring Boot run configuration.

4. Run/Debug the application.

_If you want to use standard colorful *log* format - start the application with `local` spring profile_


Postman collection
====
accounts and transactions.postman_collection.json