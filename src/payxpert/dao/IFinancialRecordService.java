package payxpert.dao;

import payxpert.model.FinancialRecord;

import java.util.Date;
import java.util.List;

public interface IFinancialRecordService {

    void AddFinancialRecord(int EmployeeId, String description, double amount, String recordType );
    FinancialRecord GetFinancialRecordById(int recordId);
    List<FinancialRecord> GetFinancialRecordsForEmployees(int EmployeeId);
    List<FinancialRecord> GetFinancialRecordsForDate(Date recordDate);

}
