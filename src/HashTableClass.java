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

	Node[] nodeArray;                  //array av noder, som innehåller "element"; alltså ett ord och "frequency"; alltså hur ofta ordet förekommer
	int sizeOfArray;                   //storleken av hashtabellen (hur många noder den innehåller)          
	public int totalWords;             //totala antalet ord i filen

	HashTableClass(int size){          //konstruktorn
		sizeOfArray = size;
		nodeArray = new Node[size];    
		Arrays.fill(nodeArray, null);  //fyller alla noder med null från början
		if(size <= 0) {
			throw new RuntimeException("ERROR" + size);          //om storleken sätts till 0 eller mindre: error
		}
	}

	public void add(String[] elementsInArray) {         //VIKTIG add funktion
		float loadFactor;                               //antal använda noder/alla noder  
		for(int i = 0; i < elementsInArray.length; i++) {
			String newElement = elementsInArray[i];     
			int arrayIndex = Integer.parseInt(newElement) % totalWords;     //vår "sparningsalgoritm": elementet (String) till int modulus totala ord blir indexet
			while(nodeArray[arrayIndex]!= null) {                           //om noden redan är upptagen: kolla nästa nod
				if(this.contains(newElement)!=null) {                       //om ordet redan finns, öka frekvensen med 1 (i noden)               
					this.contains(newElement).frequency++;                      
				}
				arrayIndex++;                      
			}
			nodeArray[arrayIndex].element=newElement;                       //spara elementet i noden
			nodeArray[arrayIndex].frequency++;                              //och öka frekvensen med 1
		}
		int counter=0;
		for(int i = 0; i<nodeArray.length; i++) {
			if(nodeArray[i]!=null) {
				counter++;                                                  //räknar hur många noder som är upptagna
			}
		}
		loadFactor = counter/totalWords;                                    //loadFactor: antal upptagna noder/totala antalet noder
		if(loadFactor >= 0.5) {                                             //om hälften eller fler av noderna är upptagna, dubbla storleken på hashtabellen för att unvika kollision
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

}

class Node{                                                                 //vår skräddarsydda nod-klass
	public String element = null;                                           //elementet är ett ord
	public int frequency = 0;                                               //frekvensen är hur ofta det förekommer

	public Node(String theElement){
		element = theElement;
	}
}




