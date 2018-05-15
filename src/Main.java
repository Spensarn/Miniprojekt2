import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
//Metoder: readFiles(File a, File b), compareHash(HashTableClass a, HashTableClass b), keyWords(), isNumeric();
/**
 * A plagiarism checker which uses hashtable and a GUI.
 * @author E. Lidbeck<br> M. Månsson  
 *
 */
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
	 * Returns Hashtable of identifiers from a file if file not found then returns null.
	 * The fil argument is the complete path to the file.
	 * @param fil Path to a file.
	 * @return    Hashtable of identifiers.
	 */
	public static HashTableClass readFile(String fil){
		FileReader a = null;
		try{
			a = new FileReader(new File(fil));
		} catch (FileNotFoundException e1) {
			return null;
		}
		HashTableClass keyWords = keyWords();

		int idCount = 0;
		String line = "";
		try { //Read file
			BufferedReader read = new BufferedReader(a);
			while(read.ready()){
				line += read.readLine() + " ";
			}
			//line.trim();
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] words = line.split("\\W+"); //Split by symbols (whitespace, {, ], etc.)
		for(int i = 0; i < words.length; i++){
			words[i] = words[i].trim();
		}
		for(String word : words){//Loop counting how many words are identifiers
			if(keyWords.contains(word) == null && !isNumeric(word)){
				idCount++;
			}	
		}
		String[] idWords = new String [idCount];
		int i = 0;
		for(String word : words){//Loop adding identifiers to array
			//If a word is neither a keyword nor a number add it to the array
			if(keyWords.contains(word) == null && !isNumeric(word)){
				idWords[i] = word;
				i++;
			}				
		}
		System.out.println(Arrays.toString(idWords));
		HashTableClass idWordsHash = new HashTableClass(idCount);
		idWordsHash.add(idWords);
		return idWordsHash;
	}

	/**
	 * Create String[] of KeyWords.txt.
	 * @return  String[] containing keywords.
	 */
	public static HashTableClass keyWords() {
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
		HashTableClass keyWordsHash = new HashTableClass(keyWords.length);
		keyWordsHash.add(keyWords);
		return keyWordsHash;
	}

	/**
	 * Returns a percentage of how closely the files resemble each other.
	 * @param a Hashtable which we will compare with b.
	 * @param b Hashtable which we will compare with a.
	 * @return  Percentage of same identifiers.
	 */
	public static double compareHash(HashTableClass a, HashTableClass b){
		double counterTotalWords = (a.size() + b.size()) / 3; //Total nodes divided by three because of the add-function
		double ret = 0; //Final return-value

		int sameWords = 0; //Number of unique identifiers of the total number of identifiers in the two files

		for(int i = 0; i < a.size(); i++){
			if(a.nodeArray[i] != null && b.contains(a.nodeArray[i].element) != null){
				//System.out.println("a: "+ a.nodeArray[i].element);
				if(a.nodeArray[i].frequency < b.contains(a.nodeArray[i].element).frequency){
					sameWords += a.nodeArray[i].frequency; 
					System.out.println("Same words: "+ a.nodeArray[i].element);
				}
				else{
					sameWords += b.nodeArray[i].frequency; 
				}
				
			}
		}

		System.out.println("Total words in files: " + counterTotalWords);
		System.out.println("Unique words in files: " + sameWords);

		ret = (double) Math.round((sameWords / (counterTotalWords - sameWords) * 100) * 10) / 10;

		return ret;
	}

	/**
	 * Check if String is a number.
	 * @param str  String to see if it's a number.
	 * @return     true or false if str is a number or not.
	 */
	public static boolean isNumeric(String str){
		return str.matches("-?\\d+(\\.\\d+)?"); 
	}
}
