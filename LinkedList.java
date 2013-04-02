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

}


















