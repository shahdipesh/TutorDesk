// CLASS: SessionInfo
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class extends Data and stores info about each tutoring session for the student.
//
//-----------------------------------------

public class SessionInfo extends Data{

    private int hoursStudies;
    private Topic topicStudied;


    public SessionInfo(String tutorId, int hoursStudies, Topic topicStudied) {
        super(tutorId);
        this.hoursStudies = hoursStudies;
        this.topicStudied = topicStudied;
    }

    int getCost(){
        return this.hoursStudies *topicStudied.getPrice();
    }

    public int getHoursStudies() {
        return hoursStudies;
    }

    public void setHoursStudies(int hoursStudies) {
        this.hoursStudies = hoursStudies;
    }

    public Topic getTopicStudied() {
        return topicStudied;
    }


    public void setTopicStudied(Topic topicStudied) {
        this.topicStudied = topicStudied;
    }
}
