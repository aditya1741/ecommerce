# 🛒 Enterprise Commerce 

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Architecture](https://img.shields.io/badge/Architecture-Modular%20Monolith-purple)

### 🚀 Enterprise E-Commerce Backend Application

Built using Spring Boot, Spring Data JPA, PostgreSQL, DTO Pattern, Validation, Exception Handling, and SOLID Principles.

</div>

---

# 📖 Table of Contents

- [Project Overview](#-project-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Database Design](#-database-design)
- [API Documentation](#-api-documentation)
- [Workflow](#-workflow)
- [SOLID Principles](#-solid-principles)
- [Getting Started](#-getting-started)
- [Future Improvements](#-future-improvements)

---

# 🎯 Project Overview

Enterprise Commerce Cloud is a modular monolith e-commerce backend application designed to simulate real-world enterprise systems.

The project implements:

- User Management
- Product Management
- Category Management
- Cart Management
- Order Processing
- DTO Pattern
- Validation
- Exception Handling
- Pagination
- Layered Architecture

This project serves as a foundation for future implementation of:

- Spring Security
- JWT Authentication
- Redis Caching
- Kafka
- Docker
- Microservices

---

# ✨ Features

## 👤 User Module

- Create User
- Get User Details
- User Validation

## 📦 Product Module

- Add Product
- Update Product
- Delete Product
- Search Product
- Product Pagination

## 🏷️ Category Module

- Create Category
- Update Category
- Delete Category
- View Categories

## 🛒 Cart Module

- Add Product To Cart
- Remove Product From Cart
- Update Quantity
- Calculate Cart Total

## 📋 Order Module

- Checkout
- Create Order
- View Orders
- Order Details

---

# 🛠 Tech Stack

| Technology | Purpose |
|------------|----------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring MVC | REST APIs |
| Spring Data JPA | Database Operations |
| Hibernate | ORM |
| PostgreSQL | Database |
| Maven | Build Tool |
| Jakarta Validation | Validation |
| ModelMapper | DTO Mapping |

---

# 🏗 Architecture

```text
Client
   │
   ▼
Controller Layer
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
Database
```

---

# 📂 Project Structure

```text
src/main/java/com/aditya/ecommerce
│
├── controller
│
├── dtos
│
├── entity
│
├── repository
│
├── service
│   ├── IService
│   └── impl
│
├── mapper
│
├── validator
│
├── exception
│
├── config
│
├── utils
│
└── EcommerceApplication.java
```

---

# 🗄 Database Design

## User

```text
id
name
email
password
created_at
```

## Category

```text
id
name
description
```

## Product

```text
id
name
description
price
stock
category_id
```

## Cart

```text
id
user_id
created_at
```

## Cart Item

```text
id
cart_id
product_id
quantity
total_price
```

## Order

```text
id
user_id
total_amount
status
created_at
```

## Order Item

```text
id
order_id
product_id
quantity
price
```

---

# 📡 API Documentation

---

# 👤 User APIs

## Create User

### Request

```http
POST /users
```

### Request Body

```json
{
  "name": "Aditya",
  "email": "aditya@gmail.com",
  "password": "123456"
}
```

### Response

```json
{
  "id": 1,
  "name": "Aditya",
  "email": "aditya@gmail.com"
}
```

---

## Get User By ID

### Request

```http
GET /users/{id}
```

Example

```http
GET /users/1
```

---

## Get All Users

### Request

```http
GET /users
```

---

# 🏷 Category APIs

## Create Category

```http
POST /categories
```

### Request Body

```json
{
  "name": "Electronics"
}
```

---

## Get All Categories

```http
GET /categories
```

---

## Get Category By ID

```http
GET /categories/{id}
```

---

## Delete Category

```http
DELETE /categories/{id}
```

---

# 📦 Product APIs

## Create Product

```http
POST /products
```

### Request Body

```json
{
  "name": "Laptop",
  "description": "Gaming Laptop",
  "price": 75000,
  "stock": 20,
  "categoryId": 1
}
```

---

## Get Product By ID

```http
GET /products/{id}
```

Example

```http
GET /products/1
```

---

## Get All Products

```http
GET /products
```

---

## Pagination

```http
GET /products?page=0&size=10
```

### Parameters

| Parameter | Description |
|------------|------------|
| page | Page Number |
| size | Records Per Page |

---

## Update Product

```http
PUT /products/{id}
```

### Request Body

```json
{
  "name": "Laptop Pro",
  "price": 80000
}
```

---

## Delete Product

```http
DELETE /products/{id}
```

---

# 🛒 Cart APIs

## Add To Cart

```http
POST /cart/add
```

### Request Body

```json
{
  "userId": 1,
  "productId": 2,
  "quantity": 3
}
```

---

## View Cart

```http
GET /cart/{userId}
```

Example

```http
GET /cart/1
```

---

## Remove Item From Cart

```http
DELETE /cart/item/{cartItemId}
```

Example

```http
DELETE /cart/item/5
```

---

# 📋 Order APIs

## Checkout

```http
POST /orders/checkout
```

### Request Body

```json
{
  "userId": 1
}
```

---

## Get All Orders

```http
GET /orders
```

---

## Get Order By ID

```http
GET /orders/{orderId}
```

---

## Get User Orders

```http
GET /orders/user/{userId}
```

---

# 🔄 Workflow

## Product Purchase Flow

```text
Browse Product
      │
      ▼
Add To Cart
      │
      ▼
View Cart
      │
      ▼
Checkout
      │
      ▼
Create Order
      │
      ▼
Update Inventory
      │
      ▼
Order Success
```

---

## Request Flow

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

---

# ⚠ Exception Handling

Implemented using:

```java
@RestControllerAdvice
```

Examples:

```text
ProductNotFoundException
UserNotFoundException
CategoryNotFoundException
```

Benefits:

- Centralized Error Handling
- Consistent API Responses
- Cleaner Controllers

---

# 🎨 SOLID Principles

## S — Single Responsibility Principle

Each class has one responsibility.

Example:

```text
ProductService
ProductRepository
ProductMapper
```

---

## O — Open Closed Principle

Open for extension, closed for modification.

---

## L — Liskov Substitution Principle

Interfaces can be replaced with implementations safely.

---

## I — Interface Segregation Principle

Small focused interfaces.

```text
IProductService
ICategoryService
IUserService
```

---

## D — Dependency Inversion Principle

Controllers depend on abstractions.

```java
private final IProductService productService;
```

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/aditya1741/ecommerce.git
```

## Configure Database

```sql
CREATE DATABASE ecommerce_db;
```

## Update application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=postgres
spring.datasource.password=password
```

## Run Application

```bash
mvn spring-boot:run
```

---

# 📈 Future Improvements

- Spring Security
- JWT Authentication
- Refresh Token
- Redis Cache
- Docker
- Kafka
- Notification Service
- Payment Gateway Integration
- Monitoring
- Microservices Architecture

---

# 🎓 Learning Outcomes

After completing this project, you will gain practical experience in:

✅ Spring Boot

✅ Spring MVC

✅ Spring Data JPA

✅ Hibernate

✅ PostgreSQL

✅ DTO Pattern

✅ Validation

✅ Exception Handling

✅ Pagination

✅ SOLID Principles

✅ Enterprise Backend Development

---

<div align="center">

### ⭐ If you found this project useful, give it a star!

Made with ❤️ using Spring Boot

</div>
