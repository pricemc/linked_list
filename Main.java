class Main {
	

	public static void main(String[] args) {
		LinkedList a = new LinkedList();
		LinkedList b = new LinkedList();
		a.push(4);
		a.push(3);
		a.push(2);
		a.push(1);
		b.push(6);
		b.push(6);
		b.push(6);
		b.push(6);
		a.print();
		b.print();
		LinkedList c = a.sortedMerge(a,b);
		c.print();

	}
}