package payxpert.dao;

import payxpert.exception.FinancialRecordException;
import payxpert.model.FinancialRecord;

import java.util.Date;
import java.util.List;

public class FinancialRecordService implements IFinancialRecordService {
    FinancialRecordDAO financialRecordDAO = new FinancialRecordDAO();

    @Override
    public void AddFinancialRecord(int EmployeeId, String description, double amount, String recordType) throws FinancialRecordException {

        financialRecordDAO.AddFinancialRecord(EmployeeId, description, amount, recordType);
    }
    @Override
    public FinancialRecord GetFinancialRecordById(int recordId) throws FinancialRecordException{
        return financialRecordDAO.GetFinancialRecordById(recordId);

    }
    @Override
    public List<FinancialRecord> GetFinancialRecordsForEmployees(int EmployeeId) throws FinancialRecordException{
        return financialRecordDAO.GetFinancialRecordsForEmployees(EmployeeId);

    }
    @Override
    public List<FinancialRecord> GetFinancialRecordsForDate(Date recordDate) throws FinancialRecordException{
        return financialRecordDAO.GetFinancialRecordsForDate(recordDate);

    }

}
