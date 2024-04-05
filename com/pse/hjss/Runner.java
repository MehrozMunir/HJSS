package com.pse.hjss;

import java.io.*;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        addLearners();
        addLessons();
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
                    displayCancelASwimmingLessonMenu();
                    // Call function to handle cancel booking
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "4":
                    displayChangeASwimmingLessonMenu();
                    // Call function to change booking a lesson
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "5":
                    System.out.println("List of registered learners!");
                    displayLearnersList();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "6":
                    System.out.println("List of All lessons!");
                    displayLessonsList();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "7":
                    System.out.println("List of Bookings!");
                    displayBookingsOfALearner();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "8":
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
        System.out.println("3. Cancel a swimming lesson for a learner");
        System.out.println("4. Change/Update a swimming lesson for a learner");
        System.out.println("5. Display Learners List");
        System.out.println("6. Display Lessons List");
        System.out.println("7. Display Bookings List");
        System.out.println("8. Exit");
    }
    public static void displayLessonsViewMenu() {
        System.out.println("How do you want the lessons to be displayed?");
        System.out.println("1. By specifying the day");
        System.out.println("2. By specifying the grade level");
        System.out.println("3. By specifying the coach's name");
        System.out.println("4. All lessons");
    }

    public static void displayLearnerRegistrationMenu() {
        System.out.println("\n-----Register a New Learner-----");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Learner's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Learner's age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Learner's gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Learner's current grade level: ");
        int currentGradeLevel = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Learner's emergency contact number: ");
        String emergencyContactNumber = scanner.nextLine();
        Manager.registerLearner(name, age, gender, currentGradeLevel, emergencyContactNumber);
    }

    public static void displayBookASwimmingLessonMenu() {
        System.out.println("\n-----Book a Swimming Lesson-----");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Learner ID:");
        int learnerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        int lessonID;
        while (true) {
           displayLessonsViewMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter M for Monday, W for Wednesday, F for Friday, S for Saturday: ");
                    String selectedDay = scanner.next();
                    scanner.nextLine(); // Wait for Enter key
                    Manager.printLessonsList("day", selectedDay);
                    System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                    lessonID = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Manager.bookALesson(lessonID, learnerID);
                    return;
                case "2":
                    System.out.print("Enter the grade level for which you want to see the lessons:");
                    int grade_level = scanner.nextInt();
                    scanner.nextLine(); // Wait for Enter key
                    Manager.printLessonsList("grade_level", grade_level+"");
                    // Call function to handle booking a lesson
                    System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                    lessonID = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Manager.bookALesson(lessonID, learnerID);
                   return;
                case "3":
                    System.out.print("Enter the coach name whose lessons you want to see: ");
                    String coach_name = scanner.nextLine();
                    Manager.printLessonsList("coach_name", coach_name);
                    // Call function to handle booking a lesson
                    System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                    lessonID = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Manager.bookALesson(lessonID, learnerID);
                    return;
                case "4":
                    Manager.printLessonsList("", "");
                    // Call function to handle booking a lesson
                    System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                    lessonID = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Manager.bookALesson(lessonID, learnerID);
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

    }
    public static void displayCancelASwimmingLessonMenu() {
        System.out.println("\n-----Cancel a Swimming Lesson-----");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Learner ID:");
        int learnerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the Booking ID that you want to cancel: ");
        String bookingID = scanner.next();
        scanner.nextLine(); // Consume the newline character
        Manager.cancelBooking(bookingID, "03", ""+learnerID);
    }

    public static void displayChangeASwimmingLessonMenu() {
        System.out.println("\n-----Change/Update a Swimming Lesson-----");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Learner ID:");
        int learnerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the Booking ID for the booking that you want to change: ");
        String bookingID = scanner.next();
        scanner.nextLine(); // Consume the newline character
        Manager.printLessonsList("", "");
        System.out.print("Enter the Lesson ID from the above lessons that you want to book now: ");
        int lessonID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        Manager.changeBooking(bookingID, "03", ""+learnerID, lessonID);
    }
    public static void displayLearnersList() {
        Manager.printLearnersList();
    }

    public static void displayLessonsList() {
        Manager.printLessonsList("", "");
    }

    public static void displayBookingsOfALearner() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Learner ID:");
        int learnerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        Manager.printBookingsList(learnerID);
    }

    private static void addLearners(){
        int id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "John", 11, "male", 1, "07009090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Sara", 11, "female", 2, "07009090904"));
        id = Manager.generateLearnerID();
        Manager.learnersHashMap.put(id, new Learner(id, "Louis", 11, "male", 4, "07009090904"));
    }
    private static void addLessons(){
        int lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 4, "James", "Wed, May 01 2024 04:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "Robert", "Wed, May 01 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "Ela", "Wed, May 01 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "Dave", "Mon, Apr 29 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "James", "Mon, Apr 29 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Ela", "Fri, May 03 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "Sara", "Sat, May 04 2024 06:00 pm"));

    }

    public static void readData() {
        try {
        String filePath = "learner_data" + File.separator + "03"+ File.separator + "11001.txt";
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
            String folderName = "learner_data"; // Specify your folder name
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
