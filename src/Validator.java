public class Validator {

    Handler handler = new Handler();
    Utils util = new Utils();
    public void validate(String input) {

        String tokens[] = input.split(" ");
        String requestType = tokens[0];
        String tutorId;
        switch (requestType) {
            case "TUTOR":
                 tutorId = tokens[1];
                int availableHours = Integer.parseInt(tokens[2]) ;
                if(tutorId.length()<=80 && !tutorId.contains(" ")) {
                    if (availableHours > 0 && availableHours <= 1000) {
                        handler.tutorHandler(tutorId, availableHours);
                    } else {
                        System.out.println("The number of hours should be between 1 and 1000");
                    }
                }else{
                    System.out.println("Invalid userId");
                }
                break;

            case "STUDENT":
                String studentId = tokens[1];
                if(studentId.length()<=80 && !studentId.contains(" ")){
                    handler.studentHandler(studentId);
                }
                else{
                    System.out.println("Invalid userId");
                }
                break;

            case "TOPIC":
                String topicName = tokens[1];
                tutorId = tokens[2];
                int price = Integer.parseInt(tokens[3]);
                if(util.validateLength(tutorId) && price>0 && price<1000){
                    handler.topicHandler(topicName,tutorId,price);
                }
                else{
                    System.out.println("Invalid topic name");
                }
                break;


            default:
                System.out.println("Invalid Request");
        }

    }
}
