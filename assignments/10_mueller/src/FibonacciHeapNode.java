
public class FibonacciHeapNode implements HeapEntry {

	public int entry;
	public double key;

	
	public FibonacciHeapNode(int entry, double key){
		this.entry=entry;
		this.key=key;
	
	}
	
	public FibonacciHeapNode(double key){
		this.key=key;
		
	}
	@Override
	public double getKey() {
		return key;
	}

	@Override
	public void setKey(double key) {
		this.key=key;
		
	}
	
	public String toString(){
		return key+":"+entry;
	}

}
