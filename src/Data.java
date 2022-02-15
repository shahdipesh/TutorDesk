// CLASS: Data
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class acts as a parent Data type for other classes
//
//-----------------------------------------

abstract class Data {
     String id;

     //Constructor
    public Data(String id) {
        this.id = id;
    }

    //getter for id
    public String getId() {
        return id;
    }

    //setter for id
    public void setId(String id) {
        this.id = id;
    }
}
