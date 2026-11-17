/*
- Final Project done by: Shade Rahman
*/

import java.util.*;
import java.io.*;
import java.text.*;
import java.time.*;
import java.time.format.*;

public class FinalProject {
    private static final Scanner myScan = new Scanner(System.in);
    private static final ArrayList<Person> UniversityClass = new ArrayList<Person>(100);

    public static void main(String[] args) {

        //initializations
        boolean run = true;

        //welcome text + get user selection
        System.out.println("\t\tWelcome to the Personal Management System");
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
                    addStaff();
                    break;
                case 6:
                    printStaffInfo();
                    break;
                case 7:
                    deletePerson();
                    break;
                case 8:
                    run = exitAttempt();
                    if (!run){
                        System.out.println("\nGoodbye!");
                    }
                    break;
                default:
                    System.out.println("Try again!");
            }
        }

    }
    
    private static int Menu(){
        System.out.println("\nChoose one of the options:");
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
            return myScan.nextInt();
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
        System.out.println("Sorry no student with ID = " + id);
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

    private static void addStaff(){
        //clear any leftover newlines
        myScan.nextLine();

        //variable initializations
        String name = "xx";
        String id = "xx";
        String department = "xx";
        String status = "xx";

        //user inputs
        for(int attempts = 0; attempts < 3; attempts++){
            //input name
            System.out.println("Enter staff info:");
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

            //input status
            System.out.print("\tStatus: ");
            status = myScan.nextLine();
            if(!Staff.checkStatus(status)){
                System.out.println("Invalid status. Must be Part-time or Full-time. Ensure you include the dash");
                if (attempts < 2){
                    System.out.println("Try again!");
                }
                continue;
            }
            status = Staff.formatStatus(status);

            //add staff to UniversityClass list
            Staff s = new Staff(name, id, department, status);
            UniversityClass.add(s);

            //confirmation message and go back to menu
            System.out.println("Staff added!");
            return;

        }
    }

    private static void printStaffInfo(){
        myScan.nextLine(); //clear leftover newlines

        //find staff ID
        System.out.print("Enter the Staff ID: ");
        String id = myScan.nextLine().toLowerCase();

        //search list
        for(Person p : UniversityClass){
            //find a match
            if(p.getId().equalsIgnoreCase(id)){

                //check if id is linked to a staff
                if(p instanceof Staff){
                    Staff s = (Staff)p;
                    s.print();
                }
                //id exists, but does not link to a staff
                else{
                    System.out.println("This ID is not matched to a Staff");
                }
                return;
            }
        }
        //no id found
        System.out.println("Sorry no staff with ID = " + id);
    }

    private static void deletePerson(){
        myScan.nextLine(); //clear leftover newlines

        //find ID of person we're deleting
        System.out.print("Enter the ID of the person you'd like to delete: ");
        String id = myScan.nextLine().toLowerCase();

        //search list and delete if found
        for(int i = 0; i < UniversityClass.size(); i++){
            if(UniversityClass.get(i).getId().equalsIgnoreCase(id)){
                UniversityClass.remove(i);
                System.out.println("Person deleted");
                return;
            }
        }
        //no id found
        System.out.println("Sorry no person with ID = " + id);
    }

    private static boolean exitAttempt(){
        myScan.nextLine(); //clear leftover newlines
        int attempts = 0;

        //generate report?
        for(attempts = 0; attempts < 3; attempts++){
            System.out.print("Would you like to create the report? (Y/N): ");
            String report = myScan.nextLine();

            //skip this for loop, go to sorting method
            if(report.equalsIgnoreCase("y")){
                break;
            }
            //return false to end program
            else if(report.equalsIgnoreCase("n")){
                return false;
            }
            else{
                System.out.println("Invalid choice.");
            }
        }

        //did user hit 3-attempt limit? return true to repeat program
        if (attempts == 3){
            return true;
        }

        //sorting method
        int sort = 0;
        for (attempts = 0; attempts < 3; attempts++){
            System.out.print("Would you like to sort your students by GPA or name? (Enter 1 for GPA. Enter 2 for name): ");
            try{
                sort = myScan.nextInt();
                myScan.nextLine(); //clear leftover newlines

                //valid input? get out of for loop and go sort
                if(sort == 1 || sort == 2){
                    break;
                }
                else{
                    System.out.println("Invalid choice.");
                }
            }
            catch(InputMismatchException e){
                myScan.nextLine();
                System.out.println("Invalid choice.");
            }
        }

        //did user hit 3-attempt limit? return true to repeat program
        if (attempts == 3){
            return true;
        }

        //make a new arraylist for only students
        ArrayList<Student> StudentClass = new ArrayList<Student>();
        for(Person p : UniversityClass){
            if(p instanceof Student){
                Student s = (Student) p;
                StudentClass.add(s);
            }
        }
        //sort StudentClass by gpa
        if(sort == 1){
            StudentClass.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        }
        //sort StudentClass by name
        else if(sort == 2){
            StudentClass.sort(Comparator.comparing(Student::getFullName));
        }

        //make a new arraylist for only faculty
        ArrayList<Faculty> FacultyClass = new ArrayList<Faculty>();
        for(Person p : UniversityClass){
            if(p instanceof Faculty){
                Faculty f = (Faculty) p;
                FacultyClass.add(f);
            }
        }
        //sort FacultyClass by department
        FacultyClass.sort(Comparator.comparing(Faculty::getDepartment));

        //find date
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter formattedDateObj = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = dateObj.format(formattedDateObj);

        //create file
        try{
            FileWriter myWriter = new FileWriter("report.txt");
            myWriter.write("Report created on " + date + "\n");
            myWriter.write("*******************************\n");

            //print all faculty members
            myWriter.write("Faculty Members\n");
            myWriter.write("-------------------------\n");
            int pos = 0;
            for(Faculty f : FacultyClass){
                pos++;
                myWriter.write(pos + ". " + f.getFullName() + "\n");
                myWriter.write("\tID: " + f.getId() + "\n");
                myWriter.write("\t" + f.getRank() + ", " + f.getDepartment() + "\n\n");
            }

            //print all staff members
            myWriter.write("Staff Members\n");
            myWriter.write("-------------------------\n");
            pos = 0;
            for(Person p : UniversityClass){
                if(p instanceof Staff){
                    pos++;
                    Staff s = (Staff) p;
                    myWriter.write(pos + ". " + s.getFullName() + "\n");
                    myWriter.write("\tID: " + s.getId() + "\n");
                    myWriter.write("\t" + s.getDepartment() + ", " + s.getStatus() + "\n\n");
                }
            }

            //print all students
            myWriter.write("Students (Sorted by ");
            if(sort == 1){
                myWriter.write("GPA)\n");
            }
            else if(sort == 2){
                myWriter.write("name)\n");
            }
            myWriter.write("-------------------------\n");
            pos = 0;
            for(Student s : StudentClass){
                pos++;
                myWriter.write(pos + ". " + s.getFullName() + "\n");
                myWriter.write("\tID: " + s.getId() + "\n");
                myWriter.write("\tGPA: " + s.getGpa() + "\n");
                myWriter.write("\tCredit hours: " + s.getCreditHours() + "\n\n");
            }
            myWriter.close();
        }
        catch(IOException e){
            System.out.println("An error has occured.");
            e.printStackTrace();
        }

        //success message
        System.out.println("Report created and saved to report.txt!");
        return false;
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
        //check next 4 chars for digit
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
    private static final double creditHourCost = 236.45;
    private static final int administrativeFee = 52;
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

        double tuition = ((creditHourCost * creditHours) + administrativeFee) * discount;
        tuition = Math.round(tuition * 100.0) / 100.0;

        return tuition;
    }

    public void printTuitionInvoice(){
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        System.out.println("Tuition invoice for " + getFullName() + ":");
        System.out.println("--------------------------------------------------");
        System.out.println(getFullName() + "\t" + getId());
        System.out.println("Credit Hours: " + creditHours + " ($236.45/credit hour)");
        System.out.println("Fees: $" + administrativeFee);
        System.out.println("Total payment (after discount): " + df.format(calculateTuition()));
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

    public static boolean checkStatus(String status){
        if(status.equalsIgnoreCase("part-time") || status.equalsIgnoreCase("full-time")){
            return true;
        }
        return false;
    }

    public static String formatStatus(String status){
        if(status.equalsIgnoreCase("part-time")){
            return "Part Time";
        }
        return "Full Time";
    }
}