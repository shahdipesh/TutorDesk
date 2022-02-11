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
}
