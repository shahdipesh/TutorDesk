public class Service {

    SinglyLinkedList tutorList = new SinglyLinkedList();
    SinglyLinkedList studentList = new SinglyLinkedList();
    Utils util = new Utils();

    public void createTutor(String tutorId, int availableHours){
        Data tutor = new Tutor(tutorId,availableHours);
        if(tutorList.find(tutorId)==null){
            tutorList.insert(tutor);
           util.generateMsg("Tutor",true,tutorId);
        }else{
           util.generateMsg("Tutor",false,tutorId);
        }
    }

    public void createStudent(String studentId) {
        Data student = new Student(studentId);
        if( studentList.find(studentId)==null){
            studentList.insert(student);
            util.generateMsg("Student",true,studentId);
        }
        else{
            util.generateMsg("Student",false,studentId);
        }
    }

    public void addTopic(String topicName, String tutorId, int price) {
        Tutor tutor = (Tutor) tutorList.find(tutorId);
        Topic topic = new Topic(topicName,price);
        if(tutor!=null){
            if(tutor.getTopics().find(topicName)==null) {
                tutor.getTopics().insert(topic);
                System.out.println("CONFIRMED: Adding " + topicName + " to tutor with id " + tutorId);
            }
            else{
                System.out.println("DUPLICATE: Failed to add" + topicName + " to tutor with id " + tutorId);
            }
        }
        else{
            System.out.println("Tutor not found");
        }
    }
}
