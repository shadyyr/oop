// Worked on by: Shade Rahman

import java.util.Scanner;

public class hw2 {

	public static void main(String[] args) {
		
		// welcome message
		System.out.println("Welcome to my program. You will be asked to enter the scores of a test one by one, and to enter -1 to stop.\n");
		
		Scanner myScan = new Scanner(System.in);
		char repeat = 'Y'; 
		
		// do-while that repeats/resets everything
		do {
			
			// variable initializations
			int userInput;
			int enterScore = 0;
			int count = 0;
			int sum = 0;
			float avg;
			int aboveSeventy = 0;
			int numOfAs = 0;
			int numOfBs = 0;
			int numOfCs = 0;
			int numOfDs = 0;
			int numOfFs = 0;
			
			// do-while that collects scores and prints report
			do {
				
				// collects user's score
				System.out.print("Enter Score (Enter -1 to Stop): ");
				userInput = myScan.nextInt();
				
				// sets up future while condition
				enterScore = userInput;
				
				// checks for valid test score between 0 and 100
				if(userInput <= 100 && userInput >= 0) {
					
					count++;
					sum = sum + userInput;
					
					// checks grade, adjusts variables accordingly
					if (userInput >= 90) {
						aboveSeventy++;
						numOfAs++;
					}
					else if (userInput >= 80) {
						aboveSeventy++;
						numOfBs++;
					}
					else if (userInput >= 70) {
						aboveSeventy++;
						numOfCs++;
					}
					else if (userInput >= 60) numOfDs++;
					else numOfFs++;
					
				}
				// if invalid score, say its rejected (excluding -1)
				if(userInput >= 101 || userInput <= -2) System.out.println("Score " + userInput + " Rejected");
				
			}while(enterScore != -1); // end of inner do-while
			
			// calculate average
			avg = (float)sum / count;
			
			// time to print the report
			System.out.println("\nHere is your report:");
			System.out.println("\t-  A total of " + count + " scores entered. " + aboveSeventy + " of them are 70 or higher.\n");
			System.out.println("\t-  Letter Grade distribution of the scores:");
			
			System.out.println("\t\t-  " + numOfAs + " Students earned the grade of A (90-100)");
			System.out.println("\t\t-  " + numOfBs + " Students earned the grade of B (80-89)");
			System.out.println("\t\t-  " + numOfCs + " Students earned the grade of C (70-79)");
			System.out.println("\t\t-  " + numOfDs + " Students earned the grade of D (60-69)");
			System.out.println("\t\t-  " + numOfFs + " Students earned the grade of F (59 or below)\n");
			
			System.out.printf("\t-  The average score is: %.2f%n", avg);
			
			// asks user if they want to do it again
			System.out.print("\nWould you like to process another class? (Y or N): ");
			repeat = myScan.next().charAt(0); // gets char from keyboard
		
		}while(Character.toUpperCase(repeat) == 'Y'); // end of outer do-while
		
		// farewell statement
		System.out.println("Goodbye!");
	}
	
}
