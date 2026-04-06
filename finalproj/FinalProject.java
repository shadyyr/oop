/*
- Final Project done by: Shade Rahman
*/

public class FinalProject {
    public static void main(String[] args) {
        
    }
}

class Student extends Person{
    //variable initializations
    private double gpa;
    private int creditHours;

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

    //public abstract void print();
}