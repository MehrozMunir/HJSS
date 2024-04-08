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
                    displayAttendASwimmingLessonMenu();
                    // Call function to change booking a lesson
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "6":
                    System.out.println("List of registered learners!");
                    displayLearnersList();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "7":
                    System.out.println("-----Monthly Learner Report-----");
                    displayMonthlyLearnerReport();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "8":
                    System.out.println("-----Monthly Coach Report-----");
                    displayMonthlyCoachReport();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "9":
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
        System.out.println("5. Attend a swimming lesson");
        System.out.println("6. Display Learners List");
        System.out.println("7. Monthly Learner Report");
        System.out.println("8. Monthly Coach Report");
        System.out.println("9. Exit");
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

    public static void displayBookASwimmingLessonMenu(){
        System.out.println("\n-----Book a Swimming Lesson-----");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter Learner ID:");
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                int lessonID;
                if(Manager.learnersHashMap.containsKey(learnerID))
                    displayLessonsViewMenu();
                else
                    throw new Utils.CustomValidationException("The learner ID: "+learnerID+" does not exist. Please enter a valid learner ID.");
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
                        Manager.printLessonsList("grade_level", grade_level + "");
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
            } catch (Utils.CustomValidationException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid learner ID.");
                scanner.nextLine(); // Consume the newline character
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
        // for my application, the lessons for the month of May can only be booked and cancelled
        if(Manager.cancelBooking(bookingID, "05", ""+learnerID)){
            System.out.println("Booking with ID: " + bookingID + " is cancelled successfully!");
        }
        else{
            System.out.println("The booking ID: " + bookingID+ "  against the learner: "+learnerID+
            " is not present.\n Try again and enter the correct learner ID and booking ID.");
            displayCancelASwimmingLessonMenu();
        }
    }
    public static void displayAttendASwimmingLessonMenu() {
        System.out.println("\n-----Attend a Swimming Lesson-----");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Learner ID:");
        int learnerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the Booking ID for the lesson that you want to attend: ");
        String bookingID = scanner.next();
        scanner.nextLine(); // Consume the newline character
        if(!Manager.attendBooking(bookingID, ""+learnerID)){
            System.out.println("The entered Booking ID and Learner ID does not match.\nTry again by entering a valid learner ID and Booking ID");
            displayAttendASwimmingLessonMenu();
        }
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
        Manager.changeBooking(bookingID, ""+learnerID, lessonID);
    }
    public static void displayLearnersList() {
        Manager.printLearnersList();
    }

    public static void displayLessonsList() {
        Manager.printLessonsList("", "");
    }

    public static void displayMonthlyLearnerReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the month number for the report(e.g., 05 for May): ");
        int month = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Learner ID:");
        int learnerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        Manager.printLearnerReport(learnerID, month);
    }
    public static void displayMonthlyCoachReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the month number for the report(e.g., 05 for May): ");
        int month = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter coach name: ");
        String coachName = scanner.next();
        scanner.nextLine(); // Consume the newline character
        Manager.printCoachReport(coachName, month);
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
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 2, "Dave", "Mon, May 06 2024 05:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "James", "Mon, May 06 2024 06:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 1, "Ela", "Fri, May 03 2024 02:00 pm"));
        lessonID = Manager.generateLessonID();
        Manager.lessonsHashMap.put(lessonID, new Lesson(lessonID, 3, "Sara", "Sat, May 04 2024 03:00 pm"));

    }
    public static void writeData(){
        String data = "This is a text inside the file.";
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
    }
}
