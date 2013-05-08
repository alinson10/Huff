
public class Word  {
	
	private String key;
	private int freq = 1;
	private int bitSize;
	
	public Word(String key){
		this.key = key;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public int getFreq(){
		return this.freq;
	}
	
	public void incFreq(){
		this.freq ++;
	}

}
