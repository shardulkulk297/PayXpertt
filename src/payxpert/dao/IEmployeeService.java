package payxpert.dao;
import payxpert.exception.EmployeeNotFoundException;
import payxpert.model.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee GetEmployeeById(int employeeId) throws EmployeeNotFoundException;

    List<Employee> GetAllEmployees() throws EmployeeNotFoundException;
    void AddEmployee(Employee employeeData) throws EmployeeNotFoundException;
    void UpdateEmployee(Employee employeeData) throws EmployeeNotFoundException;
    void RemoveEmployee(int employeeId) throws EmployeeNotFoundException;
}
