class Main {
	

	public static void main(String[] args) {
		LinkedList a = new LinkedList();
		a.buildOneTwoThree();
		a.print();
		a.sortedInsert(2);
		a.sortedInsert(5);
		a.sortedInsert(3);
		a.sortedInsert(1);
		a.sortedInsert(3);
		a.print();
	}
}