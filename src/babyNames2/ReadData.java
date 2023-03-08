package babyNames2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;

public class ReadData {
	
	// ReadData reads in a text file and return an Array of an Array of the names, genders, and 
	// frequencies of each of the names in the given file 
	
	ReadData reader;
		
	public static final String DEFAULT_PATH = "us_name_data/yob";
	private static final String TEST_PATH = "testFiles/testdata";
	
	public HashMap<String, ArrayList<ArrayList<String>>> allData(int firstyear, int lastyear){
		HashMap<String, ArrayList<ArrayList<String>>> data = new HashMap<String, ArrayList<ArrayList<String>>> ();
		for (int i = firstyear; i <= lastyear; i++) {
			String year = String.valueOf(i);
			ArrayList<ArrayList<String>> yeardata = data(year);
			data.put(year, yeardata);
		}
		return data;
	}
	
	public HashMap<String, ArrayList<ArrayList<String>>> testData(int firstyear, int lastyear){
		HashMap<String, ArrayList<ArrayList<String>>> data = new HashMap<String, ArrayList<ArrayList<String>>> ();
		for (int i = firstyear; i <= lastyear; i++) {
			String year = String.valueOf(i);
			ArrayList<ArrayList<String>> yeardata = data(TEST_PATH, year);
			data.put(year, yeardata);
		}
		return data;
	}

	public ArrayList<ArrayList<String>> data(String yearInput){
		return data(DEFAULT_PATH, yearInput);
	}
	
	public ArrayList<ArrayList<String>> data(String path, String yearInput) {
		
		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
		
		//Taken from Knpcode.com linked on BabyNamesPart1 Directions
		Scanner sc = null;
	    try {
	      sc = new Scanner(new File(path+yearInput+ ".txt"));

	      // Check if there is another line of input
	      while(sc.hasNextLine()){
	        String str = sc.nextLine();
	        // parse each line using delimiter
	        parseData(str, outer);
	        
	      }
	    } catch (IOException  exp) {
	      // TODO Auto-generated catch block
	      exp.printStackTrace();
	    }finally{
	      if(sc != null)
	        sc.close();
	    }
	   return(outer);
	  }
		
	  public void parseData(String str, ArrayList<ArrayList<String>> outer){
		  
		ArrayList<String> inner = new ArrayList<String>();   
		
	    String name, gender, count;
	    Scanner lineScanner = new Scanner(str);
	    lineScanner.useDelimiter(",");
	    while(lineScanner.hasNext()){
	      name = lineScanner.next();
	      gender = lineScanner.next();
	      count = lineScanner.next();
	      
	      (inner).add(name);
	      (inner).add(gender);
	      (inner).add(count); 
	      
	      (outer).add(inner); 
	    }
	    lineScanner.close();
	    
	   
	  }

}

