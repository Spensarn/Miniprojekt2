import java.util.Arrays;
import java.util.Iterator;


//Spara alla java nyckelord i en hashtabell
//Spara varje unik identifierare i en annan hashtabell

//Metoder: get(int),size(),put(int, string), hashcode(), contains()

/** Our custom-made HashTableClass that fits the purpose of the assignment with its necessary methods to make it work
 * @author Leif Sulaiman
 * @author Jacob Spens
 */
public class HashTableClass implements Iterable<Node>{

	Node[] nodeArray;                  //array av noder, som inneh�ller "element"; allts� ett ord och "frequency"; allts� hur ofta ordet f�rekommer
	int sizeOfArray;                   //storleken av hashtabellen (hur m�nga noder den inneh�ller)          
	public int totalWords;             //totala antalet ord i filen

	
	/** The construct
	 *  Receives the size of the array
	 *  Multiplies it by 3 and makes a HashTableClass out of it
	 * @param Input : (array).length
	 */
	HashTableClass(int size){          //konstruktorn
		sizeOfArray = size*3;
		nodeArray = new Node[sizeOfArray];    
		Arrays.fill(nodeArray, null);  //fyller alla noder med null fr�n b�rjan
		if(size <= 0) {
			throw new RuntimeException("ERROR" + size);          //om storleken s�tts till 0 eller mindre: error
		}
	}
	
	/** Pushes all words from a String-array into a HashTableClass
	 *  Variable loadFactor is unused due to all words will be put in simultaneously
	 * @param Input: a String-array containing all the words from a file
	 */
	public void add(String[] elementsInArray) {         //VIKTIG add funktion
		totalWords = elementsInArray.length;				//totalWords = alla element/strängar som finns i filen
		float loadFactor;                               //antal anv�nda noder/alla noder  
		for(int i = 0; i < elementsInArray.length; i++) {
			String newElement = elementsInArray[i];     
			int arrayIndex = hashCode(newElement);     //v�r "sparningsalgoritm": elementet (String) till int modulus totala ord blir indexet
			while(nodeArray[arrayIndex]!= null) {                           //om noden redan �r upptagen: kolla n�sta nod
				if(contains(newElement)!=null) {                       //om ordet redan finns, �ka frekvensen med 1 (i noden)               
					contains(newElement).frequency++;  
					break;
				} 
				//System.out.println("Collision at: " + arrayIndex + ", regarding element: " + newElement);
				arrayIndex++;  
				if(arrayIndex>=sizeOfArray){
					arrayIndex%=sizeOfArray;
				}
			}
			if(nodeArray[arrayIndex]==null) {
			nodeArray[arrayIndex]=new Node(newElement);                       //spara elementet i noden
			nodeArray[arrayIndex].frequency++;                                //och �ka frekvensen med 1
			}

		}
	}
	
	/** Checks if a HashTableClass contains a node with the element equal to the input
	 *  If it doesn't find it at the appropriate hash-code it will check some steps further until an empty node is found
	 * @param Input : A word that is searched for
	 * @return Returns the node in which the element input was found
	 */
	public Node contains(String element) {                                  //kollar om ett element finns i hashtabellen och returnerar dess nod
		int index =hashCode(element);                 						 //enligt "add" algoritmen
		while(nodeArray[index] != null) {
			if(!nodeArray[index].element.equals(element)) {
				index++;
				if(index>=sizeOfArray){
					index%=sizeOfArray;
				}
				
			}
			else {
				break;
				}
		}
		return nodeArray[index];
	}
	
	
	/** Receives a word in form of a String and calculates the hash-code for it
	 *  Splits the word into a char-array then adds all ascii values for each character
	 * @param Input : a word (String)
	 * @return Returns the ascii values modulus the size of the array to avoid out of bound errors
	 */
	public int hashCode(String word) {
		char[] array = word.toCharArray();
		int ascii=0;
		for(int i=0; i<array.length; i++){
			ascii = ascii + array[i];
		}
		return ascii%sizeOfArray;
	}
	
	/** Prints each element in the HashTableClass in question, can also represent an iterator
	 */
	public void printTable() {
		System.out.println("Elements  "   + "Frequency   " + "HashCode   ");
		for(int i=0; i<nodeArray.length; i++) {
			if(nodeArray[i]!=null) {
				System.out.println(nodeArray[i].element + "       " + nodeArray[i].frequency+ "            " +  i);
			}
		}
	}
	
	/** A method made for the method hashCode and the class Main, used to check what size the HashTableClass has (how many nodes it has got)
	 * @return Returns the value of sizeOfArray
	 */
	public int size() {
		return sizeOfArray;
	}
	/**A Iterator for HashTableClass.
	 * @return Returns a iterator of type Node.
	 */
	@Override
	public Iterator<Node> iterator(){
		Iterator<Node> it = new Iterator<Node>(){
			
			private int currentIndex;

			@Override
			/**Method to see if the iterator has a next value.
			 * @return Returns true or false if the hashtable has another node.
			 */
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return currentIndex < sizeOfArray;
			}
			/**Method for getting the next node.
			 * @return returns a Node in the Hashtable.
			 */
			@Override
			public Node next() {
				return nodeArray[currentIndex++];
			}

			@Override
			public void remove() {
			}
		};
		return it;
	}
}

/** A class which uses unlinked nodes
 *  A node includes the word (String) and its frequency (int)
 * @author leif9
 */
class Node{                                                                 //v�r skr�ddarsydda nod-klass
	public String element;                                           //elementet �r ett ord
	public int frequency = 0;                                               //frekvensen �r hur ofta det f�rekommer

	public Node(String theElement){
		element = theElement;

	}
}




