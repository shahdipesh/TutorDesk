Basic info about my Program:
1) The main file is named TutorDesk.java
2) The JUnit file is included along with my classes named as "junit-platform-console-standalone-1.6.0.jar"
3) "OfficialData.txt" contains the commands given to us as official test data
4) "OfficialDataOutput.txt" contains the output to the commands given to us as official test data.


To compile the code run the following commands:

1) javac Data.java Handler.java Node.java Service.java SessionInfo.java SinglyLinkedList.java Student.java Topic.java Tutor.java TutorDesk.java Utils.java Validator.java

2) javac -cp .:junit-platform-console-standalone-1.6.0.jar JUnitTest.java

3) java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path

