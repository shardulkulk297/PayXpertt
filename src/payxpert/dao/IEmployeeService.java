package payxpert.dao;
import payxpert.model.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee GetEmployeeById(int employeeId);

    List<Employee> GetAllEmployees();
    void AddEmployee(Employee employeeData);
    void UpdateEmployee(Employee employeeData);
    void RemoveEmployee(int employeeId);
}
