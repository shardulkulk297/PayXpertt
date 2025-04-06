package payxpert.dao;

import payxpert.exception.FinancialRecordException;
import payxpert.model.FinancialRecord;

import java.util.Date;
import java.util.List;

public interface IFinancialRecordService {

    void AddFinancialRecord(int EmployeeId, String description, double amount, String recordType) throws FinancialRecordException;
    FinancialRecord GetFinancialRecordById(int recordId) throws FinancialRecordException;
    List<FinancialRecord> GetFinancialRecordsForEmployees(int EmployeeId) throws FinancialRecordException;
    List<FinancialRecord> GetFinancialRecordsForDate(Date recordDate) throws FinancialRecordException;

}
