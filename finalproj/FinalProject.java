/*
- Final Project done by: Shade Rahman
*/

import java.util.*;

public class FinalProject {
    private static final Scanner myScan = new Scanner(System.in);
    private static final ArrayList<Person> UniversityClass = new ArrayList<Person>(100);

    public static void main(String[] args) {

        //initializations
        boolean run = true;

        //welcome text + get user selection
        System.out.println("\n\nWelcome to the Personal Management System");
        while (run == true){
            int selection = Menu();
            //go to desired selection
            switch(selection){
                case 1:
                    addFaculty();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    printTuitionInvoice();
                    break;
                case 4:
                    printFacultyInfo();
                    break;
                case 5:
                    System.out.println("x");
                    break;
                case 6:
                    System.out.println("x");
                    break;
                case 7:
                    System.out.println("x");
                    break;
                case 8:
                    System.out.println("x");
                    break;
                default:
                    System.out.println("\nTry again!\n");
            }
        }

        System.out.println("Goodbye!");

    }
    
    private static int Menu(){
        int input = 0;
        System.out.println("Choose one of the options:");
        System.out.println("1- Add a faculty");
        System.out.println("2- Add a student");
        System.out.println("3- Print tuition invoice for a student");
        System.out.println("4- Print faculty information by id");
        System.out.println("5- Add a staff member");
        System.out.println("6- Print the information of a staff member");
        System.out.println("7- Delete a person");
        System.out.println("8- Exit Program\n");

        System.out.print("Enter your selection: ");
        
        //makes sure user enters a number
        try{
            return input = myScan.nextInt();
        }
        catch(InputMismatchException e){
            myScan.nextLine();
            return -1;
        }
    }

