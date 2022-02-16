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
    Utils util = new Utils(); //contains util functions that our code may require.


    //------------------------------------------------------
    // createTutor
    //
    // PURPOSE: Create a new tutor and add it to tutorList.
    // PARAMETERS:
    //     tutorId: id for tutor,
    //     availableHours:  number of hours per term that they are able to tutor
    //------------------------------------------------------
    public void createTutor(String tutorId, int availableHours){
        Data tutor = new Tutor(tutorId,availableHours);
        if(tutorList.find(tutorId) == null){
            tutorList.insert(tutor);
           util.generateMsg("Tutor",true,tutorId);
        }else{
           util.generateMsg("Tutor",false,tutorId);
        }
    }

    //------------------------------------------------------
    // createStudent
    //
    // PURPOSE: Create a new student and add it to studentList.
    // PARAMETERS:
    //     studentId: id for student
    //------------------------------------------------------
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

    //------------------------------------------------------
    // addTopic
    //
    // PURPOSE: Create a new topic and assign it to tutor.
    // PARAMETERS:
    //     topicName: id for topic
    //     tutorId: id of tutor to whom the topic should be assigned
    //     price: price at which the tutor will teach the topic per hour
    //------------------------------------------------------
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

    //------------------------------------------------------
    // handleTutoringReq
    //
    // PURPOSE: Assign tutor to student who requested to be tutored.
    // PARAMETERS:
    //     studentId: id of student who requested to be tutored
    //     topic: name of the topic requested by student to study
    //     hours: hours requested by student
    //------------------------------------------------------
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
            assignTutorToStudent(tutorsAvailableToTeach, studentId, hours,topicToAssign);
        }
        else{
            System.out.println("No tutor available to teach "+topic);
        }
    }

    //------------------------------------------------------
    // assignTutorToStudent
    //
    // PURPOSE: Assign tutor to student who requested to be tutored.
    // PARAMETERS:
    //     filteredTutors: reference to the list containing tutors who teach a certain topic
    //     studentId: id of student who requested to be tutored
    //     topic: name of the topic requested by student to study
    //     requestedHours: hours requested by student
    //------------------------------------------------------
    public void assignTutorToStudent(SinglyLinkedList filteredTutors,String studentId,int requestedHours,Topic topic){
        Node curr = tutorsAvailableToTeach.getTop(); // pointer to top of the list of tutorsAvailableToTeach
        Student student = (Student) studentList.find(studentId); //student with id of studentId.
        //temporary Linked-list to hold list of tutors who are required to teach 'requestedHours' hours of topic
        SinglyLinkedList tempStdData = new SinglyLinkedList();
        /*
            If we find a student with studentId we loop through tutors available to teach a topic until
            the hour requirement is met.
         */
        if(student!=null) {
            Data data = null;
            int hoursToFulfill = requestedHours;
            while (curr != null && hoursToFulfill > 0) {
                data = curr.getData();
                if (data instanceof Tutor) {
                    int availableHours = ((Tutor) data).getAvailableHours();
                    int hoursTaught = util.hoursManager(availableHours, hoursToFulfill);
                    hoursToFulfill -= availableHours;
                    //generate a session object with tutorId, hours and topic studied
                    Data sessionInfo = new SessionInfo(((Tutor) data).getTutorId(), hoursTaught, topic);
                    //hold there data temporarily because the hour requirement might not be met once we loop through the list so
                    //rather than updating changes to the original list we hold these data temporarily
                    tempStdData.insert(sessionInfo);
                    curr = curr.getNext();
                } else {
                    System.out.println("Data is not of type tutor");
                }
            }
            //If we loop throughout the entire ordered list and we still have hours to fulfill
            //we are unable to assign any tutor.
            if (hoursToFulfill > 0) {
                System.out.println("No tutor available to teach " + topic.getTopicName() + " for " + requestedHours + " hours");
            } else {
                //Merge new session with previous session inside student class since the student with requested hours can be tutored
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
                               int cost = ((SessionInfo) sessionData).getCost(); //cost for a session with the tutor.
                            System.out.println("Tutor " + tutorId + " will tutor student " + studentId + " for " + sessionLength + " hours in topic " + topic.getTopicName() + " at a rate of " + ((SessionInfo) sessionData).getTopicStudied().getPrice());
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

    //------------------------------------------------------
    // handleStudentReport
    //
    // PURPOSE: Generate Student Report
    // PARAMETERS:
    //     studentId: id of student who's report is to be generated.
    //------------------------------------------------------
    public void handleStudentReport(String studentId) {
        Data student = studentList.find(studentId);
        if(student != null){
            if(student instanceof Student) {
                Node curr = ((Student)student).getSessionInfo().getTop();
                System.out.println("\n----Report for "+studentId+" -----");
                System.out.println("------------------------------------------------------------------------");
               // We loop through the session linked-list of the student to get info about each tutoring session he/she had.
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

    //------------------------------------------------------
    // handleStudentReport
    //
    // PURPOSE: Generate Tutor Report
    // PARAMETERS:
    //     tutorId: id of tutor who's report is to be generated.
    public void handleTutorReport(String tutorId) {
       Node curr = studentList.getTop();
       Data data;
        System.out.println("\n----Report for "+tutorId+" -----");
        System.out.println("------------------------------------------------------------------------");
        // We loop through the session linked-list of each student to see if the student had a session with the tutor
       while(curr!=null){
           Student currStudent = (Student)curr.getData();
           SessionInfo session = (SessionInfo) currStudent.getSessionInfo().find(tutorId);
           //if the current student obj has a session with the tutor we extract the required info to generate report
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
