// CLASS: Student
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class extends Data and stores info about a student.
//
//-----------------------------------------

public class Student extends Data{

    SinglyLinkedList sessionInfo; //linked-list of all the tutoring session that a student had

    public Student(String studentId) {
       super(studentId);
        sessionInfo = new SinglyLinkedList();
    }

    public String getStudentId() {
        return  super.getId();
    }

    public void setStudentId(String studentId) {
        super.setId(studentId);
    }

    public void setSessionInfo(SinglyLinkedList sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public SinglyLinkedList getSessionInfo() {
        return sessionInfo;
    }
}
