package babyNames2;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

// This test case tests all methods whose functions were defined in 
// Baby Names Part2 

public class TestCase extends junit.framework.TestCase {
	
	private static final int TEST_LAST_YEAR_OF_DATA = 2020;
	private static final int TEST_FIRST_YEAR_OF_DATA = 2018;
	
	private static final String TEST_PATH = "testFiles/testdata";

	@Test 
	public void testreadData(){
		String expected = "[[Maggie, F, 20], [Emma, F, 18], [Grace, F, 18], [Mark, M, 18], [Aidan, M, 15], [John, M, 13]]";
		ArrayList<ArrayList<String>> namesList = new ReadData().data(TEST_PATH, "2018");
		String actual = namesList.toString();
		assertEquals(expected, actual);	
	}
	
	@Test
	public void testP() {
		ArrayList<ArrayList<String>> namesList = new ReadData().data(TEST_PATH, "2018");
		String expected = "The name Maggie, gender F, in the year 2018 occurred with frequency 20, and rank 1";
		String actual = new DataFunctionsPart1().popularNameForYear("2018", "F", namesList);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testR() {
		ArrayList<ArrayList<String>> namesList = new ReadData().data(TEST_PATH, "2018");
		String expected = "The name Emma, gender F, in the year 2018 occurred with frequency 18, and rank 2";
		String actual = new DataFunctionsPart1().rankForGivenName("Emma", "2018", "F", namesList);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRank() {
		ArrayList<ArrayList<String>> namesList = new ReadData().data(TEST_PATH, "2019");
		int actual = new Rank().rank("Paul", "M", namesList);
		assertEquals(3, actual);
	}
	
	@Test 
	public void testnameGenderPairAllYearsRankings() {
		HashMap<String, Integer> YearRankMap = new DataFunctionsPart2().nameGenderPairAllYearsRankings("Maggie", "F", TEST_PATH, TEST_FIRST_YEAR_OF_DATA, TEST_LAST_YEAR_OF_DATA);
		String actual = YearRankMap.toString();
		String expected = "{2019=2, 2018=1, 2020=3}";	
		assertEquals(expected, actual);	
	}
	
	@Test 
	public void testnameGenderPairGivenYearsRankings() {
		HashMap<String, Integer> YearRankMap = new DataFunctionsPart2().nameGenderPairGivenYearsRankings("Emma", "F", 2018, 2020, TEST_PATH);
		String actual = YearRankMap.toString();
		String expected = "{2019=2, 2018=1, 2020=2}";
		assertEquals(expected, actual);	
	}
	
	@Test
	public void testrecentYearRankingForPair() {
		String actual = new DataFunctionsPart2().recentYearRankingForPair("Grace", "2018", "F", TEST_PATH);
		String expected = "Emma 2";
		assertEquals(expected, actual);		
	}
	
	@Test 
	public void testaverageRankGivenYears() {
		double actual = new DataFunctionsPart2().averageRankGivenYears("Grace", "F", 2018, 2020, TEST_PATH);
		double expected = 2.0;
		assertEquals(expected, actual);		
	}
	
	@Test
	public void testpopularLetterMap() {
		HashMap<String, Integer> popularLettersMap = new DataFunctionsPart2().popularLetterMap("F", 2018, 2020, TEST_PATH);
		String expected = "{P=2, A=2, B=2, E=3, G=3, J=2, K=4, M=3}";
		String actual = popularLettersMap.toString();
		assertEquals(expected, actual);	
	}
	
	@Test
	public void testtopLetter() {
		String actual = new DataFunctionsPart2().topLetter("F", 2018, 2020, TEST_PATH);
		String expected = "K";
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testopopularLetter() {
		ArrayList<String>  popularNamesLetter = new DataFunctionsPart2().popularLetter("F", 2018, 2020, TEST_PATH);
		String actual = popularNamesLetter.toString();
		String expected = "[K, Kara, Katie]";
		assertEquals(expected, actual);	
	}
	
}
