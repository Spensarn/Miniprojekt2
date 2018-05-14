import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MainTest {
	HashTableClass Testtxt;
	HashTableClass Test2txt;
	String[] keyWords;
	@Before
	public void setUp() throws Exception{
		Testtxt = Main.readFile("Test.txt");
		Test2txt = Main.readFile("Test2.txt");
		keyWords = Main.keyWords();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCompareHash() {
		String[] arrayA = {"Hej", "Spens", "filip", "korv", "fil"}; 
	    HashTableClass tableA = new HashTableClass(arrayA.length); 
	    tableA.add(arrayA);
	    
	    String[] arrayB = {"kommando", "bara", "spela", "Hej", "korv", "ljud"};
	    HashTableClass tableB = new HashTableClass(arrayB.length);
	    tableB.add(arrayB);
	    
	    double result = Main.compareHash(tableA, tableB);
	    
	    assertEquals("Tests if the method returns the correct value", 36.4, result, 0.1); //(11 - 7) / 11 = 36.4
	}
	@Test
	public void testReadFile(){
		String[] arrayOfTesttxt = {"Spens", "heter", "han"};
		boolean same = false;
		for(String word : arrayOfTesttxt){
			if(Testtxt.contains(word)!= null){
				same = true;
			}
		}
		assertEquals("Tests if readFile() creates hashtable with same words as a string[] containing the same words as the file", true, same);
		
		assertEquals("Tests if readFile() returns null if file not found", null, Main.readFile("nonExistingFile.txt"));
		boolean containsKey = false;
		for(String keyWord : keyWords){
			if(Test2txt.contains(keyWord)!=null){
				containsKey = true;
			}
		}
		assertEquals("Tests if hashtable of a file(containing keywords) doesn't contain keywords", false, containsKey);
	}

}