import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;


public class FibonacciHeap<E extends HeapEntry> extends AbstractQueue<E> {
	Node<E> min;
	int size;

	// add new element
	@Override
	public boolean offer(E e) {
		if (min == null)
			min = new Node<E>(e);
		else
			min = mergeLists(min, new Node<E>(e));
		size++;
		return true;
	}

	// Delete min
	@Override
	public E poll() {
		Node<E> m = min;
		if (min == null)
			return null;

		if (min.right == min)
			min = null;
		else {

			// Delete min from rootlist
			min.left.right = min.right;
			min.right.left = min.left;
			// not really the minimum; just a pointer to random element of the
			// rootlist
			min = min.right;

			// add all the children of min into the rootlist
			// prepare children of old min by removing the parent pointer;
			// iterate over childrens till you see the child of the old min
			// again
			if (m.child != null) {
				Node<E> c = m.child.right;
				while (c != m.child) {
					c.parent = null;
					c = c.right;
				}
				// remove the parent pointer of child node
				c.parent = null;
			}
		}
		// now add all the children of min into the rootlist
		min = mergeLists(min, m.child);
		if (min == null)
			return m.item;
		consolidate();

		size--;
		// we do not want to return the current minimum of the Heap but the
		// removed one
		return m.item;
	}

	public void consolidate() {
		// visit all the elements of the rootlist
		ArrayList<Node<E>> a = new ArrayList<Node<E>>();
		int sizeOfColArray = (int) Math.ceil(2 * (Math.log10(size) / Math
				.log10(2)));

		for (int i = 0; i < sizeOfColArray; i++)
			a.add(null);
		ArrayList<Node<E>> toVisit = toList(min);
		for (int i = 0; i < toVisit.size(); i++) {
			Node<E> c = toVisit.get(i);
			// as long as there exists a tree with the same degree as the
			// current degree; merge those trees
			while (true) {
				if (a.get(c.degree) == null) {
					a.set(c.degree, c);
					break;
				}
				// we already have a tree of this degree => merge the trees
				Node<E> old = a.get(c.degree);
				// free position
				a.set(c.degree, null);
				// now we have to link the two trees that share the same degree
				// (old and c)
				c = link(c, old);
			}
			if (c.getKey() < min.getKey())
				min = c;

		}
		// there is no need to touch every element of the root list again to
		// find the minimum
	}

	// return min
	@Override
	public E peek() {
		return (E) min.item;
	}

	public void decreaseKey(E node, double newKey) {

	}

	public void delete(E node) {

	}

	// append second list to first one
	public Node<E> mergeLists(Node<E> first, Node<E> second) {
		if (first == null && second == null)
			return null;
		if (first == null && second != null)
			return second;
		if (first != null && second == null)
			return first;

		Node<E> cNext = first.right;
		first.right = second.right;
		first.right.left = first;
		second.right = cNext;
		second.right.left = second;

		return first.getKey() < second.getKey() ? first : second;
	}

	// links to trees (both roots are part of the root list*) of same degree  by appending the bigger root to the smaller on (as a child)
	// * also takes care of the obvious necessity to remove the root with the bigger key from the rootlist of the fibonacci heap
	public Node<E> link(Node<E> first, Node<E> second) {
		// determine which tree has the smaller root;
		Node<E> smallerRoot, biggerRoot;
		if (first.getKey() < second.getKey()) {
			smallerRoot = first;
			biggerRoot = second;
		} else {
			smallerRoot = second;
			biggerRoot = first;
		}
		// remove the biggerRoot from the rootlist
		biggerRoot.left.right = biggerRoot.right;
		biggerRoot.right.left = biggerRoot.left;
		biggerRoot.right = biggerRoot;
		biggerRoot.left = biggerRoot;

		// just append the bigger root to the child of the smaller root
		smallerRoot.child = mergeLists(smallerRoot.child, biggerRoot);
		// bigger Root is now the child of smaller root => set parent of
		// biggerRoot
		biggerRoot.parent = smallerRoot;
		biggerRoot.isMarked = false;

		smallerRoot.degree++;
		return smallerRoot;

	}

	public ArrayList<Node<E>> toList(Node<E> start) {
		// Use ArrayList to deal with resizing.
		ArrayList<Node<E>> al = new ArrayList<Node<E>>();
		Node<E> currentElement = start.right;
		while (currentElement != start) {
			al.add(currentElement);
			currentElement = currentElement.right;
		}
		al.add(currentElement);
		return al;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public class Node<E extends HeapEntry> implements HeapEntry {

		E item;
		public Node<E> left;
		public Node<E> right;
		public Node<E> child;
		public Node<E> parent;

		public boolean isMarked;
		public int degree;

		public double key;

		/**
		 * Constructs a new node. Uses relaxed write because item can only be
		 * seen after publication via casNext.
		 */
		Node(E item) {
			this.item = item;
			left = this;
			right = this;
		}

		@Override
		public double getKey() {

			return item.getKey();
		}

		@Override
		public void setKey(double key) {
			item.setKey(key);

		}

	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
