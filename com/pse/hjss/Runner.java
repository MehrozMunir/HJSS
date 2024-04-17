package com.pse.hjss;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        AddLearnersLessons.addLearners();
        AddLearnersLessons.addLessons();
        Utils.initializeFoldersAndFiles();
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
        System.out.println("\u001B[33m Welcome to HJSS\u001B[0m");
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n-----Book a Swimming Lesson-----");
            try {
                System.out.println("If you want to return to main menu, enter 0(zero).");
                System.out.print("Enter Learner ID:");
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
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
                            if (selectedDay.equalsIgnoreCase("m") || selectedDay.equalsIgnoreCase("w")
                                    || selectedDay.equalsIgnoreCase("f") || selectedDay.equalsIgnoreCase("s")){
                                Manager.printLessonsList("day", selectedDay);
                                System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                                lessonID = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline character
                                if(Manager.lessonsHashMap.containsKey(lessonID)) {
                                    Manager.bookALesson(lessonID, learnerID);
                                    return;
                                }
                                else{
                                    throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                                }
                            }
                            else {
                                 throw new Utils.CustomValidationException("Please enter a valid day value such as M, W, F or S.");
                            }
                    case "2":
                        System.out.print("Enter the grade level for which you want to see the lessons:");
                        int grade_level = scanner.nextInt();
                        scanner.nextLine(); // Wait for Enter key
                        if (grade_level == 1 || grade_level == 2
                                || grade_level == 3 || grade_level == 4 || grade_level == 5){
                            Manager.printLessonsList("grade_level", grade_level + "");
                            // Call function to handle booking a lesson
                            System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                            lessonID = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            if(Manager.lessonsHashMap.containsKey(lessonID)) {
                                Manager.bookALesson(lessonID, learnerID);
                                return;
                            }
                            else{
                                throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                            }
                        }
                        else {
                            throw new Utils.CustomValidationException("Please enter a valid grade value from 1 to 5.");
                        }
                    case "3":
                        System.out.print("Enter the coach name whose lessons you want to see: ");
                        String coach_name = scanner.nextLine();
                        if(Manager.coachesNamesArrayList.contains(coach_name)) {
                            Manager.printLessonsList("coach_name", coach_name);
                            // Call function to handle booking a lesson
                            System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                            lessonID = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            if(Manager.lessonsHashMap.containsKey(lessonID)) {
                                Manager.bookALesson(lessonID, learnerID);
                                return;
                            }
                            else{
                                throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                            }
                        }
                        else{
                            throw new Utils.CustomValidationException("The coach name entered does not exist.");
                        }
                    case "4":
                        Manager.printLessonsList("", "");
                        // Call function to handle booking a lesson
                        System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                        lessonID = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        if(Manager.lessonsHashMap.containsKey(lessonID)) {
                            Manager.bookALesson(lessonID, learnerID);
                            return;
                        }
                        else{
                            throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                        }
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } catch (Utils.CustomValidationException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid value.");
                scanner.nextLine(); // Consume the newline character
            }
        }

    }
    public static void displayCancelASwimmingLessonMenu() {
        while(true) {
            System.out.println("\n-----Cancel a Swimming Lesson-----");
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to return to main menu, enter 0(zero).");
            System.out.print("Enter Learner ID:");
            try {
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                if (!Manager.learnersHashMap.containsKey(learnerID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.");
                System.out.print("Enter the Booking ID that you want to cancel: ");
                String bookingID = scanner.next();
                scanner.nextLine(); // Consume the newline character
                // for my application, the lessons for the month of May can only be booked and cancelled
                String cancelled = Manager.cancelBooking(bookingID, "05", "" + learnerID);
                if (cancelled.equals("cancelled")) {
                    System.out.println("Booking with ID: " + bookingID + " is cancelled successfully!");
                    return;
                }
                else if(cancelled.equals("error")){
                    System.out.println("The booking ID: " + bookingID + "  against the learner: " + learnerID +
                            " is not present.\nTry again and enter the correct learner ID and booking ID.");
                    throw new Utils.CustomValidationException("");
                }
                else if(cancelled.equals("already_cancelled")){
                    System.out.println("The booking ID: " + bookingID + "  against the learner: " + learnerID +
                            " is already cancelled.\nIf you want to cancel another booking, try again.");
                    throw new Utils.CustomValidationException("");
                }
            } catch (Utils.CustomValidationException e) {
                System.out.print(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid value.");
                scanner.nextLine(); // Consume the newline character
            }
        }
    }
    public static void displayAttendASwimmingLessonMenu() {
        System.out.println("\n-----Attend a Swimming Lesson-----");
        Scanner scanner = new Scanner(System.in);
        System.out.println("If you want to return to main menu, enter 0(zero).");
        System.out.print("Enter Learner ID:");
        int learnerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if(learnerID == 0)
            return;
        System.out.print("Enter the Booking ID for the lesson that you want to attend: ");
        String bookingID = scanner.next();
        scanner.nextLine(); // Consume the newline character
        Manager.attendBooking(bookingID, ""+learnerID);
    }

    public static void displayChangeASwimmingLessonMenu() {
        while(true) {
            System.out.println("\n-----Change/Update a Swimming Lesson-----");
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to return to main menu, enter 0(zero).");
            System.out.print("Enter Learner ID:");
            try {
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                if (!Manager.learnersHashMap.containsKey(learnerID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.\n");
                System.out.print("Enter the Booking ID for the booking that you want to change: ");
                String bookingID = scanner.next();
                scanner.nextLine(); // Consume the newline character
                Manager.printLessonsList("", "");
                System.out.print("Enter the Lesson ID from the above lessons that you want to book now: ");
                int lessonID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(!Manager.lessonsHashMap.containsKey(lessonID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.\n");
                String output = Manager.changeBooking(bookingID, "" + learnerID, lessonID);
                if (output.equals("updated")) {
                    System.out.println("Your Booking ID: " + bookingID + " is now updated for the lesson: "+lessonID);
                    return;
                }else if(output.equals("full_booked")){
                    System.out.println("There are already 4 bookings for this time slot! Try again to book a different lesson.");
                    throw new Utils.CustomValidationException("");
                }
                else if(output.equals("already_cancelled")){
                    System.out.println("The entered Booking ID: "+bookingID+" has a status of cancelled against the learner id: "+learnerID+
                            " and, therefore, this booking can't be changed.\nIf a learner wants to book this lesson, a new booking should be made.");
                    throw new Utils.CustomValidationException("");
                }
                else {
                    System.out.println("The booking ID: " + bookingID + "  against the learner: " + learnerID +
                            " is not present.\n Try again and enter the correct learner ID and booking ID.");
                    throw new Utils.CustomValidationException("");
                }
            }
            catch (Utils.CustomValidationException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid value.");
                scanner.nextLine(); // Consume the newline character
            }
        }
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
        //System.out.print("Enter Learner ID:");
        //int learnerID = scanner.nextInt();
        //scanner.nextLine(); // Consume the newline character
        Manager.printLearnerReport(month);
    }
    public static void displayMonthlyCoachReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the month number for the report(e.g., 05 for May): ");
        int month = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        //System.out.print("Enter coach name: ");
        //String coachName = scanner.next();
        //scanner.nextLine(); // Consume the newline character
        Manager.printCoachReport( month);
    }


}
