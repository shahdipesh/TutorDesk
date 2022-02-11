public class Service {

    SinglyLinkedList tutorList = new SinglyLinkedList();
    SinglyLinkedList studentList = new SinglyLinkedList();
    SinglyLinkedList orderedTutorList = new SinglyLinkedList();
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

    public void handleTutoringReq(String studentId, String topic, int hours) {
        Node currentNode = tutorList.top;
        Data currentTutor;
        while(currentNode != null){
            currentTutor= currentNode.getData();
            Topic currentTopic = (Topic) ((Tutor) currentTutor).getTopics().find(topic);
            if(currentTopic!=null){
                util.filterAvailableTutors(this.orderedTutorList,(Tutor) currentTutor,currentTopic.getPrice(),currentTopic.getId());
            }
            currentNode=currentNode.getNext();
        }
        if(orderedTutorList.getSize()>0) {
            Data tutor = orderedTutorList.getTop().getData();
            Topic topicToAssign = (Topic) ((Tutor)tutor).getTopics().find(topic);
            assignTutorToStudent(orderedTutorList, studentId, hours,topic);
        }
        else{
            System.out.println("No tutor available to teach "+topic);
        }
    }

    public void assignTutorToStudent(SinglyLinkedList l,String studentId,int requestedHours,String topic){
        Node curr = orderedTutorList.getTop();
        Student student = (Student) studentList.find(studentId);
        SinglyLinkedList tempStdData = new SinglyLinkedList();
        SinglyLinkedList tempTutorData = new SinglyLinkedList();
        if(student!=null) {
            Data data = null;
            int hoursToFulfill = requestedHours;
            while (curr != null && hoursToFulfill > 0) {
                data = curr.getData();
                if (data instanceof Tutor) {
                    int availableHours = ((Tutor) data).getAvailableHours();
                    int hoursTaught = util.hoursManager(availableHours, hoursToFulfill);
                    hoursToFulfill -= availableHours;
                    Topic topicStudied = (Topic) ((Tutor) data).getTopics().find(topic);
                    Data sessionInfoStd = new StudentSessionInfo(((Tutor) data).getTutorId(), hoursTaught, topicStudied);
                    Data sessionInfoTutor = new TutorSessionInfo(studentId,topicStudied,hoursTaught);
                    tempStdData.insert(sessionInfoStd);
                    tempTutorData.insert(sessionInfoTutor);
                    curr = curr.getNext();
                } else {
                    System.out.println("Data is not of type tutor");
                }
            }
            if (hoursToFulfill > 0) {
                System.out.println("No tutor available to teach " + topic + " for " + requestedHours + " hours");
            } else {
                /****Merge 2 ll****/
                    student.getSessionInfo().merge(tempStdData);
                Node currSession = student.getSessionInfo().getTop();
                while (currSession != null) {
                    Data sessionData = currSession.getData();
                    if (sessionData instanceof StudentSessionInfo) {
                        String tutorId = sessionData.getId();
                        int sessionLength = ((StudentSessionInfo) sessionData).getHoursStudies();
                        int cost = ((StudentSessionInfo) sessionData).getCost();
                        System.out.println("Tutor " + tutorId + " will tutor student " + studentId + " for " + sessionLength + " hours in topic " + topic + " at a rate of " + ((StudentSessionInfo) sessionData).getTopicStudied().getPrice());
                        currSession = currSession.getNext();
                    }
                }
            }
        }
        else{
            System.out.println("Student Not found");
        }
    }


    public void handleStudentReport(String studentId) {
        Data student = studentList.find(studentId);
        if(student != null){
            if(student instanceof Student) {
                Node curr = ((Student)student).getSessionInfo().getTop();
                System.out.println("----Report for "+studentId+" -----");
                while(curr!=null){
                    Data data = curr.getData();
                    if(data instanceof StudentSessionInfo){
                        StudentSessionInfo session = (StudentSessionInfo)data;
                        System.out.println("Appointment: Tutor: "+session.getId()+", topic: "+session.getTopicStudied().getTopicName()+
                                ", hours: "+session.getHoursStudies()+", cost: "+session.getCost());
                        curr=curr.getNext();
                    }
                }

            }
        }
        else{
            System.out.println("Student Not found");
        }
    }

    public void handleTutorReport(String tutorId) {
       Node curr = studentList.getTop();
       Data data;
       while(curr!=null){
           Student currStudent = (Student)curr.getData();

           curr=curr.getNext();
       }
    }
}
