# Countries Data Assessment Project 🌍

Welcome to the Countries Data Assessment project! This project aims to provide data related to countries, states, cities, and currency conversions. Have fun Exploring! 🚀

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [My Decisions and Improvements](#My-Decisions-and-Improvements)


## Getting Started

These instructions will help you get the project up and running on your local machine.

### Prerequisites
Before you can run the application, ensure that you have the following tools installed on your system:
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- Inetllij IDEA
- [Docker](https://www.docker.com/get-started)

### Installation

1. Clone the repository:

   ```shell
    git clone https://github.com/Engrtunze/Klashaasses.git
   ```

2. Change directory to the project folder:

   ```shell
   cd Klashaasses
   ```

3. Build the project:

   ```shell
   mvn clean install
   ```
### Starting Redis
4. To run Redis for caching using Docker, follow these steps:

Start Redis using Docker Compose:
  ```shell
   docker-compose up
   ```
This command will start the Redis container.

### Stopping the Redis
To stop the Redis server, use the following command:
```shell
   docker-compose down
   ```
This will stop and remove the Docker containers.

NB: Kindly start the application locally
### API Documentation

https://documenter.getpostman.com/view/7429153/2s9YC5zYqt#intro

🛠️ **My Decisions:**


**Code Organization**: The project was structured into various folders to maintain a clean and organized codebase. This division of code enhances readability and maintainability.

**Design Patterns**: Throughout the project, several design patterns were implemented to follow best practices and elevate code quality:

**Singleton Pattern**: Utilized Spring Beans to guarantee single instances of specific classes.
Builder Pattern: Employed to facilitate the creation of complex objects with flexibility and clarity.
Facade Pattern: Applied to create a unified interface for a set of subsystem interfaces, simplifying interactions.
API Client Class: To promote reusability and facilitate code maintenance, a dedicated APICLIENT class was introduced. This class encapsulates the logic for consuming external APIs, leading to cleaner code and better API management.

**ClassPathResource for CSV**: In the project, the ClassPathResource approach was adopted to access the exchange_rate.csv file, which stores currency exchange rate data. This choice was driven by key considerations:

**Resource in the Classpath**: Treating the CSV file as a classpath resource ensures it's packaged within the application's JAR or WAR file, guaranteeing its availability across diverse deployment environments.
Portability: The use of ClassPathResource enables the application to access the file without relying on absolute file paths, enhancing portability as the file's location may differ in various deployment scenarios.

**Use of Generics for Flexibility**
In this project, I have made the deliberate decision to utilize Java generics extensively to enhance flexibility and maintainability. Generics allowed me to create versatile and reusable code that can work with various data types without the need for code duplication.

**Serialization and Deserialization**
I employ generics in my JSON serialization and deserialization methods. These methods can handle JSON data of different types by using a generic placeholder <T>:

**serializeToJson**: This method serializes a List<T> of data into a JSON string. It provides the flexibility to serialize lists of various types, not limited to a specific data structure.

**deserializeFromJson**: This method deserializes a JSON string into a List<T>. By using <T>, it can deserialize lists of any type from JSON data.

**Cache Utility**
The HelperUtility class includes methods for caching and retrieving data from Redis. These methods use generics to work with different data types:

**getCachedData**: This method retrieves cached data from Redis and deserializes it into a List<T>. It can handle various data types stored in Redis, making it adaptable for different use cases.

**cacheData**: This method serializes and caches data in Redis. It accepts a List<T> dataToCache, allowing us to cache data of different types without duplicating code.

**API Client**
My ApiClient interacts with external APIs, which may return data of different structures and types. Generics enabled me to retrieve and process data from these APIs in a flexible manner, accommodating various data formats seamlessly.

In this project, Redis caching has been implemented to optimize the performance of external API calls and improve the overall responsiveness of the application. Redis is an in-memory data store that offers several advantages for handling data, especially when interacting with external APIs.

### Why I used Redis Caching

1. **Reduced Latency**: External API calls can introduce latency into your application, especially when the API endpoints are hosted remotely. Redis caching allows us to store the results of these API calls in memory. Subsequent requests can retrieve the data from Redis quickly, reducing the need to make redundant API calls.

2. **Improved Responsiveness**: Caching frequently requested data in Redis ensures that the application can respond promptly to user requests. This results in a smoother user experience and shorter response times.

3. **Load Reduction**: Caching reduces the load on external APIs. This is particularly important when working with rate-limited APIs or APIs with usage quotas. By caching data, we can stay within API rate limits and avoid unnecessary penalties.

4. **Offline Availability**: Redis provides data persistence options. This means that even if the application restarts, it can retrieve cached data from Redis, ensuring that some data remains available even when external services are temporarily inaccessible.

5. **Cost Optimization**: Reducing the number of external API calls can lead to cost savings, especially when dealing with APIs that have associated costs based on usage.

6. **Scalability**: Redis is a highly scalable solution. If the application experiences increased demand, Redis can be easily scaled horizontally to handle larger workloads.

Overall, Redis caching enhances the application's performance and reliability, making it a valuable component in the architecture, especially when dealing with external APIs.


### 🚀 Happy Coding and Keep Innovating! 🌟

If you find any bugs 🐛contact me 🤓 Let's build amazing software together! 🤖

May your code be bug-free and your coffee be hot! ☕😄
