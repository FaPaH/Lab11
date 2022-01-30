package sumdu.edu.ua.lab11;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type file name what will be scan: ");
        String input = scanner.nextLine();
        System.out.println("Type file name in which everything will be recorded : ");
        String output = scanner.nextLine();

        ParseXML parseXML = new ParseXML();
        CreateXML createXML = new CreateXML();
        createXML.create(parseXML.parseStudents(input), output);
    }
}


