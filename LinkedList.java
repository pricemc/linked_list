/**
 * LinkedList
 *
 * 		A data structure that stores a list of elements dynamically
 */
class LinkedList {

	private Node head;
	private int length;



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
		length++;
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
		return length;
	}

	public void empty() {
		head.setNext(null);
		length=0;
	}

	public void buildOneTwoThree() {
		empty();
		push(1);
		push(2);
		push(3);
	}

}