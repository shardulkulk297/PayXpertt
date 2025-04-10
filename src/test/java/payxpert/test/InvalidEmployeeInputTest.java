package payxpert.test;

import org.junit.jupiter.api.Test;
import payxpert.dao.EmployeeService;
import payxpert.exception.EmployeeNotFoundException;
import payxpert.exception.InvalidInputException;
import payxpert.model.Employee;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class InvalidEmployeeInputTest {

    EmployeeService emp = new EmployeeService();
    @Test
    public void InvalidEmployeeIdInput(){
        assertThrows(EmployeeNotFoundException.class, ()->{
            emp.GetEmployeeById(0);
        });
    }

    @Test
    public void testInvalidEmailFormat() {
        Employee empp = new Employee();
        empp.setFirstName("Test");
        empp.setLastName("User");
        empp.setEmail("invalid-email");

        empp.setPhoneNumber(1234567890); // Required field
        empp.setGender("Male");
        empp.setAddress("Some Address");
        empp.setPosition("Tester");

        // Set valid dates
        empp.setDateOfBirth(new Date());
        empp.setJoiningDate(new Date());
        empp.setTerminationDate(null); // optional field

        assertThrows(InvalidInputException.class, () -> {
            emp.AddEmployee(empp);
        });
    }

    @Test
    public void testMissingRequiredFields() {
        Employee empp = new Employee(); // Missing all fields

        assertThrows(InvalidInputException.class, () -> {
            emp.AddEmployee(empp);
        });
    }
  
}