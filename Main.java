class Main {
	

	public static void main(String[] args) {
		LinkedList a = new LinkedList();
		LinkedList b = new LinkedList();
		a.push(2);
		a.push(4);
		a.push(8);
		a.push(1);
		a.push(3);
		b.buildOneTwoThree();
		a.print();
		LinkedList[] c = a.frontBackSplit();
		c[0].print();
		c[1].print();

	}
}