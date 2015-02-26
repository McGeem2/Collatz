import java.util.ArrayList;
import java.util.Iterator;


public class LongestCollatz {

	public int findLongest(int i) {
		int[] a = {10,9,8,7,6,5,4,3,2,1};
		Collatz c = new Collatz(10);
		for(int x : a){
			ArrayList<Integer> seq = new ArrayList();
			c = new Collatz(x);
			Iterator it = c.iterator();
			while(it.hasNext())
			{
				seq.add((Integer) it.next());
				
			}
			it = seq.iterator();
			while(it.hasNext()){
				
				
			}
			
		}
		
		
		
		return 0;
	}

}
