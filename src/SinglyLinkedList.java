public class SinglyLinkedList {
    Node top ;
    private int size;
    public SinglyLinkedList() {
        this.top = null;
        size=0;
    }

    public void insert(Data data){
            if(top == null){
                 top = new Node(data,null);
            }
            else{
                 top = new Node(data,top);
            }
            size++;
    }

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

}
