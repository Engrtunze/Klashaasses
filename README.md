# Countries Data Assessment Project 🌍

Welcome to the Countries Data Assessment project! This project aims to provide data related to countries, states, cities, and currency conversions. Explore and contribute to make it even better! 🚀

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

🛠️ **My Decisions and Improvements:**


**Decisions**

**Code Organization**: The project was structured into various folders to maintain a clean and organized codebase. This division of code enhances readability and maintainability.

**Design Patterns**: Throughout the project, several design patterns were implemented to follow best practices and elevate code quality:

**Singleton Pattern**: Utilized Spring Beans to guarantee single instances of specific classes.
Builder Pattern: Employed to facilitate the creation of complex objects with flexibility and clarity.
Facade Pattern: Applied to create a unified interface for a set of subsystem interfaces, simplifying interactions.
API Client Class: To promote reusability and facilitate code maintenance, a dedicated APICLIENT class was introduced. This class encapsulates the logic for consuming external APIs, leading to cleaner code and better API management.

**ClassPathResource for CSV**: In the project, the ClassPathResource approach was adopted to access the exchange_rate.csv file, which stores currency exchange rate data. This choice was driven by key considerations:

**Resource in the Classpath**: Treating the CSV file as a classpath resource ensures it's packaged within the application's JAR or WAR file, guaranteeing its availability across diverse deployment environments.
Portability: The use of ClassPathResource enables the application to access the file without relying on absolute file paths, enhancing portability as the file's location may differ in various deployment scenarios.

In this project, Redis caching has been implemented to optimize the performance of external API calls and improve the overall responsiveness of the application. Redis is an in-memory data store that offers several advantages for handling data, especially when interacting with external APIs.

### Why I used Redis Caching

1. **Reduced Latency**: External API calls can introduce latency into your application, especially when the API endpoints are hosted remotely. Redis caching allows us to store the results of these API calls in memory. Subsequent requests can retrieve the data from Redis quickly, reducing the need to make redundant API calls.

2. **Improved Responsiveness**: Caching frequently requested data in Redis ensures that the application can respond promptly to user requests. This results in a smoother user experience and shorter response times.

3. **Load Reduction**: Caching reduces the load on external APIs. This is particularly important when working with rate-limited APIs or APIs with usage quotas. By caching data, we can stay within API rate limits and avoid unnecessary penalties.

4. **Offline Availability**: Redis provides data persistence options. This means that even if the application restarts, it can retrieve cached data from Redis, ensuring that some data remains available even when external services are temporarily inaccessible.

5. **Cost Optimization**: Reducing the number of external API calls can lead to cost savings, especially when dealing with APIs that have associated costs based on usage.

6. **Scalability**: Redis is a highly scalable solution. If the application experiences increased demand, Redis can be easily scaled horizontally to handle larger workloads.

Overall, Redis caching enhances the application's performance and reliability, making it a valuable component in the architecture, especially when dealing with external APIs.


🚀 Happy Coding and Keep Innovating! 🌟

If you find any bugs 🐛contact me 🤓 Let's build amazing software together! 🤖

May your code be bug-free and your coffee be hot! ☕😄
