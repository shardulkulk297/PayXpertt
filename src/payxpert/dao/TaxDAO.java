package payxpert.dao;

import payxpert.exception.TaxCalculationException;
import payxpert.model.Tax;
import payxpert.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxDAO {
    private Connection con;
    public TaxDAO(){
        try{
            con = DBConnection.getConnection();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public double CalculateTax(int employeeId, int taxYear) throws TaxCalculationException {

        if(employeeId == 0 || employeeId < 0 && taxYear == 0 || taxYear < 0){
            throw new TaxCalculationException("VALUES CAN'T BE 0 OR NEGATIVE");
        }


        Tax tax = null;
        double taxAmount = 0;
        double taxableIncome = 0;
        try{


            String sql = "Select * from Payroll WHERE Employee_Id = ? AND YEAR(PayPeriodStartDate) = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            stmt.setInt(2, taxYear);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                double basicSalary = rs.getDouble("BasicSalary");
                double overtimePay = rs.getDouble("OvertimePay");
                double deductions = rs.getDouble("Deductions");

                taxableIncome += basicSalary + overtimePay - deductions; //all the payrolls

            }
            taxAmount = taxableIncome * 0.10;

            String mainsql = "INSERT INTO Tax (EmployeeId, taxYear, taxableIncome, taxAmount) VALUES( ?, ?, ?, ?)";
            PreparedStatement stmt2 = con.prepareStatement(mainsql);
            stmt2.setInt(1, employeeId);
            stmt2.setInt(2, taxYear);
            stmt2.setDouble(3, taxableIncome);
            stmt2.setDouble(4, taxAmount);

            int rowsAdded = stmt2.executeUpdate();
            if(rowsAdded > 0){
                System.out.println("Calculated TAX Successfully, TaxAmount is: " + taxAmount );
            }
            else{
                throw new TaxCalculationException("SOMETHING WENT WRONG");
            }

            con.close();



        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaxCalculationException("ERROR:"+e.getMessage());
        }
        return taxAmount;


    }


    public Tax GetTaxById(int taxId) throws TaxCalculationException{

        if(taxId == 0 || taxId < 0){
            throw new TaxCalculationException("Tax Id can't be 0 or negative");
        }

        Tax tax = null;
        try{

            String sql = "Select * from Tax WHERE TaxId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, taxId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                tax = new Tax();
                tax.setTaxID(taxId);
                tax.setTaxYear(rs.getInt("TaxYear"));
                tax.setEmployeeID(rs.getInt("EmployeeId"));
                tax.setTaxAmount(rs.getDouble("TaxAmount"));
                tax.setTaxableIncome(rs.getDouble("TaxableIncome"));

            }
            else{
                throw new TaxCalculationException("TaxId not found");
            }
            con.close();



        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaxCalculationException("ERROR:"+e.getMessage());
        }
        return tax;

    }


    public List<Tax> GetTaxesForEmployee(int employeeId) throws TaxCalculationException{
        if(employeeId == 0 || employeeId < 0){
            throw new TaxCalculationException("ID Can't be 0 OR Null");
        }


        Tax tax = null;
        List<Tax> taxes = new ArrayList<>();
        try{

            String sql = "SELECT * FROM Tax WHERE EmployeeId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                tax = new Tax();
                tax.setTaxID(rs.getInt("TaxId"));
                tax.setTaxYear(rs.getInt("TaxYear"));
                tax.setEmployeeID(rs.getInt("EmployeeId"));
                tax.setTaxAmount(rs.getDouble("TaxAmount"));
                tax.setTaxableIncome(rs.getDouble("TaxableIncome"));

                taxes.add(tax);
            }
            if(taxes.isEmpty()){
                throw new TaxCalculationException("Taxes for EmployeeID" + employeeId + "NOT FOUND");
            }
            con.close();




        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaxCalculationException("ERROR:"+e.getMessage());
        }
        return taxes;

    }

    public List<Tax> GetTaxesForYear(int taxYear) throws TaxCalculationException{

        if(taxYear == 0 || taxYear < 0){
            throw new TaxCalculationException("Year can't be 0 Or null");
        }

        Tax tax = null;
        List<Tax> taxes = new ArrayList<>();
        try{

            String sql = "SELECT * FROM Tax WHERE TaxYear = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, taxYear);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                tax = new Tax();
                tax.setTaxID(rs.getInt("TaxId"));
                tax.setTaxYear(rs.getInt("TaxYear"));
                tax.setEmployeeID(rs.getInt("EmployeeId"));
                tax.setTaxAmount(rs.getDouble("TaxAmount"));
                tax.setTaxableIncome(rs.getDouble("TaxableIncome"));

                taxes.add(tax);
            }
            if(taxes.isEmpty()){
                throw new TaxCalculationException("Taxes for Year" + taxYear + "NOT FOUND");
            }
            con.close();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaxCalculationException("ERROR:"+e.getMessage());
        }
        return taxes;

    }
}
