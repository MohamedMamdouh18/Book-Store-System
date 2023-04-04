# Book Store System
This Java application is a comprehensive solution for managing a book store, using PostgreSQL on Supabase for the backend and JavaFX for the frontend. The application offers a user-friendly interface for both regular customers and managers, enabling them to perform various tasks relevant to their roles.

## Table of Contents
- [Features](#Features)
- [Used Libraries](#Used-Libraries)
- [Installation](#Installation)
- [Usage](#Usage)
- [Contributing](#Contributing)
- [Demo](#Demo)
- [Authors](#Authors)

## Features
The application is designed to handle two user roles: regular customer and manager.

### Regular Customer
As a regular customer, users can:

- Edit their personal information, including their password.
- Search for books by any of the book's attributes (with indexed searches for faster results).
- Add books to a shopping cart.
- Manage their shopping cart, including:
    - Viewing the items in the cart.
    - Viewing the individual and total prices of the books in the cart.
    - Removing items from the cart.
    - Checkout a shopping cart, providing credit card information for successful transactions.
- Logout of the system, which removes all items from the current cart.

### Manager
In addition to the regular customer features, managers can:

- Add new books.
- Modify existing books.
- Place orders for books.
- Confirm orders.
- Promote registered customers to have manager credentials.
- View sales reports, including:
    - Total sales for books in the previous month.
    - Top 5 customers with the highest purchase amount in the last three months (descending order).
    - Top 10 selling books for the last three months.

## Used Libraries
This project uses the following libraries:

- [JavaFX](https://openjfx.io/)
- [ControlsFX](https://github.com/controlsfx/controlsfx) (11.1.2)
- [BootstrapFX](https://github.com/kordamp/bootstrapfx) (0.4.0)
- [Project Lombok](https://projectlombok.org/) (1.18.24)
- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/) (42.5.1)


## Installation

### Clone the project
To clone the project, run the following command in your terminal:

```bash
!git clone https://github.com/yourusername/book-store-app.git
```

### Configure the database
- Open the DBConstants.java file located in the src/main/java/Database directory.
- Change the following variables according to your PostgreSQL and Supabase configurations:
```java
public final static String DatabaseURL = "your_database_url";
public final static String username = "your_database_username";
public final static String password = "your_database_password";
```
Note: Make sure to replace your_database_url, your_database_username, and your_database_password with your actual PostgreSQL and Supabase credentials and for every role the application have -Admin, Manager and Customer-.

## Usage
- Run the application from your IDE or via the command line.
- Log in or register as a regular customer or manager.
- Perform the actions available to your user role.


## Contributing
We welcome contributions to improve the Book Store App! Please follow the steps below to contribute:

- Fork the repository.
- Create a new branch for your feature or bugfix.
- Make changes and commit them to your branch.
- Open a pull request with a clear description of your changes.
- We will review and merge your changes as needed.

## Demo



https://user-images.githubusercontent.com/63814228/229879821-9ce2cba1-0c5b-4bf3-8cb0-6aa946256afa.mp4



## Authors

- [@Ahmed Adel](https://github.com/Deffo0)
- [@Esraa Aboshady](https://github.com/es539)
- [@Momen Gharib](https://github.com/MomenGharib1)
- [@Mahmoud Mohamed](https://github.com/Mahmoud-Moh)
- [@Mohamed Mamdouh](https://github.com/MohamedMamdouh18)
- [@Muhammad Elkotb](https://github.com/MuhammadElkotb)
- [@Youssef Bazina](https://github.com/Bazina)
- [@Ziad Reda](https://github.com/ziadreda72)
