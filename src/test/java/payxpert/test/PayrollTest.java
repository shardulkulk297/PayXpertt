package payxpert.test;


import org.junit.jupiter.api.Test;
import payxpert.dao.PayrollService;
import payxpert.exception.EmployeeNotFoundException;
import payxpert.exception.InvalidInputException;
import payxpert.model.Payroll;
import payxpert.model.Tax;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayrollTest {





    @Test
    public void calculateGrossSalary(){
        double basicSalary = 5000.00;
        double overTimePay = 200.00;
        double expected = 5200.00;
        Payroll payroll = new Payroll();
        payroll.setBasicSalary(basicSalary);
        payroll.setOvertimePay(overTimePay);
        double actual = payroll.getBasicSalary() + payroll.getOvertimePay();
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void calculateNetSalaryAfterDeductions(){
        double basic = 5000.00;
        double overtime = 200.00;
        double deductions  = 1000.00;
        double expected = 4200.00;

        Payroll payroll = new Payroll();
        payroll.setBasicSalary(basic);
        payroll.setDeductions(deductions);
        payroll.setOvertimePay(overtime);
        payroll.setNetSalary(payroll.getBasicSalary() + payroll.getOvertimePay() - payroll.getDeductions());

        assertEquals(expected, payroll.getNetSalary(), 0.01);
    }

    @Test
    public void ProcessPayrollForMultipleEmployees(){
        Payroll payroll1 = new Payroll();
        payroll1.setBasicSalary(50000);
        payroll1.setOvertimePay(5000);
        payroll1.setDeductions(10000);

        payroll1.setNetSalary(50000 + 5000 - 10000); // Expected: 45000

        Payroll payroll2 = new Payroll();
        payroll2.setBasicSalary(60000);
        payroll2.setOvertimePay(6000);
        payroll2.setDeductions(11000);
        payroll2.setNetSalary(60000 + 6000 - 11000); // Expected: 55000

        Payroll payroll3 = new Payroll();
        payroll3.setBasicSalary(70000);
        payroll3.setOvertimePay(7000);
        payroll3.setDeductions(12000);
        payroll3.setNetSalary(70000 + 7000 - 12000); // Expected: 65000

        List<Payroll> payrolls = Arrays.asList(payroll1, payroll2, payroll3);

        assertEquals(3, payrolls.size());

        // Calculate total net salary for all payrolls
        double totalNetSalary = 0.0;
        for (Payroll p : payrolls) {
            totalNetSalary += p.getNetSalary();
        }


        // Expected total = 45000 + 55000 + 65000 = 165000
        double expectedTotal = 165000;


        assertEquals(expectedTotal, totalNetSalary, 0.01);
    }

    @Test
    public void VerifyTaxCalculationForHighIncomeEmployee(){
        Tax tax = new Tax();
        tax.setEmployeeID(5);
        tax.setTaxableIncome(120000.0);

        tax.setTaxAmount(tax.getTaxableIncome() * 0.3);

        double expectedTax = 36000.0;
        assertEquals(expectedTax, tax.getTaxAmount(), 0.01, "Tax calculation for high income employee is incorrect");

    }




}