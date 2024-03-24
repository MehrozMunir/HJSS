package com.pse.hjss;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        int id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "John", 11, "male", 1, "07009090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Sara", 11, "female", 2, "07009090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Louis", 11, "male", 4, "07009090904"));
       //
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Call function to handle registering a learner
                    displayLearnerRegistrationMenu();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "2":
                    displayBookLessonMenu();
                    // Call function to handle booking a lesson
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "3":
                    displayLearnersList();
                case "4":
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

    }

    public static void displayMainMenu() {
        System.out.println("Welcome to HJSS");
        System.out.println("1. Register a new learner");
        System.out.println("2. Book a swimming lesson for a learner");
        System.out.println("3. Display Learners List");
        System.out.println("4. Exit");
    }

    public static void displayLearnerRegistrationMenu() {
        System.out.println("\n-----Register a New Learner-----");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter learner's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter learner's age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter learner's gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter learner's current grade level: ");
        int currentGradeLevel = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter learner's emergency contact number: ");
        String emergencyContactNumber = scanner.nextLine();
        Manager.registerLearner(name, age, gender, currentGradeLevel, emergencyContactNumber);
    }

    public static void displayBookLessonMenu() {
        System.out.println("\nBook a Swimming Lesson");
        // You can prompt for learner details here
    }
    public static void displayLearnersList(){
        Manager.printLearnersList();
    }
}
