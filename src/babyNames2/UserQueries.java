package babyNames2;

import java.util.HashMap;

public class UserQueries extends ReadData{
	
	// UserQueries asked the user which query they want answered and pushes that answer to the 
	// UserSelections class 
	
	ReadData reader = new ReadData();
	
	UserSelections userChoice = new UserSelections();

	// The readData class takes in the .txt file from the user's inputed year and outputs and arraylist 
	// of arrays with each array being [Name, Gender, Frequency] in order of highest to lowest frequency with 
	// female names on the top
	
	// The query method asks the user which query they would like to call,
	// asks and collects the appropriate data for said query and runs the associated method.
	
	public void userSelectionQueries() {
			System.out.println("Choose an option: ");
			System.out.println("P  Show most popular names for a given year and gender.");
			System.out.println("R  Show rank for a given name, gender, and year.");
			System.out.println("Y  Find the year in which the given name, gender combination was most popular.");
			System.out.println("Q  Quit");
						
			userChoice.selectMethod();
			
	}
}
