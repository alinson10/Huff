import java.util.*;
import java.io.*;

public class Huffman <E extends Comparable<E>> {


	public static void main (String args[]){


		Hashtable<String, Word> keywords = new Hashtable <String, Word> ();
		process("recent1",keywords);
		BSTHuff temp = contructHuff (keywords);
		
		smoothing(keywords);

		/*
		int sum = 0;

		System.out.println(temp.getBitSize("the", temp.getRoot()));

		Enumeration <String> enumKey = keywords.keys();

		while(enumKey.hasMoreElements()){
			String key = enumKey.nextElement();
			Word val = keywords.get(key);

			sum = sum + val.getFreq();			
		}
		 */
		//System.out.println(sum);
		//System.out.println(temp.getRoot().getFreq());
	}



	public static void process(String foldername, Hashtable<String, Word> keywords){

		File folder = new File(foldername);
		File[] listFiles = folder.listFiles();
		Scanner stdin;
		for(File inFile: listFiles){
			if(!inFile.exists() || !inFile.canRead()){
				System.out.println("Error with file:" +inFile.getName());
				System.exit(-1);
			}
			try{
				stdin = new Scanner(inFile); 
				parse(stdin, keywords);
			}
			catch(FileNotFoundException e){
				System.out.println("File Not Found Dude");
			}



		}


	}

	public static void parse(Scanner in, Hashtable<String, Word> keywords){

		while(in.hasNext()){
			String word = in.next();
			Word curr = new Word(word);
			if(keywords.get(word) == null){
				keywords.put(word, curr);
			}
			else{
				keywords.get(word).incFreq();
			}
		}
	}

	public static BSTHuff contructHuff(Hashtable<String, Word> keywords){


		Enumeration <String> enumKey = keywords.keys();

		ArrayList <HuffNode> roots = new ArrayList <HuffNode> ();


		huffComparator pqc = new huffComparator();
		PriorityQueue pq = new PriorityQueue( 5, pqc);


		while(enumKey.hasMoreElements()){
			String key = enumKey.nextElement();
			Word val = keywords.get(key);

			//Make a huffnode for every key word
			HuffNode  curr = new HuffNode(key, 1, val.getFreq());
			pq.add(curr);

		}


		while(pq.size() > 1){


			//Remove First Lowest
			HuffNode temp1 = ((HuffNode) pq.poll());
			//Remove Second Lowest
			HuffNode temp2 = ((HuffNode) pq.poll());



			int sizeT = temp1.getFreq() + temp2.getFreq();

			HuffNode temp3 = new HuffNode("THE_COUNT", 0, sizeT);
			temp3.setL(temp1);
			temp3.setR(temp2);

			pq.add(temp3);
		}

		System.out.println(((HuffNode)pq.peek()).getKey());
		HuffNode temp4 = (HuffNode)pq.remove();
		BSTHuff temp5 = new BSTHuff(temp4);

		return temp5;

	}

	public static void smoothing(Hashtable<String, Word> keywords){
		Enumeration <String> enumKey = keywords.keys();

		while(enumKey.hasMoreElements()){
			String key = enumKey.nextElement();
			Word val = keywords.get(key);
			val.incFreq();
		}
		
	}







}
