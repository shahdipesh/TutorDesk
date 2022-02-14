// CLASS: Node
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class holds a data of type Data and points to another node.
//
//-----------------------------------------

public class Node {
    private Data data;
    private Node next;

    public Node(Data data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }


}
