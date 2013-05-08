
public class Word  {
	
	private String key;
	private int freq = 0;
	private int bitSize;
	
	public Word(String key){
		this.key = key;
	}
	
	public void setBit(int bitSize){
		this.bitSize = bitSize;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public int getFreq(){
		return this.freq;
	}
	
	public int getBit(){
		return this.bitSize;
	}
	
	public void incFreq(){
		this.freq ++;
	}

}
