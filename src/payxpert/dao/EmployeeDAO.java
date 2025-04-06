package payxpert.dao;

import com.mysql.cj.protocol.Resultset;
import payxpert.exception.EmployeeNotFoundException;
import payxpert.model.Employee;
import payxpert.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public Employee GetEmployeeById(int employeeId) throws EmployeeNotFoundException {



        Employee emp = null;
        Connection con = null;
        try{
            if(employeeId == 0){
                throw new EmployeeNotFoundException("Employee ID Can't be 0");
            }
            con = DBConnection.getConnection();
            String query = "Select * from Employees WHERE employeeId = ?";
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
            Connection con = DBConnection.getConnection();
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


        try{
            Connection conn = DBConnection.getConnection();
            String sql = "Insert into Employee (EmployeeId, FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, EmployeeData.getEmployeeId());
            stmt.setString(2, EmployeeData.getFirstName());
            stmt.setString(3, EmployeeData.getLastName());
            stmt.setDate(4, new Date(EmployeeData.getDateOfBirth().getTime()));
            stmt.setString(5, EmployeeData.getGender());
            stmt.setString(6, EmployeeData.getEmail());
            stmt.setInt(7, EmployeeData.getPhoneNumber());
            stmt.setString(8, EmployeeData.getAddress());
            stmt.setString(9, EmployeeData.getPosition());
            stmt.setDate(10, new Date(EmployeeData.getJoiningDate().getTime()));
            stmt.setDate(11, new Date(EmployeeData.getTerminationDate().getTime()));
            int rowsAdded = stmt.executeUpdate ();

            if(rowsAdded!=0){
                System.out.println("Added Successfully");
            }
            else{
                throw new EmployeeNotFoundException("Something went wrong while adding employee");
            }
            conn.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }



    }
    public void UpdateEmployee(Employee employeeData) throws EmployeeNotFoundException{

        try{
            Connection con = DBConnection.getConnection();
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
        try{
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM Employees WHERE EmployeeId = ?";
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
