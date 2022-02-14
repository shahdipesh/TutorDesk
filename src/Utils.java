// CLASS: Utils
//
// Author: Dipesh Shah, 7882947
//
// REMARKS: This class holds all the misc methods that we need to do certain calculations.
//
//-----------------------------------------

public class Utils {

  void generateMsg(String person, boolean status,String id){
      if(status){
          System.out.println(person+" with userid "+ id+ " successfully created.");
      }
      else{
          System.out.println("Duplicate"+ person +" with userid "+ id);
      }
  }

  boolean validateLength(String str){
      if(str.length()<=80 && !str.contains(" ")){
          return true;
      }
      else{
          return false;
      }
  }

    int hoursManager(int availableHours, int hoursToFulfill){
        if (availableHours - hoursToFulfill <= 0) {
            return availableHours;
        } else {
            return hoursToFulfill;
        }

    }

    public void filterAvailableTutors(SinglyLinkedList list,Tutor tutor, int price, String topicId){
        Node currentNode=list.top;
        Node previousNode = null;
        Data currentData =null;
        boolean shouldIContinue=true;

        if(currentNode!=null){
            currentData =list.top.getData();
        }

        while(currentNode!=null && currentData!=null && shouldIContinue)
        {
            if(currentData instanceof Tutor) {
                currentData=currentNode.getData();
                SinglyLinkedList currTopics = ((Tutor) currentData).getTopics();
                Topic topic = (Topic) currTopics.find(topicId);
                if (topic.getPrice() < price) {
                    previousNode = currentNode;
                    currentNode = currentNode.getNext();
                } else if(topic.getPrice() == price){
                   if(((Tutor) currentData).getAvailableHours()>tutor.getAvailableHours()){
                       previousNode = currentNode;
                       currentNode = currentNode.getNext();
                   }
                   else{
                       shouldIContinue = false;
                   }

                } else{
                    shouldIContinue = false;
                }
            }
        }
        Node newNode= new Node(tutor,currentNode);
        if(previousNode==null){
            list.top=newNode;
        }
        else{
            previousNode.setNext(newNode);
        }
        list.setSize(list.getSize()+1);
    }


}
