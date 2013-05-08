
public class BSTNode<E> {

	private E key;
	private int freq = 0;
	private BSTNode<E> leftC;
	private BSTNode<E> RightC;

	public BSTNode(E key){
		this.key = key;
	}

	public void setKey(E key){
		this.key = key;
	}

	public void incFreq(){
		this.freq ++;
	}
	
	public E getKey(E key){
		return this.key;
	}

	public BSTNode<E> getLeft(){
		return this.leftC;
	}

	public BSTNode<E> getRight(){
		return this.RightC;
	}
	
	public void setL( BSTNode<E> left){
		this.leftC = left;
	}
	
	public void setR( BSTNode<E> right){
		this.RightC = right;
	}
}
