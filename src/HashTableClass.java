import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;


//Spara alla java nyckelord i en hashtabell
//Spara varje unik identifierare i en annan hashtabell

//Metoder: get(int),size(),put(int, string), hashcode()
public class HashTableClass {
	
	public int firstCapacity;
	public float loadFactor;
	public float countWords;
	
	public HashTableClass(int firstCapacity, float loadFactor) throws IOException {
		if(firstCapacity<=0) {
			throw new IOException("Illegal capacity: " + firstCapacity);
		}
		if(loadFactor<=0) {
			throw new IOException("Illegal load: " + loadFactor);
		}
	}
	
	public static void main(String[] args){
		
		
	}

	
	
    
	
}
