package payxpert.service;
import java.util.List;


import payxpert.model.FinancialRecord;
import payxpert.model.Payroll;
import payxpert.model.Tax;

public class ReportGenerator {

    public static void generatePayrollReport(List<payxpert.model.Payroll> payrolls) {
        System.out.println("=== Payroll Report ===");
        for (payxpert.model.Payroll payroll : payrolls) {
            System.out.printf("EmpID: %d | Period: %s to %s | Net Salary: ₹%.2f%n",
                    payroll.getEmployeeID(),
                    payroll.getPayPeriodStartDate(),
                    payroll.getPayPeriodEndDate(),
                    payroll.getNetSalary());
        }
    }

    public static void generateTaxSummary(List<Tax> taxes) {
        System.out.println("=== Tax Summary ===");
        for (Tax tax : taxes) {
            System.out.printf("EmpID: %d | Year: %d | Tax: ₹%.2f%n",
                    tax.getEmployeeID(),
                    tax.getTaxYear(),
                    tax.getTaxAmount());
        }
    }

    public static void generateFinancialStatement(List<FinancialRecord> records) {
        System.out.println("=== Financial Statement ===");
        for (FinancialRecord record : records) {
            System.out.printf("EmpID: %d | Date: %s | Type: %s | ₹%.2f | %s%n",
                    record.getEmployeeID(),
                    record.getRecordDate(),
                    record.getRecordDate(),
                    record.getAmount(),
                    record.getDescription());
        }
    }
}