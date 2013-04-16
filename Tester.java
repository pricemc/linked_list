import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.concurrent.*;
import java.util.*;

public class Tester {

	private static int testCount, passes, failures;

	protected void failure(String message) {
		System.out.println(testCount + ": " + message + " : Failed");
		failures++;
		testCount++;
	}

	protected void success(String message) {
		System.out.println(testCount + ": " + message + " : Passed");
		passes++;
		testCount++;
	}

	protected void check(boolean test, String message) {
		if (test) {
			success(message);
		} else {
			failure(message);
		}
		
		// assertTrue(test);
	}

	protected void checkNotNull(Object o, String message) {
		if (o != null) {
			success(message);
		} else {
			failure(message);
		}

		// assertNotNull(o);
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@AfterClass
    public static void tearDownClass() throws Exception {
    	System.out.println("\n\nResults");
    	System.out.println("-------");

    	System.out.println("Passes: " + passes);
    	System.out.println("Failures: " + failures);
    	System.out.println("Tests: " + testCount);
    	System.out.println("\n\nFinal Score\n-----------");

    	double score = 100;
    	if (failures > 0) {
    		score = (((double) passes) / testCount) * 50 + 50;
    	}
    	System.out.format("%.1f%n", score);
    	System.out.println("\nFinal Score formula is 50 * (Passes / tests) + 50");
    }

    protected void printDashes(String name) {
    	String dashes = "";
    	for (int i=0;i<name.length();i++) {
    		dashes += "-";
    	}
    	System.out.println(dashes);
    }

    private void printBanner(String methodName, int methodNumber) {
    	System.out.println("\n" + methodNumber + ") " + methodName);
    	printDashes(methodName);
    }

    private void runTestsOn(String methodName, int methodNumber, Class[] argList, ArrayList<MethodTest> tests, boolean privated, boolean checkForPrivate) {

    	printBanner(methodName, methodNumber);

    	try {
    		Class<?> c = new LinkedList().getClass();
    		Method method;
    		if (!privated) {
    			method = c.getMethod(methodName, argList);
    		} else {
    			method = c.getDeclaredMethod(methodName, argList);
    			method.setAccessible(true);
    		}

    		success("was attempted");

    		if (privated && checkForPrivate) {
    			try {
    				c.getMethod(methodName, argList);
    				failure("should be private");
    			} catch (NoSuchMethodException e) {
    				success("should be private");
    			}
    		}

	    	for (MethodTest test : tests) {
	    		test.method = method;
	    		final MethodTest theTest = test;
				ExecutorService executor = Executors.newSingleThreadExecutor();
				Future<String> future = executor.submit(new Task() {
					public String call() throws Exception {
				    	try {
				    		theTest.run();
				    	} catch (IllegalAccessException e) {
				    		fail(e.getMessage());
				    	} catch (Exception e) {
				    		failure("crashed while attempting: " + theTest.message);
				    	}    

				        return "Done";
				    }
				});
				try {
		            future.get(1, TimeUnit.SECONDS);
		        } catch (TimeoutException e) {
		            failure("infinite loop while attempting: " + test.message);
		        } catch (Exception e) {
		        	failure("infinite loop while attempting: " + test.message);
		        }
		        executor.shutdownNow();
	    	}	

    	} catch (NoSuchMethodException e) {
    		failure("was attempted");
    		for (MethodTest test : tests) {
    			failure(test.message);
    		}
    	}
    }


    protected void runTestsForMethod(String methodName, int methodNumber, Class[] argList, ArrayList<MethodTest> tests) {

    	runTestsOn(methodName, methodNumber, argList, tests, false, false);
    }

    protected void runTestsForPrivateMethod(String methodName, int methodNumber, Class[] argList, ArrayList<MethodTest> tests, boolean checkForPrivate) {

    	runTestsOn(methodName, methodNumber, argList, tests, true, checkForPrivate);	
    }
}

// used as an anonymous inner class
// holds an individual test for a method
// run method is defined when created so it can do arbitrary tests
abstract class MethodTest {
	public String message;
	public Method method;

	public abstract void run() throws Exception;

	public MethodTest message(String message){
	    this.message = message;
	    return this;
	}
	
}

// for running the infinite loop check
abstract class Task implements Callable<String> {
    
}