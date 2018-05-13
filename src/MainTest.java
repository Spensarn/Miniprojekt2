import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
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

}
