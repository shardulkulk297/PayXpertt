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
    FOREIGN KEY(Employee_id) REFERENCES Employee(Employee_id) ON DELETE CASCADE
);
Create table Tax(
    TaxID INT PRIMARY KEY AUTO_INCREMENT,
    Employee_id INT NOT NULL,
    TaxYear YEAR NOT NULL,
    TaxableIncome DECIMAL(10,2) NOT NULL,
    TaxAmount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY(Employee_id) REFERENCES Employee(Employee_id) ON DELETE CASCADE
);

Create table FinancialRecord(
    RecordID INT PRIMARY KEY AUTO_INCREMENT,
    Employee_id INT NOT NULL,
    RecordDate DATE NOT NULL,
    Description VARCHAR(255) NOT NULL,
    Amount DECIMAL(10,2) NOT NULL,
    RecordType ENUM("income", "expense", "tax payment", "other") NOT NULL,
    FOREIGN KEY(Employee_id) REFERENCES Employee(Employee_id) ON DELETE CASCADE
)


INSERT INTO Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) VALUES
('Ash', 'Mehta', '1992-04-15', 'Male', 'ash.mehta@gmail.com', '9876543210', 'Mumbai', 'Software Developer', '2022-01-10', NULL),
('Shardul', 'Kulkarni', '1998-12-08', 'Male', 'shardul.kulkarni@gmail.com', '9123456789', 'Pune', 'Backend Engineer', '2023-02-15', NULL),
('Virat', 'Kohli', '1988-11-05', 'Male', 'virat.kohli@gmail.com', '9000000001', 'Delhi', 'Product Manager', '2021-06-01', NULL),
('Neha', 'Rao', '1995-07-20', 'Female', 'neha.rao@gmail.com', '9000000002', 'Bangalore', 'QA Engineer', '2023-04-20', NULL);

INSERT INTO Payroll (Employee_id, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) VALUES
(1, '2024-01-01', '2024-01-31', 50000.00, 2500.00, 5000.00, 47500.00),
(2, '2024-01-01', '2024-01-31', 45000.00, 2000.00, 4000.00, 43000.00),
(3, '2024-01-01', '2024-01-31', 80000.00, 5000.00, 10000.00, 75000.00),
(4, '2024-01-01', '2024-01-31', 40000.00, 1000.00, 3000.00, 38000.00);


INSERT INTO Tax (Employee_id, TaxYear, TaxableIncome, TaxAmount) VALUES
(1, 2024, 52500.00, 15750.00),
(2, 2024, 47000.00, 14100.00),
(3, 2024, 85000.00, 25500.00),
(4, 2024, 41000.00, 12300.00);



INSERT INTO FinancialRecord (Employee_id, RecordDate, Description, Amount, RecordType) VALUES
(1, '2024-01-10', 'Performance Bonus', 3000.00, 'income'),
(1, '2024-01-20', 'Health Insurance', 2000.00, 'expense'),
(2, '2024-01-15', 'Salary Advance', 5000.00, 'expense'),
(3, '2024-01-18', 'Client Meeting Reimbursement', 2500.00, 'income'),
(4, '2024-01-25', 'TDS Payment', 12300.00, 'tax payment');

Alter table Employee Rename COLUMN Employee_id to EmployeeId;