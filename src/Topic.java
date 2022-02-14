// CLASS: Topic
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class extends Data and stores info about a Topic.
//
//-----------------------------------------

public class Topic extends Data{

    private int price;

    public Topic(String name, int price) {
       super(name);
        this.price = price;
    }

    public String getTopicName() {
       return super.getId();
    }

    public void setTopicName(String name) {
        super.setId(name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
