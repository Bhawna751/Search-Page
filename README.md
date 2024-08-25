# Makersharks Supplier Search API

This Spring Boot application provides an API for managing and searching supplier details. The API allows users to add new suppliers, search for suppliers based on specific criteria, and more.

-----

🔋 **Features:**

👉 Add Supplier: Add supplier details to the database.

👉 Search Suppliers: Retrieve a list of manufacturers based on location, nature of business, and manufacturing process.

👉 RESTful API: Exposes endpoints for managing and searching supplier data.

-----

**Prerequisites**

Before you begin, ensure you have the following installed on your local machine:

-> Java 8 or higher

-> Maven 3.6 or higher

-> MySQL 

-----

🤸 **Quick Start:**

**Cloning the Repository:**
```
git clone https://github.com/Bhawna751/Search-Page.git

```
**Configure the Database:**

Update the application.properties file (located in src/main/resources) with your MySQL database credentials:

```

spring.datasource.url=jdbc:mysql://localhost:3306/suppliers

spring.datasource.username=your_username

spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

```
