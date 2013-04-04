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
	 	int count = 0;
	 	Node next = head;
	 	for(int i = 0; i != a; i++) {
	 		next = next.getNext();
	 		count++;
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
	 public int linearSearch

}


















