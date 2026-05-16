//Shade Rahman
import java.util.*;

public class HW {

	public static void main(String[] args) {

		String fullName = "Erika T. Jones";
		String employeeNumber = "ej789";
		double payRate = 100.0, hoursWorked = 1.0;
		// TA will change the payrate and the hours worked to test your code

		Employee e;
		e = new Employee(fullName, employeeNumber, payRate, hoursWorked);

		System.out.println(e); // To Test your toString method

		e.printCheck(); // This prints the check of Erika T. Jones

		
		Company company = new Company();
		
		company.hire ( new Employee ("Saeed Happy", "sh895" , 2 , 200) );
		company.hire (e);
		
		Company.printCompanyInfo();
		
		company.hire( new Employee("Enrico Torres" , "et897" , 3 , 150) );
		
		//You may add as many employees to company as you want.
		//The TAs will add their own employees 
		//Make sure that each employee of company has a unique employeeNumber 
		
		company.printCheck("ab784");
		
		company.deleteEmployeesBySalary(256.36);
		
		company.reverseEmployees();
		
		System.out.println( company.SearchByName("WaLiD WiLLiAms") );

		company.printEmployees();
		
		System.out.println("Bye!");

	}

}



//____________________________
class Employee {

    //Add the private attributes and the methods as mentioned above…
	private String fullName;
    private String employeeNumber;
    private double payRate;
    private double hoursWorked;

    //construtor
    public Employee(String fullName, String employeeNumber, double payRate, double hoursWorked){
        this.fullName = fullName;
        this.employeeNumber = employeeNumber;
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
    }

    //setters and getters
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public double getPayRate() {
        return payRate;
    }
    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    //overriding toString method
    public String toString(){
        return "[" + employeeNumber + "/" + fullName + ", " + hoursWorked + " Hours @ " + payRate + " per hour]";
    }

    //calculate gross pay, tax deductions, and net pay
    private double netPay(){
        double grossPay = payRate * hoursWorked;
        double taxDeduction = grossPay * 0.06;
        double netPay = grossPay - taxDeduction;
        return netPay;
    }

    public void printCheck(){
        System.out.println("Employee's name\t\t" + fullName);
        System.out.println("Employee's number\t" + employeeNumber);
        System.out.printf("Hourly rate of pay:\t%.2f\n", payRate);
        System.out.printf("Hours worked:\t\t%.2f\n", hoursWorked);

        System.out.println();

        System.out.printf("Total Gross Pay:\t$%.2f\n", payRate * hoursWorked);

		System.out.println();

		System.out.println("Deductions");
		System.out.printf("Tax (6 %%):\t\t$%.2f\n", ((payRate * hoursWorked) * 0.06));

		System.out.println();

		System.out.printf("Net Pay:\t\t%.2f Dollars\n\n", netPay());
        
    }

}


//____________________________
class Company {

	private ArrayList<Employee> employeeList;
	private static String companyName;
	private static String companyTaxId;

	
	//Add static Setters and Getters for companyName and companyTaxId
	public static String getCompanyName() {
		return companyName;
	}
	public static void setCompanyName(String companyName) {
		Company.companyName = companyName;
	}

	public static String getCompanyTaxId() {
		return companyTaxId;
	}
	public static void setCompanyTaxId(String companyTaxId) {
		Company.companyTaxId = companyTaxId;
	}
	//No need to add a Setter and Getter for employeeList
	
	public Company() {
		employeeList = new ArrayList<>();
		companyName = "People's Place";
		companyTaxId = "v1rtua7C0mpan1";
	}

	public boolean hire ( Employee employee ) {
		//Add empoyee to employeeList
		//Note well that we can't add an employee whose employeeNumber already
		//assigned to another employee. In that case, this method returns false.
		//This method returns true otherwise

		//check if employeeNumber already assigned, return false if so
		for (Employee e : employeeList){
			 if (employee.getEmployeeNumber().compareToIgnoreCase(e.getEmployeeNumber()) == 0){
				return false;
			 }
		}

		//add new employee, return true
		employeeList.add(employee);
		return true;
	}
	
	public static void printCompanyInfo() {
		//This method prints the compay name and its tax id 
		//You may choose to print that any way you like!
		System.out.println("Company: " + companyName);
		System.out.println("Tax ID: " + companyTaxId);
		System.out.println();
	}
	
	public void printEmployees() {
		//This methods prints all employees (One employee per line)
		//Note that you already have toString in Employye
		for (Employee e : employeeList){
			System.out.println(e.toString());
		}
		System.out.println();
	}

	public int countEmployees( double maxSalary ) {
		//This method returns the number of employees paid less than maxSalary
		int i = 0;
		for (Employee e : employeeList){
			// NOTE: i am assuming that "salary" refers to the net pay; hw document does not
			// specify what "salary" is defined as
			if ( ((e.getPayRate() * e.getHoursWorked()) - ((e.getPayRate() * e.getHoursWorked()) * 0.06)) < maxSalary){
				i++;
			}
		}
		return i;
	}
	

	public boolean SearchByName (String fullName ) {
		//This method returns true if fullName exists as an employee. 
		//It returns false otherwise
		//this is a not a case sensitive search. 
		for (Employee e : employeeList){
			if(e.getFullName().compareToIgnoreCase(fullName) == 0){
				return true;
			}
		}
		return false;
	} 


	public void reverseEmployees () {
		//This method reverses the order in which the employees were added to
		//the list. The last employee is swapped with the first employee, the second last with the second and so on..
		int front = 0;
		int back = employeeList.size() - 1;
		for (int iter = employeeList.size() / 2; iter > 0; iter--){
			Employee temp = employeeList.get(front);
			employeeList.set(front, employeeList.get(back));
			employeeList.set(back, temp);
			front++;
			back--;
		}
	}
	
	public void deleteEmployeesBySalary (double targetSalary ) {
		//This method deletes all employees who are paid targetSalary as a net
		//salary
		int index = employeeList.size() - 1;
		while (index >= 0){
			Employee e = employeeList.get(index);
			double empSalary = ((e.getPayRate() * e.getHoursWorked()) - ((e.getPayRate() * e.getHoursWorked()) * 0.06));
			if (empSalary == targetSalary){
				employeeList.remove(index);
			}
			index--;
		}
	}
	
	public void printCheck ( String employeeNumber) {
		//This method prints the check of the employee whose employee number is
		//employeeNumber. It prints NO SUCH EMPLOYEE EXISTS if employeeNumber is
		//not a registered employee number.
		for (Employee e : employeeList){
			if (e.getEmployeeNumber().compareToIgnoreCase(employeeNumber) == 0){
				e.printCheck();
				return;
			}
		}
		//return never hits aka employee doesnt exist
		System.out.println("NO SUCH EMPLOYEE EXISTS");
		System.out.println();
	}

}//end of class Company
