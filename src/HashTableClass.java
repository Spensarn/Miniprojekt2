import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;


//Spara alla java nyckelord i en hashtabell
//Spara varje unik identifierare i en annan hashtabell

//Metoder: get(int),size(),put(int, string), hashcode(), contains()
public class HashTableClass {
	
	String[] array;
	int sizeOfArray;
	int elementInArray = 0;
	public int wordCount = 0;
	public int freq;
	public int totalWords;
		
	HashTableClass(int size){
		sizeOfArray = size;
		array = new String[size];
		Arrays.fill(array, "-1");
		if(size <= 0) {
			throw new RuntimeException("ERROR" + size);
		}
	}
	
	public void hashFunc(String[] elementsInArray, String array[]) {
		for(int i = 0; i < elementsInArray.length; i++) {
			String newElement = elementsInArray[i];
				while(elementsInArray[i] == "-1") {
					
				}
			array[Integer.parseInt(newElement)] = newElement;
				
		}
	}
	
	
	public String findKey(String key) {
		int hashCodeIndex = Integer.parseInt(key) % wordCount;
		while(array[hashCodeIndex] != "-1") {
			if(array[hashCodeIndex] == key) {
				return array[hashCodeIndex];
			}
			++hashCodeIndex;
			hashCodeIndex %= sizeOfArray;
		}
			return null;
	
	}

	public void contains() {
		
	}



}
