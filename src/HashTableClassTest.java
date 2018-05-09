import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableClassTest {

	@Test
	public void addTest() {
		String[] array = {"Hej", "Jacob", "Leif", "Max", "Erik", "Hej"};
		HashTableClass hash = new HashTableClass(array.length*3);
		hash.add(array);
		assertEquals("Tests if the size if the hashtable is correct ",18,hash.size());
		assertEquals("Check if the hashtable contains the the element Hej", "Hej", hash.contains("Hej").element);
		assertEquals("Check if the frequency is correct", 2, hash.contains("Hej").frequency);
		assertEquals("Check if the amount of words in the array are correct", 6, hash.totalWords);
		int count = 0;
		for(int i = 0; i < hash.nodeArray.length; i++) {
			if(hash.nodeArray[i] != null) {
				count++;
			}
		}
		assertEquals("Checks that the duplicates hej are added to the same node ", 5, count);
		
		
	}
	@Test
	public void containsTest() {
		String[] array = {"Hej", "Spens", "Spens", "Max", "Leif", "Spens", "Erik", "Spens", "Spens", "Spens", "Spens", "Hej", "Hej", "Hej", "Hej", "Hej", "Hejh", "Dejh", "Jejh", "Sejh", "Kej", "Mej", "jeM"};
		HashTableClass hash = new HashTableClass(array.length); 
		hash.add(array);
		assertEquals("Check if the hashtable returns null when we use the contains method on a String that does not exist in our hashtable ", null, hash.contains("H233232323ej"));
		assertEquals("Check if we",true,hash.contains("Hej").element.equals("Hej"));
		
	}
	

}
