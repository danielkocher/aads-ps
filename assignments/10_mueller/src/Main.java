







public class Main {
	public static void main(String[] args){
		FibonacciHeap<FibonacciHeapNode> fibHeap=new FibonacciHeap<FibonacciHeapNode>();
		Node<FibonacciHeapNode> node=fibHeap.insert(new FibonacciHeapNode(5));
		fibHeap.offer(new FibonacciHeapNode(0));
		fibHeap.offer(new FibonacciHeapNode(3));
		fibHeap.delete(node);
		
		
		
		
		
	}
}
