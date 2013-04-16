rm *.class
javac Node.java LinkedList.java TestableLinkedList.java Tester.java LinkedListTester.java -classpath *.jar
java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore LinkedListTester
