import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//Metoder: readFiles(File a, File b), compareHash(HashTableClass a, HashTableClass b), print()

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		String[] arrayA = {"hi", "this", "is", "a", "long", "text"};
		String[] arrayB = {"hi", "this", "is", "a", "much", "longer", "text", "thanks"};

		HashTableClass aTable = new HashTableClass(arrayA.length);
		HashTableClass bTable = new HashTableClass(arrayB.length);

		aTable.add(arrayA);
		bTable.add(arrayB);

		System.out.println(compareHash(aTable, bTable));

		System.out.println("=============================================");
		readFile("Miniprojekt2/Test.txt");

	}
	//Creates HashTableClass of identifiers from file fil(NEEDS MORE TESTING) 
	public static HashTableClass readFile(String fil){
		FileReader a = null;
		try {
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
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(line);

		String[] words = line.split("\\W+"); //Split by symbols (whitespace, {, ], etc.)
		for(String word : words){//Loop counting how many words are identifiers
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
			BufferedReader read = new BufferedReader(new FileReader("Miniprojekt2/KeyWords.txt"));
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
	
	//Check if String is a number
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?"); 
	}

	/**Prints out the percentage*/

	public static void print(){
		//sSystem.out.println(compareHash());

	}
}
