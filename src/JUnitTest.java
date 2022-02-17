import org.junit.Assert;
import org.junit.Test;



public class JUnitTest {

    @Test
    public void shouldInsertDataInList(){
        SinglyLinkedList linkedList = new SinglyLinkedList();
        Data data = new Student("Ram");
        linkedList.insert(data);
        String name = linkedList.getTop().getData().getId();
        Assert.assertEquals("Ram",name);
    }

    @Test
    public void shouldFindDataInList(){
        SinglyLinkedList linkedList = new SinglyLinkedList();
        Data data = new Student("John");
        linkedList.insert(data);
        Student result = (Student) linkedList.find(data.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals("John",result.getId());
    }

    @Test
    public  void shouldMergeTwoList(){
        SinglyLinkedList firstDataList = new SinglyLinkedList();
        SinglyLinkedList secondDataList = new SinglyLinkedList();
        Data testData1 = new Student("Ram");
        Data testData2 = new Student("Shyam");
        firstDataList.insert(testData1);
        secondDataList.insert(testData2);
        firstDataList.merge(secondDataList);
        Student result = (Student) firstDataList.find(testData2.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals("Shyam",result.getId());
    }




    @Test
    public void shouldCreateNewTutor(){
        Validator validator = new Validator();
        validator.validate("TUTOR tutor2 15");
        int tutorListSize = validator.handler.service.tutorList.getSize();
        Assert.assertEquals(1, tutorListSize);
    }

    @Test
    public void shouldCreateNewStudent(){
        Validator validator = new Validator();
        validator.validate("STUDENT test");
        int studentListSize = validator.handler.service.studentList.getSize();
        Assert.assertEquals(1, studentListSize);
    }

    @Test
    public void shouldAddTopic(){
        Validator validator = new Validator();
        validator.validate("TUTOR tutor1 10");
        validator.validate("TOPIC OO tutor1 10");
        Tutor tutor = (Tutor) validator.handler.service.tutorList.getTop().getData();
        String topicName = tutor.getTopics().getTop().getData().getId();
        Assert.assertEquals("OO",topicName );
    }


}
