



public class Main {
	public static void main(String[] args){
		FibonacciHeap<FibonacciHeapNode> fibHeap=new FibonacciHeap<FibonacciHeapNode>();
		fibHeap.offer(new FibonacciHeapNode(5));
		fibHeap.offer(new FibonacciHeapNode(0));
		System.out.println(fibHeap.poll());
		
		
//		ArrayList<FibonacciHeapNode> nodes=fibHeap.toArrayList();
//		for (int i=0;i<nodes.size();i++){
//			FibonacciHeapNode cNode=nodes.get(i);
//			System.out.println("node: "+cNode.getKey());
//		}
//		System.out.println(fibHeap.peek().getKey());
		
//		ConcurrentLinkedQueue<FibonacciHeapNode> queue=new ConcurrentLinkedQueue<FibonacciHeapNode>();
//		queue.offer(new FibonacciHeapNode(5));
//		queue.offer(new FibonacciHeapNode(6));
//		queue.offer(new FibonacciHeapNode(1));
//		System.out.println(queue.peek().getKey());
//		PriorityQueue<Integer> queue =new PriorityQueue<Integer>();
//		queue.offer(5);
//		queue.offer(6);
//		queue.offer(1);
//		System.out.println(queue.peek());
		
	}
}
