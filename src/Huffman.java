import java.util.*;
import java.io.*;

public class Huffman <E extends Comparable<E>> {

	public static int count11;

	public static void main (String args[]){

		//Hashtable holding keywords
		Hashtable<String, Word> keywords = new Hashtable <String, Word> ();
		ArrayList <String> date = new ArrayList <String> ();
		int sum = 0;
		double sum2 = 0;
		BSTHuff temp = null;


		//All Speeches
		String allSpeech = "speechdata";

		//Choose directory
		String directory = "old300";
		String outFile = directory + ".out";


		/*
		 * 
		 * Put all words for all speeches into keywords
		 * 
		 */

		File folder0 = new File(allSpeech);
		File[] allFiles = folder0.listFiles();
		Scanner stdin0;

		for(File inFile: allFiles){
			System.out.println("Loading all speeches...");
			if(!inFile.exists() || !inFile.canRead()){
				System.out.println("Error with file:" +inFile.getName());
				System.exit(-1);
			}
			try{

				stdin0 = new Scanner(inFile);

				//Add all words from all speeches to keywords, make frequency 1
				smoothing(stdin0, keywords);

			}
			catch(FileNotFoundException e){
				System.out.println("File Not Found Dude");
			}
		}







		/*
		 * 
		 *	Find frequency using set of speeches. Build Huff tree from set
		 * 
		 */
		System.out.println("Loading subset...");
		File folder = new File(directory);
		File[] listFiles = folder.listFiles();
		Scanner stdin;
		for(File inFile: listFiles){

			if(!inFile.exists() || !inFile.canRead()){
				System.out.println("Error with file:" +inFile.getName());
				System.exit(-1);
			}
			try{


				//Add date name
				//date.add(inFile.toString());



				stdin = new Scanner(inFile);

				//parse the file
				parse(stdin, keywords);

				//Construct Huff Tree using words in a set of speeches
				temp = contructHuff (keywords);

			}

			catch(FileNotFoundException e){
				System.out.println("File Not Found Dude");
			}

		}

		/*
		 * 
		 *	Find compression of all speeches with the huff tree made of subset
		 * 
		 */

		insertHuffSize(keywords, temp);
		
		
		
		
		
		try{
			PrintWriter out = new PrintWriter(outFile);
			PrintWriter dates = new PrintWriter("dates.txt");
			for(File inFile: allFiles){

				if(!inFile.exists() || !inFile.canRead()){
					System.out.println("Error with file:" +inFile.getName());
					System.exit(-1);
				}
				try{
					count11 =0;
					stdin = new Scanner(inFile);

					sum = getHuffSize(stdin, keywords);
					stdin = new Scanner(inFile);

					//count = count(stdin);

					sum2 = getBlockSize(keywords, count11);

					double ratio = sum/sum2;
					
					String[] tokens = inFile.getName().split("[ _.]+");
					dates.println(tokens[1] + "/" + tokens[2] + "/" + tokens[0]);
					
					
					System.out.println("Processing: " + inFile.toString());
					/*
					System.out.println("Huff: "+ sum);
					System.out.println("Block: "+ sum2);
					System.out.println("Ratio: " + (sum/sum2));
					*/
					
					out.println(ratio);

				}
				catch(FileNotFoundException e){
					System.out.println("File Not Found Dude");
				}
			}
			out.close();
			dates.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found Dude");
		}
		
		System.out.println("----------*Finished*----------");
	}




	public static double getBlockSize(Hashtable<String, Word> keywords, int count){
		double sum2 = 0;

		sum2 = (Math.log(keywords.size())/Math.log(2)) * count;

		return sum2;
	}

	/*
	public static int count(Scanner stdin){
		int count = 0;
		while(stdin.hasNext()){
			stdin.next();

			count ++;

		}
		return count;
	}
	 */

	public static void insertHuffSize(Hashtable<String, Word> keywords, BSTHuff temp){
		
		Enumeration <String> enumKey = keywords.keys();
		
		while(enumKey.hasMoreElements()){
			System.out.println("Defining Huff Bit Size for each word...");
			
			String key = enumKey.nextElement();
			Word val = keywords.get(key);

			val.setBit(temp.getBitSize(key, temp.getRoot()));
			temp.reset();

		}
		
	}
	
	public static int getHuffSize(Scanner stdin, Hashtable<String, Word> keywords){

		int sum = 0;
		while(stdin.hasNext()){
			String word = stdin.next();

			count11 ++;

			//Adding (the bits for a certain word) 
			sum = sum + keywords.get(word).getBit();

		}
		return sum;
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


		HuffNode temp4 = (HuffNode)pq.remove();
		BSTHuff temp5 = new BSTHuff(temp4);

		return temp5;

	}

	public static void smoothing(Scanner in, Hashtable<String, Word> keywords){


		while(in.hasNext()){
			String word = in.next();
			Word curr = new Word(word);

			if(keywords.get(word) == null){
				keywords.put(word, curr);
				keywords.get(word).incFreq();

			}
			else{
				//If it is in the table, dont do anything
			}
		}

	}







}
