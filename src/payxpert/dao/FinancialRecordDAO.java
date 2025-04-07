package payxpert.dao;

import jdk.jfr.Description;
import payxpert.exception.FinancialRecordException;
import payxpert.model.FinancialRecord;
import payxpert.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinancialRecordDAO {
    private Connection con;
    public FinancialRecordDAO(){
        try{
            con = DBConnection.getConnection();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void AddFinancialRecord(int EmployeeId, String description, double amount, String recordType) throws FinancialRecordException{
        // Edge Case: Check for null/empty description
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }

        // Edge Case: Validate employee ID
        if (EmployeeId <= 0) {
            throw new IllegalArgumentException("Invalid employee ID");
        }

        // Edge Case: Validate amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Edge Case: Validate recordType
        if (recordType == null || (!recordType.equalsIgnoreCase("income") && !recordType.equalsIgnoreCase("expense"))) {
            throw new IllegalArgumentException("Record type must be 'income' or 'expense'");
        }

        // Normalize record type (optional)
        recordType = recordType.toLowerCase();

        try{


            String sql = "Insert into FinancialRecord(EmployeeId, RecordDate, Description, Amount, recordType) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, EmployeeId);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setString(3, description);
            stmt.setDouble(4, amount);
            stmt.setString(5, recordType);
            int rowsAdded = stmt.executeUpdate();

            if(rowsAdded != 0){
                System.out.println("Record Added Successfully");
            }
            else{
                throw new FinancialRecordException("SOMETHING WENT WRONG WHILE ADDING RECORD");
            }
            con.close();

        }
        catch(SQLException e){
            e.printStackTrace();
            throw new FinancialRecordException("ERROR: "+ e.getMessage());

        }


    }

    public FinancialRecord GetFinancialRecordById(int recordId) throws FinancialRecordException{

        if(recordId == 0){
            throw new FinancialRecordException("NO RECORD FOUND FOR THE ID: " + recordId);
        }
        FinancialRecord record = null;
        try{

            String sql = "Select * from FinancialRecord WHERE recordId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, recordId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                record = new FinancialRecord();
                record.setRecordID(recordId);
                record.setRecordDate(rs.getDate("RecordDate"));
                record.setEmployeeID(rs.getInt("EmployeeId"));
                record.setDescription(rs.getString("Description"));
                record.setAmount(rs.getInt("Amount"));
                record.setRecordType(rs.getString("RecordType"));

            }
            if(record == null){
                throw new FinancialRecordException("NO RECORD FOUND FOR THE ID: " + recordId);
            }

            con.close();

        }
        catch(SQLException e){
            e.printStackTrace();
            throw new FinancialRecordException("ERROR:" + e.getMessage());
        }
        return record;

    }
    public List<FinancialRecord> GetFinancialRecordsForEmployees(int EmployeeId) throws FinancialRecordException{
        if(EmployeeId == 0){
            throw new FinancialRecordException("NO RECORD FOUND FOR THE ID: " + EmployeeId);
        }

        FinancialRecord record = null;
        List<FinancialRecord> records = new ArrayList<>();
        try{

            String sql = "Select * from FinancialRecord WHERE EmployeeID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, EmployeeId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                record = new FinancialRecord();
                record.setRecordID(rs.getInt("RecordId"));
                record.setEmployeeID(EmployeeId);
                record.setRecordDate(rs.getDate("RecordDate"));
                record.setAmount(rs.getDouble("Amount"));
                record.setDescription(rs.getString("Description"));
                record.setRecordType(rs.getString("RecordType"));

                records.add(record);
            }
            if(records.isEmpty()){
                throw new FinancialRecordException("NO RECORD FOUND FOR THE ID + " + EmployeeId);
            }
            con.close();

        }
        catch(SQLException e){
            e.printStackTrace();
            throw new FinancialRecordException("ERROR:" + e.getMessage());
        }
        return records;

    }
    public List<FinancialRecord> GetFinancialRecordsForDate(Date recordDate) throws FinancialRecordException{
        if(recordDate ==  null){
            throw new FinancialRecordException("NULL DATE");
        }

        FinancialRecord record = null;
        List<FinancialRecord> records = new ArrayList<>();

        try{

            String sql = "SELECT * FROM FinancialRecord WHERE RecordDate = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(recordDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                record = new FinancialRecord();
                record.setRecordID(rs.getInt("RecordId"));
                record.setEmployeeID(rs.getInt("EmployeeId"));
                record.setRecordDate(recordDate);
                record.setDescription(rs.getString("Description"));
                record.setAmount(rs.getDouble("Amount"));
                record.setRecordType(rs.getString("RecordType"));

                records.add(record);
            }
            if(records.isEmpty()){
                throw new FinancialRecordException("NO Records found for the date: " + recordDate);
            }
            con.close();


        }
        catch(SQLException e){
            e.printStackTrace();
            throw new FinancialRecordException("ERROR:" + e.getMessage());
        }
        return records;

    }
}
