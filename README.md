<h1 align="center" id="title">ImmobilierDz</h1>

<p align="center"><img src="https://socialify.git.ci/srbensakina/ImmobilierDZ/image?description=1&amp;font=Bitter&amp;language=1&amp;name=1&amp;pattern=Circuit%20Board&amp;theme=Dark" alt="project-image"></p>

<p id="description">The Real Estate Backend Project is a comprehensive application backend built using Spring Boot Hibernate PostgreSQL RabbitMQ Spring Security JWT authentication API Gateway Discovery Service and Email Confirmation Service. This robust backend solution facilitates the management of real estate properties encompassing creation retrieval updating and deletion of properties. The platform also features user registration and login functionalities generating JWT tokens for secure authentication. Additionally the project integrates RabbitMQ for managing property-related events and calculating the average rating of properties based on user feedback. The entire backend is containerized using Docker and orchestrated with Docker Compose for streamlined deployment.</p>

  ## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [RabbitMQ Integration](#rabbitmq-integration)
- [API Gateway](#api-gateway)
- [Discovery Service](#discovery-service)
- [Email Confirmation Service](#email-confirmation-service)
- [Contributing](#contributing)
- [Testing](#testing)
- [License](#license)
- [Contact Information](#contact-information)
- [Acknowledgments](#acknowledgments)
  
<h2>🧐 ## Features</h2>

Here're some of the project's best features:

*   User Registration and Authentication: New users can register and registered users can securely log in to the system. JWT tokens are issued for authenticated access.
*   Property Management: Endpoints for Creating Reading Updating and Deleting real estate properties are provided. Property data includes details like location pricing availability and specifications.
*   Advanced Property Search: Utilizing Spring Data JPA Specifications users can search for properties based on custom criteria like price range location and specifications.
*   RabbitMQ Integration for Rating: RabbitMQ is employed to manage queues and perform asynchronous calculations. The application calculates and updates average ratings for properties based on user feedback.
*   Secure Endpoints: Spring Security ensures that sensitive endpoints are secure and properly authenticated.
*   Exception Handling: Centralized exception handling and descriptive error messages enhance user experience.
*   API Gateway: The API Gateway acts as a single entry point for various microservices providing a unified interface to the clients.
*   Service Discovery: The Discovery Service enables dynamic service registration and discovery aiding in microservice communication.
*   Email Confirmation: The Email Confirmation Service handles the email verification process during user registration.

<h2>🛠️ Installation Steps:</h2>

<p>1. Clone the repository:</p>

```
git clone https://github.com/srbensakina/ImmobilierDZ
```

<p>2. Navigate to the project directory:</p>

```
cd real-estate-backend
```

<p>3. Build and run the application using Docker Compose:</p>

```
docker-compose up -d
```

  
  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Java
*   Spring Boot
*   Hibernate
*   Docker
*   Maven
*   RabbitMq
*   Docker-copmose
*   PostgreSql
