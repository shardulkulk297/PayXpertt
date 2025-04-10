package payxpert.main;

import payxpert.dao.EmployeeService;
import payxpert.dao.FinancialRecordService;
import payxpert.dao.PayrollService;
import payxpert.dao.TaxService;
import payxpert.exception.*;
import payxpert.model.Employee;
import payxpert.model.Payroll;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    private static Date ConvertDate(int year, int month, int day){
        LocalDate inputDate = LocalDate.of(year, month, day);
        Date date = Date.from(inputDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }

    public static void main(String[] args) throws InvalidInputException {
        while(true){
            System.out.println("--");
            System.out.println("WELCOME TO PayXpert");
            System.out.println("--");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. View Specific Employee");
            System.out.println("6. Generate Payroll For Employee");
            System.out.println("7. Get specific Payroll");
            System.out.println("8. Get Payrolls for Specific Employee");
            System.out.println("9. Get Payrolls for Period");
            System.out.println("10. Calculate Tax for Employee");
            System.out.println("11. Get Tax By TaxID");
            System.out.println("12. Get Tax for Specific Employee");
            System.out.println("13. Get Tax by Tax Year");
            System.out.println("14. Add new Financial Record");
            System.out.println("15. Get Financial Record");
            System.out.println("16. Get Financial Record for Specific Employee");
            System.out.println("17. Get Financial Record for Specific Date");
            System.out.println("0. Exit");
            System.out.println("Enter your option:");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            switch(option){
                case 1->{

                    Date dob = ConvertDate(2003, 07, 29);
                    Date JoiningDate = ConvertDate(2025, 06, 15);

                    Employee employeeData = new Employee(
                            "Shardul",
                            "Kulkarni",
                            dob,
                            "Male",
                            "shardulkulk297@gmail.com",
                            986786756,
                            "Kurundwad",
                            "FullStack",
                            JoiningDate,
                            null
                    );




                    EmployeeService emp = new EmployeeService();
                    try{
                        emp.AddEmployee(employeeData);
                    }
                    catch(EmployeeNotFoundException e){
                          System.out.println(e.getMessage());
                    }


                }
                case 2->{

                    System.out.println("Enter employeeId of employee you want to update:");
                    int empId = sc.nextInt();

                    Date dob = ConvertDate(2003, 07, 29);
                    Date JoiningDate = ConvertDate(2025, 05, 15);

                    Employee employeeData = new Employee(
                            "Shardul",
                            "Kulkarni",
                            dob,
                            "Male",
                            "shardulkulk297@gmail.com",
                            988767786, //updated phone number
                            "Kurundwad",
                            "FullStack Developer", //updating position
                            JoiningDate, //updating date
                            null
                    );
                    employeeData.setEmployeeId(empId);

                    EmployeeService emp = new EmployeeService();
                    try{
                        emp.UpdateEmployee(employeeData);

                    }
                    catch(EmployeeNotFoundException e){
                          System.out.println(e.getMessage());
                    }

                }

                case 3->{
                    System.out.println("Enter employeeId of employee you want to remove: ");
                    int empId = sc.nextInt();
                    EmployeeService emp = new EmployeeService();
                    try{
                        emp.RemoveEmployee(empId);

                    }
                    catch(EmployeeNotFoundException e){
                          System.out.println(e.getMessage());
                    }

                }

                case 4->{
                    System.out.println("All Employees:");
                    EmployeeService emp = new EmployeeService();

                    try{
                        Employee employee = new Employee();
                        List<Employee> employees =  emp.GetAllEmployees();
                        for(Employee e: employees)
                        {
                            System.out.println(e);
                        }


                    }
                    catch(EmployeeNotFoundException e){
                          System.out.println(e.getMessage());
                    }
                }

                case 5->{
                    Employee employee = null;
                    System.out.println("Enter id of Employee You want to see: ");
                    int empId = sc.nextInt();
                    EmployeeService emp = new EmployeeService();
                    try{
                        employee = emp.GetEmployeeById(empId);
                        System.out.println(employee);
                    }
                    catch(EmployeeNotFoundException e)
                    {

                        System.out.println(e.getMessage());
                    }

                }

                case 6->{
                    System.out.println("Generating Payroll");
                    System.out.println("Enter EmployeeId: ");
                    int empId = sc.nextInt();
                    System.out.println("Enter Start Date Year: ");
                    int year = sc.nextInt();
                    System.out.println("Enter start Date Month: ");
                    int month = sc.nextInt();
                    System.out.println("Enter start Date Day: ");
                    int day = sc.nextInt();
                    System.out.println("Enter end Date Year: ");
                    int yearr = sc.nextInt();
                    System.out.println("Enter end Date Month: ");
                    int monthh = sc.nextInt();
                    System.out.println("Enter end Date Day: ");
                    int dayy = sc.nextInt();
                    Date startDate = ConvertDate(year, month, day);
                    Date endDate = ConvertDate(yearr, monthh, dayy);

                    PayrollService pay = new PayrollService();
                    try{
                        Payroll payroll = null;
                        payroll = pay.GeneratePayroll(empId, startDate, endDate);
                        System.out.println(payroll);
                    }
                    catch(PayrollGenerationException e)
                    {
                          System.out.println(e.getMessage());
                    }

                }
                case 7->{
                    System.out.println("Enter PayRoll ID: ");
                    int payrollId = sc.nextInt();

                    PayrollService pay = new PayrollService();
                    try{
                        Payroll payroll = null;
                        payroll = pay.GetPayrollById(payrollId);
                    }
                    catch(PayrollGenerationException e){
                          System.out.println(e.getMessage());

                    }

                }
                case 8->{
                    System.out.println("Enter id of employee for payroll: ");
                    int empId = sc.nextInt();

                    PayrollService pay = new PayrollService();
                    try{
                        List<Payroll> payrolls = new ArrayList<>();
                        payrolls = pay.GetPayrollsForEmployee(empId);
                        System.out.println(payrolls);
                    }
                    catch(PayrollGenerationException e){
                          System.out.println(e.getMessage());

                    }

                }

                case 9->{
                    System.out.println("Enter Start Date Year: ");
                    int year = sc.nextInt();
                    System.out.println("Enter start Date Month: ");
                    int month = sc.nextInt();
                    System.out.println("Enter start Date Day: ");
                    int day = sc.nextInt();
                    System.out.println("Enter end Date Year: ");
                    int yearr = sc.nextInt();
                    System.out.println("Enter end Date Month: ");
                    int monthh = sc.nextInt();
                    System.out.println("Enter end Date Day: ");
                    int dayy = sc.nextInt();
                    Date startDate = ConvertDate(year, month, day);
                    Date endDate = ConvertDate(yearr, monthh, dayy);

                    PayrollService pay = new PayrollService();
                    try{
                        List<Payroll> payrolls = new ArrayList<>();
                        payrolls = pay.GetPayrollsForPeriod(startDate, endDate);
                        System.out.println(payrolls);
                    }
                    catch(PayrollGenerationException e){
                          System.out.println(e.getMessage());

                    }

                }

                case 10->{
                    System.out.println("Enter id of employee for the tax: ");
                    int empId = sc.nextInt();
                    System.out.println("Enter Year: ");
                    int year = sc.nextInt();
                    TaxService tax = new TaxService();
                    try{
                        tax.CalculateTax(empId, year);
                    }
                    catch(TaxCalculationException t){
                        System.out.println(t.getMessage());
                    }
                }
                case 11->{
                    System.out.println("Enter taxId: ");
                    int taxId = sc.nextInt();
                    TaxService tax = new TaxService();
                    try{
                        tax.GetTaxById(taxId);

                    }
                    catch(TaxCalculationException t){
                        System.out.println(t.getMessage());
                    }

                }
                case 12-> {
                    System.out.println("Enter employeeID: ");
                    int empId = sc.nextInt();
                    TaxService tax = new TaxService();
                    try{
                        tax.GetTaxesForEmployee(empId);

                    }
                    catch(TaxCalculationException t){
                        System.out.println(t.getMessage());
                    }
                }
                case 13 ->{
                    System.out.println("Enter year");
                    int year = sc.nextInt();
                    TaxService tax = new TaxService();
                    try{
                        tax.GetTaxesForYear(year);

                    }
                    catch(TaxCalculationException t){
                        System.out.println(t.getMessage());
                    }
                }
                case 14 ->{
                    System.out.println("Add new financial Record");
                    System.out.println("Enter employeeId: ");
                    int empId = sc.nextInt();
                    System.out.println("Enter Descritpion: ");
                    String desc = sc.next();
                    System.out.println("Enter Amount: ");
                    Double amount = sc.nextDouble();
                    System.out.println("Enter Record Type: ");
                    String recordType = sc.next();

                    FinancialRecordService fin = new FinancialRecordService();
                    try{
                        fin.AddFinancialRecord(empId, desc, amount, recordType);
                    }
                    catch(FinancialRecordException f)
                    {
                        System.out.println(f.getMessage());
                    }

                }
                case 15 ->{
                    System.out.println("Getting RecordID: ");
                    int recordID = sc.nextInt();

                    FinancialRecordService fin = new FinancialRecordService();
                    try{
                        fin.GetFinancialRecordById(recordID);
                    }
                    catch(FinancialRecordException f)
                    {
                        System.out.println(f.getMessage());
                    }
                }
                case 16 ->{
                    System.out.println("Enter EmployeeId: ");
                    int empId = sc.nextInt();

                    FinancialRecordService fin = new FinancialRecordService();
                    try{
                        fin.GetFinancialRecordsForEmployees(empId);
                    }
                    catch(FinancialRecordException f)
                    {
                        System.out.println(f.getMessage());
                    }

                }
                case 17 ->{
                    System.out.println("Enter Start Date Year: ");
                    int year = sc.nextInt();
                    System.out.println("Enter start Date Month: ");
                    int month = sc.nextInt();
                    System.out.println("Enter start Date Day: ");
                    int day = sc.nextInt();

                    Date recordDate = ConvertDate(year, month, day);

                    FinancialRecordService fin = new FinancialRecordService();
                    try{
                        fin.GetFinancialRecordsForDate(recordDate);

                    }
                    catch(FinancialRecordException f)
                    {
                        System.out.println(f.getMessage());
                    }
                }
                case 0->{
                    System.out.println("--");
                    System.out.println("Thanks for Visiting!! GoodByeeee😊");
                    System.out.println("--");
                    sc.close();
                    System.exit(0);
                }




                default -> {
                    throw new InvalidInputException("Invalid Input");
                }


            }

        }


        //ith paryant

    }
}
