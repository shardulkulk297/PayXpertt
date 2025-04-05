package payxpert.dao;

import payxpert.model.Payroll;

import java.time.Year;
import java.util.Date;
import java.util.List;

public interface IPayrollService {
    Payroll GeneratePayroll(int EmployeeId, Date startDate, Date endDate);
    Payroll GetPayrollById(int payrollId);
    List<Payroll> GetPayrollsForEmployee(int EmployeeId);
    List<Payroll> getPayrollsForPeriod(Date startDate, Date endDate);

}