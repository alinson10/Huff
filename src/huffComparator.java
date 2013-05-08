import java.util.*;

public class huffComparator implements Comparator<HuffNode> {
	

	public int compare(HuffNode node1, HuffNode node2) {
		
		if (node1.getFreq() < node2.getFreq()){
			return -1;
		}
		if (node1.getFreq() > node2.getFreq()){
			return 1;
		}
		
		return 0;
	}



}
