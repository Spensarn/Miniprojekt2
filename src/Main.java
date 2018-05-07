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
	//Scannar filrader och splita raden vid java syntax
	public static HashTableClass readFile(File a){
		HashTableClass theHash = new HashTableClass();
		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(a);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				String[] words = line.split("\\{|\\}|\\(|\\)|\\<|\\>|\\*|\\+|\\-|\\/|\\.|\\%|\\&|\\|");
				for(String word : words){
					theHash.add(word);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theHash;
	}
	
	public static int compareHash(HashTableClass a, HashTableClass b){
		return 0;
	}
	
	public static void print(){
		
	}
}
