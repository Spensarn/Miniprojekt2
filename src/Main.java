import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//Metoder: readFiles(File a, File b), compareHash(HashTableClass a, HashTableClass b), print()

public class Main {
	public static void main(String[] args) {
		HashTableClass keyWords = new HashTableClass();
		try {
			Scanner scan = new Scanner(new File("KeyWords.txt"));
			while(scan.hasNext()){
				String keyword = scan.next();
				keyWords.add(keyword);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Scannar filrader och splita raden vid java syntax BYT VOID TILL HASHTABLECLASS N�R DEN �R F�RDING
	public static void readFile(File a){
		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(new File("test.txt"));
			while(scan.hasNextLine()){
				int wordCount = 0;
				String line = scan.nextLine();
				String[] words = line.split("\\{|\\}|\\(|\\)|\\<|\\>|\\*|\\+|\\-|\\/|\\.|\\%|\\&|\\||\t");
				for(String word : words){
					wordCount++;
					
				}
				System.out.println(words[0]);
				//System.out.println(Arrays.toString(words));
				System.out.println(wordCount);
			}
		} catch (FileNotFoundException e) {
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
		System.out.println(compareHash());

	}
}
