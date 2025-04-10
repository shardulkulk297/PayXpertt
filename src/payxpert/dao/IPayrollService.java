package payxpert.dao;

import payxpert.exception.PayrollGenerationException;
import payxpert.model.Payroll;

import java.util.Date;
import java.util.List;

public interface IPayrollService {
    Payroll GeneratePayroll(int EmployeeId, Date startDate, Date endDate) throws PayrollGenerationException;
    Payroll GetPayrollById(int payrollId) throws PayrollGenerationException;
    List<Payroll> GetPayrollsForEmployee(int EmployeeId) throws PayrollGenerationException;
    List<Payroll> GetPayrollsForPeriod(Date startDate, Date endDate) throws PayrollGenerationException;


}