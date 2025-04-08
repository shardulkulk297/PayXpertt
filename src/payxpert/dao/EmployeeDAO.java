package payxpert.dao;

import com.mysql.cj.protocol.Resultset;
import payxpert.exception.EmployeeNotFoundException;
import payxpert.model.Employee;
import payxpert.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection con;

    public EmployeeDAO(){
        try{
            con = DBConnection.getConnection();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Employee GetEmployeeById(int employeeId) throws EmployeeNotFoundException {



        Employee emp = null;

        try{
            if(employeeId == 0){
                throw new EmployeeNotFoundException("Employee ID Can't be 0");
            }
            String query = "Select * from Employees WHERE EmployeeID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                emp = new Employee();
                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("FirstName"));
                emp.setLastName(rs.getString("LastName"));
                emp.setPhoneNumber(rs.getInt("Phonenumber"));

            }
            else{
                throw new EmployeeNotFoundException("Employee not found for Id: " + employeeId);
            }

            con.close();



        }
        catch (SQLException e){
            e.printStackTrace();
        }



        return emp;

    }
    public List<Employee> GetAllEmployees() throws EmployeeNotFoundException{

        List<Employee> employees = new ArrayList<>();

        try{
            String sql = "Select * from Employee";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Employee emp = new Employee();

                emp.setEmployeeId(rs.getInt("EmployeeId"));
                emp.setFirstName(rs.getString("FirstName"));
                emp.setLastName(rs.getString("LastName"));
                emp.setEmail(rs.getString("Email"));
                emp.setDateOfBirth(rs.getDate("DateOfBirth"));
                emp.setPhoneNumber(rs.getInt("PhoneNumber"));

                employees.add(emp);
            }
            if(employees.isEmpty()){
                throw new EmployeeNotFoundException("SOMETHING WENT Wrong while getting employees");
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;

    }
    public void AddEmployee(Employee EmployeeData) throws EmployeeNotFoundException {
        if(EmployeeData == null){
            throw new EmployeeNotFoundException("NULL DATA");
        }

        try{

            String sql = "Insert into Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, EmployeeData.getFirstName());
            stmt.setString(2, EmployeeData.getLastName());
            stmt.setDate(3, new Date(EmployeeData.getDateOfBirth().getTime()));
            stmt.setString(4, EmployeeData.getGender());
            stmt.setString(5, EmployeeData.getEmail());
            stmt.setInt(6, EmployeeData.getPhoneNumber());
            stmt.setString(7, EmployeeData.getAddress());
            stmt.setString(8, EmployeeData.getPosition());
            stmt.setDate(9, new Date(EmployeeData.getJoiningDate().getTime()));
            if (EmployeeData.getTerminationDate() != null) {
                stmt.setDate(10, new Date(EmployeeData.getTerminationDate().getTime()));
            } else {
                stmt.setNull(10, java.sql.Types.DATE);
            }
            int rowsAdded = stmt.executeUpdate ();

            if(rowsAdded!=0){
                System.out.println("Added Successfully");
            }
            else{
                throw new EmployeeNotFoundException("Something went wrong while adding employee");
            }
            con.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }



    }
    public void UpdateEmployee(Employee employeeData) throws EmployeeNotFoundException{
        if(employeeData == null){
            throw new EmployeeNotFoundException("NULL DATA");
        }
        try{
            String sql = "UPDATE Employee SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, Email = ?, PhoneNumber = ?, Address = ?, Position = ?, JoiningDate = ?, TerminationDate = ? WHERE EmployeeID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, employeeData.getFirstName());
            stmt.setString(2, employeeData.getLastName());
            stmt.setDate(3, new java.sql.Date(employeeData.getDateOfBirth().getTime()));
            stmt.setString(4, employeeData.getGender());
            stmt.setString(5, employeeData.getEmail());
            stmt.setInt(6, employeeData.getPhoneNumber());
            stmt.setString(7, employeeData.getAddress());
            stmt.setString(8, employeeData.getPosition());
            stmt.setDate(9, new java.sql.Date(employeeData.getJoiningDate().getTime()));
            if (employeeData.getTerminationDate() != null) {
                stmt.setDate(10, new java.sql.Date(employeeData.getTerminationDate().getTime()));
            } else {
                stmt.setNull(10, java.sql.Types.DATE);
            }
            stmt.setInt(11, employeeData.getEmployeeId());

            int rows = stmt.executeUpdate();

            if(rows > 0){
                System.out.println("Employee Updated Successfully");
            }
            else{
                throw new EmployeeNotFoundException("Employee not found for id: " + employeeData.getEmployeeId());
            }
            con.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }



    }
    public void RemoveEmployee(int EmployeeId) throws EmployeeNotFoundException{
        if(EmployeeId == 0 || EmployeeId < 0){
            throw new EmployeeNotFoundException("Employee ID should not be 0 OR Negative");
        }
        try{
            String sql = "DELETE FROM Employee WHERE EmployeeID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, EmployeeId);
            int rowsAffected = stmt.executeUpdate();

            if(rowsAffected > 0){
                System.out.println("DELETED SUCCESSFULLY");
            }
            else{
                throw new EmployeeNotFoundException("Employee ID NOT FOUND: "+ EmployeeId);
            }
            con.close();

        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

}
