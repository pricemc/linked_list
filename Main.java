class Main {
	

	public static void main(String[] args) {
		LinkedList a = new LinkedList();
		a.push(2);
		a.push(4);
		a.push(8);
		a.push(1);
		a.push(3);
		a.print();
		a.insertSort();
		a.print();
		a.removeDuplicates();

	}
}