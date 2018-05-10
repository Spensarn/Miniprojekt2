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
		String[] arrayA = {"hi", "this", "is", "a", "long", "text"};
		String[] arrayB = {"hi", "this", "is", "a", "much", "longer", "text", "thanks"};

		HashTableClass aTable = new HashTableClass(arrayA.length);
		HashTableClass bTable = new HashTableClass(arrayB.length);

		aTable.add(arrayA);
		bTable.add(arrayB);

		System.out.println(compareHash(aTable, bTable));

		//		try {
		//			readFile(new FileReader("Test.txt"));
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

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

	public static double compareHash(HashTableClass a, HashTableClass b){
		double counterSameWords = 0;
		double counterTotalWords = (a.size() + b.size()) / 3;
				
		String temp = "";
		
		
		for(int i=0; i<a.size(); i++){
			if(a.nodeArray[i]!=null){
				temp = a.nodeArray[i].element;
				if(b.contains(temp) != null){
					counterSameWords++;
				}
			}
		}
		
		System.out.println("Same words: " + counterSameWords);
		System.out.println("Total words: " + counterTotalWords);

		double ret = (double) Math.round((counterSameWords / counterTotalWords * 100) * 10) / 10;
		
		return ret;
	}

	/**Prints out the percentage*/

	public static void print(){
		//sSystem.out.println(compareHash());

	}
}
