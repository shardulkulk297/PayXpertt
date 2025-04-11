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
    public Payroll GeneratePayroll(int employeeId, Date startDate, Date endDate, double basicSalary, long OverTimeDays) throws PayrollGenerationException {
        //handling business logic of Calculating payroll
        //taken input of basic salary from the user as it is not given in the caseStudy
        LocalDate localStart = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEnd = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long workingDays = ChronoUnit.DAYS.between(localStart, localEnd) + 1;

        if(OverTimeDays > workingDays){
            throw new PayrollGenerationException("The working days should be within the Start date and End Date");
        }

        double OvertimePay = OverTimeDays * 200; //taken input for how many days employee has done overtime
        double deductions = basicSalary * 0.10;
        double netSalary = basicSalary + OvertimePay - deductions;

        return payrolldao.GeneratePayroll(employeeId, localStart, localEnd, basicSalary, OvertimePay, deductions, netSalary);

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
