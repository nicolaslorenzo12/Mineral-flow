# Project README

## Overview
This school project implements a microservices based system for managing warehouse and logistics operations, leveraging hexagonal architecture and domain driven design (DDD) principles. It is structured into four bounded contexts (microservices), each handling a distinct business domain:

- **Warehouse:** Manages warehouse stock, material storage, dispatch and stock activities.
- **Landside:** Oversees truck appointments, weighing and logistics on the landside operations.
- **Waterside:** Manages the material dispatch related to waterside shipping operations.
- **Invoicing:** Handles billing, daily fees and commission calculations for customers.

Each microservice uses its own separate schema in a shared MySQL database instance. Each schema contains several tables related to that microservice’s domain. This approach allows logical separation of data while sharing the same database server, useful for learning and simplified management.

Communication between services happens asynchronously using RabbitMQ for event-driven messaging.

## Key Technologies
- Java with Hexagonal Architecture & SOLID principles
- MySQL 9.0.1 database with multiple schemas (one per microservice). Each containing several tables
- RabbitMQ 3.13.7 for message brokering and event driven communication
- Keycloak 25.0.5 for identity and access management
- Docker & Docker Compose for containerized deployment and local environment setup

## System Architecture

### 1. Warehouse Context
Responsible for managing material storage in warehouses. This service tracks stock levels, processes incoming and outgoing material movements and ensures warehouse capacity rules are enforced. Data is stored in the `warehouse` schema with multiple related tables.

### 2. Landside Context
Handles truck appointments and weighing logistics. It manages truck arrival schedules, status updates and coordinates with warehouses for receiving or dispatching materials. Data is stored in the `landside` schema with several tables supporting the domain.

### 3. Waterside Context
Manages material dispatch related to water transport operations. It handles shipment orders, loading and event publishing to update warehouses and invoicing services. Data is stored in the `waterside` schema with multiple tables.

### 4. Invoicing Context
Calculates daily storage and commission fees per customer and generates invoices upon request. Integrating data from warehouses and shipment operations. Data is stored in the `invoicing` schema, which contains several tables to support billing and commission calculations.

## Deployment Setup

### Docker Compose Services
- `mysql_app_db`: Single MySQL database instance hosting multiple schemas (one per microservice), each schema containing several tables.
- `app_rabbitmq`: RabbitMQ server for asynchronous message passing between microservices.
- `idp_mysql`: MySQL database used by Keycloak for identity management.
- `idp_keycloak`: Keycloak server running in development mode for authentication and authorization.

### Networks
- `backend`: Network connecting the application services and RabbitMQ.
- `kc`: Network isolating Keycloak services.

## Running the System Locally

### Prerequisites:
- Docker & Docker Compose installed
- Java 17+ and Gradle (for building microservices)

### Start Dependencies:
Run the following command in the root directory to start all services (databases, RabbitMQ, Keycloak, and microservices) together:

```bash
docker-compose up
```


