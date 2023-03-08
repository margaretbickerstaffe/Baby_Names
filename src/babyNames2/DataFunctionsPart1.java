package babyNames2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataFunctionsPart1 {
	
	// This class contains methods that return the answers to the functionality questions
	// given in BabyNames Part1
	
	
	private static final int DEFAULT_LAST_YEAR_OF_DATA = 2021;
	private static final int DEFAULT_FIRST_YEAR_OF_DATA = 1880;
	
	ReadData reader = new ReadData();
	Rank callRank = new Rank();

	// This method (P) takes in a user year and outputs the most popular name and gender for that year. 
	// It iterates through the arraylist of names outputted by data(year) and takes the first array with 
	// a matching gender and outputs the name and the frequency. It also calls the rank method to return
	// rank.
	
	public String popularNameForYear(String year, String gender, ArrayList<ArrayList<String>> namesList) {
		
		String topRankingName = "";
		String frequencyOfName = "";
		for (ArrayList<String> nameDetails: namesList) {	
			if ((nameDetails.get(1)).equals(gender)) {
				topRankingName = nameDetails.get(0);
				frequencyOfName = nameDetails.get(2);
				break;
			}
		}
		int rank = callRank.rank(topRankingName, gender, namesList);
		return ("The name " + topRankingName + ", gender " + gender + ", in the year " + year + " occurred with frequency " + frequencyOfName + ", and rank "+ rank);
	}
	
	// This method(R) returns the rank for a user inputed name, gender, and year.
	// We have the name, gender, and year, but must iterate through the arrays to find the frequency. Then 
	// we call the rank method to find rank. 
	
	
	public String rankForGivenName(String name, String year, String gender, ArrayList<ArrayList<String>> namesList) {
		Scanner userQuerySelection = new Scanner(System.in);
		String frequencyOfName = "0";
		int rank = 0;
		for (ArrayList<String> nameDetails: namesList) {
			if ((nameDetails.get(0)).equals(name) && (nameDetails.get(1)).equals(gender)) {
				frequencyOfName = nameDetails.get(2);
				break;
			}
		}
		while (frequencyOfName.equals("0")) {
			System.out.println("Unavaliable combination. Try a new name:");
			name = userQuerySelection.nextLine();
			rank = 0;
			for (ArrayList<String> nameDetails: namesList) {
				if ((nameDetails.get(0)).equals(name) && (nameDetails.get(1)).equals(gender)) {
					frequencyOfName = nameDetails.get(2);
					break;
				}
			}
			
		}
		
		rank = callRank.rank(name, gender, namesList);
		
		userQuerySelection.close();
		return ("The name " + name + ", gender " + gender + ", in the year " + year + " occurred with frequency " + frequencyOfName + ", and rank "+ rank);
	}
	
	// The method (Y) returns the year a name was most popular based on frequency, not rank. 
    // The hashmap is iterated through to return the
	// highest frequency and its associated year. 		
	
	public String yearNameMostPopular(String name, String gender, String path) {
		return yearNameMostPopular(name, gender, path, DEFAULT_FIRST_YEAR_OF_DATA, DEFAULT_LAST_YEAR_OF_DATA);
	}
	
	public String yearNameMostPopular(String name, String gender, String path, int first_year, int last_year) {

		Scanner userQuerySelection = new Scanner(System.in);
		int highestFrequency = 0;
		String topYearForName = "";
		int rank = 0;
		ArrayList<ArrayList<String>> namesList = new ArrayList<ArrayList<String>>();
		
		HashMap<String, Integer> YearFrequencyMap = yearFrequency(name, gender, path, first_year, last_year);
		
		if (YearFrequencyMap.isEmpty()) {
			System.out.println("Unavaliable combination. Try another name: ");
			name = userQuerySelection.nextLine();
			YearFrequencyMap = yearFrequency(name, gender, path, first_year, last_year);
		}
		for (String yearFromMap : YearFrequencyMap.keySet()) {
				int frequencyInMap= YearFrequencyMap.get(yearFromMap);				
				if (highestFrequency <= frequencyInMap){
					highestFrequency = frequencyInMap;
					topYearForName = yearFromMap;
				}
		}
		namesList = reader.data(topYearForName);
		rank =  callRank.rank(name, gender, namesList);
		return ("The name " + name + ", gender " + gender + ", in the year " + topYearForName + " occurred with frequency " + highestFrequency + ", and rank "+ rank);
	}
	
	// It runs through every array of names pulling out each year and frequency associated with 		
	// user's chosen name and gender into a hashmap.
	
	public HashMap<String, Integer> yearFrequency(String name, String gender, String path, int first_year, int last_year){
		String year = "";
		String frequency = "";
		HashMap<String, Integer> YearFrequencyMap = new HashMap<String, Integer>();
		HashMap<String, ArrayList<ArrayList<String>>> yearsData = reader.allData(DEFAULT_FIRST_YEAR_OF_DATA, DEFAULT_LAST_YEAR_OF_DATA);
		
		for (int i = first_year; i <= last_year; i++) {
			year = Integer.toString(i);	
			ArrayList<ArrayList<String>> namesList = yearsData.get(year);
			for (ArrayList<String> nameBreakdown: namesList) {					
				if ((nameBreakdown.get(0)).equals(name) && (nameBreakdown.get(1)).equals(gender)) {						
					frequency = nameBreakdown.get(2);
					int frequencyOfName = Integer.parseInt(frequency);						
					YearFrequencyMap.put(year, frequencyOfName);
				}
			}
		}
		
		return YearFrequencyMap;
	}
		
		
}


