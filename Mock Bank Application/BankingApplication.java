package bankSource;
/*
 * Author: Isabella Stephens
 * Created: 10 June 2026
 * Updated: 12 June 2026
 * 
 * Description: A prototype of the full version of the Bank Account application.
 */

// imports
import java.util.Scanner;
import java.math.BigDecimal; 	// BigDecimal is best for financial calculations
import java.util.Random;
import java.util.ArrayList; 

public class BankingApplication {
	static boolean contApp = true; 		// determines whether the user wants to continue the application or not
	static String userAccess;			// what user types in to access their account
	static String userChoice;			// what option the user selects when logged into the app
	static double moneyManip = 0.00;
	static ArrayList<BankAccount> accountHolder = new ArrayList<BankAccount>();;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BankAccount test1 = new BankAccount("Johnny", "Bravo");
		BankAccount test2 = new BankAccount("Matilda", "Ferro");
		BankAccount test3 = new BankAccount("James", "Bond");
		BankAccount test4 = new BankAccount("Buffy", "Slayer");
		BankAccount test5 = new BankAccount("Charlie", "Smith");
		accountHolder.add(test1); 
		accountHolder.add(test2);
		accountHolder.add(test3);
		accountHolder.add(test4);
		accountHolder.add(test5);
		
		// user signs into account or creates account
		checkSignIn(sc);
		
		// user has signed into the application
		while(contApp==true) {
			System.out.println("What do you want to do?");
			System.out.println("1=Deposit, 2=Withdraw, 3=See ID, 4=See Name, 5=Change Name, 6=Quit");
			userChoice = sc.next();
			
			switch(userChoice) {
			case "1":
				System.out.println("How much do you want to deposit in your account?");
				moneyManip = sc.nextDouble();
				//deposit(moneyManip);
				break;
			case "2":
				//withdraw();
				break;
			case "3":
				//getUserID();
				break;
			case "4":
				//getUserName();
				break;
			case "5":
				//setName();
				break;
			case "6":
				contApp = false;
				break;
			default:
				System.out.println("ERROR: Try again.");	
			}
		}
		
		
		
		sc.close();
	} // end of main
	
	// checks to see if the user wants to sign into the 
	public static void checkSignIn(Scanner sc) {
		System.out.println("Please type in your pin to access your account. Otherwise, type 'Create'.");
		userAccess = sc.next();
		
	}

} // end of BankingApplication


/*
 * Creates a bank account object.
 */
class BankAccount {
	private int userID; 				// user ID - completely unique to the user & cannot be changed
	private int userPin;				// user pin - must be used to access the account
	private String firstName; 			// user first name - can be repeated, must be used to access the account
	private String lastName; 			// user last name - can be repeated, must be used to access the account; can include middle name
	private BigDecimal userBalance;  	// current balance
	
	// create a bank account
	public BankAccount(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userBalance = new BigDecimal("0.00");
		
		Random r = new Random();
		
		// create userPin - should have 6 nums
		this.userPin = r.nextInt((999999 - 100000) + 1) + 100000;
				
		// create userID - should have 9 nums - DOES NOT CHECK TO SEE IF IT MATCHES OTHER IDs
		this.userID = r.nextInt((999999999 - 100000000) + 1) + 100000000;
	}
	
	// do not use in final version
	void quickPrint() {
		System.out.println(userID + " " + userPin + " " + firstName + " " + lastName + " $" + userBalance);
	}
	
	void deposit(double newMoney) {
		BigDecimal safeConv = frmDbleConv(newMoney);
		this.userBalance.add(safeConv);
	}
	
	BigDecimal withdraw(double getRidOf) {
		BigDecimal safelyConv = frmDbleConv(getRidOf);
		this.userBalance.subtract(safelyConv);
		return this.userBalance;
	}

	int getUserID(int typedPin, String typedLName, String typedFName) {
		if((typedPin==userPin) && (typedLName.equals(lastName)) && (typedFName.equals(typedFName))) {
			return this.userID;
		} else {
			return -1;
		}
	}
	
	String getUserName(int typedPin) {
		if(typedPin==userPin) {
			return this.firstName + " " + this.lastName;
		}
		
		return "Error: Pin does not match any in our system.";
	}
	
	String setName(String userFirst, String userLast) {
		firstName = userFirst;
		lastName = userLast;
		
		return "Name changed to " + firstName + " " + lastName;
	}
	
	// converts double values into BigDecimal
	BigDecimal frmDbleConv(double temp) {
		BigDecimal fromDouble = BigDecimal.valueOf(temp);
		return fromDouble;
	}

	
} // end of BankAccount