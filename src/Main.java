import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;
//Metoder: readFiles(File a, File b), compareHash(HashTableClass a, HashTableClass b), print()

public class Main {
	public static void main(String[] args) {
		try {
			readFile(new FileReader("src/TestJava.java"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		HashTableClass keyWords = new HashTableClass();
//		try {
//			Scanner scan = new Scanner(new File("KeyWords.txt"));
//			while(scan.hasNext()){
//				String keyword = scan.next();
//				keyWords.add(keyword);
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	//Scannar filrader och splita raden vid java syntax BYT VOID TILL HASHTABLECLASS N�R DEN �R F�RDING
	public static void readFile(FileReader a){
		int wordCount = 0;
		String line = "";
		try {
			
			@SuppressWarnings("resource")
			BufferedReader read = new BufferedReader(a);
			while(read.ready()){
				
				 line += read.readLine();
				
			}
			
			System.out.println(line);
			
			//String[] words = line.split("\\{|\\}|\\(|\\)|\\<|\\>|\\*|\\+|\\-|\\/|\\.|\\%|\\&|\t|\\s+|\\;|\\|");
			String[] words = line.split("\\W+");
			for(String word : words){
				word.trim();
				wordCount++;					
			}
			
			System.out.println(Arrays.toString(words));
			System.out.println(wordCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Returns a percentage of how closely the files resemble each other*/

	public static int compareHash(HashTableClass a, HashTableClass b){
		return 0;
	}
	
	/**Prints out the percentage*/

	public static void print(){
		//System.out.println(compareHash());

	}
}
