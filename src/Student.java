public class Student extends Data{

    SinglyLinkedList sessionInfo;

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
