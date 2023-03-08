package babyNames2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserSelections {
	
	// UserSelections reads in the user input for which functionality is requested 
	// and calls the appropriate method

	private static final int LAST_YEAR_OF_DATA = 2021;
	private static final int FIRST_YEAR_OF_DATA = 1880;
	private static final String GENDER_OPTION_MALE = "M";
	private static final String GENDER_OPTION_FEMALE = "F";
	
	DataFunctionsPart1 methodQueries = new DataFunctionsPart1();
	ReadData reader = new ReadData();
	HashMap<String, ArrayList<ArrayList<String>>> yearsData = reader.allData(FIRST_YEAR_OF_DATA, LAST_YEAR_OF_DATA);
	
	//Iterates through the prompts and ensures that the right method is called 
	
	public void selectMethod() {
		
		
		boolean askingQueries = true;
		Scanner userQuerySelection = new Scanner(System.in);
		String selection = userQuerySelection.nextLine(); 
		
		//Method P 
		if (selection.contains("P")) {
			String gender = inputGender();				
			String year = inputYear();
			ArrayList<ArrayList<String>> namesList = yearsData.get(year);
			System.out.println(methodQueries.popularNameForYear(year, gender, namesList));		
		}
			
		//Method R
		else if (selection.contains("R")) {
			System.out.println("Enter the name. ");				
			String name = userQuerySelection.nextLine();
			String gender = inputGender();
			String year = inputYear();;
			ArrayList<ArrayList<String>> namesList = yearsData.get(year);
			System.out.println(methodQueries.rankForGivenName(name, year, gender, namesList));				
		}
			
		//Method Y
		else if (selection.contains("Y")) {
			System.out.println("Enter the name. ");				
			String name = userQuerySelection.nextLine();
			String gender = inputGender();
			String path = "us_name_data/yob";
			System.out.println(methodQueries.yearNameMostPopular(name, gender, path));
		}
			
		//Exit System, Method Q
		else if (selection.contains("Q")) { 
			askingQueries = false;
			System.exit(0);
		}		
		else {
			System.out.println("Invalid Option. Choose P, R, Y, or Q");
			this.selectMethod();
		}
		
		while (askingQueries) {
			new UserQueries().userSelectionQueries();
		}
	}

	// Prompts the user for a year and ensures that it exists within the parameters
	// of the data files. If the year is outside of them or a invalid input, it prompts
	// the user to try again.
	
	public String inputYear() {
		Scanner userQuerySelection = new Scanner(System.in);
		System.out.println("Enter the year. ");	
		String yearSelection = userQuerySelection.nextLine();
		int selection = 0;
		boolean successful = false;

		while (!successful) {
			try {
				selection = Integer.parseInt(yearSelection);
				while (selection > LAST_YEAR_OF_DATA || selection < FIRST_YEAR_OF_DATA) {
					System.out.println("Invalid year. Enter a year from " + FIRST_YEAR_OF_DATA + " to " + LAST_YEAR_OF_DATA);
					yearSelection = userQuerySelection.nextLine();
					selection = Integer.parseInt(yearSelection);
				}
				successful = true;
			}
			catch (Exception NumberFormatException) {
				System.out.println("Invalid year. Enter a year from " + FIRST_YEAR_OF_DATA + " to " + LAST_YEAR_OF_DATA);
				yearSelection = userQuerySelection.nextLine();
			}
		}
		return yearSelection;
	
	}

	// Prompts the user for a gender of either M or F. If it is not M or F
	// it prompts the user to try again until it is. 
	
	public String inputGender() {
		Scanner userQuerySelection = new Scanner(System.in);
		System.out.println("Enter the gender as a capital letter.");
		String genderSelection = userQuerySelection.nextLine();
		while ((!genderSelection.equals(GENDER_OPTION_FEMALE)) && (!genderSelection.equals(GENDER_OPTION_MALE))) {
			System.out.println("Invalid gender input. Must enter either " + GENDER_OPTION_MALE + " or "+ GENDER_OPTION_FEMALE);
			genderSelection = userQuerySelection.nextLine();
		}
		return genderSelection;
	}
	
}

