package com.pse.hjss;

import java.io.*;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        int id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "John", 11, "male", 1, "07009090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Sara", 11, "female", 2, "07009090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Louis", 11, "male", 4, "07009090904"));

        int lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "James"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "Robert"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "Ela"));

        Manager.cancelABooking(""+1234,"03",""+11001);
       Manager.printBookingsList(11001);

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
                    displayBookASwimmingLessonMenu();
                    // Call function to handle booking a lesson
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "3":
                    System.out.println("List of registered learners!");
                    displayLearnersList();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "4":
                    System.out.println("List of lessons!");
                    displayLessonsList();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "5":
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
        System.out.println("4. Display Lessons List");
        System.out.println("5. Exit");
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

    public static void displayBookASwimmingLessonMenu() {
        System.out.println("\n-----Book a Swimming Lesson-----");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter learner ID:");
        int learnerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Lesson ID: ");
        int lessonID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        //Learner learner = Manager.learnersHashMap.get(learnerID);
        Manager.bookALesson(lessonID, learnerID);
    }

    public static void displayLearnersList() {
        Manager.printLearnersList();
    }

    public static void displayLessonsList() {
        Manager.printLessonsList();
    }

    public static void displayBookingsOfALearner(int learnerID) {
         Manager.printBookingsList(learnerID);
    }

    public static void readData() {
        try {
        String filePath = "Learner_Data" + File.separator + "03"+ File.separator + "11001.txt";
         BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                // Process each booking data
                String[] parts = line.split(";");
                for (String part : parts) {
                    String[] keyValue = part.split(":");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    System.out.println(key + ": " + value);
                }
                System.out.println(); // Separate bookings
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeData(){
        String data = "This is a text inside the file.";

        try {
            String folderName = "Learner_Data"; // Specify your folder name
            File folder = new File(folderName);

            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Folder created successfully!");
                } else {
                    System.out.println("Failed to create folder.");
                }
            } else {
                System.out.println("Folder already exists.");
            }
            //String folderPath = "myFolder"; // Specify the folder path
            String filePath = folderName + File.separator + "output.txt"; // Full path to output.txt
            // Create a FileWriter to write to the file "output.txt"
            FileWriter file = new FileWriter(filePath);

            // Create a PrintWriter using the FileWriter
            PrintWriter output = new PrintWriter(file);

            // Write the data to the file
            output.print(data);

            // Close the PrintWriter
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
