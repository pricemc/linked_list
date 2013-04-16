import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.concurrent.*;
import java.util.*;

public class SortTester extends Tester {

	@Test 
	public void m16_insert() {
		final String methodName = "insert";
		int methodNumber = 16;
		final Class[] methodArgs = new Class[]{int.class, new Node().getClass()};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				method.invoke(list, 7, list.getHead());
				TestableLinkedList test = new TestableLinkedList(new int[]{7});
				check(test.equals(list), message);
			}
		}.message("should work on an empty list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list, 7, list.getHead());
				TestableLinkedList test = new TestableLinkedList(new int[]{7,1,2,3});
				check(test.equals(list), message);
			}
		}.message("should work at the beginning of the list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list, 7, list.getHead().getNext());
				TestableLinkedList test = new TestableLinkedList(new int[]{1,7,2,3});
				check(test.equals(list), message);
			}
		}.message("should work in the middle of the list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list, 7, list.getHead().getNext().getNext().getNext());
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,7});
				check(test.equals(list), message);
			}
		}.message("should work at the end of the list"));

		runTestsForPrivateMethod(methodName, methodNumber, methodArgs, tests, true);
	}

	@Test 
	public void m17_alternatingSplit() {
		String methodName = "alternatingSplit";
		int methodNumber = 17;
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
				check(result.length == 2 && (first), message);
			}
		}.message("should return [{}, {1}] for {1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1, 2, 3});
				LinkedList[] result = (LinkedList[])method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{2});
				TestableLinkedList test2 = new TestableLinkedList(new int[]{1,3});
				boolean first  = test.equals(result[0]) && test2.equals(result[1]);
				check(result.length == 2 && (first), message);
			}
		}.message("should work on a list with odd length > 1"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1, 2, 3, 4});
				LinkedList[] result = (LinkedList[])method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{2,4});
				TestableLinkedList test2 = new TestableLinkedList(new int[]{1,3});
				boolean first  = test.equals(result[0]) && test2.equals(result[1]);
				check(result.length == 2 && (first), message);
			}
		}.message("should work on a list of even length"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m18_sortedMerge() {
		String methodName = "sortedMerge";
		int methodNumber = 18;
		Class[] methodArgs = new Class[]{new LinkedList().getClass(), new LinkedList().getClass()};

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList arg0 = new TestableLinkedList();
				TestableLinkedList arg1 = new TestableLinkedList();
				LinkedList result = (LinkedList)method.invoke(null, arg0, arg1);
				TestableLinkedList test = new TestableLinkedList();
				check(test.equals(result), message);
			}
		}.message("should return {} for ({}, {})"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList arg0 = new TestableLinkedList();
				TestableLinkedList arg1 = new TestableLinkedList(new int[]{1});
				LinkedList result = (LinkedList)method.invoke(null, arg0, arg1);
				LinkedList result2 = (LinkedList)method.invoke(null, arg1, arg0);
				TestableLinkedList test = new TestableLinkedList(new int[]{1});
				check(test.equals(result) && test.equals(result2), message);
			}
		}.message("should return {1} for ({}, {1}) and ({1},{})"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList arg0 = new TestableLinkedList(new int[]{1,2,3});
				TestableLinkedList arg1 = new TestableLinkedList(new int[]{4,5,6});
				LinkedList result = (LinkedList)method.invoke(null, arg0, arg1);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4,5,6});
				check(test.equals(result), message);
			}
		}.message("should return {1, 2, 3, 4, 5, 6} for ({1, 2, 3}, {4, 5, 6})"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList arg0 = new TestableLinkedList(new int[]{4,5,6});
				TestableLinkedList arg1 = new TestableLinkedList(new int[]{1,2,3});
				LinkedList result = (LinkedList)method.invoke(null, arg0, arg1);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4,5,6});
				check(test.equals(result), message);
			}
		}.message("should return {1, 2, 3, 4, 5, 6} for ({4, 5, 6}, {1, 2, 3})"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList arg0 = new TestableLinkedList(new int[]{1,3,5,8});
				TestableLinkedList arg1 = new TestableLinkedList(new int[]{3,3,4,5,10});
				LinkedList result = (LinkedList)method.invoke(null, arg0, arg1);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,3,3,3,4,5,5,8,10});
				check(test.equals(result), message);
			}
		}.message("should return {1, 3, 3, 3, 4, 5, 5, 8, 10} for ({1, 3, 5, 8}, {3, 3, 4, 5, 10})"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList arg0 = new TestableLinkedList(new int[]{3,3,4,5,10});
				TestableLinkedList arg1 = new TestableLinkedList(new int[]{1,3,5,8});
				LinkedList result = (LinkedList)method.invoke(null, arg0, arg1);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,3,3,3,4,5,5,8,10});
				check(test.equals(result), message);
			}
		}.message("should return {1, 3, 3, 3, 4, 5, 5, 8, 10} for ({3, 3, 4, 5, 10}, {1, 3, 5, 8})"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList arg0 = new TestableLinkedList(new int[]{1,5,7});
				TestableLinkedList arg1 = new TestableLinkedList(new int[]{2,5,6,8});
				LinkedList result = (LinkedList)method.invoke(null, arg0, arg1);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,5,7});
				TestableLinkedList test2 = new TestableLinkedList(new int[]{2,5,6,8});
				check(test.equals(arg0) && test2.equals(arg1), message);
			}
		}.message("should not change the lists it is passed"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m19_mergeSort() {
		String methodName = "mergeSort";
		int methodNumber = 19;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList();
				check(test.equals(result), message);
			}
		}.message("{} should be {}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3,4});
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4});
				check(test.equals(result), message);
			}
		}.message("{1, 2, 3, 4} should be {1 ,2 ,3 ,4}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{4,3,2,1});
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,2,3,4});
				check(test.equals(result), message);
			}
		}.message("{4, 3, 2, 1} should be {1, 2, 3, 4}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{3, 1, 7, 3, 4, 1, 1, 3 ,5, 4});
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1, 1, 1, 3, 3, 3, 4, 4, 5, 7});
				check(test.equals(result), message);
			}
		}.message("{3, 1, 7, 3, 4, 1, 1, 3 ,5, 4} should be {1, 1, 1, 3, 3, 3, 4, 4, 5, 7}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{4,4,4,2,2,3});
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{2,2,3,4,4,4});
				check(test.equals(result), message);
			}
		}.message("{4, 4, 4, 2, 2, 3} should be {2, 2, 3, 4, 4, 4}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{7,7,7});
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{7,7,7});
				check(test.equals(result), message);
			}
		}.message("{7, 7, 7} should be {7, 7, 7}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{2,1,2,1,2,1,2,1,2,1,2});
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{1,1,1,1,1,2,2,2,2,2,2});
				check(test.equals(result), message);
			}
		}.message("{2, 1, 2, 1, 2, 1, 2, 1 ,2, 1, 2} should be {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m20_makeReverse() {
		String methodName = "makeReverse";
		int methodNumber = 20;
		Class[] methodArgs = null;

		ArrayList<MethodTest> tests = new ArrayList<MethodTest>();

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList();
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList();
				check(test.equals(result), message);
			}
		}.message("should not change an empty list"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{4});
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{4});
				check(test.equals(result), message);
			}
		}.message("should not change a list with 1 item"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				LinkedList result = (LinkedList)method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{3,2,1});
				check(test.equals(result), message);
			}
		}.message("should change {1, 2, 3} to {3, 2, 1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,6,5,3,1,4});
				TestableLinkedList test = new TestableLinkedList(new int[]{4,1,3,5,6,1});
				LinkedList result = (LinkedList)method.invoke(list);
				check(test.equals(result), message);
			}
		}.message("should change {1, 6, 5, 3, 1, 4} to {4, 1, 3, 5, 6, 1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,1,1,1,1,1,1,1,2});
				TestableLinkedList test = new TestableLinkedList(new int[]{2,1,1,1,1,1,1,1,1});
				LinkedList result = (LinkedList)method.invoke(list);
				check(test.equals(result), message);
			}
		}.message("should change {1, 1, 1, 1, 1, 1, 1, 1, 2} to {2, 1, 1, 1, 1, 1, 1, 1, 1}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m21_reverse() {
		String methodName = "reverse";
		int methodNumber = 21;
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
				TestableLinkedList list = new TestableLinkedList(new int[]{4});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{4});
				check(test.equals(list), message);
			}
		}.message("should not change a list with 1 item"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{3,2,1});
				check(test.equals(list), message);
			}
		}.message("should change {1, 2, 3} to {3, 2, 1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,6,5,3,1,4});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{4,1,3,5,6,1});
				check(test.equals(list), message);
			}
		}.message("should change {1, 6, 5, 3, 1, 4} to {4, 1, 3, 5, 6, 1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,1,1,1,1,1,1,1,2});
				TestableLinkedList test = new TestableLinkedList(new int[]{2,1,1,1,1,1,1,1,1});
				method.invoke(list);
				check(test.equals(list), message);
			}
		}.message("should change {1, 1, 1, 1, 1, 1, 1, 1, 2} to {2, 1, 1, 1, 1, 1, 1, 1, 1}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);
	}

	@Test 
	public void m22_recursiveReverse() {
		String methodName = "recursiveReverse";
		int methodNumber = 22;
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
				TestableLinkedList list = new TestableLinkedList(new int[]{4});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{4});
				check(test.equals(list), message);
			}
		}.message("should not change a list with 1 item"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,2,3});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{3,2,1});
				check(test.equals(list), message);
			}
		}.message("should change {1, 2, 3} to {3, 2, 1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,6,5,3,1,4});
				method.invoke(list);
				TestableLinkedList test = new TestableLinkedList(new int[]{4,1,3,5,6,1});
				check(test.equals(list), message);
			}
		}.message("should change {1, 6, 5, 3, 1, 4} to {4, 1, 3, 5, 6, 1}"));

		tests.add(new MethodTest() {
			public void run() throws Exception{
				TestableLinkedList list = new TestableLinkedList(new int[]{1,1,1,1,1,1,1,1,2});
				TestableLinkedList test = new TestableLinkedList(new int[]{2,1,1,1,1,1,1,1,1});
				method.invoke(list);
				check(test.equals(list), message);
			}
		}.message("should change {1, 1, 1, 1, 1, 1, 1, 1, 2} to {2, 1, 1, 1, 1, 1, 1, 1, 1}"));

		runTestsForMethod(methodName, methodNumber, methodArgs, tests);		
	}
}