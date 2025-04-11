package payxpert.main;

import payxpert.dao.EmployeeService;
import payxpert.dao.FinancialRecordService;
import payxpert.dao.PayrollService;
import payxpert.dao.TaxService;
import payxpert.exception.*;
import payxpert.model.Employee;
import payxpert.model.FinancialRecord;
import payxpert.model.Payroll;
import payxpert.model.Tax;

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
            sc.nextLine();
            switch(option){
                case 1->{

                    try {
                        System.out.print("Enter First Name: ");
                        String firstName = sc.nextLine();

                        System.out.print("Enter Last Name: ");
                        String lastName = sc.nextLine();


                        System.out.println("Enter Date of Birth:");
                        System.out.print("Year: ");
                        int birthYear = sc.nextInt();
                        System.out.print("Month: ");
                        int birthMonth = sc.nextInt();
                        System.out.print("Day: ");
                        int birthDay = sc.nextInt();
                        Date dob = ConvertDate(birthYear, birthMonth, birthDay);
                        sc.nextLine();

                        System.out.print("Enter Gender (Male/Female): ");
                        String gender = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Phone Number: ");
                        String phone = sc.next();
                        sc.nextLine();

                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();

                        System.out.print("Enter Position: ");
                        String position = sc.nextLine();

                        System.out.println("Enter Joining Date:");
                        System.out.print("Year: ");
                        int joinYear = sc.nextInt();
                        System.out.print("Month: ");
                        int joinMonth = sc.nextInt();
                        System.out.print("Day: ");
                        int joinDay = sc.nextInt();
                        Date joiningDate = ConvertDate(joinYear, joinMonth, joinDay);

                        sc.nextLine();
                        System.out.print("Is Termination Date available? (yes/no): ");
                        String hasTermination = sc.nextLine();
                        Date terminationDate = null;
                        if (hasTermination.equalsIgnoreCase("yes")) {
                            System.out.println("Enter Termination Date:");
                            System.out.print("Year: ");
                            int termYear = sc.nextInt();
                            System.out.print("Month: ");
                            int termMonth = sc.nextInt();
                            System.out.print("Day: ");
                            int termDay = sc.nextInt();
                            terminationDate = ConvertDate(termYear, termMonth, termDay);
                        }
                        Employee employeeData = new Employee(
                                firstName,
                                lastName,
                                dob,
                                gender,
                                email,
                                phone,
                                address,
                                position,
                                joiningDate,
                                terminationDate
                        );
                        EmployeeService emp = new EmployeeService();
                        emp.AddEmployee(employeeData);

                    } catch (EmployeeNotFoundException | InvalidInputException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                }
                case 2->{

                    try {
                        System.out.print("Enter Employee ID to update: ");
                        int empId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter First Name: ");
                        String firstName = sc.nextLine();

                        System.out.print("Enter Last Name: ");
                        String lastName = sc.nextLine();

                        System.out.println("Enter Date of Birth:");
                        System.out.print("Year: ");
                        int birthYear = sc.nextInt();
                        System.out.print("Month: ");
                        int birthMonth = sc.nextInt();
                        System.out.print("Day: ");
                        int birthDay = sc.nextInt();
                        Date dob = ConvertDate(birthYear, birthMonth, birthDay);
                        sc.nextLine();

                        System.out.print("Enter Gender (Male/Female): ");
                        String gender = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Phone Number (10 digits): ");
                        String phoneStr = sc.next();

                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();

                        System.out.print("Enter Position: ");
                        String position = sc.nextLine();

                        System.out.println("Enter Joining Date:");
                        System.out.print("Year: ");
                        int joinYear = sc.nextInt();
                        System.out.print("Month: ");
                        int joinMonth = sc.nextInt();
                        System.out.print("Day: ");
                        int joinDay = sc.nextInt();
                        Date joiningDate = ConvertDate(joinYear, joinMonth, joinDay);
                        sc.nextLine();

                        System.out.print("Does the employee have a termination date? (yes/no): ");
                        String hasTermination = sc.nextLine();
                        Date terminationDate = null;
                        if (hasTermination.equalsIgnoreCase("yes")) {
                            System.out.println("Enter Termination Date:");
                            System.out.print("Year: ");
                            int termYear = sc.nextInt();
                            System.out.print("Month: ");
                            int termMonth = sc.nextInt();
                            System.out.print("Day: ");
                            int termDay = sc.nextInt();
                            terminationDate = ConvertDate(termYear, termMonth, termDay);
                        }

                        Employee employeeData = new Employee(
                                firstName,
                                lastName,
                                dob,
                                gender,
                                email,
                                phoneStr,
                                address,
                                position,
                                joiningDate,
                                terminationDate
                        );
                        employeeData.setEmployeeId(empId);

                        EmployeeService empService = new EmployeeService();
                        empService.UpdateEmployee(employeeData);

                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                        e.printStackTrace();
                    }

                    sc.close();
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
                    System.out.println("Enter Basic Salary of Employee: ");
                    int basicSalary = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter For how many days employee has done overTime (Should be within the start date and End Date):");
                    long OverTimeDays = sc.nextLong();
                    PayrollService pay = new PayrollService();
                    try{
                        Payroll payroll = null;
                        payroll = pay.GeneratePayroll(empId, startDate, endDate, basicSalary, OverTimeDays);
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
                        System.out.println(payroll);
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
                        for(Payroll p: payrolls){
                            System.out.println(p);
                        }
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

                        for(Payroll p: payrolls){
                            System.out.println(p);
                        }
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
                       double taxI = tax.CalculateTax(empId, year);

                        System.out.println("Taxable Income: " + taxI);
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
                        Tax taxx = tax.GetTaxById(taxId);
                        System.out.println(taxx);

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
                      List<Tax> taxes = tax.GetTaxesForEmployee(empId);
                      for(Tax t: taxes){
                          System.out.println(t);
                      }

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
                       List<Tax> taxes = tax.GetTaxesForYear(year);

                       for(Tax t: taxes){
                           System.out.println(t);
                       }

                    }
                    catch(TaxCalculationException t){
                        System.out.println(t.getMessage());
                    }
                }
                case 14 ->{
                    System.out.println("Add new financial Record");
                    System.out.println("Enter employeeId: ");
                    int empId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Description: ");
                    String desc = sc.nextLine();
                    System.out.println("Enter Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter Record Type: ");
                    String recordType = sc.nextLine();

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
                        System.out.println(fin.GetFinancialRecordById(recordID));
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
                        List<FinancialRecord> records = fin.GetFinancialRecordsForEmployees(empId);
                        for(FinancialRecord f: records)
                        {
                            System.out.println(f);
                        }
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
                      List<FinancialRecord> records =  fin.GetFinancialRecordsForDate(recordDate);
                      for(FinancialRecord f: records)
                      {
                          System.out.println(f);
                      }

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
