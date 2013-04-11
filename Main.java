class Main {
	

	public static void main(String[] args) {
		LinkedList a = new LinkedList();
		a.buildOneTwoThree();
		a.print();
		System.out.println(a.pop());
		a.print();
		a.insertNth(0, 4);
		a.print();
	}
}