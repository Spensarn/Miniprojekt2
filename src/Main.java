import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
//Metoder: readFiles(File a, File b), compareHash(HashTableClass a, HashTableClass b), keyWords(), isNumeric();

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Returns HashTableClass of identifiers from a file if file not found then returns null
	 * The fil argument is the complete path to the file
	 * @param fil the path to a file
	 * @return    a hashtable of identifiers
	 */
	public static HashTableClass readFile(String fil){
		FileReader a = null;
		try{
			a = new FileReader(new File(fil));
		} catch (FileNotFoundException e1) {
			return null;
		}
		String[] keyWords = keyWords();

		int idCount = 0;
		String line = "";
		try { //Read file
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
			if(!Arrays.asList(keyWords).contains(word) && !isNumeric(word)){
				idCount++;
			}	
		}
		String[] idWords = new String [idCount];
		int i = 0;
		for(String word : words){//Loop adding identifiers to array
			//If a word is neither a keyword nor a number add it to the array
			if(!Arrays.asList(keyWords).contains(word) && !isNumeric(word)){
				idWords[i] = word;
				i++;
			}				
		}

		HashTableClass idWordsHash = new HashTableClass(idCount);
		idWordsHash.add(idWords);
		return idWordsHash;
	}

	/**
	 * Create a String[] of KeyWords.txt
	 * @return  a array of strings containing keywords
	 */
	public static String[] keyWords() {
		String line = "";
		try {//Read KeyWords.txt
			BufferedReader read = new BufferedReader(new FileReader("KeyWords.txt"));
			while(read.ready()){
				line += read.readLine();
			}
			read.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] keyWords = line.split(" ");
		return keyWords;
	}
	
	/**Returns a percentage of how closely the files resemble each other*/
	public static double compareHash(HashTableClass a, HashTableClass b){
		double counterTotalWords = (a.size() + b.size()) / 3; //Total nodes divided by three because of the add-function
		double ret = 0; //Final return-value

		int identifiers = 0; //Number of unique identifiers of the total number of identifiers in the two files

		//
		for(int i = 0; i < a.size(); i++){
			if(a.nodeArray[i] != null && b.contains(a.nodeArray[i].element) == null){
				System.out.println("a: "+ a.nodeArray[i].element);
				identifiers++;
			}
		}

		for(int j = 0; j < b.size(); j++){
			if(b.nodeArray[j] != null && a.contains(b.nodeArray[j].element) == null){
				System.out.println("b: "+ b.nodeArray[j].element);
				identifiers++;
			}			
		}
		
		System.out.println("Total words in file: " + counterTotalWords);
		System.out.println("Unique words in file: " + identifiers);

		ret = (double) Math.round(((counterTotalWords - identifiers) / counterTotalWords * 100) * 10) / 10;

		return ret;
	}

	/**
	 * Check if String is a number
	 * @param str  a string to check if it's a number
	 * @return     true or false if str is a number or not
	 */
	public static boolean isNumeric(String str){
		return str.matches("-?\\d+(\\.\\d+)?"); 
	}
}
