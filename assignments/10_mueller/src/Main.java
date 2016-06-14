import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		FibonacciHeap<FibonacciHeapNode> fibHeap = new FibonacciHeap<FibonacciHeapNode>();
		Node<FibonacciHeapNode> node = fibHeap.insert(new FibonacciHeapNode(5));
		
		int n = (int) Math.pow(10, 4);
		Random rand = new Random();
		int randomIntegers[] = new int[n];
		for (int i = 0; i < n; i++)
			randomIntegers[i] = rand.nextInt(100);
			
		// FibHeap
		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++)
			fibHeap.offer(new FibonacciHeapNode(randomIntegers[i]));
		System.out.println(System.currentTimeMillis() - start);

	
			
//		fibHeap = new FibonacciHeap<FibonacciHeapNode>();
//		start = System.currentTimeMillis();
//		for (int i = 0; i < n / 2; i++)
//			fibHeap.offer(new FibonacciHeapNode(randomIntegers[i]));
//		for (int i = 0; i < n / 2; i++)
//			fibHeap.poll();
//		System.out.println(System.currentTimeMillis() - start);
//		
		fibHeap = new FibonacciHeap<FibonacciHeapNode>();
		
		for (int i = 0; i < n; i++)
			fibHeap.offer(new FibonacciHeapNode(randomIntegers[i]));
		start = System.currentTimeMillis();
		for (int i = 0; i < n; i++){
			System.out.println(fibHeap.poll());
		}
		System.out.println(System.currentTimeMillis() - start);
//
//		fibHeap = new FibonacciHeap<FibonacciHeapNode>();
//		start = System.currentTimeMillis();
//		for (int i = 0; i < n / 2; i++)
//			fibHeap.offer(new FibonacciHeapNode(randomIntegers[i]));
//		for (int i = 0; i < n / 2; i++) {
//			if (i % 2 == 0)
//				fibHeap.offer(new FibonacciHeapNode(randomIntegers[i
//						+ (int) (n / 2)]));
//			else
//				fibHeap.poll();
//		}
//		


		// PriorityQueue

	}

}
