package payxpert.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Employee {
    private int EmployeeId;
    private String FirstName;
    private String LastName;
    private Date DateOfBirth;
    private String Gender;
    private String Email;
    private int PhoneNumber;
    private String Address;
    private String Position;
    private Date JoiningDate;
    private Date TerminationDate;

    public Employee(){}
    public Employee(String firstName, String lastName, Date dateOfBirth,
                    String gender, String email, int phoneNumber, String address,
                    String position, Date joiningDate, Date terminationDate) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.DateOfBirth = dateOfBirth;
        this.Gender = gender;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.Address = address;
        this.Position = position;
        this.JoiningDate = joiningDate;
        this.TerminationDate = terminationDate;
    }

    //Getter Functions
    public int getEmployeeId(){
        return EmployeeId;
    }
    public String getFirstName(){
        return FirstName;
    }
    public String getLastName(){
        return LastName;
    }
    public Date getDateOfBirth(){
        return DateOfBirth;
    }
    public String getGender(){
        return Gender;
    }
    public String getEmail(){
        return Email;
    }
    public int getPhoneNumber(){
        return PhoneNumber;
    }

    public String getAddress(){
        return Address;
    }
    public String getPosition(){
        return Position;
    }
    public Date getJoiningDate(){
        return JoiningDate;
    }
    public Date getTerminationDate(){
        return TerminationDate;
    }
    //Setter Functions

    public void setEmployeeId(int EmployeeId){
        this.EmployeeId = EmployeeId;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName){
        this.LastName = LastName;
    }

    public void setDateOfBirth(Date DateOfBirth){
        this.DateOfBirth = DateOfBirth;
    }

    public void setGender(String Gender){
        this.Gender = Gender;
    }

    public void setEmail(String Email){
        this.Email = Email;
    }

    public void setPhoneNumber(int PhoneNumber){
        this.PhoneNumber = PhoneNumber;
    }

    public void setAddress(String Address){
        this.Address = Address;
    }

    public void setPosition(String Position){
        this.Position = Position;
    }

    public void setJoiningDate(Date joiningDate) {
        this.JoiningDate = joiningDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.TerminationDate = terminationDate;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "EmployeeId = " + EmployeeId +
                ", FirstName = '" + FirstName + '\'' +
                ", LastName = '" + LastName + '\'' +
                ", DateOfBirth = " + DateOfBirth +
                ", Gender = '" + Gender + '\'' +
                ", Email = '" + Email + '\'' +
                ", PhoneNumber = " + PhoneNumber +
                ", Address = '" + Address + '\'' +
                ", Position = '" + Position + '\'' +
                ", JoiningDate = " + JoiningDate +
                ", TerminationDate = " + TerminationDate +
                '}';
    }

    public int CalculateAge(){
        if(DateOfBirth == null){
            return 0;
        }

        LocalDate dob = DateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate current = LocalDate.now();

        int age = Period.between(dob, current).getYears();

        return age;


    }













}
