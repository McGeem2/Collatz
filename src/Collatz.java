import java.util.Iterator;
import java.util.Scanner;

public class Collatz implements Iterator{
	
	public int current;
	
	
	
	public Collatz(int start) {
		this.current = start;
	}

	@Override
	public boolean hasNext(){
		
		if(current <= 0){
			
			throw new CollatzOutOfBoundsException();
		}
		
		if(current >= 2){
			return true;
		}
		else{
			return false;
		}
		
		
	}

	@Override
	public Object next() {
		if(current<=1){
			
			throw new CollatzOutOfBoundsException();
			
		}
		
		if(current%2 == 0){
			current = current/2;
			
		}else{
			
			current = 3*current + 1;
		}
		return current;
	}

	public Iterator iterator() {
		return this;
	}
	
	
	
}