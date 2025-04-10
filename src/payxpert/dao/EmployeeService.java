package payxpert.dao;

import payxpert.exception.EmployeeNotFoundException;
import payxpert.exception.InvalidInputException;
import payxpert.model.Employee;

import java.util.List;

public class EmployeeService implements IEmployeeService {
        private EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public Employee GetEmployeeById(int employeeId) throws EmployeeNotFoundException  {
        return employeeDAO.GetEmployeeById(employeeId);

    }
    @Override
    public List<Employee> GetAllEmployees() throws EmployeeNotFoundException {
        return employeeDAO.GetAllEmployees();

    }
    @Override
    public void AddEmployee(Employee employeeData) throws EmployeeNotFoundException, InvalidInputException {
        employeeDAO.AddEmployee(employeeData);

    }
    @Override
    public void RemoveEmployee(int EmployeeId) throws EmployeeNotFoundException {
        employeeDAO.RemoveEmployee(EmployeeId);

    }
    @Override
    public void UpdateEmployee(Employee employeeData) throws EmployeeNotFoundException {
        employeeDAO.UpdateEmployee(employeeData);

    }
}
