
public class BSTHuff <E extends Comparable<E>>{
	
	private HuffNode<E> root;
	private int count;
	private boolean found = false;
	
	
	public BSTHuff(HuffNode root) {
		this.root = root;
	}
	
	public HuffNode getRoot(){
		return this.root;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public void reset(){
		this.count = 0;
		this.found = false;
	}
	
	public int getBitSize(String key, HuffNode curRoot){
		int count1 = 0;
		if(curRoot == null){
			return  0;
		}
		
		if(curRoot.getKey().equals(key)){
			found = true;
			return 0;
		}
		
		//Recursion
		count1 = getBitSize(key, curRoot.getLeft());
		
		if(!found){
				count1 = getBitSize(key, curRoot.getRight());
		}
		
		if (found){
			return count1 + 1;
		}
		
		return count1;
	}

}
