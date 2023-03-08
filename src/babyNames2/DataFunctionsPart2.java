package babyNames2;

import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

public class DataFunctionsPart2 {
	
	// DataFunctions Part 2 contain methods used to solve the functionality
	// problems given in BabyNames Part 2
	
	private static final int DEFAULT_LAST_YEAR_OF_DATA = 2020;
	private static final int DEFAULT_FIRST_YEAR_OF_DATA = 1880;
	
	ReadData reader = new ReadData();
	Rank callRank = new Rank();

	
	//// BabyNames Part 2
	
	// loop through all of the years and add the rank and year of the given name to a hashmap, 
	//return the hashmap.
	
	public HashMap<String, Integer> nameGenderPairAllYearsRankings(String name, String gender, String path){
		return nameGenderPairAllYearsRankings(name, gender, path, DEFAULT_FIRST_YEAR_OF_DATA, DEFAULT_LAST_YEAR_OF_DATA);
	}
	
	public HashMap<String, Integer> nameGenderPairAllYearsRankings(String name, String gender, String path, int first_year, int last_year){
		HashMap<String, Integer> YearRankMap = new HashMap<String, Integer>();
		for (int i = first_year; i <= last_year; i++) {
			String year = Integer.toString(i);
			ArrayList<ArrayList<String>> namesList = reader.data(path, year);
			int rankOfName = callRank.rank(name, gender, namesList);
			YearRankMap.put(year, rankOfName);
		}
		return (YearRankMap);
	}
	
	//loop through the user given years and call the rank method for each occurrence of the name
	//add to a hashmap and return said hashmap
	
	public HashMap<String, Integer> nameGenderPairGivenYearsRankings(String name, String gender, int years_start, int years_end, String path){
		HashMap<String, Integer> YearRankMap = new HashMap<String, Integer>();
		for (int i = years_start; i <= years_end; i++) {
			String year = Integer.toString(i);
			ArrayList<ArrayList<String>> namesList = reader.data(year);
			int rankOfName = callRank.rank(name, gender, namesList);
			YearRankMap.put(year, rankOfName);
		}
		return (YearRankMap);
	}
	
	//find the rank of the name/gender pair and then iterate through all the names in the new list 
	//to find the name gender pair in the new list with the same rank
	
	public String recentYearRankingForPair(String name, String year, String gender, String path) {
		return recentYearRankingForPair(name, year, gender, path, DEFAULT_LAST_YEAR_OF_DATA);
	}
	
	public String recentYearRankingForPair(String name, String year, String gender, String path, int last_year){
		ArrayList<ArrayList<String>> namesList = reader.data(path, year);
		int rankOfName = callRank.rank(name, gender, namesList); 
		String mostRecentYearOfData = Integer.toString(last_year);
		ArrayList<ArrayList<String>> names = reader.data(mostRecentYearOfData);
		for (ArrayList<String> nameBreakdown: names) {
			String currentName = nameBreakdown.get(0);
			String currentGender = nameBreakdown.get(1);
			int rank = callRank.rank(currentName, currentGender, names); 
			if ((rankOfName == rank) && (currentGender.equals(gender))) {
				return (currentName + " " + rank);
			}
		}
		return (name + " " + rankOfName);		
	}
	
	//find the rank of every occurrence of the name in the given years
	//average the ranks
	
	public double averageRankGivenYears(String name, String gender, int years_start, int years_end, String path){
		int sumOfRankings = 0; 
		int numOfNames = 0;
		for (int i = years_start; i <= years_end; i++) {
			String year = Integer.toString(i);
			ArrayList<ArrayList<String>> namesList = reader.data(path, year);
			int rankOfName = callRank.rank(name, gender, namesList);
			sumOfRankings += rankOfName;
			numOfNames += 1;	
		}
		double averageRank = sumOfRankings/numOfNames;
		return (averageRank);
	}
	
	//given the top letter iterate through the list and return a list with all names that match the 
	// top letter and inputed gender
	
	public ArrayList<String> popularLetter(String gender, int years_start, int years_end, String path){
		String topRankingLetter = topLetter(gender, years_start, years_end, path);
		String topLetter = "";
		ArrayList<String> popularNamesLetters = new ArrayList<String>();
		for (int i = years_start; i <= years_end; i++) {
			String year = Integer.toString(i);
			ArrayList<ArrayList<String>> allNamesList = reader.data(path, year);
			for (ArrayList<String> nameBreakdown: allNamesList) {
				String letterStartingName = nameBreakdown.get(0);
				String currentGender = nameBreakdown.get(1);
				topLetter = letterStartingName.substring(0,1);
					if (topLetter.equals(topRankingLetter) && currentGender.equals(gender) && !popularNamesLetters.contains(letterStartingName)) {
						popularNamesLetters.add(letterStartingName);
					}
			}
		}
		popularNamesLetters.add(0, topRankingLetter);
		Collections.sort(popularNamesLetters);
		return popularNamesLetters;
	}
	
	//given a hashmap with the letters and number of occurrences 
	//find the letter with the most occurrences 
	
	public String topLetter(String gender, int years_start, int years_end, String path){
		HashMap<String, Integer> popularLettersMap = popularLetterMap(gender, years_start, years_end, path);
		String topRankingLetter = "";
		int topRank = 0;
		for (String letter : popularLettersMap.keySet()) {
			int currentRank = popularLettersMap.get(letter);
			if (currentRank > topRank) {
				topRank = currentRank;
				topRankingLetter = letter;
			}
			
		}
		return topRankingLetter; 
	}
	
	//iterate through a given number of years and find the list of names associated
	//return a hashmap with the starting letters of all the names and the number of their occurrences
	
	public HashMap<String, Integer> popularLetterMap(String gender, int years_start, int years_end, String path){
		HashMap<String, Integer> popularLettersMap = new HashMap<String, Integer>();
		for (int i = years_start; i <= years_end; i++) {
			String year = Integer.toString(i);
			int occurrences = 0;
			ArrayList<ArrayList<String>> allNamesList = reader.data(path, year);
			for (ArrayList<String> nameBreakdown: allNamesList) {		
				String currentName = nameBreakdown.get(0);
				String currentGender = nameBreakdown.get(1);
				String topLetter = currentName.substring(0,1);
				if (currentGender.equals(gender)) {
					if (popularLettersMap.get(topLetter) == null){
						occurrences = 1;
					}
					else {
						occurrences = popularLettersMap.get(topLetter) + 1;
					}
				popularLettersMap.put(topLetter, occurrences);
				}
			}
		}
		return popularLettersMap;
	}
}
