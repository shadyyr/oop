// Worked on by: Shade Rahman

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner myScan = new Scanner(System.in); // scanner variable
		float x; // user's dollar amount variable
		
		System.out.print("Enter your dollar amount: "); // starting message, asks user for input
		x = myScan.nextFloat(); // scans user's keyboard for float
		
		x = x * 100; // move decimal point 2 to the right (to have a whole number)
		int y = (int)x; // int version of input, avoids floating point precision error when dividing
		
		// declare cash variables
		int hundreds;
		int fiftys;
		int twentys;
		int tens;
		int fives;
		int ones;
		int quarters;
		int dimes;
		int nickels;
		int cents;
		
		// find # of hundreds, subtract from y value, take extra 100 into account when dividing
		hundreds = y / 10000;
		y = y - (hundreds * 10000);
		
		// same process, but with fiftys
		fiftys = y / 5000;
		y = y - (fiftys * 5000);
		
		// same process, but with twentys
		twentys = y / 2000;
		y = y - (twentys * 2000);
				
		// same process, but with tens
		tens = y / 1000;
		y = y - (tens * 1000);
		
		// same process, but with fives
		fives = y / 500;
		y = y - (fives * 500);
		
		// same process, but with ones
		ones = y / 100;
		y = y - (ones * 100);
		
		// same process, but with quarters
		quarters = y / 25;
		y = y - (quarters * 25);
						
		// same process, but with dimes
		dimes = y / 10;
		y = y - (dimes * 10);
				
		// same process, but with nickels
		nickels = y / 5;
		y = y - (nickels * 5);
				
		// same process, but with cents
		cents = y / 1;
		y = y - (cents * 1);
		
		// print final message
		System.out.println("\nYou have: \n");
		System.out.println("-\t" + hundreds + " hundred(s)");
		System.out.println("-\t" + fiftys + " fifty(s)");
		System.out.println("-\t" + twentys + " twenty(s)");
		System.out.println("-\t" + tens + " ten(s)");
		System.out.println("-\t" + fives + " five(s)");
		System.out.println("-\t" + ones + " one(s)");
		System.out.println("-\t" + quarters + " quarter(s)");
		System.out.println("-\t" + dimes + " dime(s)");
		System.out.println("-\t" + nickels + " nickel(s)");
		System.out.println("-\t" + cents + " cent(s)");
		System.out.println("\nGoodbye!");
		
	}

}
