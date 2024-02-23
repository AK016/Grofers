# Grofers

This document provides an overview of the RESTful API endpoints available for the Grofers application, focusing on functionalities related to administration and user actions. The API facilitates operations such as managing suppliers, products, categories, users, and orders.

## Base URL

Please note that all URLs referenced in the documentation are based on a local development environment. You might need to adjust them according to your deployment.

```
http://localhost:8080
```

## Administration Endpoints

These endpoints are protected and require the user to have an `ADMIN` role.

### Supplier Management

- **Add a Supplier**
  - **Method:** POST
  - **URL:** `/admin/suppliers`
  - **Body:**
  ```json
  {
    "name": "Supplier Name",
    "email": "supplier@example.com"
  }
  ```

### Product Management

- **Add a Product for a Specific Supplier**
  - **Method:** POST
  - **URL:** `/admin/products/{supplierId}`
  - **Body:**
  ```json
  {
    "name": "Product Name",
    "categoryId": 1,
    "price": 100.0
  }
  ```

### Category Management

- **Add a Category**
  - **Method:** POST
  - **URL:** `/admin/categories`
  - **Body:**
  ```json
  {
    "name": "Category Name"
  }
  ```

### User Management

- **Add a New Admin User**
  - **Method:** POST
  - **URL:** `/admin/users/admin`
  - **Body:**
  ```json
  {
    "name": "Admin Name",
    "email": "admin@example.com",
    "password": "securepassword",
    "role": "ADMIN"
  }
  ```

- **Fetch All Users (Admin)**
  - **Method:** GET
  - **URL:** `/admin/users/admin`

### Order Management

- **Delete an Order (Admin)**
  - **Method:** DELETE
  - **URL:** `/admin/orders/{orderId}/admin`

## User Endpoints

### Authentication and User Registration

- **Register a New User**
  - **Method:** POST
  - **URL:** `/users/`
  - **Body:**
  ```json
  {
    "name": "User Name",
    "email": "user@example.com",
    "password": "userpassword",
    "role": "USER"
  }
  ```

- **Authenticate a User and Return JWT**
  - **Method:** POST
  - **URL:** `/users/auth/login`
  - **Body:**
  ```json
  {
    "username": "user@example.com",
    "password": "userpassword"
  }
  ```

### Order Placement and Management

- **Place an Order**
  - **Method:** POST
  - **URL:** `/users/orders/{userId}`
  - **Body:**
  ```json
  {
    "products": [1, 2, 3],
    "orderDate": "2024-02-23",
    "deliveryDate": "2024-02-25",
    "totalAmount": 150.50
  }
  ```

### User Order History

- **Fetch a User's Order History**
  - **Method:** GET
  - **URL:** `/users/{userId}/orders`

## General Notes

- Ensure the request headers include a valid JWT token for authenticated endpoints.
- Adjust JSON payloads based on the specific attributes of your entities.
- This is a simplified documentation. Depending on your API's complexity and requirements, you might need to include additional details such as headers, authentication methods, and status codes.

## Postman Collection

To test these endpoints, you can create a Postman collection. This allows for easier management and execution of requests during development and testing phases.
