import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import  org.junit.jupiter.api.Test;


public class JUnitTest {

    @Test
    @DisplayName("Should insert Data in Linked List")
    void shouldInsertDataInList(){
        SinglyLinkedList linkedList = new SinglyLinkedList();
        Data data = new Student("Ram");
        linkedList.insert(data);
        String name = linkedList.getTop().getData().getId();
        Assertions.assertEquals("Ram",name);
    }

    @Test
    @DisplayName("Should find Data in Linked List")
    void shouldFindDataInList(){
        SinglyLinkedList linkedList = new SinglyLinkedList();
        Data data = new Student("John");
        linkedList.insert(data);
        Student result = (Student) linkedList.find(data.getId());
        Assertions.assertNotNull(result);
        Assertions.assertEquals("John",result.getId());
    }

    @Test
    @DisplayName("Should merge two Linked List")
    void shouldMergeTwoList(){
        SinglyLinkedList firstDataList = new SinglyLinkedList();
        SinglyLinkedList secondDataList = new SinglyLinkedList();
        Data testData1 = new Student("Ram");
        Data testData2 = new Student("Shyam");
        firstDataList.insert(testData1);
        secondDataList.insert(testData2);
        firstDataList.merge(secondDataList);
        Student result = (Student) firstDataList.find(testData2.getId());
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Shyam",result.getId());
    }


    @Test
    @DisplayName("Creating a new tutor")
    void shouldCreateNewTutor(){
        Validator validator = new Validator();
        validator.validate("TUTOR tutor2 15");
        int tutorListSize = validator.handler.service.tutorList.getSize();
       Assertions.assertEquals(1, tutorListSize);
    }

    @Test
    @DisplayName("Creating a new student")
    void shouldCreateNewStudent(){
        Validator validator = new Validator();
        validator.validate("STUDENT test");
        int studentListSize = validator.handler.service.studentList.getSize();
        Assertions.assertEquals(1, studentListSize);
    }

    @Test
    @DisplayName("Should add topic to tutor")
     void shouldAddTopic(){
        Validator validator = new Validator();
        validator.validate("TUTOR tutor1 10");
        validator.validate("TOPIC OO tutor1 10");
        Tutor tutor = (Tutor) validator.handler.service.tutorList.getTop().getData();
        String topicName = tutor.getTopics().getTop().getData().getId();
        Assertions.assertEquals("OO",topicName );
    }


}
