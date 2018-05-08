import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;


//Spara alla java nyckelord i en hashtabell
//Spara varje unik identifierare i en annan hashtabell

//Metoder: get(int),size(),put(int, string), hashcode(), contains()
public class HashTableClass {

	Node[] nodeArray;                  //array av noder, som inneh�ller "element"; allts� ett ord och "frequency"; allts� hur ofta ordet f�rekommer
	int sizeOfArray;                   //storleken av hashtabellen (hur m�nga noder den inneh�ller)          
	public int totalWords;             //totala antalet ord i filen

	HashTableClass(int size){          //konstruktorn
		sizeOfArray = size;
		nodeArray = new Node[size];    
		Arrays.fill(nodeArray, null);  //fyller alla noder med null fr�n b�rjan
		if(size <= 0) {
			throw new RuntimeException("ERROR" + size);          //om storleken s�tts till 0 eller mindre: error
		}
	}

	public void add(String[] elementsInArray) {         //VIKTIG add funktion
		float loadFactor;                               //antal anv�nda noder/alla noder  
		for(int i = 0; i < elementsInArray.length; i++) {
			String newElement = elementsInArray[i];     
			int arrayIndex = Integer.parseInt(newElement) % totalWords;     //v�r "sparningsalgoritm": elementet (String) till int modulus totala ord blir indexet
			while(nodeArray[arrayIndex]!= null) {                           //om noden redan �r upptagen: kolla n�sta nod
				if(this.contains(newElement)!=null) {                       //om ordet redan finns, �ka frekvensen med 1 (i noden)               
					this.contains(newElement).frequency++;                      
				}
				arrayIndex++;                      
			}
			nodeArray[arrayIndex].element=newElement;                       //spara elementet i noden
			nodeArray[arrayIndex].frequency++;                              //och �ka frekvensen med 1
		}
		int counter=0;
		for(int i = 0; i<nodeArray.length; i++) {
			if(nodeArray[i]!=null) {
				counter++;                                                  //r�knar hur m�nga noder som �r upptagna
			}
		}
		loadFactor = counter/totalWords;                                    //loadFactor: antal upptagna noder/totala antalet noder
		if(loadFactor >= 0.5) {                                             //om h�lften eller fler av noderna �r upptagna, dubbla storleken p� hashtabellen f�r att unvika kollision
			Node[] temp = new Node[nodeArray.length*2]; 
			System.arraycopy( nodeArray, 0, temp, 0, nodeArray.length);
			nodeArray = new Node[temp.length];
			System.arraycopy(temp, 0, nodeArray, 0, temp.length);
			sizeOfArray = nodeArray.length;
		}
	}

	public Node contains(String element) {                                  //kollar om ett element finns i hashtabellen och returnerar dess nod
		int index =Integer.parseInt(element) % totalWords;                  //enligt "add" algoritmen
		while(nodeArray[index].element != element) {
			index++;
		}
		return nodeArray[index];
	}

	
	public int hashCode(String word) {
		return Integer.parseInt(word) % totalWords;
	}
	
	
	
}

class Node{                                                                 //v�r skr�ddarsydda nod-klass
	public String element = null;                                           //elementet �r ett ord
	public int frequency = 0;                                               //frekvensen �r hur ofta det f�rekommer

	public Node(String theElement){
		element = theElement;
	}
}




