/*
- Final Project done by: Shade Rahman
*/

import java.util.*;

public class FinalProject {
    public static void main(String[] args) {

        //initializations
        ArrayList<Person> UniversityClass = new ArrayList<Person>(100);
        int selection = 0;

        //welcome text + get user selection
        System.out.println("\n\nWelcome to the Personal Management System");
        selection = Menu();

        //go to desired selection
        switch(selection){
            case 1:
                addFaculty();
                break;
            case 2:
                System.out.println("x");
                break;
            case 3:
                System.out.println("x");
                break;
            case 4:
                System.out.println("x");
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
                System.out.println("Try again!");
        }

    }
    
    private static int Menu(){
        int input = 0;
        Scanner myScan = new Scanner(System.in);
        System.out.println("Choose one of the options:");
        System.out.println("1- Add a faculty");
        System.out.println("2- Add a student");
        System.out.println("3- Print tuition invoice for a student");
        System.out.println("4- Print faculty information");
        System.out.println("5- Add a staff member");
        System.out.println("6- Print the information of a staff member");
        System.out.println("7- Delete a person");
        System.out.println("8- Exit Program\n");

        System.out.print("Enter your selection: ");
        return input = myScan.nextInt();
    }

    private static void addFaculty(){

        //variable initializations
        Scanner myScan = new Scanner(System.in);
        String name = "xx";
        String id = "xx";
        String department = "xx";
        String rank = "xx";

        //user inputs
        System.out.println("Enter faculty info:");
        System.out.print("\tName: ");
        name = myScan.nextLine();
        System.out.print("\tID: ");
        id = myScan.nextLine();
        System.out.print("\tDepartment: ");
        department = myScan.nextLine();
        System.out.print("\tRank: ");
        rank = myScan.nextLine();

    }

    private static int checkId(String id){
        int i = 0;
        //check length of id, return 0 if longer than expected
        if(id.length() > 6){
            return 0;
        }
        //check first two chars for letter
        for(i = 0; i < 2; i++){
            if(Character.isLetter(id.charAt(i))){
                continue;
            }
            else{
                return 0;
            }
        }
        //check first two chars for letter
        for(i = 2; i < 6; i++){
            if(Character.isDigit(id.charAt(i))){
                continue;
            }
            else{
                return 0;
            }
        }
        return 1;
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
        this.id = id;
    }

    //constructors
    public Person(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    //public abstract void print();
}

class Student extends Person{
    //variable initializations + constants
    private double gpa;
    private int creditHours;
    double creditHourCost = 236.45;
    double administrativeFee = 52.00;
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

}