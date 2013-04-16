public class TestableLinkedList extends LinkedList {

	public TestableLinkedList() {
		super();
	}

	public TestableLinkedList(int[] a) {
		super();
		for (int i=a.length-1;i>=0;i--) {
			this.push(a[i]);
		}
	}

	public boolean equals(LinkedList other) {
		if (this.getLength() != other.getLength()) {
			return false;
		}

		Node curr1 = this.getHead().getNext();
		Node curr2 = other.getHead().getNext();
		while (curr1 != null && curr2 != null) {
			if (curr1.getValue() != curr2.getValue()) {
				return false;
			}
			curr1 = curr1.getNext();
			curr2 = curr2.getNext();
		}

		return true;
	}

	// public static void main(String[] args) {
	// 	int[] a = {3, 4, 6, 1};
	// 	TestableLinkedList list = new TestableLinkedList(a);
	// 	list.print();

	// 	int[] b = {3, 4, 6, 1};
	// 	TestableLinkedList list2 = new TestableLinkedList(b);
	// 	list2.print();

	// 	int[] c = {1,2,3};
	// 	TestableLinkedList list3 = new TestableLinkedList(c);
	// 	list3.print();

	// 	int[] d = {1,2,3,4};
	// 	TestableLinkedList list4 = new TestableLinkedList(d);
	// 	list4.print();

	// 	System.out.println(list.equals(list2));
	// 	System.out.println(list.equals(list3));
	// 	System.out.println(list.equals(list4));


	// }

}