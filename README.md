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
cd demo
```
**Configure the Database:**

Update the application.properties file (located in src/main/resources) with your MySQL database credentials:

```

spring.datasource.url=jdbc:mysql://localhost:3306/supplier

spring.datasource.username=root

spring.datasource.password=root123

spring.jpa.hibernate.ddl-auto=update

```
**Build and Run the Application**
```
mvn clean install
mvn spring-boot:run
```
**Access the API:**

Once the application is running, you can access the API at:

Base URL: `http://localhost:8080/api/supplier`

-----
**Example API Requests:**

➕ Add a Supplier
----
To add a supplier, use the following endpoint:

URL: `http://localhost:8080/api/supplier/add`

Method: POST

cURL:
```
curl --location 'http://localhost:8080/api/supplier/add' \
--header 'Content-Type: application/json' \
--data '{
    "companyName": "JKL Manufacturing",
    "website": "http://jkl-manufacturing.com",
    "location": "India",
    "natureOfBusiness": "small_scale",
    "manufacturingProcesses": "3d-Printing"
}'
```

Body:
```
{
    "companyName": "ABC Manufacturing",
    "website": "http://abc-manufacturing.com",
    "location": "India",
    "natureOfBusiness": "small_scale",
    "manufacturingProcesses": "3d_printing"
}
```
🔍 Search for Suppliers
----

To search for suppliers based on location, nature of business, and manufacturing processes:

URL: `http://localhost:8080/api/supplier?page=1&size=5&location=India&natureOfBusiness=small_scale&manufacturingProcesses=3d-Printing,casting,moulding,coating`

Method: GET

cURL:
```
curl --location 'http://localhost:8080/api/supplier?page=1&size=5&location=India&natureOfBusiness=small_scale&manufacturingProcesses=3d-Printing%2Ccasting%2Cmoulding%2Ccoating'
```

⤵️ Import Data from JSON File
----

You can add multiple suppliers from a JSON file using the following endpoint:

URL: `http://localhost:8080/api/supplier/add-from-json`

cURL:
```
curl --location --request POST 'http://localhost:8080/api/supplier/add-from-json'
```
Method: POST

Replace C:/path/to/suppliers.json with the actual file path on your system.

✅ Testing
----
```
mvn test
```
📜 Swagger API Documentation
----
To access Swagger API documentation, access it at:

URL: `http://localhost:8080/swagger-ui.html`





