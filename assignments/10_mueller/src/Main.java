import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		FibonacciHeap<FibonacciHeapNode> fibHeap = new FibonacciHeap<FibonacciHeapNode>();
		PriorityQueue<Integer> prioQueue=new PriorityQueue<Integer>();
		
		int n = (int) Math.pow(10, 7);
		Random rand = new Random();
		int randomIntegers[] = new int[n];
		for (int i = 0; i < n; i++)
			randomIntegers[i] = rand.nextInt(100);
			
		// FibHeap
		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++)
			fibHeap.offer(new FibonacciHeapNode(randomIntegers[i]));
		System.out.println(System.currentTimeMillis() - start);

		//PriorityQueue
		start = System.currentTimeMillis();
		for (int i = 0; i < n; i++)
			prioQueue.offer(randomIntegers[i]);
		System.out.println(System.currentTimeMillis() - start);
		
		//FibHeap
		fibHeap = new FibonacciHeap<FibonacciHeapNode>();
		start = System.currentTimeMillis();
		for (int i = 0; i < n / 2; i++)
			fibHeap.offer(new FibonacciHeapNode(randomIntegers[i]));
		for (int i = 0; i < n / 2; i++)
			fibHeap.poll();
		System.out.println(System.currentTimeMillis() - start);
		
		//PriorityQueue
		prioQueue=new PriorityQueue<Integer>();
		start = System.currentTimeMillis();
		for (int i = 0; i < n / 2; i++)
			prioQueue.offer(randomIntegers[i]);
		for (int i = 0; i < n / 2; i++)
			prioQueue.poll();
		System.out.println(System.currentTimeMillis() - start);
		


	}

}
