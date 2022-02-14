// CLASS: Tutor
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class extends Data and stores info about a Tutor.
//
//-----------------------------------------

public class Tutor extends Data {

    private int availableHours;
    SinglyLinkedList topics;
    SinglyLinkedList sessionInfo;

    public Tutor(String tutorId) {
       super(tutorId);
        this.availableHours=Integer.MIN_VALUE;
    }

    public Tutor(String tutorId, int availableHours) {
        super(tutorId);
        this.availableHours = availableHours;
        this.topics=new SinglyLinkedList();
    }



    public String getTutorId() {
        return super.getId();
    }

    public void setTutorId(String tutorId) {
        super.setId(tutorId);
    }

    public int getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(int availableHours) {
        this.availableHours = availableHours;
    }

    public SinglyLinkedList getTopics() {
        return topics;
    }

    public SinglyLinkedList getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SinglyLinkedList sessionInfo) {
        this.sessionInfo = sessionInfo;
    }


    public void setTopics(Topic topic) {
        topics.insert(topic);
    }
}
