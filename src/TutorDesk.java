//-----------------------------------------
// NAME		:Dipesh Shah
// STUDENT NUMBER	: 7882947
// COURSE		: COMP 2150
// INSTRUCTOR	: Ali Neshati
// ASSIGNMENT	: assignment 1
//
// REMARKS: What is the purpose of this program?
// A new service that helps students find tutoring for courses they are taking.
//
//-----------------------------------------


import java.io.*;
import java.util.Scanner;

public class TutorDesk {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s;
        Validator validator = new Validator();
        final String pathname;
        System.out.println("Please enter fileName:");
        s= new Scanner(System.in);
        pathname = s.nextLine();
        s = new Scanner(new File(pathname));
        String line;
        while(s.hasNextLine()){
            line = s.nextLine().trim();
            if(!line.contains("#") && !line.isBlank()) {
                validator.validate(line);
            }
        }
        System.out.println("EXITING...No QUIT command found");


    }
}
