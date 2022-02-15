// CLASS: SinglyLinkedList
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This data structure holds our Data class type objects.
//
//-----------------------------------------
public class SinglyLinkedList {
    Node top ;
    private int size;
    public SinglyLinkedList() {
        this.top = null;
        size=0;
    }

    //------------------------------------------------------
    // insert
    //
    // PURPOSE: Insert data of type Data into the linked-list.
    // PARAMETERS:
    //     data: data to be inserted
    //------------------------------------------------------
    public void insert(Data data){
            if(top == null){
                 top = new Node(data,null);
            }
            else{
                 top = new Node(data,top);
            }
            size++;
    }


    //------------------------------------------------------
    // find
    //
    // PURPOSE: find a data with given id from the given list
    // PARAMETERS:
    //     id: id to lookup from the linked-list
    //------------------------------------------------------
    public Data find(String id){
        boolean found = false;
            Node curr = top;
            String currId = "";
            while (curr != null && !found) {
                currId = (curr.getData()).getId();
                if (currId.equals(id)) {
                    found = true;
                }else {
                    curr = curr.getNext();
                }
            }
            if(found){
                return curr.getData();
            }
            else{
                return null;
            }

    }

    //------------------------------------------------------
    // merge
    //
    // PURPOSE: merge two linked list
    // PARAMETERS:
    //     list: reference to the list which should be merged to this linked list
    //------------------------------------------------------
    public void merge(SinglyLinkedList list){
        if(list!=null){
            Node curr = list.getTop();
            while(curr != null){
                if(curr.getData() != null) {
                    this.insert(curr.getData());
                }
                else{
                    System.out.println("Unable to merge mismatched List");
                }
                curr=curr.getNext();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTop(Node top) {
        this.top = top;
    }

    public Node getTop() {
        return top;
    }

}
