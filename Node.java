/**
 * Node
 *
 * 		A member of a linked list. Holds an integer value
 * 		 and a link to another node
 */

public class Node {

	private int value;
	private Node next;

	public Node(int value, Node next) {
		this.value = value;
		this.next = next;
	}

	public Node() {
		this(0, null);
	}

	public Node(int value) {
		this(value, null);
	}


	public int getValue() {
		return value;
	}

	public Node getNext() {
		return next;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
}