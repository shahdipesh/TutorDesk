import java.io.*;
import java.util.Scanner;

public class TutorDesk {

    public static void main(String[] args) throws FileNotFoundException {

        Validator validator = new Validator();
        final String pathname ="/Users/dipeshasd/Desktop/TutorDesk/src/Commands.txt";
        Scanner s = new Scanner(new File(pathname));
        String line="";
        while(s.hasNextLine()){
            line = s.nextLine().trim();
            if(!line.contains("#") && !line.isBlank()) {
                validator.validate(line);
            }
        }
        System.out.println("EXITING...No QUIT command found");


    }
}
