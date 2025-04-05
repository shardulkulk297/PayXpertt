-- Active: 1742545260664@@127.0.0.1@3306@payxpert
show databases;
Create Database PayXpert;
use PayXpert;
Create table employee(
    Employee_id int PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female') NOT NULL,
    Email VARCHAR(200) NOT NULL UNIQUE,
    PhoneNumber VARCHAR(20) NOT NULL UNIQUE,
    Address VARCHAR(100) NOT NULL,
    Position VARCHAR(100) NOT NULL,
    JoiningDate DATE NOT NULL,
    TerminationDate DATE DEFAULT NULL
    );
Create table Payroll(
    PayrollID INT PRIMARY KEY AUTO_INCREMENT,
    Employee_id INT NOT NULL, 
    PayPeriodStartDate DATE NOT NULL,
    PayPeriodEndDate DATE NOT NULL,
    BasicSalary DECIMAL(10,2) NOT NULL,
    OvertimePay DECIMAL(10,2) NOT NULL,
    Deductions DECIMAL(10,2) NOT NULL,
    NetSalary DECIMAL(10,2) NOT NULL,
    FOREIGN KEY(Employee_id) REFERENCES Employee(Employee_id)
);
Create table Tax(
    TaxID INT PRIMARY KEY AUTO_INCREMENT,
    Employee_id INT NOT NULL,
    TaxYear YEAR NOT NULL,
    TaxableIncome DECIMAL(10,2) NOT NULL,
    TaxAmount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY(Employee_id) REFERENCES Employee(Employee_id)
);

Create table FinancialRecord(
    RecordID INT PRIMARY KEY AUTO_INCREMENT,
    Employee_id INT NOT NULL,
    RecordDate DATE NOT NULL,
    Description VARCHAR(255) NOT NULL,
    Amount DECIMAL(10,2) NOT NULL,
    RecordType ENUM("income", "expense", "tax payment", "other") NOT NULL,
    FOREIGN KEY(Employee_id) REFERENCES Employee(Employee_id)
)


