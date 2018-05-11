import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//Metoder: readFiles(File a, File b), compareHash(HashTableClass a, HashTableClass b), print()
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		HashTableClass Table1 = readFile("Test.txt");
		HashTableClass Table2 = readFile("Test.txt");

		System.out.println("The resemblence between the two Files : " + compareHash(Table1, Table2) + "%");
	}
	//Creates HashTableClass of identifiers from file fil(NEEDS MORE TESTING) 
	public static HashTableClass readFile(String fil){
		FileReader a = null;
		try{
			a = new FileReader(new File(fil));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		HashTableClass keyWords = keyWords();

		int idCount = 0;
		String line = "";
		try {
			BufferedReader read = new BufferedReader(a);
			while(read.ready()){
				line += read.readLine();
			}
			line.trim();
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] words = line.split("\\W+"); //Split by symbols (whitespace, {, ], etc.)
		for(String word : words){//Loop counting how many words are identifiers
			word.trim();
			if(keyWords.contains(word)==null && !isNumeric(word)){
				idCount++;
			}				
		}
		String[] idWords = new String [idCount];
		int i = 0;
		for(String word : words){//Loop adding identifiers to array
			if(keyWords.contains(word)==null && !isNumeric(word)){
				idWords[i] = word;
				i++;
			}				
		}

		HashTableClass idWordsHash = new HashTableClass(idCount);
		idWordsHash.add(idWords);
		return idWordsHash;
	}
	//Create a HashTableClass of KeyWords.txt
	private static HashTableClass keyWords() {
		String line = "";
		try {
			BufferedReader read = new BufferedReader(new FileReader("KeyWords.txt"));
			while(read.ready()){
				line += read.readLine();
			}
			read.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] keyWords = line.split(" ");

		HashTableClass keyWordHash = new HashTableClass(keyWords.length);
		keyWordHash.add(keyWords);

		return keyWordHash;
	}
	
	/**Returns a percentage of how closely the files resemble each other*/
	public static double compareHash(HashTableClass a, HashTableClass b){
		double counterTotalWords = (a.size() + b.size()) / 3;
		double ret = 0;

		int identifiers = 0;

		for(int i = 0; i < a.size(); i++){
			if(a.nodeArray[i] != null){
				identifiers++;
			}
		}

		for(int j = 0; j < b.size(); j++){
			if(b.nodeArray[j] != null && a.contains(b.nodeArray[j].element) == null){
				identifiers++;
			}			
		}

		ret = (double) Math.round(((counterTotalWords - identifiers) / identifiers * 100) * 10) / 10;

		return ret;
	}

	//Check if String is a number
	public static boolean isNumeric(String str){
		return str.matches("-?\\d+(\\.\\d+)?"); 
	}
}
