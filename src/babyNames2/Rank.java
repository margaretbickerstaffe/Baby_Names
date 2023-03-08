package babyNames2;

import java.util.ArrayList;

public class Rank {
	
	// Rank is used solely to return the rank of a name in a given year, many of the functionality
	// methods will call it 
	
	ReadData reader = new ReadData();
	
	// The rank method returns the rank for a given name, gender, and year. It calls data(year) and 		
	// runs through the array of names ensuring that the gender is the same. If the gender is correct 
	// it runs through till it finds the correct name. It also checks the frequency of the name ahead of it
	// if the frequency is the same, the rank does not increase.
		
	public int rank(String name, String gender, ArrayList<ArrayList<String>> names) {
		int rank = 1;

		for (int i = 0; i < names.size() - 1; i++) {				
			String currentGender = (names.get(i)).get(1);
			String currentName = (names.get(i)).get(0);
			String currentFrequency = (names.get(i)).get(2);
			String nextFrequency = (names.get(i+1)).get(2);
					
			if (currentGender.equals(gender)) {
				if ((currentName.equals(name))) {
					return rank;	
				}
		
				if (currentFrequency.equals(nextFrequency)) {
		
				} 
				else {
					rank++;
				}
			}			
		}
		return rank;
	}
}
