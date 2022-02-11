public class TutorSessionInfo  extends Data{

    private Topic topicTaught;
    private int hours;


    public TutorSessionInfo(String id, Topic topicTaught, int hours) {
        super(id);
        this.topicTaught = topicTaught;
        this.hours = hours;
    }

    public int gerEarning(){
        return hours * (topicTaught.getPrice());
    }

    public Topic getTopicTaught() {
        return topicTaught;
    }

    public void setTopicTaught(Topic topicTaught) {
        this.topicTaught = topicTaught;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
