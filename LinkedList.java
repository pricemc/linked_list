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
	 * 		
	 */
	public void push(int value) {
		Node next = new Node(value, head.getNext());
		length++;
		head.setNext(next);
	}

	public int length() {
		return length;
	}

	

}