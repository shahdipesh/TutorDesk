// CLASS: Service
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class contains all the business logics required to handle the different commands
//
//-----------------------------------------

public class Service {

    SinglyLinkedList tutorList = new SinglyLinkedList(); // list of all Tutors
    SinglyLinkedList studentList = new SinglyLinkedList(); // list of all students
    SinglyLinkedList tutorsAvailableToTeach = new SinglyLinkedList(); // stores tutors that are available to teach a requested topic
    Utils util = new Utils();


    public void createTutor(String tutorId, int availableHours){
        Data tutor = new Tutor(tutorId,availableHours);
        if(tutorList.find(tutorId) == null){
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
                util.filterAvailableTutors(this.tutorsAvailableToTeach,(Tutor) currentTutor,currentTopic.getPrice(),currentTopic.getId());
            }
            currentNode=currentNode.getNext();
        }
        if(tutorsAvailableToTeach.getSize()>0) {
            Data data = tutorsAvailableToTeach.getTop().getData();
            Tutor tutor = ((Tutor)data);
            Topic topicToAssign = (Topic) tutor.getTopics().find(topic);
            assignTutorToStudent(tutorsAvailableToTeach, studentId, hours,topic);
        }
        else{
            System.out.println("No tutor available to teach "+topic);
        }
    }

    public void assignTutorToStudent(SinglyLinkedList l,String studentId,int requestedHours,String topic){
        Node curr = tutorsAvailableToTeach.getTop();
        Student student = (Student) studentList.find(studentId);
        SinglyLinkedList tempStdData = new SinglyLinkedList();
//        SinglyLinkedList tempTutorData = new SinglyLinkedList();
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
                    Data sessionInfoStd = new SessionInfo(((Tutor) data).getTutorId(), hoursTaught, topicStudied);
//                    Data sessionInfoTutor = new TutorSessionInfo(studentId,topicStudied,hoursTaught);
                    tempStdData.insert(sessionInfoStd);
//                    tempTutorData.insert(sessionInfoTutor);
                    curr = curr.getNext();
                } else {
                    System.out.println("Data is not of type tutor");
                }
            }
            if (hoursToFulfill > 0) {
                System.out.println("No tutor available to teach " + topic + " for " + requestedHours + " hours");
            } else {
                /****Merge new session with previous session inside student class****/
                student.getSessionInfo().merge(tempStdData);
                Node currSession = student.getSessionInfo().getTop();
                while (currSession != null) {
                    Data sessionData = currSession.getData();
                    if (sessionData instanceof SessionInfo) {
                        String tutorId = sessionData.getId();
                            Tutor tutor = (Tutor) tutorList.find(tutorId);
                           if(tutor != null) {
                            int sessionLength = ((SessionInfo) sessionData).getHoursStudies();
                               Tutor tutorToDecrementHoursFrom = (Tutor) tutor;
                               tutorToDecrementHoursFrom.setAvailableHours(tutorToDecrementHoursFrom.getAvailableHours() - sessionLength);
                               int cost = ((SessionInfo) sessionData).getCost();
                            System.out.println("Tutor " + tutorId + " will tutor student " + studentId + " for " + sessionLength + " hours in topic " + topic + " at a rate of " + ((SessionInfo) sessionData).getTopicStudied().getPrice());
                            currSession = currSession.getNext();
                        }
                           else{
                               System.out.println("Unable to find tutor inside");
                           }
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
                System.out.println("\n----Report for "+studentId+" -----");
                System.out.println("------------------------------------------------------------------------");
                while(curr!=null){
                    Data data = curr.getData();
                    if(data instanceof SessionInfo){
                        SessionInfo session = (SessionInfo)data;
                        System.out.println("Appointment: Tutor: "+session.getId()+", topic: "+session.getTopicStudied().getTopicName()+
                                ", hours: "+session.getHoursStudies()+", cost: "+session.getCost());
                        curr=curr.getNext();
                    }
                }
                System.out.println("------------------------------------------------------------------------");
            }
        }
        else{
            System.out.println("Student Not found");
        }
    }

    public void handleTutorReport(String tutorId) {
       Node curr = studentList.getTop();
       Data data;
        System.out.println("\n----Report for "+tutorId+" -----");
        System.out.println("------------------------------------------------------------------------");
       while(curr!=null){
           Student currStudent = (Student)curr.getData();
           SessionInfo session = (SessionInfo) currStudent.getSessionInfo().find(tutorId);
           if(session!=null){
               String studentId = currStudent.getStudentId();
               String topic = session.getTopicStudied().getTopicName();
               int hours = session.getHoursStudies();
               int revenue = session.getCost();
               System.out.println("Appointment: Student: "+studentId+", topic: "+topic+
                       ", hours: "+hours+", total Revenue: "+revenue);
           }
           curr=curr.getNext();
       }
        System.out.println("------------------------------------------------------------------------");
    }
}
