import java.io.InputStreamReader;
import java.util.Scanner;

public class TutorDesk {

    public static void main(String[] args) {

        Validator validator = new Validator();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your input: ");
        String input = scanner.nextLine();
        validator.validate(input);
        System.out.println("User Input from console: " + input);
        input = scanner.nextLine();
        validator.validate(input);

    }
}
