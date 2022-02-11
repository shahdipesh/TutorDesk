public class Student extends Data{

    public Student(String studentId) {
       super(studentId);
    }

    public String getStudentId() {
        return  super.getId();
    }

    public void setStudentId(String studentId) {
        super.setId(studentId);
    }
}
