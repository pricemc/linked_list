/**
 * LinkedList
 *
 * 		A data structure that stores a list of elements dynamically
 */
class LinkedList {

	private Node head;



	/**
	 * LinkedList
	 *
	 * 		Constructor that sets the Head node
	 */
	public LinkedList() {
		head = new Node();
	}

	public LinkedList(Node a) {
		head = a;
	}
	/**
	 * push
	 *
	 * 		Given an int, adds a new node at the head of the list with this value.
	 *
	 * @param value - value of the new node
	 */
	public void push(int value) {
		Node next = new Node(value, head.getNext());
		head.setNext(next);
	}

	public Node getHead() {
		return head;
	}

	/**
	 * length
	 *
	 * 		Returns the length of the list
	 * 	
	 * @return length of the list
	 */
	public int length() {
		int length = 0;
		Node next = head;
		while(next.getNext()!=null) {
			next = next.getNext();
			length++;
		}
		return length;
	}

	/**
	 * empty
	 *
	 * 		empties the list
	 */
	public void empty() {
		head.setNext(null);
	}

	/**
	 * buildOneTwoThree
	 *
	 * 		creates a list of {1, 2, 3}
	 */
	public void buildOneTwoThree() {
		empty();
		push(3);
		push(2);
		push(1);
	}

	/**
	 * print
	 *
	 * 		prints the list
	 */
	public void print() {
		Node next = head;
		while(next.getNext()!= null) {
			next = next.getNext();
			System.out.print(next.getValue()+" ");
		}
		System.out.println();
	}

	/**
	 * count
	 *
	 * 		counts the number of times a given number apears in the list
	 *
	 * @param a - number to check for
	 * @return the number of times a is in the list
	 */
	 public int count(int a) {
	 	int count = 0;
	 	Node next = head;
	 	while(next.getNext()!= null) {
	 		next = next.getNext();
	 		if(next.getValue() == a) count++;
	 	}
	 	return count;
	 }

	 /**
	  * getNth
	  *
	  * 	Given an index, return the value of the node at that index
	  *
	  * @param  a - index(int)
	  * @return the value of the node at that index
	  */
	 public int getNth(int a) {
	 	if(a>length()) return -1;
	 	Node next = head;
	 	for(int i = 0; i != a; i++) {
	 		next = next.getNext();
	 	}
	 	return next.getValue();
	 }

	 /**
	  * pop
	  *
	  * 	removes the first node from the list and returns its value
	  *
	  * @return the value of the first node
	  */
	 public int pop() {
	 	Node first = head.getNext();
	 	Node next = first.getNext();
	 	first.setNext(null);
	 	head.setNext(next);
	 	return first.getValue();
	 }

	 /**
	  * linearSearch
	  *
	  * 	Given an int n, return the index where it is found. Return -1 if it is not in the list.
	  *
	  * @param n - index(int)
	  * @return index where n is found
	  */
	 public int linearSearch(int n) {
	 	int index = 0;
	 	Node next = head;
	 	boolean found = false;
	 	while(next.getNext()!=null&&!found) {
	 		next = next.getNext();
	 		if(next.getValue() == n) return index;
	 		index++;
	 	}
	 	return 0;
	 }
	 /**
	  * insertNth
	  * @param n     index
	  * @param value value of the new node
	  */
	 public void insertNth(int n, int value) {
	 	int index = 0;
	 	Node next = head;
	 	Node current = head;
	 	if(n == 0) {
	 		push(value);
	 	}
	 	while(next.getNext()!= null&&index!=n) {
	 		next = next.getNext();
	 		index++;
	 		if(index == n) {
	 			current = next.getNext();
	 			next.setNext(new Node(value, current));
	 		}
	 	}
	 }

	 public void sortedInsert(int value){
		Node current = head.getNext();
		int index = 0;
		while(index<length() && current.getValue()<value){
			index++;
			if(current.getNext() != null){
				current = current.getNext();
			}
		}
		insertNth(index, value);
	}

	public void insertSort() {
		int[] listValues = new int[length()];
		Node next = head.getNext();
		int index = 0;
		while(index<length()) {
			listValues[index] = next.getValue();
			index++;
			next = next.getNext();
		}
		empty();
		for(int i = 0; index < length(); i++) {
			sortedInsert(listValues[i]);
		}
	}

	public void append(LinkedList as) {
		Node next = head;
		while(next.getNext()!=null) {
			next = next.getNext();
		}
		next.setNext(as.getHead().getNext());
	}

	public LinkedList[] frontBackSplit() {
		LinkedList[] a = new LinkedList[2];
		int length = length()/2;
		int index = 0;
		Node next = head;
		if(head.getNext()==null) {
			return a;
		}
		while(index<length) {
			a[0].push(next.getValue());
			next = next.getNext();
			index++;
		}
		while(index<length()) {
			a[1].push(next.getValue());
			next = next.getNext();
			index++;
		}
		return a;
	}


}


















