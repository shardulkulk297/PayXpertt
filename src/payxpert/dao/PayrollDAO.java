package payxpert.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import payxpert.exception.PayrollGenerationException;
import payxpert.model.Payroll;
import payxpert.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {
    public Payroll GeneratePayroll(int employeeId, LocalDate localStart, LocalDate localEnd, double basicSalary, double deductions, double netSalary) throws PayrollGenerationException {
        Payroll payroll = null;
        try{
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO Payroll (employeeId, PayPeriodStartDate, PayPeriodEndDate, basicSalary, deductions, netSalary) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, employeeId);
            stmt.setDate(2, Date.valueOf(localStart));
            stmt.setDate(3, Date.valueOf(localEnd));
            stmt.setDouble(4, basicSalary);
            stmt.setDouble(5, deductions);
            stmt.setDouble(6, netSalary);




            int rowsAdded = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rowsAdded > 0){
                System.out.println("Payroll Generated Successfully");
                int payrollId = rs.getInt(1);
                payroll = new Payroll();
                payroll.setPayrollID(payrollId);
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

        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return payroll;

    }

    public Payroll GetPayrollById(int payrollId) throws PayrollGenerationException{

        Payroll payroll = null;
        try{
            Connection con = DBConnection.getConnection();
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
                throw new PayrollGenerationException("Payroll was not able to be generated");
            }



        }
        catch (SQLException e){
            e.printStackTrace();
            throw new PayrollGenerationException("ERROR: " + e.getMessage());
        }
        return payroll;

    }

    public List<Payroll> GetPayrollsForEmployee(int employeeId) throws PayrollGenerationException{
        Payroll payroll = null;
        List<Payroll> payrolls = new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
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
                throw new PayrollGenerationException("SOMETHING WENT WRONG");

            }


        }
        catch (SQLException e){
            e.printStackTrace();
            throw new PayrollGenerationException("ERROR: " + e.getMessage());
        }
        return payrolls;
    }
    public List<Payroll> GetPayrollsForPeriod(java.util.Date startDate, java.util.Date endDate) throws PayrollGenerationException{
        Payroll payroll = null;
        List<Payroll> payrolls = new ArrayList<>();

        try{
            Connection con = DBConnection.getConnection();
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
                throw new PayrollGenerationException("SOMETHING WENT WRONG");
            }

        }
        catch(SQLException e){
            e.printStackTrace();
            throw new PayrollGenerationException("ERROR: " + e.getMessage());
        }
        return payrolls;

    }



}
