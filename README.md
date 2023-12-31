﻿# 💲 Currency rate application 💲
 # Project description 👀
The "Currency Rate Application" is a web application designed to retrieve and store currency exchange rates from two APIs: MonoBank and PrivatBank. The application provides a convenient way to manage and utilize these exchange rates for various purposes.

**Key Features**:

•**Integration with MonoBank API**: The application establishes a connection with the MonoBank API to fetch the latest currency exchange rates. It retrieves rates for a wide range of currencies against the Ukrainian hryvnia (UAH), providing up-to-date and accurate information.

•**Integration with PrivatBank API**: The application also interacts with the PrivatBank API to obtain currency exchange rates. This integration expands the available currency options and allows for comprehensive rate comparison and analysis.

•**Permanent Storage**: The retrieved exchange rates are stored persistently in a database, ensuring the preservation of historical data. This feature enables users to access and analyze past exchange rate trends, facilitating informed decision-making and strategy development.
# Business value📈
The "Currency Rate Application" offers significant business value by providing the following benefits:

1.**Real-Time Exchange Rate Data**: The application ensures that businesses have access to up-to-date and accurate currency exchange rates. This information is crucial for companies involved in international trade, financial institutions, travel agencies, and any organization that deals with foreign currencies.

2.**Enhanced Decision-Making**: By utilizing historical exchange rate data and visualizations, businesses can make informed decisions regarding currency conversions, hedging strategies, pricing, and international investments. The application equips users with valuable insights into market trends and helps identify favorable opportunities.

3.**Efficient Financial Planning**: The ability to access and analyze historical exchange rate data allows businesses to perform comprehensive financial planning. Forecasting currency fluctuations and assessing potential impacts on revenue, expenses, and profitability becomes easier, enabling proactive financial strategies.

4.**Customization and Alerts**: The application's customization features enable businesses to focus on specific currency pairs relevant to their operations. The ability to set up notifications or alerts for specific exchange rate thresholds ensures timely awareness of market changes, enabling businesses to take appropriate actions promptly.

5.**Streamlined Operations**: The Currency Rate Application simplifies the process of obtaining and managing exchange rate information. By centralizing the data from multiple APIs and offering a user-friendly interface, the application saves time and effort for businesses, allowing them to focus on core operations.

6.**Competitive Advantage**: Access to accurate and timely exchange rate data gives businesses a competitive edge in the global market. It enables them to optimize pricing strategies, negotiate favorable terms with international partners, and stay ahead of market trends, enhancing overall competitiveness.

# PROJECT STRUCTURE🛠
The project follows a 4-tier architecture

**Data Access Tier**:

• **repository (directory)**: Repositories responsible for data access and interaction with the database.

• **specification (directory)**: Specifications used to create complex queries to the database for data filtering.

**liquibase (directory)**: Configuration and files related to Liquibase used for table creation and data population.

**Presentation Layer**:

• **controller (directory)**: Controllers that handle HTTP requests from users and process them.

• **dto (directory)**: Objects for data transfer (DTOs) used for data exchange between the client and server.

• **validation (directory)**: Rules and checks for data validation.

**Business Logic Layer**:

• **model (directory)**: Models representing the core entities of your application .

• **service (directory)**: Services responsible for executing the business logic of the application and interacting with repositories.

• **mapper (directory)**: Mappers responsible for transforming objects between different representations.

• **exception (directory)**: Classes for handling exceptions and errors that occur at the business logic and data levels.

• **MyGlobalExceptionHandler**: A global exception handler responsible for handling various types of exceptions at the presentation layer.
• **config (directory)**: Configuration files, such as MapperConfig and SecurityConfig.

**Security Tier**:

• **security (directory)**: A package responsible for ensuring the security of the application, including authentication and authorization.

# Technologies

1. Spring boot

2. Java 17

3. Spring Security

4. Maven

5. RESTfull api

6. Swagger

7. JSON Web Tokens (JWT)

8. Liquibase

9. MapStruct

10. Database: PostgreSQL database

11. Validation

12. Design patterns

13. N-tier architecture

14. Checkstyle

15. Search Parameters

16. Pagination

17.  Apache HttpClient

18.  JSON

19.  Cron job

