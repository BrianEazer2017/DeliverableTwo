package lab1Pt2;
//This import allows the user to input a date
import java.util.Scanner;
// These classes allow for instances to be made so I can use methods to calculate difference in time and do validation
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

// Period finds the difference in time between dates in years, months and days
import java.time.Period;

// These are used in my validation method
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;




public class differenceInDates {
	
	public static void main(String[] args) {
		// This calls the input method
		input();
	}


	private static void input() {
		// This is an instance of the scanner class that allows for user input
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a date using the format year/month/day. Examples: 2018-12-25 or 0001-01-01 or 2020-02-29 etc. ");
		String first = sc.nextLine();
		System.out.println("OK, sure. Your first date is " + first + ". Now enter a second date using the same format.");
		String second = sc.nextLine(); 
		String validDateFormat = "yyyy-MM-dd";
		if (validater(validDateFormat, first)&& validater(validDateFormat, second)) {
		// Calls the getDiff method and passes the two user string inputs in as arguments.
		System.out.println("Great! You've inputted both dates correctly. Now we'll find the difference in time between " + first + " and " + second + ".");
		getDiff(first, second);
		}
		else {
			System.out.println("The date was entered incorrectly. Please try again");
			// Recursion. Allows the user to start over.
			input();
		}
	}
	
	/* This method checks to see if the user input the date to the correct format and also that the date put in is an actual calendar date.
	This prevents dates like 2018-01-32 */
	public static boolean validater(String format, String value) {
	    LocalDateTime ldt = null;
	    //Checks to see that the format is correct
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
	    
	    // "try" is used in the event that there is an exception in parsing the date
	    try {
	        ldt = LocalDateTime.parse(value, formatter);
	        String result = ldt.format(formatter);
	        // if true, then will call getDiff();
	        return result.equals(value);
	    } catch (DateTimeParseException e) {
	        try {
	            LocalDate ld = LocalDate.parse(value, formatter);
	            String result = ld.format(formatter);
	            return result.equals(value);
	        } catch (DateTimeParseException exp) {
	            try {
	                LocalTime lt = LocalTime.parse(value, formatter);
	                String result = lt.format(formatter);
	                return result.equals(value);
	            } catch (DateTimeParseException e2) {
	              
	            }
	        }
	    }

	    return false;
	}


	private static void getDiff(String fir, String sec) {
		// I create two instances of the local date class and parse the user string inputs.
		LocalDate firstLocalDate = LocalDate.parse(fir);
		LocalDate secondLocalDate = LocalDate.parse(sec);
		// I use the period class to find the difference in time between my two dates
		Period diff = Period.between(firstLocalDate, secondLocalDate);
		 
		/* I print the difference in years, months, and days. I use the '%d' as a placeholder and avoid concatenating. 
		Math.abs() makes it so it doesn't matter what order the dates are. */
		System.out.printf("Difference is %d years, %d months and %d days", 
		                    Math.abs(diff.getYears()), Math.abs(diff.getMonths()), Math.abs(diff.getDays()));
		
	}
	    
	
}
