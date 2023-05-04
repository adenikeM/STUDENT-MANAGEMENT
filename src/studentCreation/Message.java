package studentCreation;

import java.util.Random;
import java.util.Scanner;

public class Message {
    public static void displayWelcomeMessage() {
        System.out.println("Welcome to school registration");
//        System.out.println("Please create a new student or proceed to login");

    }

    public static int selectBaseOperation() {
        return selectOperation("Press 1 to create a new student\nPress 2 to view student\nPress 3 to view student by ID\nPress 4 to delete student by ID\nPress 5 to leave " +
                "application");
    }

    private static int selectOperation(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
