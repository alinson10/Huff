
public class HuffNode <E> {
	private E key;
	private int type; //1 is letter 0 is holder
	private int freq = 0;
	private HuffNode<E> leftC;
	private HuffNode<E> RightC;

	public HuffNode(E key){
		this.key = key;
	}
	
	public HuffNode(E key, int type, int freq){
		this.key = key;
		this.freq = freq;
		this.type = type;
	}

	public void setKey(E key){
		this.key = key;
	}
	
	public void setFreq(int freq){
		this.freq = freq;
	}
	
	public int getFreq(){
		return this.freq;
	}
	
	public void incFreq(){
		this.freq ++;
	}

	public E getKey(){
		return this.key;
	}
	
	
	

	public HuffNode<E> getLeft(){
		return this.leftC;
	}

	public HuffNode<E> getRight(){
		return this.RightC;
	}

	public void setL( HuffNode<E> left){
		this.leftC = left;
	}

	public void setR( HuffNode<E> right){
		this.RightC = right;
	}
}
