# Employee Database App

## Overview
A **Java 17+ console app** connecting to **MySQL** or **PostgreSQL** with JDBC for employee management (CRUD).  
Supports transactions for commit/rollback of multiple operations. Beginner-friendly with clear instructions and examples.

---

## Features
- Add/View/Update/Delete employees  
- Transaction support for safe commits/rollback  
- PreparedStatement for security  
- Works with MySQL or PostgreSQL  
- Modern Java 17+ style

---

## Technologies
- Java 17+  
- JDBC API  
- MySQL/PostgreSQL databases  
- MySQL Connector/J or PostgreSQL JDBC driver

---

## Setup & Installation

### 1. Install
- Java 17+ (check `java -version`)  
- MySQL or PostgreSQL

### 2. Create Database & Table

**For MySQL:**

CREATE DATABASE employee_db;
USE employee_db;
CREATE TABLE employees (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
department VARCHAR(50),
salary DOUBLE
);


**For PostgreSQL:**

CREATE DATABASE employee_db;
\c employee_db;
CREATE TABLE employees (
id SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL,
department VARCHAR(50),
salary DOUBLE PRECISION
);


### 3. Download JDBC Driver
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)  
- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download.html)

### 4. Configure in `EmployeeApp.java`

private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
private static final String USER = "root"; // your DB username
private static final String PASSWORD = "password"; // your DB password

*Change `jdbc:mysql` to `jdbc:postgresql` for PostgreSQL.*

---

## How to Run

Compile the Java file:

Run it with the JDBC driver in your classpath:
javac EmployeeApp.java

**Windows:**
java -cp ".;mysql-connector-j-9.0.0.jar" EmployeeApp

**Linux/macOS:**
java -cp ".:mysql-connector-j-9.0.0.jar" EmployeeApp


---

## Example Usage

===== Employee Database Menu =====

1. Add Employee
2. View All Employees
3. Update Employee Salary
4. Delete Employee
5. Exit
6. 
Enter choice: 1
Enter name: Burhan
Enter department: IT
Enter salary: 550000
✅ Employee added successfully!


---

## Learning Points

- JDBC connections and CRUD with PreparedStatement  
- Transactions for commit/rollback control  
- Input validation best practices  
- Easily switch between MySQL and PostgreSQL  

---

## License


