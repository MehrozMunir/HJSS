package com.pse.hjss;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        AddLearnersLessons.addLearners();
        AddLearnersLessons.addLessons();
        AddLearnersLessons.addCoaches();
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
                    System.out.println("List of all Lessons!");
                    Manager.printLessonsList("","");
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "8":
                    System.out.println("Names of all the Coaches....");
                    Utils.printCoachesNames();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "9":
                    System.out.println("-----Monthly Learner Report-----");
                    displayMonthlyLearnerReport();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "10":
                    System.out.println("-----Monthly Coach Report-----");
                    displayMonthlyCoachReport();
                    System.out.println("\nPress Enter to return to the main menu...");
                    scanner.nextLine(); // Wait for Enter key
                    break;
                case "0":
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
        System.out.println("7. Display all Lessons List");
        System.out.println("8. Display Coaches Names");
        System.out.println("9. Monthly Learner Report");
        System.out.println("10. Monthly Coach Report");
        System.out.println("0. Exit");
    }
    public static void displayLessonsViewMenu() {
        System.out.println("How do you want the lessons to be displayed?");
        System.out.println("1. By specifying the day");
        System.out.println("2. By specifying the grade level");
        System.out.println("3. By specifying the coach's name");
        System.out.println("4. All lessons");
    }

    public static void displayLearnerRegistrationMenu() {
        Scanner scanner = new Scanner(System.in);
       String firstName, lastName, gender, emergencyContactNumber;
       int age, currentGradeLevel;
        while (true) {
            try {
                System.out.println("\n-----Register a New Learner-----");
                System.out.print("Enter Learner's First Name: ");
                firstName = scanner.nextLine();
                if(firstName.equalsIgnoreCase(""))
                    throw new Utils.CustomValidationException("First name can't be empty.\nTry again!");
                System.out.print("Enter Learner's Last Name: ");
                lastName = scanner.nextLine();
                System.out.print("Enter Learner's Age: ");
                age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(age < 4 || age > 11)
                    throw new Utils.CustomValidationException("Age of the learner can only be between 4 and 11.\nTry again!");
                System.out.print("Enter Learner's Gender male/female/other: ");
                gender = scanner.nextLine();
                if(!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("other"))
                    throw new Utils.CustomValidationException("Gender can be only male, female or other.\nTry again!");
                System.out.print("Enter Learner's Current Grade Level: ");
                currentGradeLevel = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(currentGradeLevel<0 || currentGradeLevel>5)
                    throw new Utils.CustomValidationException("Current grade level can only be between 0 and 5.\nTry again!");
                System.out.print("Enter Learner's Emergency Contact Number: ");
                emergencyContactNumber = scanner.nextLine();
                Manager.registerLearner(firstName, lastName, age, gender, currentGradeLevel, emergencyContactNumber);
                return;
            }
            catch (Utils.CustomValidationException e){
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid value.");
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    public static void displayBookASwimmingLessonMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n-----Book a Swimming Lesson-----");
            try {
                System.out.println("If you want to return to main menu, enter 0(zero).");
                System.out.print("Enter Learner ID: ");
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
                                    boolean booked = Manager.bookALesson(lessonID, learnerID);
                                    if(booked)
                                        throw new Utils.CustomValidationException("If you want to book another lesson, carry on!");
                                    else
                                        throw new Utils.CustomValidationException("Try again!.");
                                }
                                else{
                                    throw new Utils.CustomValidationException("Enter a valid lesson ID.");
                                }
                            }
                            else {
                                 throw new Utils.CustomValidationException("Please enter a valid day value such as M, W, F or S.");
                            }
                    case "2":
                        System.out.print("Enter the grade level for which you want to see the lessons: ");
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
                                boolean booked = Manager.bookALesson(lessonID, learnerID);
                                if(booked)
                                    throw new Utils.CustomValidationException("If you want to book another lesson, carry on!");
                                else
                                    throw new Utils.CustomValidationException("Try again!.");
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
                        String coach_name1 = scanner.nextLine();
                        String coach_name = coach_name1.substring(0, 1).toUpperCase() + coach_name1.substring(1).toLowerCase();
                        if(Manager.coachesNamesArrayList.contains(coach_name)) {
                            Manager.printLessonsList("coach_name", coach_name);
                            // Call function to handle booking a lesson
                            System.out.print("Enter the Lesson ID from the above lessons that you want to book: ");
                            lessonID = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            if(Manager.lessonsHashMap.containsKey(lessonID)) {
                                boolean booked = Manager.bookALesson(lessonID, learnerID);
                                if(booked)
                                    throw new Utils.CustomValidationException("If you want to book another lesson, carry on!");
                                else
                                    throw new Utils.CustomValidationException("Try again!.");
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
                            boolean booked = Manager.bookALesson(lessonID, learnerID);
                            if(booked)
                                throw new Utils.CustomValidationException("If you want to book another lesson, carry on!");
                            else
                                throw new Utils.CustomValidationException("Try again!.");
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
            System.out.print("Enter Learner ID: ");
            try {
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                if (!Manager.learnersHashMap.containsKey(learnerID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.");
                System.out.print("Enter the Booking ID that you want to cancel, e.g. B300101: ");
                String bookingID = scanner.next();
                scanner.nextLine(); // Consume the newline character
                // for my application, the lessons for the month of May can only be booked and cancelled
                String cancelled = Manager.cancelBooking(bookingID, Manager.BOOKING_MONTH, "" + learnerID);
                if (cancelled.equals("cancelled")) {
                    System.out.println("The lesson with Booking ID: " + bookingID + " is cancelled successfully!");
                    return;
                }
                else if(cancelled.equals("error")){
                    System.out.println("The booking ID: " + bookingID + "  against the learner: " + learnerID +
                            " is not present.\nTry again and enter the correct Learner ID and Booking ID.");
                    throw new Utils.CustomValidationException("");
                }
                else if(cancelled.equals("already_cancelled")){
                    System.out.println("The booking ID: " + bookingID + "  against the learner: " + learnerID +
                            " is already cancelled.\nTry again if you want to cancel another booking.");
                    throw new Utils.CustomValidationException("");
                }
                else if(cancelled.equals("already_attended")){
                    System.out.println("The lesson for the booking ID: " + bookingID + "  is already attended by the learner: " + learnerID +
                            " and, therefore, can't be cancelled.\nTry again if you want to cancel another booking.");
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
        while(true) {
            System.out.println("\n-----Attend a Swimming Lesson-----");
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to return to main menu, enter 0(zero).");
            System.out.print("Enter Learner ID: ");
            try {
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                if (!Manager.learnersHashMap.containsKey(learnerID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.\n");
                System.out.print("Enter the Booking ID, e.g. B300101, for the lesson that you want to attend: ");
                String bookingID = scanner.next();
                scanner.nextLine(); // Consume the newline character
                Manager.attendBooking(bookingID, "" + learnerID);
            } catch (Utils.CustomValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid value.");
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    public static void displayChangeASwimmingLessonMenu() {
        while(true) {
            System.out.println("\n-----Change/Update a Swimming Lesson-----");
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to return to main menu, enter 0(zero).");
            System.out.print("Enter Learner ID: ");
            try {
                int learnerID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(learnerID == 0)
                    return;
                if (!Manager.learnersHashMap.containsKey(learnerID))
                    throw new Utils.CustomValidationException("The entered learner ID does not exist, try again.\n");
                System.out.print("Enter the Booking ID, e.g. B300101, for the booking that you want to change: ");
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
                }else if(output.equals("full_booked")){
                    System.out.println("There are already 4 bookings for this time slot! Try again to book a different lesson.");
                    throw new Utils.CustomValidationException("");
                }
                else if(output.equals("already_cancelled")){
                    System.out.println("The entered Booking ID: "+bookingID+" has a status of cancelled against the learner id: "+learnerID+
                            " and, therefore, this booking can't be changed.\nIf a learner wants to book this lesson, a new booking should be made.");
                    throw new Utils.CustomValidationException("");
                }
                else if(output.equals("already_booked")){
                    System.out.println("The entered Lesson ID: "+lessonID+" is already booked against the Learner with Learner ID: "+learnerID+
                            " and, therefore, can't be re-booked.\nTry again with a different lesson.");
                    throw new Utils.CustomValidationException("");
                }
                else if(output.equals("already_attended")){
                    System.out.println("The entered Lesson ID: "+lessonID+" is already attended by the Learner with Learner ID: "+learnerID+
                            " and, therefore, it can't be booked again.\nTry again with a different lesson.");
                    throw new Utils.CustomValidationException("");
                }
                else if(output.equals("grade_level_error")){
                    Learner learner = Manager.learnersHashMap.get(learnerID);
                    System.out.println("\nThe learner's current grade level is "+learner.getCurrentGradeLevel()+
                            "\nThe learner can book a lesson only of his/her current grade level"+
                            " or a grade level +1.");
                    throw new Utils.CustomValidationException("");
                }
                else {
                    System.out.println("The booking ID: " + bookingID + "  against the learner: " + learnerID +
                            " is not present.\nTry again and enter the correct learner ID and booking ID.");
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

    public static void displayMonthlyLearnerReport() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("If you want to return to main menu, enter 0(zero).\nOR");
                System.out.print("Enter the month number for the report(e.g., 04 for April): ");
                int month = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(month == 0){
                    return;
                }
                else if(month!=4){
                    throw  new Utils.CustomValidationException("For now, the report is only available for the month of April which 4th month.\nSo, enter 04 to see the report.");
                }
                else {
                    System.out.println("1. See All Learners Report");
                    System.out.println("2. See Individual Learner Report");
                    System.out.println("3. Go Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            Manager.printLearnerReport();
                            return;
                        case "2":
                            System.out.print("Enter Learner ID: ");
                            int learnerID = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            if (!Manager.learnersHashMap.containsKey(learnerID)) {
                                throw new Utils.CustomValidationException("The entered Learner ID does not exist, try again.\n");
                            }
                            else {
                                Utils.printIndividualLearnerReport(learnerID);
                                return;
                            }
                        case "3":
                            return;
                        default:
                            throw new Utils.CustomValidationException("Invalid choice, try again!\n");

                    }
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
    public static void displayMonthlyCoachReport() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("If you want to return to main menu, enter 0(zero).\nOR");
                System.out.print("Enter the month number for the coach report(e.g., 04 for April): ");
                int month = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (month == 0) {
                    return;
                } else if (month != 4) {
                    throw new Utils.CustomValidationException("For now, the report is only available for the month of April which 4th month.\nSo, enter 04 to see the report.");
                } else {
                    Manager.printCoachReport(month);
                }
            } catch (Utils.CustomValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid value.");
                scanner.nextLine(); // Consume the newline character
            }
        }
    }
}
