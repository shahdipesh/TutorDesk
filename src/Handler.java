// CLASS: Handler
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class handles the command from user
//
//-----------------------------------------

public class Handler {

    Service service = new Service();

    public void tutorHandler(String tutorId, int availableHours){
        service.createTutor(tutorId, availableHours);
    }

    public void studentHandler(String studentId){
        service.createStudent(studentId);
    }


    public void topicHandler(String topicName, String tutorId, int price) {
        service.addTopic(topicName,tutorId,price);
    }

    public void handleRequest(String studentId, String topic, int hours) {
        service.handleTutoringReq(studentId,topic,hours);
    }

    public void handleStudentReport(String studentId) {
        service.handleStudentReport(studentId);
    }

    public void handleTutorReport(String tutorId) {
        service.handleTutorReport(tutorId);
    }
}
