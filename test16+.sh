rm *.class
javac Node.java LinkedList.java TestableLinkedList.java Tester.java SortTester.java -classpath *.jar
java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore SortTester
