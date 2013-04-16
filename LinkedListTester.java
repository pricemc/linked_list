import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.concurrent.*;
import java.util.*;

public class LinkedListTester extends Tester {

	@Test
	public void	m00_constructor() {
		System.out.println("constructor");
		System.out.println("-----------");

		String message = "should create a list";
		try {
			TestableLinkedList list = new TestableLinkedList();
			checkNotNull(list, message);
		} catch (Exception e) {
			failure("should not crash");
			fail(e.getMessage());
		}
	}

	@Test 
	public void m01_push() {
		String methodName = "push";
		int methodNumber = 1;
		Class[] methodArgs = new Class[]{int.class};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();
		
		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list, 1);
				int [] a ={1};
				TestableLinkedList test = new TestableLinkedList(a);
				check(test.equals(list), message);
			}
		}.message("should work on an empty list"));
		
		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1});
				method.invoke(list, 3); // push
				method.invoke(list, 2); // push
				method.invoke(list, 4); // push
				method.invoke(list, 2); // push

				TestableLinkedList test = new TestableLinkedList(new int[]{2, 4, 2, 3, 1});
				check(test.equals(list), message);
			}
		}.message("should work on a non-empty list"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m02_length() {
		String methodName = "length";
		int methodNumber = 2;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();
		
		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				check(((Integer)method.invoke(list)).intValue() == 0, message);	
			}
		}.message("should be 0 for an empty list"));
		
		tests.add(new MethodTest() {
			public void run() throws Exception{
				int [] a ={1, 2, 3};
				TestableLinkedList test = new TestableLinkedList(a);
				check(((Integer)method.invoke(test)).intValue() == 3, message);
			}
		}.message("should be 3 for {1, 2, 3}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m03_buildOneTwoThree() {
		String methodName = "buildOneTwoThree";
		int methodNumber = 3;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list); // build123
				int [] a ={1, 2, 3};
				TestableLinkedList test = new TestableLinkedList(a);
				check(test.equals(list), message);
			}
		}.message("should work on an empty list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{3,3,3,3});
				method.invoke(list); // build123
				int [] a ={1, 2, 3};
				TestableLinkedList test = new TestableLinkedList(a);
				check(test.equals(list), message); // build123
			}
		}.message("should work on an non-empty list"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test
	public void	m04_print() {
		String methodName = "print";
		int methodNumber = 4;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list);
				success(message);
			}
		}.message("should print an empty list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list);
				success(message);
			}
		}.message("should print a non-empty list"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m05_count() {
		String methodName = "count";
		int methodNumber = 5;
		Class[] methodArgs = new Class[]{int.class};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				check(((Integer)method.invoke(list, 0)).intValue() == 0, message);
			}
		}.message("should be 0 for an empty list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{3, 2, 3, 3, 2, 3});
				check(((Integer)method.invoke(list, 3)).intValue() == 4, message);
			}
		}.message("should be 4 for (3) -> {3, 2, 3, 3, 2, 3}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m06_getNth() {
		String methodName = "getNth";
		int methodNumber = 6;
		Class[] methodArgs = new Class[]{int.class};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();
		
		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				check(((Integer)method.invoke(list, 0)).intValue() == 1, message);
			}
		}.message("should be 1 for (0) -> {1,2,3}"));
		
		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				check(((Integer)method.invoke(list, 1)).intValue() == 2, message);
			}
		}.message("should be 2 for (1) -> {1,2,3}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				check(((Integer)method.invoke(list, 2)).intValue() == 3, message);
			}
		}.message("should be 3 for (2) -> {1,2,3}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m07_empty() {
		String methodName = "empty";
		int methodNumber = 7;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{});
				check(test.equals(list), message);
			}
		}.message("should empty a list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{});
				check(test.equals(list), message);
			}
		}.message("should not break on an empty list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				
			}
		}.message(""));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				
			}
		}.message(""));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m08_pop() {
		String methodName = "pop";
		int methodNumber = 8;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				check(((Integer)method.invoke(list)).intValue() == 1, message);
			}
		}.message("should be 1 for (0) -> {1,2,3}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list); // pop
				TestableLinkedList test = new TestableLinkedList(new int[]{2,3});
				check(test.equals(list), message);
			}
		}.message("should remove the node it popped"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				list.push(1);
				list.push(1);
				method.invoke(list); // pop
				list.push(1);
				TestableLinkedList test = new TestableLinkedList(new int[] {1,1});
				check(test.equals(list), message);
			}
		}.message("should leave a working list"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m09_linearSearch() {
		String methodName = "linearSearch";
		int methodNumber = 9;
		Class[] methodArgs = new Class[]{int.class};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();
		
		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{5,4,3,2,1});
				check(((Integer)method.invoke(list, 2)).intValue() == 3, message);
			}
		}.message("should be 3 for (2) -> {5, 4, 3, 2, 1}"));
		
		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{5,5,5,5,5});
				check(((Integer)method.invoke(list, 5)).intValue() == 0, message);
			}
		}.message("should be 0 for (5) -> {5, 5, 5, 5, 5}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,1,1});
				check(((Integer)method.invoke(list, 2)).intValue() == -1, message);
			}
		}.message("should be -1 for (2) -> {1, 1, 1}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m10_insertNth() {
		String methodName = "insertNth";
		int methodNumber = 10;
		Class[] methodArgs = new Class[]{int.class, int.class};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list, 0, 1);
				TestableLinkedList test = new TestableLinkedList(new int[]{1});
				check(test.equals(list), message);
			}
		}.message("(0, 1) -> {} should be  {1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,1,1,1,1,1});
				method.invoke(list, 3, 2);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,1,1,2,1,1,1});
				check(test.equals(list), message);
			}
		}.message("(3, 2) -> {1, 1, 1, 1, 1, 1} should be  {1, 1, 1, 2, 1, 1, 1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list, 3, 4);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4});
				check(test.equals(list), message);				
			}
		}.message("(3, 4) -> {1, 2, 3} should be {1, 2, 3, 4}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m11_sortedInsert() {
		String methodName = "sortedInsert";
		int methodNumber = 11;
		Class[] methodArgs = new Class[]{int.class};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list, 1);
				TestableLinkedList test = new TestableLinkedList(new int[]{1});
				check(test.equals(list), message);
			}
		}.message("(1) -> {} should be  {1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3,4,5});
				method.invoke(list, 4);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4,4,5});
				check(test.equals(list), message);
			}
		}.message("(4) -> {1, 2, 3, 4, 5} should be  {1, 2, 3, 4, 4, 5}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3,4,5});
				method.invoke(list, 1);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,1,2,3,4,5});
				check(test.equals(list), message);
			}
		}.message("(1) -> {1, 2, 3, 4, 5} should be  {1, 1, 2, 3, 4, 5}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3,4,5});
				method.invoke(list, 6);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4,5,6});
				check(test.equals(list), message);
			}
		}.message("(6) -> {1, 2, 3, 4, 5} should be  {1, 2, 3, 4, 5, 6}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m12_insertSort() {
		String methodName = "insertSort";
		int methodNumber = 12;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList();
				check(test.equals(list), message);
			}
		}.message("{} should be {}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3,4});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4});
				check(test.equals(list), message);
			}
		}.message("{1, 2, 3, 4} should be {1 ,2 ,3 ,4}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{4,3,2,1});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4});
				check(test.equals(list), message);
			}
		}.message("{4, 3, 2, 1} should be {1, 2, 3, 4}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{3, 1, 7, 3, 4, 1, 1, 3 ,5, 4});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1, 1, 1, 3, 3, 3, 4, 4, 5, 7});
				check(test.equals(list), message);
			}
		}.message("{3, 1, 7, 3, 4, 1, 1, 3 ,5, 4} should be {1, 1, 1, 3, 3, 3, 4, 4, 5, 7}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m13_append() {
		String methodName = "append";
		int methodNumber = 13;
		Class[] methodArgs = new Class[]{new LinkedList().getClass()};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list, (LinkedList) new TestableLinkedList(new int[]{1,2,3}));
				TestableLinkedList test = new TestableLinkedList(new int[]{1, 2, 3});
				check(test.equals(list), message);
			}
		}.message("({}) -> {1, 2, 3} should be  {1, 2, 3}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list, (LinkedList) new TestableLinkedList(new int[]{1,2,3}));
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,1,2,3});
				check(test.equals(list), message);
			}
		}.message("({1, 2, 3}) -> {1, 2, 3} should be  {1, 2, 3, 1, 2, 3}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list, (LinkedList) new TestableLinkedList());
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3});
				check(test.equals(list), message);
			}
		}.message("({1, 2, 3}) -> {} should be  {1, 2, 3}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				TestableLinkedList appended = new TestableLinkedList(new int[]{4,5,6});
				TestableLinkedList appendedCopy = new TestableLinkedList(new int[]{4,5,6});
				method.invoke(list, (LinkedList)appended);
				check(appended.equals(appendedCopy), message);
			}
		}.message("should not change the appended list"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m14_frontBackSplit() {
		String methodName = "frontBackSplit";
		int methodNumber = 14;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				LinkedList[] result = (LinkedList[])method.invoke(list);
				TestableLinkedList test = new TestableLinkedList();
				check(test.equals(result[0]) && test.equals(result[1]), message);
			}
		}.message("should return [{}, {}] for {}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1});
				LinkedList[] result = (LinkedList[])method.invoke(list);
				TestableLinkedList test = new TestableLinkedList();
				TestableLinkedList test2 = new TestableLinkedList(new int[]{1});
				boolean first  = test.equals(result[0]) && test2.equals(result[1]);
				boolean second = test2.equals(result[0]) && test.equals(result[1]);
				check(result.length == 2 && (first || second), message);
			}
		}.message("should return [{}, {1}] or [{1}, {}] for {1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1, 2, 3});
				LinkedList[] result = (LinkedList[])method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1});
				TestableLinkedList test2 = new TestableLinkedList(new int[]{2,3});
				TestableLinkedList test3 = new TestableLinkedList(new int[]{1,2});
				TestableLinkedList test4 = new TestableLinkedList(new int[]{3});
				boolean first  = test.equals(result[0]) && test2.equals(result[1]);
				boolean second = test3.equals(result[0]) && test4.equals(result[1]);
				check(result.length == 2 && (first || second), message);
			}
		}.message("should work on a list with odd length > 1"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1, 2, 3, 4});
				LinkedList[] result = (LinkedList[])method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2});
				TestableLinkedList test2 = new TestableLinkedList(new int[]{3,4});
				boolean first  = test.equals(result[0]) && test2.equals(result[1]);
				check(result.length == 2 && (first), message);
			}
		}.message("should work on a list of even length"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m15_removeDuplicates() {
		String methodName = "removeDuplicates";
		int methodNumber = 15;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList();
				check(test.equals(list), message);
			}
		}.message("should not change an empty list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,2,3});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3});
				check(test.equals(list), message);
			}
		}.message("{1, 2, 2, 3} becomes {1, 2, 3}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,1,1,1,2,3,3,3});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3});
				check(test.equals(list), message);
			}
		}.message("{1, 1, 1, 1, 2, 3, 3, 3} becomes {1, 2, 3}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{4,5,6,7,10});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{4,5,6,7,10});
				check(test.equals(list), message);
			}
		}.message("should not change a list with no duplicates"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

}