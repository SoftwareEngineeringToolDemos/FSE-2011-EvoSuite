Steps to run the tool:

1. Open Terminal(for the sake of convinence terminal is already open if not you could open)
2. cd ~/Desktop/EvoSuite
3. run ls command to find evosuite-1.0.1.jar and sample ATMExample project.
4. java -jar evosuite-1.0.1.jar -class example.ATM  -projectCP ~/Desktop/EvoSuite/ATMExample/src/ 
5. When you run the above command you see that tests for ATM class under example package
6. run ls command to find evosuite-report and evosuite-tests
7. Browse through ~/Desktop/EvoSuite/evosuite-tests to find test cases generated
8. The test case generated would be in evosuite-tests under example package along with a helper class for test cases


Validating Test Cases:

1. Copy test case and the helper class in eclipse(eclipse shortcut on desktop) under example package under src/test/java folder in EvoSuite-Demo project.
2. copy ATM.java from ~/Desktop/EvoSuite/ATMExample/src/example to eclipse EvoSuite-Demo project in the eclipse in example package under src/main/java folder.
3. run the src/test/java folder as junit tests to see all the test cases successfully running.

While this is only simple test to check if the tool is working as supposed to be in the paper. The tool offers more powerful options for automated test case generation. To dig deep into this tool follow the below link.

http://www.evosuite.org/documentation/commandline/
