// CLASS: Handler
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class handles the command from user
//
//-----------------------------------------

public class Handler {

    Service service = new Service();

    // PURPOSE: Passes the commands with TUTOR as first keyword to service class.
    public void tutorHandler(String tutorId, int availableHours){
        service.createTutor(tutorId, availableHours);
    }

    // PURPOSE: Passes the commands with SRUDENT as first keyword to service class.
    public void studentHandler(String studentId){
        service.createStudent(studentId);
    }

    // PURPOSE: Passes the commands with TOPIC as first keyword to service class.
    public void topicHandler(String topicName, String tutorId, int price) {
        service.addTopic(topicName,tutorId,price);
    }

    // PURPOSE: Passes the commands with REQUEST as first keyword to service class.
    public void handleRequest(String studentId, String topic, int hours) {
        service.handleTutoringReq(studentId,topic,hours);
    }
    // PURPOSE: Passes the commands with STUDENTREPORT as first keyword to service class.
    public void handleStudentReport(String studentId) {
        service.handleStudentReport(studentId);
    }
    // PURPOSE: Passes the commands with TUTORREPORT as first keyword to service class.
    public void handleTutorReport(String tutorId) {
        service.handleTutorReport(tutorId);
    }
}
