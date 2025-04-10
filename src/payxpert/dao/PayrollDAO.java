package payxpert.dao;

import payxpert.exception.PayrollGenerationException;
import payxpert.model.Employee;
import payxpert.model.Payroll;
import payxpert.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {
    private Connection con;
    public PayrollDAO(){
        try{
            con = DBConnection.getConnection();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


    public Payroll GeneratePayroll(int employeeId, LocalDate localStart, LocalDate localEnd, double basicSalary, double OvertimePay, double deductions, double netSalary) throws PayrollGenerationException {

        if (employeeId <= 0) {
            throw new PayrollGenerationException("Invalid employee ID.");
        }
        if (localStart == null || localEnd == null) {
            throw new PayrollGenerationException("Start and end dates cannot be null.");
        }
        if (localEnd.isBefore(localStart)) {
            throw new PayrollGenerationException("End date cannot be before start date.");
        }
        if (basicSalary < 0 || OvertimePay < 0 || deductions < 0) {
            throw new PayrollGenerationException("Salary, overtime pay, or deductions cannot be negative.");
        }
        if (netSalary != (basicSalary + OvertimePay - deductions)) {
            throw new PayrollGenerationException("Net salary mismatch. Please verify the calculation.");
        }


        Payroll payroll = null;
        try{

            String sql = "INSERT INTO Payroll (employeeId, PayPeriodStartDate, PayPeriodEndDate, basicSalary, OvertimePay, deductions, netSalary) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, employeeId);
            stmt.setDate(2, Date.valueOf(localStart));
            stmt.setDate(3, Date.valueOf(localEnd));
            stmt.setDouble(4, basicSalary);
            stmt.setDouble(5, OvertimePay);
            stmt.setDouble(6, deductions);
            stmt.setDouble(7, netSalary);




            int rowsAdded = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rowsAdded > 0){

                if(rs.next()){
                    int payrollId = rs.getInt(1);
                    payroll = new Payroll();
                    payroll.setPayrollID(payrollId);
                }
                System.out.println("Payroll Generated Successfully");
            }
            else{
                throw new PayrollGenerationException("SOMETHING WENT WRONG WHILE GENERATING PAYROLL");
            }
            payroll.setEmployeeID(employeeId);
            payroll.setPayPeriodStartDate(Date.valueOf(localStart));
            payroll.setPayPeriodEndDate(Date.valueOf(localEnd));
            payroll.setBasicSalary(basicSalary);
            payroll.setDeductions(deductions);
            payroll.setNetSalary(netSalary);
            con.close();

        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return payroll;

    }

    public Payroll GetPayrollById(int payrollId) throws PayrollGenerationException{

        if(payrollId ==0 || payrollId < 0){
            throw new PayrollGenerationException("ID Can't be 0 OR Negative");
        }

        Payroll payroll = null;
        try{

            String sql = "Select * FROM Payroll WHERE PayrollID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, payrollId);

            ResultSet rs = stmt.executeQuery();
            payroll = new Payroll();
            if(rs.next()){
                payroll.setPayrollID(payrollId);
                payroll.setEmployeeID(rs.getInt("employeeId"));
                payroll.setPayPeriodStartDate(rs.getDate("PayPeriodStartDate"));
                payroll.setPayPeriodEndDate(rs.getDate("PayPeriodEndDate"));
                payroll.setBasicSalary(rs.getDouble("BasicSalary"));
                payroll.setDeductions(rs.getDouble("Deductions"));
                payroll.setOvertimePay(rs.getDouble("OvertimePay"));
                payroll.setNetSalary(rs.getDouble("NetSalary"));

            }
            else{
                throw new PayrollGenerationException("Payroll NOT Found");
            }
            con.close();



        }
        catch (SQLException e){
            e.printStackTrace();
            throw new PayrollGenerationException("ERROR: " + e.getMessage());
        }
        return payroll;

    }

    public List<Payroll> GetPayrollsForEmployee(int employeeId) throws PayrollGenerationException{
        if(employeeId == 0 || employeeId < 0){
            throw new PayrollGenerationException("EmployeeId can't be 0 OR Negative");
        }


        Payroll payroll = null;
        List<Payroll> payrolls = new ArrayList<>();
        try{

            String sql = "Select * from Payroll WHERE EmployeeId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            payroll = new Payroll();
            while(rs.next()){
                payroll.setPayrollID(rs.getInt("PayrollId"));
                payroll.setEmployeeID(employeeId);
                payroll.setBasicSalary(rs.getDouble("BasicSalary"));
                payroll.setPayPeriodStartDate(rs.getDate("PayPeriodStartDate"));
                payroll.setPayPeriodEndDate(rs.getDate("PayPeriodEndDate"));
                payroll.setDeductions(rs.getDouble("Deductions"));
                payroll.setOvertimePay(rs.getDouble("OvertimePay"));
                payroll.setNetSalary(rs.getDouble("Netsalary"));
                payrolls.add(payroll);
            }
            if(payrolls.isEmpty()){
                throw new PayrollGenerationException("Payroll Not Found for the Employee ID: " + employeeId );

            }
            con.close();


        }
        catch (SQLException e){
            e.printStackTrace();
            throw new PayrollGenerationException("ERROR: " + e.getMessage());
        }
        return payrolls;
    }
    public List<Payroll> GetPayrollsForPeriod(java.util.Date startDate, java.util.Date endDate) throws PayrollGenerationException{

        if(startDate == null && endDate == null){
            throw new PayrollGenerationException("Dates can't be Null");
        }

        Payroll payroll = null;
        List<Payroll> payrolls = new ArrayList<>();

        try{

            String sql = "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, new Date(startDate.getTime()));
            stmt.setDate(2, new Date(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            payroll = new Payroll();
            while(rs.next()){
                payroll.setPayrollID(rs.getInt("PayrollId"));
                payroll.setEmployeeID(rs.getInt("EmployeeId"));
                payroll.setBasicSalary(rs.getDouble("BasicSalary"));
                payroll.setPayPeriodStartDate(rs.getDate("PayPeriodStartDate"));
                payroll.setPayPeriodEndDate(rs.getDate("PayPeriodEndDate"));
                payroll.setDeductions(rs.getDouble("Deductions"));
                payroll.setOvertimePay(rs.getDouble("OvertimePay"));
                payroll.setNetSalary(rs.getDouble("Netsalary"));
                payrolls.add(payroll);

            }
            if(payrolls.isEmpty()){
                throw new PayrollGenerationException("No records found for the given Date");
            }
            con.close();

        }
        catch(SQLException e){
            e.printStackTrace();
            throw new PayrollGenerationException("ERROR: " + e.getMessage());
        }
        return payrolls;

    }



}
