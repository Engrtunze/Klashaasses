# Countries Data Assessment Project 🌍

Welcome to the Countries Data Assessment project! This project aims to provide data related to countries, states, cities, and currency conversions. Explore and contribute to make it even better! 🚀

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

These instructions will help you get the project up and running on your local machine.

### Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- Inetllij IDEA

### Installation

1. Clone the repository:

   ```shell
    git clone https://github.com/Engrtunze/Klashaasses.git
   ```

2. Change directory to the project folder:

   ```shell
   cd countries-data-assessment
   ```

3. Build the project:

   ```shell
   mvn clean install
   ```


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
Improvements
While developing the project, opportunities for future enhancements were identified:

**Caching with Redis**: There was an intention to implement data caching using Redis to reduce response times for external API calls. This would have significantly improved application performance by storing and retrieving frequently accessed data from an in-memory cache. However, due to time constraints for submission, this feature could not be implemented in this iteration.

**Comprehensive Testing**: Although the goal was to establish comprehensive unit and integration test coverage, only one test was completed during the development process. In future iterations, expanding test coverage to ensure code reliability and robustness would be a priority.

Addressing these improvements in future development cycles will lead to a more performant, reliable, and maintainable application, ensuring its continued growth and efficiency.


🚀 Happy Coding and Keep Innovating! 🌟

If you find any bugs 🐛contact me 🤓 Let's build amazing software together! 🤖

May your code be bug-free and your coffee be hot! ☕😄