    private static boolean checkDuplicateId(String id){
        for (Person p : UniversityClass){
            if(p.getId().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }

    private static void addFaculty(){
        //clear any leftover newlines
        myScan.nextLine();

        //variable initializations
        String name = "xx";
        String id = "xx";
        String department = "xx";
        String rank = "xx";

        //user inputs
        for(int attempts = 0; attempts < 3; attempts++){
            //input name
            System.out.println("Enter faculty info:");
            System.out.print("\tName: ");
            name = myScan.nextLine();

            //input id
            System.out.print("\tID: ");
            id = myScan.nextLine().toLowerCase();
            if(!Person.checkId(id)){
                System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                if (attempts < 2){
                    System.out.println("Try again!");
                }
                continue;
            }
            if(checkDuplicateId(id)){
                System.out.println("This ID already exists!");
                if (attempts < 2){
                    System.out.println("Try again!");
                }
                continue;
            }

            //input department
            System.out.print("\tDepartment: ");
            department = myScan.nextLine();
            if(!Employee.checkDepartment(department)){
                System.out.println("Invalid department. Must be Mathematics, Engineering, or English");
                if (attempts < 2){
                    System.out.println("Try again!");
                }
                continue;
            }
            department = Employee.formatDepartment(department);

            //input rank
            System.out.print("\tRank: ");
            rank = myScan.nextLine();
            if(!Faculty.checkRank(rank)){
                System.out.println("Invalid rank. Must be Professor or Adjunct");
                if (attempts < 2){
                    System.out.println("Try again!");
                }
                continue;
            }
            rank = Faculty.formatRank(rank);

            //add faculty to UniversityClass list
            Faculty f = new Faculty(name, id, department, rank);
            UniversityClass.add(f);

            //confirmation message and go back to menu
            System.out.println("Faculty added!");
            return;

        }
    }

    private static void addStudent(){
        myScan.nextLine(); //clear leftover newlines

        //variable initializations
        String name = "xx";
        String id = "xx";
        double gpa = 0.00;
        int creditHours = -1;

        //user inputs
        for(int attempts = 0; attempts < 3; attempts++){
            //input name
            System.out.println("Enter student info:");
            System.out.print("\tName: ");
            name = myScan.nextLine();

            //input id
            System.out.print("\tID: ");
            id = myScan.nextLine().toLowerCase();
            if(!Person.checkId(id)){
                System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                if (attempts < 2){
                    System.out.println("Try again!");
                }
                continue;
            }
            if(checkDuplicateId(id)){
                System.out.println("This ID already exists!");
                if (attempts < 2){
                    System.out.println("Try again!");
                }
                continue;
            }

            try{
                //input gpa
                System.out.print("\tGPA: ");
                gpa = myScan.nextDouble();
                myScan.nextLine(); //clear leftover newlines
                if(gpa < 0 || gpa > 4.00){
                    System.out.println("Invalid GPA. Please enter a number between 0 and 4.0");
                    if (attempts < 2){
                        System.out.println("Try again!");
                    }
                    continue;
                }

                //input credit hours
                System.out.print("\tCredit Hours: ");
                creditHours = myScan.nextInt();
                myScan.nextLine(); //clear leftover newlines
                if(creditHours < 0){
                    System.out.println("Invalid credit hours. Please enter a positive number");
                    if (attempts < 2){
                        System.out.println("Try again!");
                    }
                    continue;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input");
                myScan.nextLine(); //clear leftover newlines
                if (attempts < 2){
                    System.out.println("Try again!");
                }
                continue;
            }

            //add student to UniversityClass list
            Student s = new Student(name, id, gpa, creditHours);
            UniversityClass.add(s);

            //confirmation message and go back to menu
            System.out.println("Student added!");
            return;
        }
    }

    private static void printTuitionInvoice(){
        myScan.nextLine(); //clear leftover newlines
        
        //find student's ID
        System.out.print("Enter the student's ID: ");
        String id = myScan.nextLine().toLowerCase();

        //search list
        for(Person p : UniversityClass){
            //find a match
            if(p.getId().equalsIgnoreCase(id)){

                //check if id is linked to a student
                if(p instanceof Student){
                    Student s = (Student)p;
                    s.printTuitionInvoice();
                }
                //id exists, but does not link to a student
                else{
                    System.out.println("This ID is not matched to a Student");
                }
                return;
            }
        }
        //no id found
        System.out.println("Student not found!");
    }

    private static void printFacultyInfo(){
        myScan.nextLine(); //clear leftover newlines

        //find faculty's ID
        System.out.print("Enter the Faculty ID: ");
        String id = myScan.nextLine().toLowerCase();

        //search list
        for(Person p : UniversityClass){
            //find a match
            if(p.getId().equalsIgnoreCase(id)){

                //check if id is linked to a faculty
                if(p instanceof Faculty){
                    Faculty f = (Faculty)p;
                    f.print();
                }
                //id exists, but does not link to a faculty
                else{
                    System.out.println("This ID is not matched to a Faculty");
                }
                return;
            }
        }
        //no id found
        System.out.println("Sorry no faculty with ID = " + id);
    }
}

abstract class Person{
    //variable initializations
    private String fullName;
    private String id;

    //setters and getters
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id.toLowerCase();
    }

    //constructors
    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id.toLowerCase();
    }

    //functions
    public abstract void print();
    public static boolean checkId(String id){
        int i = 0;
        //check length of id, return 0 if longer than expected
        if(id == null || id.length() != 6){
            return false;
        }
        //check first two chars for letter
        for(i = 0; i < 2; i++){
            if(Character.isLetter(id.charAt(i))){
                continue;
            }
            else{
                return false;
            }
        }
        //check first two chars for letter
        for(i = 2; i < 6; i++){
            if(Character.isDigit(id.charAt(i))){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }
}

class Student extends Person{
    //variable initializations + constants
    private double gpa;
    private int creditHours;
    double creditHourCost = 236.45;
    int administrativeFee = 52;
    double discount = 1.00;

    //setters and getters
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public int getCreditHours() {
        return creditHours;
    }
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    //constructors
    public Student(String fullName, String id, double gpa, int creditHours) {
        super(fullName, id);
        this.gpa = gpa;
        this.creditHours = creditHours;
    }
    public Student(String fullName, String id) {
        super(fullName, id);
    }
    
    //functions
    @Override
    public void print(){
        System.out.println(getFullName());
        System.out.println("ID: " + getId());
        System.out.println("GPA: " + getGpa());
        System.out.println("Credit Hours: " + getCreditHours());
    }

    public double calculateTuition() {
        //check to see if student is eligible for discount
        if (gpa >= 3.85){
            discount = 0.75; //25% discount is applied
        }
        else{
            discount = 1.00; //no discount applied
        }

        return ((creditHourCost * creditHours) + administrativeFee) * discount;
    }

    public void printTuitionInvoice(){
        System.out.println("Tuition invoice for " + getFullName() + ":");
        System.out.println("--------------------------------------------------");
        System.out.println(getFullName() + "\t" + getId());
        System.out.println("Credit Hours: " + creditHours + " ($236.45/credit hour)");
        System.out.println("Fees: $" + administrativeFee);
        System.out.println("Total payment (after discount): " + calculateTuition());
        System.out.println("--------------------------------------------------");
    }

}

abstract class Employee extends Person{
    //variable initializations
    private String department;

    //setters and getters
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    //constructors
    public Employee(String fullName, String id, String department) {
        super(fullName, id);
        this.department = department;
    }

    public Employee(String fullName, String id) {
        super(fullName, id);
    }

    //functions
    public static boolean checkDepartment(String department){
        if(department.equalsIgnoreCase("Mathematics") || department.equalsIgnoreCase("Engineering") || department.equalsIgnoreCase("English")){
            return true;
        }
        return false;
    }

    public static String formatDepartment(String department){
        if(department.equalsIgnoreCase("mathematics")){
            return "Mathematics";
        }
        if(department.equalsIgnoreCase("engineering")){
            return "Engineering";
        }
        return "English";
    }
    
}

class Faculty extends Employee{
    //variable initializations
    private String rank;

    //setters and getters
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    public Faculty(String fullName, String id, String department, String rank) {
        super(fullName, id, department);
        this.rank = rank;
    }

    public Faculty(String fullName, String id, String department) {
        super(fullName, id, department);
    }

    //functions
    @Override
    public void print(){
        System.out.println(getFullName());
        System.out.println("ID: " + getId());
        System.out.println(getRank() + ", " + getDepartment());
    }

    public static boolean checkRank(String rank){
        if(rank.equalsIgnoreCase("Professor") || rank.equalsIgnoreCase("Adjunct")){
            return true;
        }
        return false;
    }

    public static String formatRank(String rank){
        if(rank.equalsIgnoreCase("professor")){
            return "Professor";
        }
        return "Adjunct";
    }
    
}

class Staff extends Employee{
    //variable initializations
    private String status;

    //setters and getters
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Staff(String fullName, String id, String department, String status) {
        super(fullName, id, department);
        this.status = status;
    }

    public Staff(String fullName, String id, String department) {
        super(fullName, id, department);
    }

    //functions
    @Override
    public void print(){
        System.out.println(getFullName());
        System.out.println("ID: " + getId());
        System.out.println(getDepartment() + ", " + getStatus());
    }
}