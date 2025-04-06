package payxpert.dao;

import payxpert.model.Payroll;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import payxpert.exception.PayrollGenerationException;

public class PayrollService implements IPayrollService {
    PayrollDAO payrolldao = new PayrollDAO();
    @Override
    public Payroll GeneratePayroll(int employeeId, Date startDate, Date endDate) throws PayrollGenerationException {
        //handling business logic of Calculating payroll
        double basicSalary = 50000.00; //manually entering value as it is not given in case study
        LocalDate localStart = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEnd = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long workingDays = ChronoUnit.DAYS.between(localStart, localEnd) + 1;
        double OvertimePay = workingDays * 200;
        double deductions = basicSalary * 0.10;
        double netSalary = basicSalary + OvertimePay - deductions;

        return payrolldao.GeneratePayroll(employeeId, localStart, localEnd, basicSalary, deductions, netSalary);

    }
    @Override
    public Payroll GetPayrollById(int payrollId) throws PayrollGenerationException {

        return payrolldao.GetPayrollById(payrollId);

    }
    @Override
    public List<Payroll> GetPayrollsForEmployee(int employeeId) throws PayrollGenerationException {

        return payrolldao.GetPayrollsForEmployee(employeeId);
    }
    @Override
    public List<Payroll> GetPayrollsForPeriod(Date startDate, Date endDate) throws PayrollGenerationException{
        return payrolldao.GetPayrollsForPeriod(startDate, endDate);
    }

}
