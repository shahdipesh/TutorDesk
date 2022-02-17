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
        Data testData1 = new Student("Ali");
        Data testData2 = new Student("Peter");
        firstDataList.insert(testData1);
        secondDataList.insert(testData2);
        firstDataList.merge(secondDataList);
        Student result = (Student) firstDataList.find(testData2.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals("Peter",result.getId());
    }


    @Test
    public  void shouldInsertTutorInSortedOrderBasedOnPrice(){
        Data data;
        Utils util = new Utils();
        Validator validator = new Validator();
        SinglyLinkedList tutorList = new SinglyLinkedList();
        Tutor tutor1 = new Tutor("tutor1",5);
        Tutor tutor2 = new Tutor("tutor2",10);
        Tutor tutor3 = new Tutor("tutor3",15);
        Topic topic1 = new Topic("00",40);
        Topic topic2 = new Topic("00",30);
         data = getData(util, tutorList, tutor1, tutor2, tutor3, topic1, topic2);
        Assert.assertEquals("tutor3",data.getId());
    }

    @Test
    public  void shouldInsertTutorInSortedOrderBasedOnNumHours(){
        Data data;
        Utils util = new Utils();
        Validator validator = new Validator();
        SinglyLinkedList tutorList = new SinglyLinkedList();
        Tutor tutor1 = new Tutor("tutor1",40);
        Tutor tutor2 = new Tutor("tutor2",100);
        Tutor tutor3 = new Tutor("tutor3",15);
        Topic topic1 = new Topic("00",20);
        Topic topic2 = new Topic("00",20);
        data = getData(util, tutorList, tutor1, tutor2, tutor3, topic1, topic2);
        Assert.assertEquals("tutor2",data.getId());
    }



    private Data getData(Utils util, SinglyLinkedList tutorList, Tutor tutor1, Tutor tutor2, Tutor tutor3, Topic topic1, Topic topic2) {
        Data data;
        Topic topic3 = new Topic("00",20);
        tutor1.getTopics().insert(topic1);
        tutor2.getTopics().insert(topic2);
        tutor3.getTopics().insert(topic3);
        util.filterAvailableTutors(tutorList,tutor1,topic1.getPrice(),topic1.getId());
        util.filterAvailableTutors(tutorList,tutor2,topic2.getPrice(),topic1.getId());
        util.filterAvailableTutors(tutorList,tutor3,topic3.getPrice(),topic1.getId());

        Node top = tutorList.getTop();
        data = top.getData();
        return data;
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

    @Test
    public void shouldCreateSession(){
        Validator validator = new Validator();
        validator.validate("TUTOR tutor1 10");
        validator.validate("TOPIC OO tutor1 10");
        validator.validate("STUDENT std1");
        validator.validate("REQUEST std1 OO 5");
        Student data = (Student) validator.handler.service.studentList.getTop().getData();
        String tutorName =data.getSessionInfo().getTop().getData().getId();
        Assert.assertEquals("tutor1",tutorName );
    }



}
